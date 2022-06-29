package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") // 브라우저에 요청을 받기위한 함수
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "Inhwan");
        // 응답 페이지 설정 return " 페이지 명 "
        return "greetings2"; // templates/greetings.mustache -> 브라우저로 전송
    }

    @GetMapping("/bye") // 브라우저에 요청을 받기위한 함수
    public String seeYouAgain(Model model) {
        model.addAttribute("nickname", "인환");
        // 응답 페이지 설정 return " 페이지 명 "
        return "goodbye2"; // templates/greetings.mustache -> 브라우저로 전송
    }
}