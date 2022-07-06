package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // Rest API용 컨트롤러! 데이터(JSON)를 반환
public class ArticleApiController {
    @Autowired // DI (Dependency Injection) => 외부에서 가져온다
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    // 단일 article
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // POST
   @PostMapping("/api/articles")
   public Article create(@RequestBody ArticleForm dto){ // RequestBody : JSON 데이터를 받기 위한 어노테이션!
       Article article = dto.toEntity();
       return articleRepository.save(article);
   }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){ // RequestBody : JSON 데이터를 받기 위한 어노테이션!
        // 1. 수정용 Entity 생성
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());
        // 2. 대상 Entity를 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른경우)
        if(target == null || id != article.getId()){
            // 400,  잘못된 요청에 대한 응답!
            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 잘못된 요청이 아니라면 , 정상업데이트 진행 및 응답(200)
//        Article updated = articleRepository.save(article);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
        // 4-1. 데이터를 다 넣어주지 않았을때도 볼 수있게끔 수정!
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }


    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        // 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}