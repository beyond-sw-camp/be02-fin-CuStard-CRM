package com.example.backend.customer.service;

import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.model.entity.CustomerEmailVerify;
import com.example.backend.customer.repository.CustomerEmailVerifyRepository;
import com.example.backend.customer.repository.CustomerRepository;
import com.example.backend.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerEmailVerifyService {

    private final CustomerEmailVerifyRepository customerEmailVerifyRepository;
    private final JavaMailSender emailSender;
    private final CustomerRepository customerRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Integer expiredTimeMs;
    public Boolean confirm(String customerEmail, String uuid){
        Optional<CustomerEmailVerify> result = customerEmailVerifyRepository.findByCustomerEmail(customerEmail);

        if(result.isPresent()) {
            CustomerEmailVerify customerEmailVerify = result.get();

            if(customerEmailVerify.getUuid().equals(uuid)) {
                Optional<Customer> member = customerRepository.findByCustomerEmail(customerEmail);
                if (member.isPresent()) {
                    member.get().setStatus(true);
                    customerRepository.save(member.get());
                    return true;
                }
            }
        }
        return false;
    }

    public void create(String customerEmail, String uuid) {
        CustomerEmailVerify customerEmailVerify = CustomerEmailVerify.builder()
                .customerEmail(customerEmail)
                .uuid(uuid)
                .build();

        customerEmailVerifyRepository.save(customerEmailVerify);

    }

    public void sendCustomerMail(Customer customer){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getCustomerEmail());
        message.setSubject("[CuStard] 회원 이메일 인증입니다.");
        // uuid 생성
        String uuid = UUID.randomUUID().toString();
        create(customer.getCustomerEmail(), uuid);
        // jwt 생성
        String jwt = TokenProvider.generateAccessToken(customer, expiredTimeMs);
        jwt = TokenProvider.replaceToken(jwt);
        message.setText("http://localhost:8080/customerconfirm?customerEmail=" + customer.getCustomerEmail() + "&uuid=" + uuid + "&jwt=" + jwt);
        emailSender.send(message);
    }

    public void update(String customerEmail) {
        Optional<Customer> result = customerRepository.findByCustomerEmail(customerEmail);
        if (result.isPresent()) {
            Customer customer = result.get();
            customer.setStatus(true);
            customerRepository.save(customer);
        }
    }
}

