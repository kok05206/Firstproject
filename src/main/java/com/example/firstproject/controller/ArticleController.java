package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션!!
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결!
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    // @GetMapping : HTTP GET 요청을 특정 핸들러 메소드에 맵핑하기위한 annotation.
    // 주소에 파라미터가 노출 됨.
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        // 로깅이란 ? --> 서버에서 일어나는 일들을 다 기록할 수 있게 하는것.
        // System.out.println(form.toString()); --> 로깅 기능으로 대체!!
        log.info(form.toString()); // 이렇게!!

        // 1. DTO를 Entity로 변환!!
        Article article = form.toEntity();
        // System.out.println(article.toString());
        log.info(article.toString()); // print문을 다 변경이 가능!!

        // 2. Repository 에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        // System.out.println(saved.toString());
        log.info(saved.toString()); // 마찬가지!!

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴!
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // id값을 통해서 찾았는데 만약에 해당 id값이 없다면 null을 반환해라.
        // id값이 있다면 articleEntity에 해당 id값이 들어가고 없다면 null값이 들어간다.
        // Optional<Article> articleEntity = articleRepository.findById(id)
        // articleRepository가 findById로 값을 반환할 때, 그 리턴타입이 Article이 아니다.
        // 이를 해결하기 위해서 Optional<> 안에 Article을 넣어서 에러를 해결.
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        // 3. 보여줄 페이지를 설정!
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

        // 1. 모든 Article을 가져온다!
        ArrayList<Article> articleEntityList = articleRepository.findAll(); // 현재 리턴타입이 서로 불일치 해서 에러가 발생.
        // 해결방법 1. 캐스팅을 해준다!
        // List<Article> articleEntityList = (List<Article>)articleRepository.findAll();

        // 해결방법 2. 리턴타입을 맞춰준다!
        // Iterable<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 Article 묶음을 뷰로 전달한다!
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지를 설정한다!
        return "articles/index"; // articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // id를 가져오기 위해 @PathVariable 어노테이션을 사용!
        // 수정할 데이터를 가져오기!!
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록!
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정!!
        return "/articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        // 1. DTO -> Entity로 변환한다!
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. Entity를 DB로 저장한다!

        // 2-1. DB에 기존 데이터를 가져온다!
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터에 값을 갱신한다!
        if (target != null) {
            articleRepository.save(articleEntity); // Entity 가 DB로 갱신된다!
        }

        // 3. 수정 결과 페이지로 Redirect 한다!
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!");

        // 삭제 처리 개요 3가지.
        // 1. 삭제 대상을 가져온다!
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상을 삭제 한다!
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다!");
        }

        // 3. 결과 페이지로 redirect 한다!
        return "redirect:/articles";
    }
}