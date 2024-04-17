package com.example.demo.job;

import com.example.demo.common.CustomerLevel;
import com.example.demo.customer.model.entity.CustomerDocument;
import com.example.demo.customer.repository.CustomerDocumentRepository;
import com.example.demo.login.model.entity.LoginDocument;
import com.example.demo.login.repository.LoginDocumentRepository;
import com.example.demo.orders.model.entity.OrdersDocument;
import com.example.demo.orders.repository.OrdersDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.AbstractPagingItemReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class LevelCalculateReader extends AbstractPagingItemReader<Map<Long, CustomerLevel>> {
    private final CustomerDocumentRepository customerDocumentRepository;
    private final OrdersDocumentRepository ordersDocumentRepository;
    private final LoginDocumentRepository loginDocumentRepository;


    public CustomerLevel calculateLevel(Integer score) {
        CustomerLevel level;
        if (score >= 100000000) {
            level = CustomerLevel.DIAMOND;
        } else if (score >= 8000000) {
            level = CustomerLevel.PLATINUM;
        } else if (score >= 600000) {
            level = CustomerLevel.GOLD;
        } else if (score >= 70000) {
            level = CustomerLevel.SILVER;
        } else if (score >= 30000) {
            level = CustomerLevel.BRONZE;
        } else {
            level = CustomerLevel.NEWBIE;
        }
        return level;
    }

    @Override
    protected void doReadPage() {
        if (results == null) {
            results = new ArrayList<>();
        } else {
            results.clear();
        }
        Map<Long, CustomerLevel> levels = new HashMap<>();
        Iterable<CustomerDocument> customers = customerDocumentRepository.findAll();
        for (CustomerDocument customer : customers) {
            Integer totalAmount = 0, count = 0;

            List<OrdersDocument> orders = ordersDocumentRepository.findAllByCustomerIdx(customer.getIdx());
            for (OrdersDocument order : orders) {
                totalAmount += order.getPrice();
            }
            List<LoginDocument> logins = loginDocumentRepository.findAllByCustomerIdx(customer.getIdx());
            count = logins.size();

            CustomerLevel level = calculateLevel(totalAmount * 8 + count * 2);
            levels.put(customer.getIdx(), level);
        }
        results.add(levels);
    }

    @Override
    protected void doJumpToPage(int i) {

    }
}
