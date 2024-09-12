
CREATE TABLE tbl_board(
	bno int PRIMARY KEY auto_increment,			-- 게시글 번호
    title VARCHAR(200) NOT NULL,				-- 제목
    content TEXT null,							-- 내용
    writer VARCHAR(20) NOT NULL,				-- 작성자 이름
    regdate TIMESTAMP NOT NULL default now(),	-- 글 등록시간
    viewcnt INT default 0						-- 조회수
);

DESC tbl_board;

commit;

SELECT * FROM tbl_board;

INSERT INTO tbl_board(title,content,writer) 
SELECT title,content,writer FROM tbl_board;

commit;


/*
	게시글 댓글 테이블
*/
CREATE TABLE tbl_comment(
	cno INT PRIMARY KEY AUTO_INCREMENT,	 			-- 댓글 번호
    bno INT NOT NULL DEFAULT 1,			 			-- 게시글 번호
    commentAuth VARCHAR(50) NOT NULL,	 			-- 작성자 이름
    commentText TEXT NOT NULL,			 	 		-- 댓글 내용
    regdate TIMESTAMP NOT NULL DEFAULT now(), 		-- 작성 시간
    updatedate TIMESTAMP NOT NULL DEFAULT now(),	-- 수정 시간
    CONSTRAINT fk_tbl_bno FOREIGN KEY(bno) 
    REFERENCES tbl_board(bno) ON DELETE CASCADE,
    INDEX(bno)
);

SELECT * FROM tbl_comment;

commit;

INSERT INTO tbl_comment(bno,commentAuth,commentText) 
SELECT bno,commentAuth,commentText FROM tbl_comment WHERE bno = 792;

SELECT count(*) FROM tbl_comment;
SELECT * FROM tbl_comment;

commit;








