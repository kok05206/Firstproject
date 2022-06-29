package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 어노테이션!!
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결!
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        // 로깅이란 ? --> 서버에서 일어나는 일들을 다 기록할 수 있게 하는것.
        // System.out.println(form.toString()); --> 로깅 기능으로 대체!!
        log.info(form.toString()); // 이렇게!!

        // 1. DTO를 Entity로 변환!!
        Article article = form.toEntity();
        // System.out.println(article.toString());
        log.info(article.toString()); // print문을 다 변경이 가능!!

        // 2. Repository 에게 Entity를 DB안에 저장하게 함!
       Article saved  = articleRepository.save(article);
        // System.out.println(saved.toString());
        log.info(saved.toString()); // 마찬가지!!

        return "";
    }
}
