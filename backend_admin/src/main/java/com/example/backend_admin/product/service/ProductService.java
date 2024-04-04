package com.example.backend_admin.product.service;

import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.log.entity.ProductDetailLog;
import com.example.backend_admin.log.repository.ProductDetailLogRespository;
import com.example.backend_admin.orders.model.entity.Orders;
import com.example.backend_admin.orders.repository.OrdersRepository;
import com.example.backend_admin.product.model.entity.Product;
import com.example.backend_admin.product.model.response.GetProductRecRes;
import com.example.backend_admin.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;
    private final ProductDetailLogRespository productDetailLogRespository;
    private final CustomerRepository customerRepository;

    public GetProductRecRes recommend() {
        // 아이템 간의 유사도를 계산하기 위한 아이템 특성 정보
        Map<String, Map<String, Double>> productFeatures = new HashMap<>();
        // 예시 아이템의 특성 정보 추가
        List<Product> products = productRepository.findAll();


        for (Product product : products) {
            Map<String, Double> productFeaturesMap = new HashMap<>();
            productFeaturesMap.put(product.getCategory().toString(), 1.0);
            productFeaturesMap.put(product.getProductName(), 1.0);
            productFeaturesMap.put(product.getProductDetails(), 1.0);

            productFeatures.put(product.getProductName(), productFeaturesMap);
        }

        // 아이템 간의 유사도 계산
        Map<String, Map<String, Double>> similarityMatrix = calculateProductSimilarity(productFeatures);

        // 테스트 사용자와 그 사용자의 조회 및 구매 내역
        Map<Customer, List<Product>> viewHistory = new HashMap<>();
        List<ProductDetailLog> productDetailLogs = productDetailLogRespository.findAll();
        for (ProductDetailLog productDetailLog : productDetailLogs) {
            Customer customer = productDetailLog.getCustomer();
            Product product = productDetailLog.getProduct();
            if (!viewHistory.containsKey(customer)) {
                viewHistory.put(customer, new ArrayList<>());
            }
            viewHistory.get(customer).add(product);
        }
        Map<Customer, List<Product>> purchaseHistory = new HashMap<>();
        List<Orders> orders = ordersRepository.findAll();
        for (Orders order : orders) {
            Customer customer = order.getCustomer();
            Optional<Product> result = productRepository.findById(order.getProductIdx());
            Product product = result.get();

            if (!purchaseHistory.containsKey(customer)) {
                purchaseHistory.put(customer, new ArrayList<>());
            }
            purchaseHistory.get(customer).add(product);
        }

        // 사용자들에게 추천될 아이템과 점수 계산
        Map<Customer, Map<Product, Double>> recommendationScores = recommendProducts(similarityMatrix, viewHistory, purchaseHistory);

        Map<String, Map<Product, Double>> recommendList = new HashMap<>();
        for (Map.Entry<Customer, Map<Product, Double>> entry : recommendationScores.entrySet()) {
            Customer customer = entry.getKey();
            Map<Product, Double> productScores = entry.getValue();
            recommendList.put(customer.getCustomerEmail(), productScores);
        }
        return GetProductRecRes.builder().recommendList(recommendList).build();

    }
