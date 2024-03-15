package com.example.backend_admin.calculate.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetTodayLoginRes {
    Long todayLogin;
    Long difLogin;
}
