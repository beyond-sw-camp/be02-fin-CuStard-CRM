package com.example.backend_admin.email.repository;

import com.example.backend_admin.email.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
