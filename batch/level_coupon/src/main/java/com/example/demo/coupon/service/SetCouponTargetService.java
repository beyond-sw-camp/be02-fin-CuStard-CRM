package com.example.demo.coupon.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class SetCouponTargetService {
    private final AmazonS3 amazonS3;
    private final ObjectMapper objectMapper;
    private final ResourceLoader resourceLoader;


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private String filename = "couponTarget.json";
    public String readFile() {
        StringBuilder jsonContent = new StringBuilder();
        S3Object o = amazonS3.getObject(new GetObjectRequest(bucket, filename));
        try (InputStream objectData = o.getObjectContent();
             BufferedReader reader = new BufferedReader(new InputStreamReader(objectData)))  {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonContent.toString();
    }

    public List<String> extractLevels() {
        String jsonString = readFile();
        List<String> levels = null;
        try {
            Map<String, Object> targetMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
            });
            levels = (List<String>) targetMap.get("levels");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            // 형변환 오류 처리
            e.printStackTrace();
        }

        return levels;
    }

    public List<Integer> extractLevelCoupon() {
        String jsonString = readFile();
        List<Integer> coupons = null;
        try {
            Map<String, Object> targetMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
            });
            coupons = (List<Integer>) targetMap.get("levelCoupon");
            System.out.println("coupon : " + coupons);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            // 형변환 오류 처리
            e.printStackTrace();
        }

        return coupons;
    }
}
