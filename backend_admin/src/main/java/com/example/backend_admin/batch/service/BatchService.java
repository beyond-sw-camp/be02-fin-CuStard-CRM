package com.example.backend_admin.batch.service;

import com.example.backend_admin.admin.model.request.PostAdminLevelCouponReq;
import com.example.backend_admin.admin.model.request.PostAdminSleeperCouponReq;
import com.example.backend_admin.admin.service.AdminService;
import com.example.backend_admin.batch.model.request.LevelCouponProcessorReq;
import com.example.backend_admin.batch.model.request.LevelCouponWriterReq;
import com.example.backend_admin.batch.model.request.SleeperCouponProcessorReq;
import com.example.backend_admin.batch.model.request.SleeperCouponWriterReq;
import com.example.backend_admin.batch.model.response.SleeperCouponReaderRes;
import com.example.backend_admin.batch.model.response.LevelCouponProcessorRes;
import com.example.backend_admin.batch.model.response.LevelCouponReaderRes;
import com.example.backend_admin.batch.model.response.SleeperCouponProcessorRes;
import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
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
    private final AdminService adminService;
    public void batch(){

        System.out.println("출력");
    }

    public SleeperCouponReaderRes sleeperCouponReader(){
        List<Customer> customerList = customerRepository.findAll();
        List<List<LoginLog>> result = new ArrayList<>();
        List<LoginLog> loginLogList;
        for (Customer customer : customerList) {
            loginLogList = loginLogRespository.findByCustomerIdx(customer.getIdx());
            if (!loginLogList.isEmpty()){
                result.add(loginLogList);
            }
        }

//        for (List<LoginLog> loginLogs: result) {
//            if (loginLogs.isEmpty()){
//                continue;
//            }else {
//                for (LoginLog loginLog:loginLogs) {
//                    System.out.print(loginLog + " ");
//                }
//            }
//            System.out.println();
//        }
        return SleeperCouponReaderRes.builder()
                .loginLogLists(result)
                .build();
    }

//    public List<List<LoginLog>> loginLogDataReader(List<Long> idxs){
//        List<List<LoginLog>> result = new ArrayList<>();
//        for (Long idx: idxs) {
//            List<LoginLog> log = loginLogRespository.findByCustomerIdx(idx);
//            result.add(log);
//        }
//        return result;
//    }
    public SleeperCouponProcessorRes sleeperCouponProcessor(SleeperCouponProcessorReq sleeperCouponProcessorReq){

        Integer period = 10; //TODO: batch 변환시 기간을 매개변수로 받기
        LocalDateTime now = LocalDateTime.now();
        List<Long> couponRecipientList = new ArrayList<>();
//        List<LoginLog> loginLogList;


        for (List<LoginLog> loginLogList: sleeperCouponProcessorReq.getLoginLogLists()) {
            LocalDateTime lastLogin = loginLogList.get(loginLogList.size()-1).getCreatedDate();
            Duration duration = Duration.between(lastLogin, now);
//            /(3600*24)
            if (duration.getSeconds()> period){
                couponRecipientList.add(loginLogList.get(loginLogList.size()-1).getCustomer().getIdx());
            }
        }

        return SleeperCouponProcessorRes.builder()
                .couponRecipientList(couponRecipientList)
                .build();
//        for (Customer customer:customerList) {
//            loginLogList = loginLogDataReader(customer.getIdx());
//
//            if (loginLogList.isEmpty()){
//                continue;
//            }
//            LocalDateTime lastLogin = loginLogList.get(loginLogList.size()-1).getCreatedDate();
//            Duration duration = Duration.between(lastLogin, now);
//
//            if (duration.getSeconds()/(3600*24)> period){
//                couponRecipientList.add(customer.getIdx());
//            }
//        }

    }

    public void sleeperCouponWriter(SleeperCouponWriterReq sleeperCouponWriterReq){
        adminService.sleeperCoupon(PostAdminSleeperCouponReq.builder()
                .couponRecipientList(sleeperCouponWriterReq.getCouponRecipientList())
                .build());
    }
    public LevelCouponReaderRes levelCouponReader(){
        List<Customer> customerList = customerRepository.findAll();
        return LevelCouponReaderRes.builder()
                .customerList(customerList)
                .build();
    }
    public LevelCouponProcessorRes levelCouponProcessor(LevelCouponProcessorReq levelCouponProcessorReq){
        List<Long> newbieList = new ArrayList<>();
        List<Long> bronzeList = new ArrayList<>();
        List<Long> silverList = new ArrayList<>();
        List<Long> goldList = new ArrayList<>();
        List<Long> platinumList = new ArrayList<>();
        List<Long> diamondList = new ArrayList<>();
        List<List<Long>> targetList = new ArrayList<>();

        for (Customer customer:levelCouponProcessorReq.getCustomerList()) {
            if (customer.getLevel() == CustomerLevel.NEWBIE) {
                newbieList.add(customer.getIdx());
            } else if (customer.getLevel() == CustomerLevel.BRONZE) {
                bronzeList.add(customer.getIdx());
            } else if (customer.getLevel() == CustomerLevel.SILVER) {
                silverList.add(customer.getIdx());
            } else if (customer.getLevel() == CustomerLevel.GOLD) {
                goldList.add(customer.getIdx());
            } else if (customer.getLevel() == CustomerLevel.PLATINUM) {
                platinumList.add(customer.getIdx());
            } else if (customer.getLevel() == CustomerLevel.DIAMOND) {
                diamondList.add(customer.getIdx());
            }else {

            }
        }
        targetList.add(newbieList);
        targetList.add(bronzeList);
        targetList.add(silverList);
        targetList.add(goldList);
        targetList.add(platinumList);
        targetList.add(diamondList);

        return LevelCouponProcessorRes.builder()
                .targetList(targetList)
                .build();
    }
    public void levelCouponWriter(LevelCouponWriterReq levelCouponWriterReq){
        adminService.levelCoupon(PostAdminLevelCouponReq.builder()
                .targetList(levelCouponWriterReq.getTargetList())
                .build());
    }

}
