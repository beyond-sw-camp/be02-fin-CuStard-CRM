package com.example.backend.answer.model.entity;


import com.example.backend.qna.model.entity.Qna;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String answerContent;

    @OneToOne
    @JoinColumn(name = "qnaIdx")
    private Qna qna;
}
