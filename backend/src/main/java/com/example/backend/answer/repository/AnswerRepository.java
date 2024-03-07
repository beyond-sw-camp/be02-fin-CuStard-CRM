package com.example.backend.answer.repository;

import com.example.backend.answer.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface    AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByQnaIdx(Long qnaIdx);
}
