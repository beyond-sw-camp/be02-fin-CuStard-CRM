package com.example.backend_admin.admin.service;

import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.admin.model.request.PostAdminLoginReq;
import com.example.backend_admin.admin.model.request.PostAdminSignupReq;
import com.example.backend_admin.admin.model.response.PostAdminLoginRes;
import com.example.backend_admin.admin.model.response.PostAdminSignupRes;
import com.example.backend_admin.admin.repository.AdminRepository;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.backend_admin.common.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${jwt.token.expired-time-ms}")
    private Integer expiredTimeMs;

    public PostAdminSignupRes signup(PostAdminSignupReq postAdminSignupReq)throws BaseException {

        Optional<Admin> duplicatedAdmin = adminRepository.findByAdminEmail(postAdminSignupReq.getAdminEmail());
        // 멤버 정보를 빌드로 저장
        if (!duplicatedAdmin.isPresent()) {

            Admin admin = adminRepository.save(Admin.builder()
                    .adminEmail(postAdminSignupReq.getAdminEmail())
                    .adminPwd(passwordEncoder.encode(postAdminSignupReq.getAdminPwd()))
                    .authority("Administrator")
                    .build());


            PostAdminSignupRes postAdminSignupRes = PostAdminSignupRes.builder()
                    .idx(admin.getIdx())
                    .adminEmail(admin.getAdminEmail())
                    .build();

            return postAdminSignupRes;

        }
        throw new BaseException(ADMIN_SIGNUP_DUPLICATE_EMAIL);
    }
        public Object adminLogin(PostAdminLoginReq postAdminLoginReq) throws BaseException{
            Optional<Admin> result = adminRepository.findByAdminEmail(postAdminLoginReq.getAdminEmail());
            System.out.println(postAdminLoginReq.getAdminEmail());
            if (result.isPresent()) {
                if (passwordEncoder.matches(postAdminLoginReq.getAdminPwd(), result.get().getAdminPwd())) {

                    PostAdminLoginRes postAdminLoginRes = PostAdminLoginRes.builder()
                            .accessToken(TokenProvider.generateAccessToken(result.get(), expiredTimeMs))
                            .adminEmail(result.get().getAdminEmail())
                            .idx(result.get().getIdx())
                            .build();


                    return postAdminLoginRes;
                } else {
                    throw new BaseException(ADMIN_LOGIN_INCORRECT_ACCOUNT);
                }
            }
            throw new BaseException(ADMIN_LOGIN_INCORRECT_ACCOUNT);
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
