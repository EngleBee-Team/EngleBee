# EngleBee

## 1. 프로젝트 개요
- AI 기반 학생 맞춤형 화상 영어 과외 서비스

![스크린샷 2024-10-24 오전 11.01.32.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_15NTyw%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.01.32.png)

![스크린샷 2024-10-24 오전 11.01.48.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_JbhGM7%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.01.48.png)

![스크린샷 2024-10-24 오전 11.04.14.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_XMnbpS%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.04.14.png)

```text
코로나 이후 비대면 서비스가 증가하면서
온라인 수업이 더욱 활성화 되었습니다.

하지만 온라인 수업은 오프라인에 비해 
학생에게 적절한 수업을 제공하는데 한계가 있습니다.

온라인 수업의 한계를 극복하기 위해 
AI를 활용한 학생 맞춤형 수업 제공 서비스를 제공합니다.

EngleBee 서비스를 통해
선생님과 학생은 서로 1 대 1 화상 과외 서비스로 오로지 서로에게 집중할 수 있어 집중력이 저하되지 않습니다.
선생님은 해당 학생에 맞춤형 학습을 제공할 수 있고,
해당 학생이 어떤 부분을 이해를 못하는지 파악하여 피드백을 진행할 수 있습니다.
학생은 자신에게 맞는 학습을 제공받고 본인이 부족한 부분을 채워주는 피드백을 제공 받을 수 있습니다.
```

## 2. 팀원 소개 및 역할

### 팀장 - 최원준
   - Spring Security 와 OAuth2 를 이용한 인증, 인가 (구글/네이버 로그인)
  - Github Actions와 Docker를 이용한 CI/CD 파이프라인 및 인프라 구축
  - OpenAI API를 사용한 시험문제 추천 / 피드백 추천, 자동 댓글 생성 기능 구현
  - 인증 인가 및 인프라, 시험  관련 도메인 개발

### 팀원 - 김하람
   - WebRTC와 WebSocket을 이용한 온라인 1:1 화상 채팅 기능 구현
   - AOP와 ChartJS를 이용한 로그 시스템을 구축하여 관리자 대시보드 구현
   - 화상채팅 및 관리자 관련 도메인 개발

### 팀원 - 허영롱
   - JUnit5를 통한 단위 테스트 및 통합 테스트 도입
   - JPA 기반 도메인 설계 및 JPA의 N+1 문제 해결
   - 학생 및 선생님 관련 도메인 개발

### 3. 프로젝트 구조

#### 3-1. 기술 스택

![스크린샷 2024-10-24 오전 10.48.59.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_DQFDWR%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2010.48.59.png)

```
프로그래밍 언어
- Java 17

프레임워크
- Spring Boot 3.3.2

IDE
- InteliJ Ultimate IDEA

뷰
- BootStrap + ThymeLeaf

데이터 접근 기술
- JPA / Spring Data JPA

DB
- MariaDB

테스트
- JUnit5 / Mockito Library

인프라
- AWS EC2 / Docker / Nginx / AWS RDS

CI / CD
- Github Actions / Gradle / Docker / AWS EC2

3rd Party Library
- OAuth2 / WebSocket / WebRTC / OpenAI API
```

#### 3-2. 프로젝트 아키텍처 구성

![스크린샷 2024-10-24 오전 10.52.54.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_2NdBLM%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2010.52.54.png)


### 4. 프로젝트 진행 방식

#### 4-1. Git & Github

![스크린샷 2024-10-24 오전 10.53.43.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_gXpFRz%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2010.53.43.png)

![스크린샷 2024-10-24 오전 10.56.23.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_jB7Zrp%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2010.56.23.png)

![스크린샷 2024-10-24 오전 10.57.07.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_OfjwY9%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2010.57.07.png)

![스크린샷 2024-10-24 오전 10.57.43.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_H5jwL6%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2010.57.43.png)

![스크린샷 2024-10-24 오전 10.58.25.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_ljeR24%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2010.58.25.png)

![스크린샷 2024-10-24 오전 11.09.54.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_YAZg6O%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.09.54.png)
- 
- Git을 통해 버전관리를 진행하고, Github을 통해 코드를 관리했습니다.

- Git Flow를 따르고, Pull Request 및 코드리뷰를 통해 코드를 안정적으로 통합했습니다.

- Git Commit Convention을 따라 커밋 메시지를 작성하여, 코드 작성 의도 공유 및 코드 이력 추적을 쉽게 할 수 있었습니다.

- Issue Template과 PR Template을 작성하여, 팀원간 작업 진행상황을 효과적으로 공유했습니다.

