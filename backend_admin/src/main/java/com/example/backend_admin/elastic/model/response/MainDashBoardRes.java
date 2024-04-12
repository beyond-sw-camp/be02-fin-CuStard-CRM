package com.example.backend_admin.elastic.model.response;

import com.example.backend_admin.dashboard.model.dto.GetTodaySignupRes;
import  com.example.backend_admin.dashboard.model.dto.GetSleepAccountRes;
import com.example.backend_admin.elastic.model.dto.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MainDashBoardRes {
    GetTodayLoginRes getTodayLoginRes;
    GetTodaySignupRes getTodaySignupRes;
    GetTodayOrdersRes getTodayOrdersRes;
    GetCategoryOrdersRes getCategoryOrdersRes;
    GetCategoryOrdersRes getMonthOrdersRes;
    GetSleepAccountRes getSleepAccountRes;
    GetLoginTimeRes getLoginTimeRes;
}
