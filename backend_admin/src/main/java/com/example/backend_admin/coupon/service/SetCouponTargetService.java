package com.example.backend_admin.coupon.service;

import com.example.backend_admin.coupon.model.request.PostCouponCreateReq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SetCouponTargetService {
    private final ObjectMapper objectMapper;

    public String readFile() {
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/couponTarget.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonContent.toString();
    }


    public void writeFile(PostCouponCreateReq postCouponCreateReq, Long couponIdx) {
        String jsonString = readFile();
        String selected = postCouponCreateReq.getSelectedOption();

        try {
            Map<String, Object> targetMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});

            if (selected.equals("level")) {
                List<String> levels = (List<String>) targetMap.get("levels");
                levels.add((String) postCouponCreateReq.getDataToSend());
                targetMap.put("levels", levels); // 수정된 levels 값을 다시 Map에 설정

                List<Integer> levelCoupon = (List<Integer>) targetMap.get("levelCoupon");
                levelCoupon.add(Integer.parseInt(couponIdx.toString()));
                targetMap.put("levelCoupon", levelCoupon);
            } else if (selected.equals("money")) {
                List<Integer> money = (List<Integer>) targetMap.get("money");
                money.add((Integer.parseInt(postCouponCreateReq.getDataToSend().toString())));
                targetMap.put("money", money); // 수정된 money 값을 다시 Map에 설정

                List<Integer> levelCoupon = (List<Integer>) targetMap.get("moneyCoupon");
                levelCoupon.add(Integer.parseInt(couponIdx.toString()));
                targetMap.put("moneyCoupon", levelCoupon);
            } else if (selected.equals("sleeper")) {
                targetMap.put("sleeper", postCouponCreateReq.getDataToSend());
                targetMap.put("sleeperCoupon", Integer.parseInt(postCouponCreateReq.getDataToSend().toString()));
            }
            // 수정된 Map을 JSON 문자열로 변환
            String updatedJsonString = objectMapper.writeValueAsString(targetMap);
            // JSON 파일에 쓰기
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/couponTarget.json"))) {
                writer.write(updatedJsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
