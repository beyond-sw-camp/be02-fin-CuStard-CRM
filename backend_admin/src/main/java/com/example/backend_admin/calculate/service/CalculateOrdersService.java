package com.example.backend_admin.calculate.service;

import com.example.backend_admin.calculate.model.response.GetTodayOrdersRes;
import com.example.backend_admin.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CalculateOrdersService {
    private final OrdersRepository ordersRepository;

    public GetTodayOrdersRes todayOrders() {
        LocalDateTime today = LocalDateTime.now();

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
}
