package com.example.backend_admin.calculate.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetLoginTimeRes {
    int[] timeDataList;

}
