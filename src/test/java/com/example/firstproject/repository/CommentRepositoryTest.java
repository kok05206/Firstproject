package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트!
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회") // 테스트 결과에 보여줄 이름
    void findByArticleId() {
        // * Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행결과를 예상과정
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "Park", "범죄도시");
            Comment b = new Comment(2L, article, "Kim", "헤어질 결심");
            Comment c = new Comment(3L, article, "Choi", "마녀");
            List <Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }

        // * Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행결과를 예상과정
            Article article = new Article(1L, "가가가가", "1111");
            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음!");
        }

        // TODO
        /* Case 3: 9번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 9L;

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행결과를 예상과정
            Article article = new Article(articleId, null, null);
            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "9번 글은 댓글이 없음!");

        }

        /* Case 4: 999번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 999L;

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행결과를 예상과정
            Article article = new Article(articleId, null, null);
            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "999번 글은 댓글이 없음!");

        }

        /* Case 5: -1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = -1L;

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 수행결과를 예상과정
            Article article = new Article(articleId, null, null);
            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "-1번 글은 댓글이 없음!");

        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: 'Park'의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "Park";

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 수행결과를 예상과정
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "범죄도시");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "마라탕");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "풋살");
            List <Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글을 출력!");
        }

        /* Case 2: 'Kim'의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "Kim";

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 수행결과를 예상과정
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "헤어질 결심");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "치킨");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "탁구");
            List <Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "Kim의 모든 댓글을 출력!");
        }

        /* Case 3: null 의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = null;

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByNickname(null);

            // 수행결과를 예상과정
            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected, comments);

        }

        /* Case 4: "" 의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "";

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 수행결과를 예상과정
            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "닉네임이 잘못되었습니다!");

        }

        /* Case 5: "i" 의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "Kim";
            String nickname1 = "Choi";

            // 실제 수행과정
            List<Comment> comments = commentRepository.findByNickname(nickname);
            List<Comment> comments1 = commentRepository.findByNickname(nickname1);
            comments.addAll(comments1);

            // 수행결과를 예상과정
            // Kim 댓글
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "헤어질 결심");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "치킨");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "탁구");

            // Choi 댓글
            Comment d = new Comment(3L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname1, "마녀");
            Comment e = new Comment(6L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname1, "족발");
            Comment f = new Comment(9L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname1, "농구");
            List <Comment> expected = Arrays.asList(a, b, c, d, e, f);

            System.out.println("비교 값 : " + expected);
            System.out.println("결과 값 : " + comments);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "i 의 모든 댓글을 출력!");

        }
    }
}