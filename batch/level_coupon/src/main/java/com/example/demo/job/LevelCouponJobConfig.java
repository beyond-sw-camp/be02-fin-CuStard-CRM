package com.example.demo.job;

import com.example.demo.coupon.service.SetCouponTargetService;
import com.example.demo.customer.model.entity.Customer;
import com.example.demo.customer.model.entity.CustomerDocument;
import com.example.demo.customer.repository.CustomerDocumentRepository;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.email.service.EmailService;
import com.example.demo.havecoupon.service.HaveCouponService;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class LevelCouponJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomerDocumentRepository customerRepository;
    private final SetCouponTargetService setCouponTargetService;
    private final HaveCouponService haveCouponService;
    private final EmailService emailService;

    @Bean("LevelCouponJob")
    public Job levelCouponJob(Step levelCouponStep) {
        return jobBuilderFactory.get("LevelCouponJob")
                .incrementer(new RunIdIncrementer())
                .start(levelCouponStep)
                .build();
    }

    @JobScope
    @Bean
    public Step levelCouponStep(ItemReader levelCouponReader,
                                ItemWriter levelCouponWriter) {
        return stepBuilderFactory.get("levelCouponStep")
                .<Customer, String>chunk(5)
                .reader(levelCouponReader)
                .writer(levelCouponWriter)
                .build();
    }

    @Bean
    @StepScope
    public CustomerRepositoryItemReader levelCouponReader() {
        return new CustomerRepositoryItemReader(customerRepository, setCouponTargetService);
    }


    @Bean
    @StepScope
    public ItemWriter<List<CustomerDocument>> levelCouponWriter() {
        return new CustomerItemWriter(setCouponTargetService, haveCouponService, emailService);
    }

}
