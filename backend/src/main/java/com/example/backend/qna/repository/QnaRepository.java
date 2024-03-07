package com.example.backend.qna.repository;

import com.example.backend.qna.model.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna,Long> {
}
