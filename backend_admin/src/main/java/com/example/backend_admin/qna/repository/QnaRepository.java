package com.example.backend_admin.qna.repository;

import com.example.backend_admin.qna.model.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna,Long> {
}
