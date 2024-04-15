package com.example.demo.email.service;


import com.example.demo.customer.model.entity.Customer;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.email.model.entity.Email;
import com.example.demo.email.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;

    public void sendLevelCoupon(Long targetidx) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Optional<Customer> result = customerRepository.findById(targetidx);
        if (result.isPresent()) {
            Customer customer = result.get();
            helper.setSubject("[광고] CuStard :) 고객님만을 위한 쿠폰이 발급되었어요!");
            helper.setTo(customer.getName());
            Context context = new Context();
            context.setVariable("level", customer.getLevel());

            String html = templateEngine.process("mail/level", context);
            helper.setText(html, true);

            javaMailSender.send(message);

            emailRepository.save(Email.builder().emailContent(html).customer(customer).build());
        } else {
            //사용자가 존재하지 않을 경우 오류 처리
        }
    }
}



