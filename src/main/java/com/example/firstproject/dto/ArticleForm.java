package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {

    private String title;
    private String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    
    // 변환 메소드 클래스 선언
    public Article toEntity() {
        return new Article(null, title, content); // Entity클래스에 객체를 생성하기 위해 , 생성자를 호출.
    }
}
