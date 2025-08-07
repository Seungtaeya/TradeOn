package com.ecommerce.tradeon.Exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomLoginException.class)
    public String LoginException(CustomLoginException e) {

        return "auth/login";
    }

    @ExceptionHandler(IOException.class)
    public String IOException(IOException e , RedirectAttributes model) {
        model.addFlashAttribute("errorMassage","상품 등록 실패했습니다. 다시 등록해주세요");
        return "redirect:/product/new";
    }
}
