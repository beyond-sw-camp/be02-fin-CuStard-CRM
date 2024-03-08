package com.example.backend_admin.log.repository;


import com.example.backend_admin.log.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRespository extends JpaRepository<LoginLog, Long> {
}
