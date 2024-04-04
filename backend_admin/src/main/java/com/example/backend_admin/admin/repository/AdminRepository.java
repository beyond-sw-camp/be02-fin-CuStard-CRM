package com.example.backend_admin.admin.repository;

import com.example.backend_admin.admin.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByAdminEmail(String adminEmail);
}
