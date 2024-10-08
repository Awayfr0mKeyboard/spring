CREATE TABLE IF NOT EXISTS spring_user(
	u_no INT PRIMARY KEY AUTO_INCREMENT,			-- 회원 번호
	u_id VARCHAR(200) NOT NULL UNIQUE,				-- 회원 아이디(email)
	u_pw VARCHAR(100) NOT NULL,						-- 비밀번호
	u_name VARCHAR(50) NOT NULL,					-- 이름 
	u_profile VARCHAR(300) NULL,					-- 프로필 이미지
	u_phone VARCHAR(20) NOT NULL,					-- 전화번호
	u_birth VARCHAR(20) NOT NULL,					-- 생년월일(19820607)
	u_addr VARCHAR(20),								-- 주소
	u_addr_detail VARCHAR(200),						-- 상세주소
	u_addr_post VARCHAR(50),						-- 우편번호
	u_point INT DEFAULT 0,							-- 포인트
	u_date TIMESTAMP NOT NULL DEFAULT now(),		-- 계정 생성일
	u_visit_date TIMESTAMP NOT NULL DEFAULT now(),	-- 최종 방문일(마지막 로그인)
	u_withdraw char(1) DEFAULT 'n'					-- 회원 탈퇴 여부(회원정보 숨김)   
);

DESC spring_member;

SELECT * FROM spring_user;

-- 테이블 이름 변경 spring_member => spring_user
RENAME TABLE spring_member TO spring_user;

-- 주소 저장 column 길이 확장 , u_addr
ALTER TABLE spring_user CHANGE COLUMN u_addr u_addr VARCHAR(200);
