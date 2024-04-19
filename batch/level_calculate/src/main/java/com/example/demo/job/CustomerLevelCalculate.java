package com.example.demo.job;

import com.example.demo.common.CustomerLevel;
import com.example.demo.customer.model.entity.Customer;
import com.example.demo.customer.model.entity.CustomerDocument;
import com.example.demo.customer.repository.CustomerDocumentRepository;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.login.repository.LoginDocumentRepository;
import com.example.demo.orders.repository.OrdersDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CustomerLevelCalculate {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomerDocumentRepository customerDocumentRepository;
    private final OrdersDocumentRepository ordersDocumentRepository;
    private final LoginDocumentRepository loginDocumentRepository;
    private final CustomerRepository customerRepository;

    @Bean("LevelCalculateJob")
    public Job levelCouponJob(Step levelCouponStep) {
        return jobBuilderFactory.get("LevelCalculateJob")
                .incrementer(new RunIdIncrementer())
                .start(levelCouponStep)
                .build();
    }

    @JobScope
    @Bean
    public Step levelCalculateStep(ItemReader levelCalculateReader,
                                   ItemWriter levelCalculateWriter) {
        return stepBuilderFactory.get("levelCalculateStep")
                .<CustomerDocument, Map<Long, CustomerLevel>>chunk(5)
                .reader(levelCalculateReader)
                .writer(levelCalculateWriter)
                .build();
    }

    @Bean
    @StepScope
    public LevelCalculateReader levelCalculateReader() {
        System.out.println("READER 실행");
        return new LevelCalculateReader(customerDocumentRepository, ordersDocumentRepository, loginDocumentRepository);
    }


    @Bean
    @StepScope
    public ItemWriter<Map<Long, CustomerLevel>> levelCalculateWriter() {
        System.out.println("WRITER 실행");
        return new LevelCalculateWriter(customerRepository);
    }

}
