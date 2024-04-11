package com.example.backend.product.service;


import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponseStatus;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.repository.CustomerRepository;
import com.example.backend.log.entity.ProductDetailLog;
import com.example.backend.log.repository.ProductDetailLogRespository;
import com.example.backend.log.service.ProductDetailLogService;
import com.example.backend.log.service.SearchLogService;
import com.example.backend.orders.model.entity.Orders;
import com.example.backend.orders.repository.OrdersRepository;
import com.example.backend.product.model.entity.Product;
import com.example.backend.product.model.response.GetProductListRes;
import com.example.backend.product.model.response.GetProductRecRes;
import com.example.backend.product.model.response.GetProductRes;
import com.example.backend.product.repository.ProductRepository;
import com.example.backend.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailLogService productDetailLogService;
    private final CustomerRepository customerRepository;
    private final SearchLogService searchLogService;
    private final ProductDetailLogRespository productDetailLogRespository;
    private final OrdersRepository ordersRepository;

    private static final Logger productDetailLogger = LoggerFactory.getLogger("productDetailLogger");
    private static final Logger productSearchLogger = LoggerFactory.getLogger("productSearchLogger");

    public List<GetProductListRes> list() throws BaseException {
        List<GetProductListRes> productListRes = new ArrayList<>();
        PageRequest pageable = PageRequest.of(0, 15);

        for (int i = 0; i < 6; i++) {
            Page<Product> productList = productRepository.findByCategory(i, pageable);
            for (Product product : productList) {
                productListRes.add(GetProductListRes.builder()
                        .idx(product.getIdx())
                        .category(product.getCategory())
                        .productName(product.getProductName())
                        .productPrice(product.getProductPrice())
                        .productImage(product.getProductImage())
                        .build());
            }
        }
        return productListRes;
    }

    public List<GetProductListRes> getProductsByCategory(Integer category, Integer page) {
        PageRequest pageable = PageRequest.of(page, 20);
        Page<Product> productList = productRepository.findByCategory(category, pageable);

        List<GetProductListRes> productListRes = new ArrayList<>();
        for (Product product : productList) {
            productListRes.add(GetProductListRes.builder()
                    .idx(product.getIdx())
                    .category(product.getCategory())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productImage(product.getProductImage())
                    .build());
        }

        return productListRes;
    }

    public GetProductRes read(Long idx, Authentication authentication)throws BaseException {
        Optional<Product> result = productRepository.findById(idx);

        if (result.isPresent()) {
            Product product = result.get();
            GetProductRes getProductRes = GetProductRes.builder()
                    .idx(product.getIdx())
                    .category(product.getCategory())
                    .productName(product.getProductName())
                    .productDetails(product.getProductDetails())
                    .productPrice(product.getProductPrice())
                    .productImage(product.getProductImage())
                    .build();

            if (authentication != null && authentication.isAuthenticated()) {
                Customer customer = (Customer) authentication.getPrincipal();
                Optional<Customer> c = customerRepository.findById(customer.getIdx());
                if (c.isPresent()) {
                    productDetailLogService.productDetailLogging(c.get(), product);

                    //상품 상세 조회 로그 남기기
                    productDetailLogger.info("[상품 조회] 고객 번호: {}, 상품: {}, 카테고리: {}", c.get().getIdx(), product.getIdx(), product.getCategory());

                }
            }
            return getProductRes;
        }
        return null;
    }

    public List<GetProductListRes> searchByName(String keyword,  Authentication authentication) throws BaseException{
        List<Product> productList = productRepository.findByProductNameContaining(keyword);

        List<GetProductListRes> productListRes = new ArrayList<>();

        for (Product product : productList) {
            productListRes.add(GetProductListRes.builder()
                    .idx(product.getIdx())
                    .category(product.getCategory())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productImage(product.getProductImage())
                    .build());
        }

        if (authentication != null && authentication.isAuthenticated()) {

            Customer customer = (Customer) authentication.getPrincipal();
            Optional<Customer> c = customerRepository.findById(customer.getIdx());

            if (c.isPresent()) {
                searchLogService.SearchLogging(c.get(), keyword);

                //상품 검색 로그 남기기
                productSearchLogger.info("[상품 검색] 고객 번호: {}, 검색 키워드: {}", customer.getIdx(), keyword);

            }
        }
        return productListRes;
    }

    public GetProductRecRes recommend() throws BaseException{
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

    public Map<String, Map<String, Double>> calculateProductSimilarity(Map<String, Map<String, Double>> productFeatures) {
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

    public Map<Customer, Map<Product, Double>> recommendProducts(Map<String, Map<String, Double>> similarityMatrix, Map<Customer, List<Product>> viewHistory, Map<Customer, List<Product>> purchaseHistory) {
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
