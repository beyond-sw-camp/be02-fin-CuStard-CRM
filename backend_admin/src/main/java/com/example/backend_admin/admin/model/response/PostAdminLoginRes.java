package com.example.backend_admin.admin.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostAdminLoginRes {
    private String adminEmail;
    private String accessToken;
    private Long idx;
}
