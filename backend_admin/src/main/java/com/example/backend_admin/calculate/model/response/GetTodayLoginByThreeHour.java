package com.example.backend_admin.calculate.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetTodayLoginByThreeHour {
    private List<Long> timeDataList;
}