//    public Map<String, Map<String, Double>> calculateProductSimilarity(Map<String, Map<String, Double>> productFeatures) {
//        Map<String, Map<String, Double>> similarityMatrix = new HashMap<>();
//
//        for (Map.Entry<String, Map<String, Double>> entry1 : productFeatures.entrySet()) {
//            String product1 = entry1.getKey();
//            Map<String, Double> features1 = entry1.getValue();
//
//            // 아이템1과 다른 모든 아이템 간의 유사도 계산
//            Map<String, Double> similarities = new HashMap<>();
//            for (Map.Entry<String, Map<String, Double>> entry2 : productFeatures.entrySet()) {
//                String product2 = entry2.getKey();
//                if (!product1.equals(product2)) {
//                    Map<String, Double> features2 = entry2.getValue();
//                    double similarity = calculateJaccardSimilarity(features1.keySet(), features2.keySet());
//                    similarities.put(product2, similarity);
//                }
//            }
//            similarityMatrix.put(product1, similarities);
//        }
//
//        return similarityMatrix;
//    }
//
//    // 자카드 유사도 계산
//    private double calculateJaccardSimilarity(Set<String> set1, Set<String> set2) {
//        Set<String> intersection = new HashSet<>(set1);
//        intersection.retainAll(set2);
//
//        Set<String> union = new HashSet<>(set1);
//        union.addAll(set2);
//
//        if (union.size() == 0) {
//            return 0.0;
//        } else {
//            return (double) intersection.size() / union.size();
//        }
//    }

    private Map<String, Map<String, Double>> calculateProductSimilarity(Map<String, Map<String, Double>> productFeatures) {
        Map<String, Map<String, Double>> similarityMatrix = new HashMap<>();

        for (Map.Entry<String, Map<String, Double>> entry1 : productFeatures.entrySet()) {
            String product1 = entry1.getKey();
            Map<String, Double> features1 = entry1.getValue();

            // 아이템1과 다른 모든 아이템 간의 유사도 계산
            Map<String, Double> similarities = new HashMap<>();
            for (Map.Entry<String, Map<String, Double>> entry2 : productFeatures.entrySet()) {
                String product2 = entry2.getKey();
                if (!product1.equals(product2)) {
                    Map<String, Double> features2 = entry2.getValue();
                    double similarity = calculateCosineSimilarity(features1, features2);
                    similarities.put(product2, similarity);
                }
            }
            similarityMatrix.put(product1, similarities);
        }

        return similarityMatrix;
    }

    // 코사인 유사도 계산
    private double calculateCosineSimilarity(Map<String, Double> vector1, Map<String, Double> vector2) {
        double dotProduct = 0;
        double magnitude1 = 0;
        double magnitude2 = 0;

        for (Map.Entry<String, Double> entry : vector1.entrySet()) {
            String feature = entry.getKey();
            double value1 = entry.getValue();
            double value2 = vector2.getOrDefault(feature, 0.0);

            dotProduct += value1 * value2;
            magnitude1 += Math.pow(value1, 2);
            magnitude2 += Math.pow(value2, 2);
        }

        double magnitudeProduct = Math.sqrt(magnitude1) * Math.sqrt(magnitude2);
        if (magnitudeProduct == 0) {
            return 0; // 분모가 0이 되는 경우 예외 처리
        } else {
            return dotProduct / magnitudeProduct;
        }
    }

    private Map<Customer, Map<Product, Double>> recommendProducts(Map<String, Map<String, Double>> similarityMatrix, Map<Customer, List<Product>> viewHistory, Map<Customer, List<Product>> purchaseHistory) {
        Map<Customer, Map<Product, Double>> recommendationScores = new HashMap<>();

        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            Set<Product> viewedProducts = new HashSet<>(viewHistory.getOrDefault(customer, Collections.emptyList()));
            Set<Product> purchasedProducts = new HashSet<>(purchaseHistory.getOrDefault(customer, Collections.emptyList()));

            Map<Product, Double> recommendationScoresMap = new HashMap<>();
            for (Customer otherCustomer : customers) {
                if (!otherCustomer.equals(customer)) {
                    List<Product> otherPurchasedProducts = purchaseHistory.getOrDefault(otherCustomer, Collections.emptyList());
                    for (Product otherPurchasedProduct : otherPurchasedProducts) {
                        double similarity = calculateProductSimilarity(similarityMatrix, otherPurchasedProduct.getProductName(), viewedProducts);
                        if (similarity > 0 && !purchasedProducts.contains(otherPurchasedProduct)) {
                            recommendationScoresMap.put(otherPurchasedProduct, recommendationScoresMap.getOrDefault(otherPurchasedProduct, 0.0) + similarity);
                        }
                    }
                }
            }

            Map<Product, Double> sortedRecommendationScoresMap = recommendationScoresMap.entrySet().stream()
                    .sorted(Map.Entry.<Product, Double>comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            recommendationScores.put(customer, sortedRecommendationScoresMap);
        }

        return recommendationScores;
    }

    private double calculateProductSimilarity(Map<String, Map<String, Double>> similarityMatrix, String viewedProductName, Set<Product> purchasedProducts) {
        double similarityScore = 0.0;
        for (Product purchasedProduct : purchasedProducts) {
            similarityScore += similarityMatrix.getOrDefault(purchasedProduct.getProductName(), Collections.emptyMap()).getOrDefault(viewedProductName, 0.0);
        }
        return similarityScore;
    }
}
