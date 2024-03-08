package com.example.backend.log.repository;

import com.example.backend.log.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRespository extends JpaRepository<LoginLog, Long> {
}
