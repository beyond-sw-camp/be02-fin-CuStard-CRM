package com.example.backend_admin.admin.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PostAdminSignupReq {
    private String adminPwd;
    private String adminEmail;
}
