package com.example.backend.qna.controller;

import com.example.backend.common.BaseResponse;
import com.example.backend.qna.model.request.PostQnaReadReq;
import com.example.backend.qna.model.request.PostQnaRegisterReq;
import com.example.backend.qna.model.response.GetQnaListRes;
import com.example.backend.qna.model.response.PostQnaReadRes;
import com.example.backend.qna.model.response.PostQnaRegisterRes;
import com.example.backend.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
@CrossOrigin("*")
public class QnaController {
    private final QnaService qnaService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public BaseResponse<PostQnaRegisterRes> registerArticle(@RequestHeader(value = "Authorization") String token, @RequestBody PostQnaRegisterReq postQnaRegisterReq) {
        return qnaService.registerQna(token, postQnaRegisterReq);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/read/{idx}")
    public ResponseEntity<PostQnaReadRes> readArticle(@PathVariable String idx, @RequestBody PostQnaReadReq postQnaReadReq) {
        try {
            Long idxValue = Long.parseLong(idx);
            PostQnaReadRes postQnaReadRes = qnaService.readQna(idxValue, postQnaReadReq);
            return ResponseEntity.ok(postQnaReadRes);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<GetQnaListRes>> findAll() {
        List<GetQnaListRes> articles = qnaService.list();
        return ResponseEntity.ok(articles);
    }

}