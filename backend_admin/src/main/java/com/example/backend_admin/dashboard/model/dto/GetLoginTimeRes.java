package com.example.backend_admin.dashboard.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetLoginTimeRes {
    int[] timeDataList;

}
