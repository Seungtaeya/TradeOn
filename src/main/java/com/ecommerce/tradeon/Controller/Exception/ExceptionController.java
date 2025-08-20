package com.ecommerce.tradeon.Controller.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    @GetMapping("/error/exception")
    public String exceptionView() {
        return "Exception/Exception";
    }
}
