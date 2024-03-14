package com.example.backend_admin.batch.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SleeperCouponWriterReq {
    private List<Long> couponRecipientList;
}
