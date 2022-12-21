package com.example.semina.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerHello {
    @GetMapping("hello")//hello치고 오면 메소드 호출
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//http의 바디부분에 해당 데이터를 리턴
    //리턴 밸류를 그냥 바로 삽입
    public String helloString(@RequestParam("name")String name){
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//key : value json 방식 리턴
        //객체 반환?? 디폴트 >> json방식으로 데이터를 만들어 http에 응답
        //MappingJacksonMessageConverter를 통해서 Jackson라이브러리 사용객체를 json으로 변환하는 라이브러리

    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
