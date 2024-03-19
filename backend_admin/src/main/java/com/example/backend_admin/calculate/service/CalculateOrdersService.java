package com.example.backend_admin.calculate.service;

import com.example.backend_admin.calculate.model.response.GetCategoryOrdersRes;
import com.example.backend_admin.calculate.model.response.GetTodayOrdersRes;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.log.entity.ProductDetailLog;
import com.example.backend_admin.log.repository.ProductDetailLogRespository;
import com.example.backend_admin.orders.model.entity.Orders;
import com.example.backend_admin.orders.repository.OrdersRepository;
import com.example.backend_admin.product.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.backend_admin.product.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateOrdersService {
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;
    private final ProductDetailLogRespository productDetailLogRespository;
    LocalDateTime today = LocalDateTime.now();

    public GetTodayOrdersRes todayOrders() throws BaseException {
        Integer todayOrdersCount = ordersRepository.countByCreatedDateAfter(today.minusDays(1));
        Integer fromYesterdayOrdersCount = ordersRepository.countByCreatedDateAfter(today.minusDays(2));

        Integer todayOrdersAmount = ordersRepository.sumProductPriceByCreatedDateAfter(today.minusDays(1));
        Integer fromYesterdayOrdersAmount = ordersRepository.sumProductPriceByCreatedDateAfter(today.minusDays(2));

        return GetTodayOrdersRes.builder()
                .todayOrdersCount(todayOrdersCount)
                .difOrdersCount(2*todayOrdersCount - fromYesterdayOrdersCount)
                .todayOrdersAmount(todayOrdersAmount)
                .difOrdersAmount(2*todayOrdersAmount -fromYesterdayOrdersAmount)
                .build();
    }

    public GetCategoryOrdersRes categoryOrderRes() throws BaseException{

        List<Orders> ordersList = ordersRepository.findByCreatedDateAfter(today.minusDays(14));
        int[] array = new int[6];
        int ordersAmount = 0;
        for(Orders orders : ordersList){
            Long productIdx = orders.getProductIdx();
            Product product = productRepository.findById(orders.getProductIdx()).get();

            Integer category = product.getCategory();
            Integer price = product.getProductPrice();

            array[category] = orders.getProductPrice();
            ordersAmount += price;
        }

        return GetCategoryOrdersRes.builder()
                .orders(array)
                .ordersCount(ordersList.size())
                .ordersAmount(ordersAmount)
                .build();
    }

    public GetCategoryOrdersRes monthOrdersRes() throws BaseException{
        List<Orders> ordersList = ordersRepository.findByCreatedDateAfter(today.minusDays(365));
        int[] array = new int[12];

        for(Orders orders : ordersList){
            Integer month = orders.getCreatedDate().getMonthValue();
            array[month-1] = orders.getProductPrice();
        }

        return GetCategoryOrdersRes.builder().orders(array).build();
    }

    public GetCategoryOrdersRes customerOrdersRes(Long idx)throws BaseException{
        List<Orders> ordersList = ordersRepository.findByCustomerIdx(idx);

        int[] ordersCategory = new int[6];
        for(Orders orders : ordersList){
            Long productIdx = orders.getProductIdx();
            Integer category = productRepository.findById(productIdx).get().getCategory();
            ordersCategory[category] = orders.getProductPrice();
        }

        int[] productCategory = new int[6];
       List<ProductDetailLog> productDetailLogs = productDetailLogRespository.findByCustomerIdx(idx);
        for(ProductDetailLog productDetailLog :productDetailLogs){
            productCategory[productDetailLog.getCategory()]++;
        }

        return GetCategoryOrdersRes.builder().orders(ordersCategory).productRead(productCategory).build();
    }

}