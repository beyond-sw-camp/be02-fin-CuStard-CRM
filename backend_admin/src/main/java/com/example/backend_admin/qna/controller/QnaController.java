package com.example.backend_admin.qna.controller;

import com.example.backend_admin.common.BaseResponse;
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
    public BaseResponse<PostQnaReadRes> readArticle(@PathVariable Long idx) {
        return qnaService.readQna(idx);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public BaseResponse<List<GetQnaListRes>> findAll() {
        return qnaService.list();
    }

}
