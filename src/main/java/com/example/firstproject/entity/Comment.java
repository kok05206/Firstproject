package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.omg.CORBA.portable.IDLEntity;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다! (다대일 관계 설정)
    @JoinColumn(name = "article_id") // 테이블에서 연결된 대상의 정보를 가져야하는데 그 대상정보의 컬럼을 "article_id" 로 하겠다!
    private Article article; // 댓글의 부모 게시글



    @Column
    private String nickname;


    @Column
    private String body;

}
