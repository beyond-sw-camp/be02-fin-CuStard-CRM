package com.example.backend_admin.qna.controller;

import com.example.backend_admin.qna.model.response.GetQnaListRes;
import com.example.backend_admin.qna.model.response.PostQnaReadRes;
import com.example.backend_admin.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/qna")
@CrossOrigin("*")
public class QnaController {
    private final QnaService qnaService;
    @RequestMapping(method = RequestMethod.POST, value = "/read/{idx}")
    public ResponseEntity<PostQnaReadRes> readArticle(@PathVariable Long idx) {
        PostQnaReadRes postQnaReadRes = qnaService.readQna(idx);
        return ResponseEntity.ok(postQnaReadRes);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<GetQnaListRes>> findAll() {
        List<GetQnaListRes> articles = qnaService.list();
        return ResponseEntity.ok(articles);
    }

}
