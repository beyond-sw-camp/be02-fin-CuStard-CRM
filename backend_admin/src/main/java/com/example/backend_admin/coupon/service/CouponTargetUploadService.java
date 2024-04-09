package com.example.backend_admin.coupon.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CouponTargetUploadService {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String saveFile() throws IOException {
        File file = new File("/src/main/resources/couponTarget.json");
        String filename = file.getName();

        amazonS3.deleteObject(bucket, filename);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.length());
        metadata.setContentType("application/json");
        amazonS3.putObject(bucket, filename, new FileInputStream(file), metadata);
        return amazonS3.getUrl(bucket, filename).toString();
    }
}