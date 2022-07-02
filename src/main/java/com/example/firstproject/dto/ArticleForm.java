package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// 생성자 파트 리팩터링 과정
@AllArgsConstructor // 매번 생성자를 써주기 귀찮아서 간소화 해주기 위한 어노테이션
@ToString // ToString메소드를 간소화 해주기 위한 어노테이션
public class ArticleForm {

    private Long id; // id 필드 추가!
    private String title;
    private String content;


    // 변환 메소드 클래스 선언
    public Article toEntity() {
        return new Article(id, title, content); // Entity클래스에 객체를 생성하기 위해 , 생성자를 호출.
                                                // id 필드의 추가로 인한 toEntity()메소드의 코드 변경!
    }
}