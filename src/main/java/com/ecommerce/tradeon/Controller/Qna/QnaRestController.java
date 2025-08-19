package com.ecommerce.tradeon.Controller.Qna;

import com.ecommerce.tradeon.Dto.Qna.QnaDto;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Service.QnaService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QnaRestController {

    private final QnaService qnaService;

    @GetMapping("/qna/product/{id}")
    public List<QnaDto> getQnaByProductId(@PathVariable("id") Long productId) {
        return qnaService.findQnaByProductId(productId);
    }

    @PostMapping("/qna/write")
    public ResponseEntity<Void> saveQna(@RequestBody QnaDto qnaDto, HttpSession session) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");
        qnaDto.setMemberId(loginMember.getId());
        qnaService.saveQna(qnaDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/seller/qna")
    public List<QnaDto> getQnaListForSeller() {
        return qnaService.findAllQna();
    }

    @PostMapping("/seller/qna/reply")
    public ResponseEntity<Void> saveReply(@RequestBody QnaDto qnaDto, HttpSession session) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");
        qnaDto.setMemberId(loginMember.getId());

        qnaService.saveQna(qnaDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/qna/lists")
    public List<QnaDto> getMyQnaList(HttpSession session) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");
        List<QnaDto> qnaByMemberId = qnaService.findQnaByMemberId(loginMember.getId());
        for (QnaDto qnaDto : qnaByMemberId) {
            System.out.println("qnaDto.getContent() = " + qnaDto.getContent());
        }
        return qnaByMemberId;
    }
}
