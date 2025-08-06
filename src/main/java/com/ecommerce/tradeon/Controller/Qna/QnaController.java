package com.ecommerce.tradeon.Controller.Qna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QnaController {

    @GetMapping("/mypage/Qna")
    public String Qna() {
        return "Qna/MyQnaList";
    }
}
