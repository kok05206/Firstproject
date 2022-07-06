package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식이 가능하게 선언! (해당 클래스로 테이블을 만든다!!!)
@AllArgsConstructor // 매번 생성자를 써주기 귀찮아서 간소화 해주기 위한 어노테이션
@ToString // ToString메소드를 간소화 해주기 위한 어노테이션
@NoArgsConstructor // 디폴트 생성자를 추가해주는 어노테이션
@Getter
public class Article {

    @Id // 대표값 , 사람으로 따지면 주민등록번호 같은 것.
    //@GeneratedValue // 1, 2, 3, ... id 번호를 자동 생성해주는 어노테이션!
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성하게 해주는 어노테이션!
    private Long id;

    @Column // DB에서 이해할 수 있게끔 선언 해주는 것. , DB에서 관리하는 테이블에 연결하기 위함.
    private String title;

    @Column // DB에서 이해할 수 있게끔 선언 해주는 것. , DB에서 관리하는 테이블에 연결하기 위함.
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}