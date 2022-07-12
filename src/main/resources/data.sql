INSERT INTO article(id, title, content) VALUES(1, '가가가가', '1111');
INSERT INTO article(id, title, content) VALUES(2, '나나나나', '2222');
INSERT INTO article(id, title, content) VALUES(3, '다다다다', '3333');

-- sql문에서 주석처리는 -- 로 처리한다!

-- article 더미 데이터 추가 --
INSERT INTO article(id, title, content) VALUES(4, '당신의 인생 영화는?', '댓글 ㄱ');
INSERT INTO article(id, title, content) VALUES(5, '당신의 소울 푸드는?', '댓글 ㄱㄱ');
INSERT INTO article(id, title, content) VALUES(6, '당신의 취미는?', '댓글 ㄱㄱㄱ');

-- Comment 더미 데이터 추가 --
-- 4번 게시글의 댓글들
INSERT INTO comment (id, article_id, nickname, body) VALUES(1, 4, 'Park', '범죄도시');
INSERT INTO comment (id, article_id, nickname, body) VALUES(2, 4, 'Kim', '헤어질 결심');
INSERT INTO comment (id, article_id, nickname, body) VALUES(3, 4, 'Choi', '마녀');

-- 5번 게시글의 댓글들
INSERT INTO comment (id, article_id, nickname, body) VALUES(4, 5, 'Park', '마라탕');
INSERT INTO comment (id, article_id, nickname, body) VALUES(5, 5, 'Kim', '치킨');
INSERT INTO comment (id, article_id, nickname, body) VALUES(6, 5, 'Choi', '족발');

-- 6번 게시글의 댓글들
INSERT INTO comment (id, article_id, nickname, body) VALUES(7, 6, 'Park', '풋살');
INSERT INTO comment (id, article_id, nickname, body) VALUES(8, 6, 'Kim', '탁구');
INSERT INTO comment (id, article_id, nickname, body) VALUES(9, 6, 'Choi', '농구');