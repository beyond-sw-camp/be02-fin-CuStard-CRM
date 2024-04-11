package com.example.backend_admin.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.util.TimeZone;

@Configuration
public class AppConfig {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("KST"));
    }
}