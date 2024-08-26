-- Sequence 테이블 생성
CREATE SEQUENCE IF NOT EXISTS CALL_LLM_LOG_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS CREATED_LECTURE_LOG_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS MEMBER_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS EXAM_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS LECTURE_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS MEMBER_SUBJECT_LEVEL_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS QUESTION_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS REPLY_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS SUBJECT_LEVEL_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS TEACHER_QUESTION_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS LECTURE_SUBJECT_LEVEL_SEQ START WITH 1 INCREMENT BY 1;

-- CALL_LLM_LOG 테이블 생성
CREATE TABLE IF NOT EXISTS CALL_LLM_LOG (
                                            seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                            token_count BIGINT NOT NULL,
                                            created_at DATETIME NOT NULL,
                                            updated_at DATETIME NOT NULL
);

-- CREATED_LECTURE_LOG 테이블 생성
CREATE TABLE IF NOT EXISTS CREATED_LECTURE_LOG (
                                                   seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                                   teacher_seq BIGINT NOT NULL,
                                                   student_seq BIGINT NOT NULL,
                                                   created_at DATETIME NOT NULL,
                                                   updated_at DATETIME NOT NULL
);

-- MEMBER 테이블 생성
CREATE TABLE IF NOT EXISTS MEMBER (
                                      seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                      nickname VARCHAR(20) NOT NULL,
                                      email VARCHAR(100) NOT NULL,
                                      profile VARCHAR(255),
                                      role VARCHAR(20) NOT NULL,
                                      status VARCHAR(20) NOT NULL,
                                      grade VARCHAR(20),
                                      refresh_token VARCHAR(255),
                                      login_type VARCHAR(20),
                                      personal_info_collection_agreed BOOLEAN NOT NULL,
                                      created_at DATETIME NOT NULL,
                                      updated_at DATETIME NOT NULL,
                                      CONSTRAINT UNIQUE_EMAIL_CONSTRAINT UNIQUE (email),
                                      CONSTRAINT UNIQUE_NICKNAME_CONSTRAINT UNIQUE (nickname)
);

-- EXAM 테이블 생성
CREATE TABLE IF NOT EXISTS EXAM (
                                    seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                    lecture_seq BIGINT NOT NULL UNIQUE,
                                    status VARCHAR(20) NOT NULL,
                                    title VARCHAR(50) NOT NULL,
                                    feedback VARCHAR(500),
                                    created_at DATETIME NOT NULL,
                                    updated_at DATETIME NOT NULL
);

-- LECTURE 테이블 생성
CREATE TABLE IF NOT EXISTS LECTURE (
                                       seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                       teacher_seq BIGINT NOT NULL,
                                       student_seq BIGINT NOT NULL,
                                       title VARCHAR(100) NOT NULL,
                                       status VARCHAR(20) NOT NULL,
                                       created_at DATETIME NOT NULL,
                                       updated_at DATETIME NOT NULL
);

-- MEMBER_SUBJECT_LEVEL 테이블 생성
CREATE TABLE IF NOT EXISTS MEMBER_SUBJECT_LEVEL (
                                                    seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                                    student_seq BIGINT NOT NULL,
                                                    subject_level_seq BIGINT NOT NULL,
                                                    created_at DATETIME NOT NULL,
                                                    updated_at DATETIME NOT NULL
);

-- QUESTION 테이블 생성
CREATE TABLE IF NOT EXISTS QUESTION (
                                        seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                        member_seq BIGINT NOT NULL,
                                        title VARCHAR(100) NOT NULL,
                                        content VARCHAR(3000) NOT NULL,
                                        created_at DATETIME NOT NULL,
                                        updated_at DATETIME NOT NULL
);

-- REPLY 테이블 생성
CREATE TABLE IF NOT EXISTS REPLY (
                                     seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                     question_seq BIGINT NOT NULL,
                                     member_seq BIGINT NOT NULL,
                                     content VARCHAR(2000) NOT NULL,
                                     created_at DATETIME NOT NULL,
                                     updated_at DATETIME NOT NULL
);

-- SUBJECT_LEVEL 테이블 생성
CREATE TABLE IF NOT EXISTS SUBJECT_LEVEL (
                                             seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                             subject_code VARCHAR(20) NOT NULL,
                                             level_code VARCHAR(20) NOT NULL,
                                             created_at DATETIME NOT NULL,
                                             updated_at DATETIME NOT NULL
);

-- TEACHER_QUESTION 테이블 생성
CREATE TABLE IF NOT EXISTS TEACHER_QUESTION (
                                                seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                                exam_seq BIGINT NOT NULL,
                                                direction VARCHAR(2000) NOT NULL,
                                                choices VARCHAR(300) NOT NULL,
                                                correct_answer INT NOT NULL,
                                                intent VARCHAR(500),
                                                student_answer INT,
                                                created_at DATETIME NOT NULL,
                                                updated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS LECTURE_SUBJECT_LEVEL (
                                                     seq BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                                     subject_level_seq BIGINT NOT NULL,
                                                     lecture_seq BIGINT NOT NULL,
                                                     created_at DATETIME NOT NULL,
                                                     updated_at DATETIME NOT NULL,
                                                     CONSTRAINT FK_LECTURE_SUBJECT_LEVEL_SUBJECT FOREIGN KEY (subject_level_seq) REFERENCES SUBJECT_LEVEL(seq),
                                                     CONSTRAINT FK_LECTURE_SUBJECT_LEVEL_LECTURE FOREIGN KEY (lecture_seq) REFERENCES LECTURE(seq)
);