- 에자일 프로세스를 일부 도입하여, 빠른 피드백을 기반으로 프로젝트를 진행했습니다.

- JUnit5와 Mockito를 이용하여 단위/통합 테스트를 수행했습니다.

- Spring Boot의 Profile 기능을 이용하여 개발 / 테스트 / 배포 환경을 분리했습니다.

- Github Actions의 Workflow를 통해 CI/CD 파이프라인을 구축했습니다.

- Gradle을 통해 빌드/테스트를 자동화하고, Docker를 활용하여 컨테이너 환경에 배포하여 개발 환경과 운영 환경을 동일하게 유지할 수 있었습니다.


### 5. 프로젝트 주요 기능

#### 5-1. 로그인, 회원가입 ( 구글, 네이버 로그인 )

![스크린샷 2024-10-24 오전 11.04.58.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_3D4xZC%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.04.58.png)

![스크린샷 2024-10-24 오전 11.09.31.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_8SuvRn%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.09.31.png)


- 구글 로그인, 네이버 로그인이 가능합니다.

- 소셜 로그인 이후 추가 회원가입 로직을 따라 학생/선생님 회원으로 회원가입 가능합니다.

#### 5-2. 메인 페이지

![스크린샷 2024-10-24 오전 11.06.56.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_e7UfDb%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.06.56.png)

- (선생님 유저) 메인 페이지를 통해 자신의 수업, 출제할 시험, 출제 완료된 시험을 확인할 수 있씁니다.

- (학생 유저) 메인 페이지를 통해 자신의 수업, 풀어야 할 시험, 푼 시험을 확인할 수 있씁니다.


#### 5-3. 수업 생성하기

![스크린샷 2024-10-24 오전 11.08.17.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_ADLXFq%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.08.17.png)

- (선생님) 수업을 생성할 수 있습니다.

#### 5-4. 화상 과외 수업이 가능합니다.

![스크린샷 2024-10-24 오전 11.08.47.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_80r19c%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.08.47.png)

- 1대1로 학생과 선생님이 화상 채팅을 진행할 수 있습니다.

#### 5-5-1. LLM 활용 문제를 추천받을 수 있습니다. ( 난이도 : 쉽게, 보통, 어렵게 )

![스크린샷 2024-10-24 오전 11.11.34.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_pcfXTL%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.11.34.png)

- 학생의 학년, 수준에 맞게 이미 구축되어있는 기출문제 DB의 문제와 유사한 문제를 출제자 선생님에게 추천해줍니다.

- 원리
![스크린샷 2024-10-24 오전 11.12.25.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_MjwhNt%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.12.25.png)

#### 5-5-2. LLM 활용 질문에 대한 답변을 받을 수 있습니다. ( EngleBee 인턴 )

![스크린샷 2024-10-24 오전 11.12.05.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_xiPhqC%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.12.05.png)

- 원리
![스크린샷 2024-10-24 오전 11.12.36.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_Gk70pI%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.12.36.png)

  
#### 6. 시험 등록 페이지

![스크린샷 2024-10-24 오전 11.13.08.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_tjgEhc%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.13.08.png)


#### 7. Q&A 게시판

![스크린샷 2024-10-24 오전 11.13.29.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_8L6fpo%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.13.29.png)

- 질문 내용, 작성자, 작성일이 표시됩니다.
- 페이지네이션이 구현되어 있습니다.

#### 8. MY 페이지

![스크린샷 2024-10-24 오전 11.14.04.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_ii3Xs8%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.14.04.png)

#### 9. 계정 페이지

![스크린샷 2024-10-24 오전 11.14.18.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_JlnAwu%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.14.18.png)

#### 10. 관리자 페이지

![스크린샷 2024-10-24 오전 11.14.30.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk0%2Frhlbzq2d4m5d_2_9rqsgw9400000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_gLG1aM%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-10-24%20%EC%98%A4%EC%A0%84%2011.14.30.png)

- 유저 종류별 방문 빈도 / 연령대별 방문 빈도 / 시간대별 방문 빈도 / LLM API 호출 기록 (과금용)을 추적 관리할 수 있는 관리자 페이지입니다.

### 발표자료 링크

[발표자료](https://www.canva.com/design/DAGQKukecWg/1k37lTSF9mfcYXH6DbGDnw/view?utm_content=DAGQKukecWg&utm_campaign=share_your_design&utm_medium=link&utm_source=shareyourdesignpanel)

### 서비스 링크

https://www.englebee.co.kr

- ❗️ 현재 서버 비용 문제로 서버를 닫아놓은 상태입니다.

