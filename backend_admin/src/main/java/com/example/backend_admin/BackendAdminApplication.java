package com.example.backend_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAdminApplication.class, args);
	}


}
