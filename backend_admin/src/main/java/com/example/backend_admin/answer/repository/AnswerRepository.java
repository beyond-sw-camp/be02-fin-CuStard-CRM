package com.example.backend_admin.answer.repository;

import com.example.backend_admin.answer.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByQnaIdx(Long qnaIdx);
}
