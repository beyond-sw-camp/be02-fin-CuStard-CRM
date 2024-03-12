package com.example.backend_admin.admin.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostAdminLevelCouponReq {

    private List<Integer> count;
    private List<Integer> category;
    private List<Integer> discount;
}
