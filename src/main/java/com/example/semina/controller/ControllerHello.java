package com.example.semina.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerHello {
    @GetMapping("hello")//hello치고 오면 메소드 호출
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }
}
