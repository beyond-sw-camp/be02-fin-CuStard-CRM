package com.example.backend_admin.calculate.service;

import com.example.backend_admin.calculate.model.response.GetCategoryOrdersRes;
import com.example.backend_admin.calculate.model.response.GetTodayOrdersRes;
import com.example.backend_admin.orders.model.entity.Orders;
import com.example.backend_admin.orders.repository.OrdersRepository;
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
    LocalDateTime today = LocalDateTime.now();

    public GetTodayOrdersRes todayOrders() {
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

    public GetCategoryOrdersRes monthOrdersRes() {
        List<Orders> ordersList = ordersRepository.findByCreatedDateAfter(today.minusDays(365));
        int[] array = new int[12];

        for(Orders orders : ordersList){
            Integer month = orders.getCreatedDate().getMonthValue();
            array[month-1] = orders.getProductPrice();
        }

        return GetCategoryOrdersRes.builder().array(array).build();
    }
}