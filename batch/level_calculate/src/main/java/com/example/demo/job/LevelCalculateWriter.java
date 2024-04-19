package com.example.demo.job;


import com.example.demo.common.CustomerLevel;
import com.example.demo.customer.model.entity.Customer;
import com.example.demo.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class LevelCalculateWriter implements ItemWriter<Map<Long, CustomerLevel>> {
    private final CustomerRepository customerRepository;

    @Override
    public void write(List<? extends Map<Long, CustomerLevel>> list) throws Exception {
        for (Map<Long, CustomerLevel> map : list) {
            for (Map.Entry<Long, CustomerLevel> entry : map.entrySet()) {
                Long customerId = entry.getKey();
                CustomerLevel level = entry.getValue();

                Optional<Customer> result = customerRepository.findById(customerId);

                if (result.isPresent()) {
                    Customer customer = result.get();
                    customer.setLevel(level);
                } else {
                    System.out.println("존재하지 않는 고객");
                }
            }
        }
        System.out.println("등급 산정 완료");
    }

}
