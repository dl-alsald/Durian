package com.durianfirst.durian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemErrorController {

    @GetMapping("/error/accessDenied")
    public String admin(){
        return "error/accessDenied";
    }

    @GetMapping("/error/error-403")
    public void error_403(){}

    @GetMapping("/error/error-404")
    public void error_404(){}

    @GetMapping("/error/error-500")
    public void error_500(){}

}
