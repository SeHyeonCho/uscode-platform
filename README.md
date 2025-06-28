# GCA Backend
![로고](docs/GCA_full.png)
GCA(Good Cheap with Artificial inteligence)의 백엔드 서버입니다.
판매자 상품 등록/ 구매자 주문/ 회원 관리 기능 등을 제공합니다.

## Stack
- **FrameWork** : Spring Boot 3.5.3
- **Database** : MySQL, Redis
- **ORM** : Spring Data JPA (Hibernate)
- **Security** : Spring Security + JWT
- **File Handling** : Local Storage(Nignx 경유)
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

### 주문
- 구매자 상품 주문 생성
- 주문 내역 확인

---

## 폴더 구조

