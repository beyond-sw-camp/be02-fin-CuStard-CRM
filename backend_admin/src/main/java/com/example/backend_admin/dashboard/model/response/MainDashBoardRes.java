package com.example.backend_admin.dashboard.model.response;

import com.example.backend_admin.dashboard.model.dto.*;
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
