package com.example.backend_admin.batch.service;

import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.entity.ProductDetailLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
import com.example.backend_admin.log.repository.ProductDetailLogRespository;
import com.example.backend_admin.product.model.entity.Product;
import com.example.backend_admin.product.repository.ProductRepository;
import com.example.backend_admin.product.service.ProductService;
import com.mysql.cj.log.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BatchService {
    private final CustomerRepository customerRepository;
    private final LoginLogRespository loginLogRespository;
    public void batch(){

        System.out.println("출력");
    }

    public List<Customer> customerDataReader(){
        return customerRepository.findAll();
    }

    public List<LoginLog> loginLogDataReader(Long idx){
        return loginLogRespository.findByCustomerIdx(idx);
    }
    public void sleeperCouponProccessor(List<Customer> customerList,Integer period){

        List<LoginLog> loginLogList;
        LocalDateTime now = LocalDateTime.now();
        List<Long> couponRecipientList = new ArrayList<>();

        for (Customer customer:customerList) {
            loginLogList = loginLogDataReader(customer.getIdx());

            if (loginLogList.isEmpty()){
                continue;
            }
            LocalDateTime lastLogin = loginLogList.get(loginLogList.size()-1).getCreatedDate();
            Duration duration = Duration.between(lastLogin, now);

            if (duration.getSeconds()/(3600*24)> period){
                couponRecipientList.add(customer.getIdx());
            }
        }
    }

    public void sleeperCouponWriter(){

    }

}
