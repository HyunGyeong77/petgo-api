# PetGo

반려동물과 보호자를 위한 서비스 **PetGo**의 백엔드 프로젝트입니다.

현재 Spring Boot를 기반으로 REST API를 개발하고 있으며, Supabase PostgreSQL을 데이터베이스로 사용합니다.

## 🛠 Tech Stack

### Backend

* Java 26
* Spring Boot
* Spring MVC
* Spring Data JPA
* QueryDSL
* Lombok

### Security & Configuration

* Spring Security
* CORS 설정 (Spring Security 연동)

### Database & Deployment

* Supabase PostgreSQL
* Render

---

## 🗄 Database

Supabase PostgreSQL을 사용합니다.

Spring Boot에서는 환경변수를 통해 데이터베이스 정보를 주입합니다.

```yaml
spring:
  datasource:
    url: ${SUPABASE_DB_URL}
    username: ${SUPABASE_DB_USERNAME}
    password: ${SUPABASE_DB_PASSWORD}
    driver-class-name: org.postgresql.Driver
```

---

## 🔐 Environment Variables

로컬 환경에서는 다음 환경변수가 필요합니다.

```text
SUPABASE_DB_URL
SUPABASE_DB_USERNAME
SUPABASE_DB_PASSWORD
```

---

## 🚀 Implemented APIs

현재 `src/main`의 컨트롤러에서 제공하는 조회 API입니다.

| Method | Endpoint | 설명 |
| --- | --- | --- |
| `GET` | `/api/hospital/regions?level={level}&code={code}` | 지역 단계별 지역 목록 조회 |
| `GET` | `/api/dog-info/checklist/today` | 오늘의 체크리스트 최대 5개 조회 |
| `GET` | `/api/recommend/category/all` | 추천 상품 카테고리 트리 조회 |
| `GET` | `/api/post/categories` | 게시글 카테고리와 게시글 수 조회 |
| `GET` | `/api/post/category/list` | 카테고리별 게시글 목록 조회 |
| `GET` | `/api/post/category/{categoryId}` | 특정 카테고리의 제목, 설명, 게시글 조회 |
| `GET` | `/api/post/statistics` | 전체 게시글 및 카테고리 수 조회 |

### 지역 목록

`level`은 필수이며 여러 값을 반복해서 전달할 수 있습니다. `code`는 선택 사항으로, 지정하면 해당 지역 코드의 하위 지역을 조회합니다.

```http
GET /api/hospital/regions?level=2&code=1100000000
```

응답 항목은 `code`, `name`, `level` 필드로 구성됩니다.

### 오늘의 체크리스트

```http
GET /api/dog-info/checklist/today
```

체크리스트 항목은 `id`, `title` 필드로 구성됩니다. 같은 날짜에는 같은 순서로 최대 5개 항목을 반환합니다.

### 추천 카테고리

```http
GET /api/recommend/category/all
```

부모 카테고리 아래에 하위 카테고리와 각 카테고리의 추천 상품 목록을 포함한 트리 형태로 반환합니다.

### 게시글

`/api/post/categories`는 카테고리의 `id`, `title`, `description`, `icon`, `postCount`를 반환합니다. `/api/post/category/list`는 카테고리별 게시글 목록을 반환하며, 각 게시글은 `categoryId`, `postId`, `title`, `level`, `readtime` 필드로 구성됩니다.

카테고리 상세 조회는 UUID 형식의 `categoryId`가 필요합니다.

```http
GET /api/post/category/71e74a60-99c8-4e0e-9391-0ce8a4e659c6
```

게시글 통계 API는 `totalPost`, `categoryCount`를 반환합니다.

---

## ☁️ Deployment

백엔드 서버는 Render에서 실행되며, 데이터베이스는 Supabase PostgreSQL을 사용합니다.

배포 구조:

```text
GitHub
   ↓
Render
   ↓
Spring Boot
   ↓
Supabase PostgreSQL
```

배포된 API를 호출할 때는 `<render-domain>`을 실제 Render 도메인으로 바꾸고, 위 표의 엔드포인트를 뒤에 붙입니다. 예를 들어:

```text
https://<render-domain>/api/dog-info/checklist/today
```
