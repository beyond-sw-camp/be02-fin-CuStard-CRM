package com.example.backend_admin.admin.service;

import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.admin.model.request.PostAdminLevelCouponReq;
import com.example.backend_admin.admin.model.request.PostAdminLoginReq;
import com.example.backend_admin.admin.model.request.PostAdminSignupReq;
import com.example.backend_admin.admin.model.request.PostAdminSleeperCouponReq;
import com.example.backend_admin.admin.model.response.PostAdminLoginRes;
import com.example.backend_admin.admin.model.response.PostAdminSignupRes;
import com.example.backend_admin.admin.model.response.PostAdminSleeperCouponRes;
import com.example.backend_admin.admin.repository.AdminRepository;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.coupon.model.request.PostCouponCreateReq;
import com.example.backend_admin.coupon.model.response.PostCouponCreateRes;
import com.example.backend_admin.coupon.service.CouponService;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.havecoupon.model.entity.request.PostHaveCouponCreateReq;
import com.example.backend_admin.havecoupon.service.HaveCouponService;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
import com.example.backend_admin.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final HaveCouponService haveCouponService;
    private final CouponService couponService;
    private final LoginLogRespository loginLogRespository;

    @Value("${jwt.token.expired-time-ms}")
    private Integer expiredTimeMs;

    public BaseResponse signup(PostAdminSignupReq postAdminSignupReq) {

        Optional<Admin> duplicatedAdmin = adminRepository.findByAdminEmail(postAdminSignupReq.getAdminEmail());
        // 멤버 정보를 빌드로 저장
        if (!duplicatedAdmin.isPresent()) {

            Admin admin = adminRepository.save(Admin.builder()
                    .adminEmail(postAdminSignupReq.getAdminEmail())
                    .adminPwd(passwordEncoder.encode(postAdminSignupReq.getAdminPwd()))
                    .authority("Administrator")
                    .build());


            BaseResponse baseResponse = BaseResponse.successResponse("회원가입 성공", PostAdminSignupRes.builder()
                    .idx(admin.getIdx())
                    .adminEmail(admin.getAdminEmail())
                    .build());

            return baseResponse;

        } else {


            return BaseResponse.failResponse(2000, "계정 중복");

        }
    }
        public PostAdminLoginRes adminLogin(PostAdminLoginReq postAdminLoginReq) {
            Optional<Admin> result = adminRepository.findByAdminEmail(postAdminLoginReq.getAdminEmail());

            if (result.isPresent()) {
                if (passwordEncoder.matches(postAdminLoginReq.getAdminPwd(), result.get().getAdminPwd())) {

                    PostAdminLoginRes postAdminLoginRes = PostAdminLoginRes.builder()
                            .accessToken(TokenProvider.generateAccessToken(result.get(), expiredTimeMs))
                            .adminEmail(result.get().getAdminEmail())
                            .idx(result.get().getIdx())
                            .build();


                    return postAdminLoginRes;
                } else {
                    return null;
                }
            }
            return null;
        }

        public String levelCoupon(String token,PostAdminLevelCouponReq postAdminLevelCouponReq){

            token = TokenProvider.replaceToken(token);
            Long adminIdx = TokenProvider.getIdx(token);

            CustomerLevel[] level = CustomerLevel.values();
            List<Long> couponIdx = new ArrayList<>();

            for(int cnt = 0; cnt<level.length; cnt ++){
                PostCouponCreateRes response = couponService.create(PostCouponCreateReq.builder()
                        .adminIdx(adminIdx)
                        .couponCategory(postAdminLevelCouponReq.getCategory().get(cnt))
                        .discount(postAdminLevelCouponReq.getDiscount().get(cnt))
                        .build());

                couponIdx.add(response.getCouponIdx());
            }

            List<Customer> customerList = customerRepository.findAll();

            for (Customer customer: customerList) {
                if (customer.getLevel()==CustomerLevel.NEWBIE){
                    haveCouponService.create(PostHaveCouponCreateReq.builder()
                            .customerIdx(customer.getIdx())
                            .couponIdx(couponIdx.get(0))
                            .count(postAdminLevelCouponReq.getCount().get(0))
                            .build());



                } else if (customer.getLevel()==CustomerLevel.BRONZE) {
                    haveCouponService.create(PostHaveCouponCreateReq.builder()
                            .customerIdx(customer.getIdx())
                            .couponIdx(couponIdx.get(1))
                            .count(postAdminLevelCouponReq.getCount().get(1))
                            .build());
                } else if (customer.getLevel()==CustomerLevel.SILVER) {
                    haveCouponService.create(PostHaveCouponCreateReq.builder()
                            .customerIdx(customer.getIdx())
                            .couponIdx(couponIdx.get(2))
                            .count(postAdminLevelCouponReq.getCount().get(2))
                            .build());
                } else if (customer.getLevel()==CustomerLevel.GOLD) {
                    haveCouponService.create(PostHaveCouponCreateReq.builder()
                            .customerIdx(customer.getIdx())
                            .couponIdx(couponIdx.get(3))
                            .count(postAdminLevelCouponReq.getCount().get(3))
                            .build());
                } else if (customer.getLevel()==CustomerLevel.PLATINUM) {
                    haveCouponService.create(PostHaveCouponCreateReq.builder()
                            .customerIdx(customer.getIdx())
                            .couponIdx(couponIdx.get(4))
                            .count(postAdminLevelCouponReq.getCount().get(4))
                            .build());
                } else if (customer.getLevel()==CustomerLevel.DIAMOND) {
                    haveCouponService.create(PostHaveCouponCreateReq.builder()
                            .customerIdx(customer.getIdx())
                            .couponIdx(couponIdx.get(5))
                            .count(postAdminLevelCouponReq.getCount().get(5))
                            .build());
                } else {

                }
            }
            return "쿠폰 발급 완료";
        }
        //TODO: 장기 미접속자는 매일매일 쿠폰을 받기 때문에 수정 필요 및 testDateTime 제거 필요
        public PostAdminSleeperCouponRes sleeperCoupon(String token, PostAdminSleeperCouponReq postAdminSleeperCouponReq){
            token = TokenProvider.replaceToken(token);
            Long adminIdx = TokenProvider.getIdx(token);
            Long cnt=0L;

            // 데이터 읽기
            List<Customer> customerList = customerRepository.findAll();
            //
            List<LoginLog> loginLogList;
            LocalDateTime now = LocalDateTime.now();

            // 데이터 처리
            for (Customer customer:customerList) {
                loginLogList = loginLogRespository.findByCustomerIdx(customer.getIdx());

                if (loginLogList.isEmpty()){
                    continue;
                }
                LocalDateTime lastLogin = loginLogList.get(loginLogList.size()-1).getCreatedDate();
                Duration duration = Duration.between(lastLogin, now);
            }
            //
            for (Customer customer:customerList) {
                loginLogList = loginLogRespository.findByCustomerIdx(customer.getIdx());
                if (loginLogList.isEmpty()){
                    continue;
                }
                LocalDateTime lastLogin = loginLogList.get(loginLogList.size()-1).getCreatedDate();
//                LocalDateTime testDateTime = LocalDateTime.of(2023, 3, 13, 15, 30, 0);    // lastlogin 대신 넣을 테스트 날짜 데이터
                Duration duration = Duration.between(lastLogin, now);

                if (duration.getSeconds()/(3600*24)> postAdminSleeperCouponReq.getPeriod()){
                    PostCouponCreateRes response = couponService.create(PostCouponCreateReq.builder()
                            .adminIdx(adminIdx)
                            .discount(postAdminSleeperCouponReq.getDiscount())
                            .couponCategory(postAdminSleeperCouponReq.getCouponCategory())
                            .build());
                    haveCouponService.create(PostHaveCouponCreateReq.builder()
                            .couponIdx(response.getCouponIdx())
                            .customerIdx(customer.getIdx())
                            .count(postAdminSleeperCouponReq.getCount())
                            .build());

                    cnt++;
                }
            }
            return PostAdminSleeperCouponRes.builder().cnt(cnt).build();
        }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<Admin> result = adminRepository.findByAdminEmail(username);
            Admin admin = null;
            if(result.isPresent()) {
                admin = result.get();
            }
            return admin;
    }
}
