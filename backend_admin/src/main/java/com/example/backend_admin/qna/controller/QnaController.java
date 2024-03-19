package com.example.backend_admin.qna.controller;

import com.example.backend_admin.common.BaseException;
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
    public ResponseEntity readArticle(@PathVariable Long idx) {
        try{
            return ResponseEntity.ok().body(BaseResponse.successResponse(qnaService.readQna(idx)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(qnaService.list()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

}
