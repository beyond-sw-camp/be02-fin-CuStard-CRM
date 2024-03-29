package com.example.backend_admin.dashboard.model.dto;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class GetSleepAccountRes {
    private Long todaySleepAccount;
    private Long difSleepAccount;
}
