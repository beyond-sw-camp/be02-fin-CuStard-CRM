package com.example.backend_admin.batch.model.response;

import com.example.backend_admin.log.entity.LoginLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SleeperCouponReaderRes {
    private List<List<LoginLog>> loginLogLists;
}
