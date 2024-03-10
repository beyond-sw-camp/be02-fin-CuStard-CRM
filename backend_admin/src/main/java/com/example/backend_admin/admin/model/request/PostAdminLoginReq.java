package com.example.backend_admin.admin.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostAdminLoginReq {
    private String adminEmail;
    private String adminPwd;
}
