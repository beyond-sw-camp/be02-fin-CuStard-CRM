package com.example.backend.qna.controller;

import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import com.example.backend.common.BaseResponseStatus;
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
    public ResponseEntity registerArticle(@RequestHeader(value = "Authorization") String token, @RequestBody PostQnaRegisterReq postQnaRegisterReq) {
        try {
            return ResponseEntity.ok().body(qnaService.registerQna(token, postQnaRegisterReq));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(exception.getBaseResponseStatus());
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/read/{idx}")
    public ResponseEntity readArticle(@PathVariable String idx, @RequestBody PostQnaReadReq postQnaReadReq) {
        try {
            Long idxValue = Long.parseLong(idx);
            PostQnaReadRes postQnaReadRes = qnaService.readQna(idxValue, postQnaReadReq);
            return ResponseEntity.ok(postQnaReadRes);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok().body(qnaService.list());
        } catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }

}
