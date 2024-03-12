package com.example.backend_admin.answer.model;


import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.qna.model.Qna;
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

    @OneToOne
    @JoinColumn(name = "adminIdx")
    private Admin admin;
}
