package com.example.backend_admin.elastic.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetTodaySignupRes {
    Long todaySignup;
    Long difSignup;
}
