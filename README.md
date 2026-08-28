# PetGo

반려동물과 보호자를 위한 서비스 **PetGo**의 백엔드 프로젝트입니다.

현재 Spring Boot를 기반으로 REST API를 개발하고 있으며, Supabase PostgreSQL을 데이터베이스로 사용합니다.

## 🛠 Tech Stack

### Database

* Supabase PostgreSQL

### Deployment

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

## 🏥 Hospital API

현재 지역 정보를 조회하는 API를 구현하고 있습니다.

### 지역 하위 목록 조회

```http
GET /api/hospital/regions/{code}?level={level}
```

응답 예시:

```json
[
  {
    "code": "11110",
    "name": "종로구",
    "level": 2
  },
  {
    "code": "11140",
    "name": "중구",
    "level": 2
  }
]
```

---

## ☁️ Deployment

백엔드 서버는 Render를 이용하여 배포할 예정입니다.

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

배포가 완료되면 Render에서 발급된 URL을 통해 API에 접근할 수 있습니다.

```text
https://<render-domain>/api/hospital/regions/{code}?level={level}
```
