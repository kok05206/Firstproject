package com.example.firstproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 인식이 가능하게 선언!
public class Article {

    @Id // 대표값 , 사람으로 따지면 주민등록번호 같은 것.
    @GeneratedValue // 1, 2, 3, ... 자동 생성 어노테이션!
    private Long id;

    @Column // DB에서 이해할 수 있게끔 선언 해주는 것. , DB에서 관리하는 테이블에 연결하기 위함.
    private String title;

    @Column // DB에서 이해할 수 있게끔 선언 해주는 것. , DB에서 관리하는 테이블에 연결하기 위함.
    private String content;


    // 생성자 추가
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // toString() 추가
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}