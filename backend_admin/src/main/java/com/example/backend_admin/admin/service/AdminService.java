package com.example.backend_admin.admin.service;

import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.admin.model.request.PostAdminLoginReq;
import com.example.backend_admin.admin.model.request.PostAdminSignupReq;
import com.example.backend_admin.admin.model.response.PostAdminLoginRes;
import com.example.backend_admin.admin.model.response.PostAdminSignupRes;
import com.example.backend_admin.admin.repository.AdminRepository;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${jwt.secret-key}")
    private String secretKey;
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
