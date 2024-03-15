package com.example.backend_admin.calculate.service;

import com.example.backend_admin.calculate.model.response.GetCategoryOrdersRes;
import com.example.backend_admin.calculate.model.response.GetTodayOrdersRes;
import com.example.backend_admin.orders.model.entity.Orders;
import com.example.backend_admin.orders.repository.OrdersRepository;
import com.example.backend_admin.product.model.entity.Product;
import com.example.backend_admin.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalculateOrdersService {
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    private LocalDateTime today = LocalDateTime.now();
    public GetTodayOrdersRes todayOrders() {
        Integer todayOrdersCount = ordersRepository.countByCreatedDateAfter(today.minusDays(1));
        Integer fromYesterdayOrdersCount = ordersRepository.countByCreatedDateAfter(today.minusDays(2));

        Integer todayOrdersAmount = ordersRepository.sumProductPriceByCreatedDateAfter(today.minusDays(1));
        Integer fromYesterdayOrdersAmount = ordersRepository.sumProductPriceByCreatedDateAfter(today.minusDays(2));

//        if(todayOrdersAmount == null) todayOrdersAmount = 0;

        return GetTodayOrdersRes.builder()
                .todayOrdersCount(todayOrdersCount)
                .difOrdersCount(2*todayOrdersCount - fromYesterdayOrdersCount)
                .todayOrdersAmount(todayOrdersAmount)
                .difOrdersAmount(2*todayOrdersAmount -fromYesterdayOrdersAmount)
                .build();
    }


    public GetCategoryOrdersRes categoryOrderRes() {
        List<Orders> ordersList = ordersRepository.findByCreatedDateAfter(today.minusDays(14));
        int[] array = new int[6];

        for(Orders orders : ordersList){
            Long productIdx = orders.getProductIdx();
            Integer category = productRepository.findById(productIdx).get().getCategory();
            array[category] = orders.getProductPrice();
        }

        return GetCategoryOrdersRes.builder().array(array).build();
    }
}
