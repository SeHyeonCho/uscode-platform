# GCA Backend
![로고](docs/GCA_full.png)

**GCA** (Good, Cheap, with AI) 프로젝트의 백엔드 서버입니다.

이 서버는 판매자의 상품 등록, 구매자의 주문 처리, 회원 정보 관리 등 전자상거래 플랫폼의 핵심 기능을 제공합니다.

또한, 외부 AI 서버와의 연동을 통해 상품 이미지에 대한 분석 요청을 처리함으로써 지능형 서비스와의 연결을 지원합니다.

## Stack
- **FrameWork** : Spring Boot 3.5.3
- **Database** : MySQL, Redis
- **ORM** : Spring Data JPA (Hibernate)
- **Security** : Spring Security + JWT
- **File Handling** : Local Storage(Nginx 경유)
- **Build Tool** : Gradle

---

## 주요 기능

### 회원
- 회원가입 / 로그인 (JWT)
- SMTP 이메일 인증 
- 사용자 역할 분기 (SELLER / BUYER)
- 회원 정보 수정

### 상품
- 판매자 상품 등록 (이미지 포함)
- 상품 목록 조회 - BUYER
- 이미지 정적 파일로 접근 가능
- AI 가격 및 품질 판정 요청

### 주문
- 구매자 상품 주문 생성
- 주문 내역 확인
---

## 폴더 구조

~~~bash
├── ai       # AI 관련 기능
├── auth     # JWT 토큰 인증 등 인증/인가 관련 기능
├── email    # 이메일 발송 관련 로직
├── order    # 주문 생성 및 주문 상태 관리
├── product  # 상품 등록, 조회, 이미지 저장 등 상품 관리
└── user     # 사용자 프로필, 회원가입, 정보 수정 등 유저 관리
~~~


## API 문서
[Swagger API 문서 보기](http://34.64.226.141/swagger-ui/index.html)

## 환경 변수 (.env)
루트 디렉토리에 **'.env'** 파일을 생성하여 아래와 같이 작성 하세요:
JWT_SECRET=YOUR_SECRET_KEY

~~~yaml
jwt:
  secret: 
    key: ${JWT_SECRET}
~~~

## 관련 리포지토리

- [Frontend Repository](https://github.com/gwxodn020/US-Code)
- [AI Server Repository](https://github.com/LifeIsMoment/agriculture-ai-project)