# PetGo - api

<br>

## API 소개

이 API는 **DB에 저장된 정보들을 프론트엔드로 전달**하는 역할을 합니다.  
- `GET /api/home/recommend` : 모든 상품 목록 조회
- `GET /api/home/hospital/sido` : 모든 시/도 목록 조회
- `GET /api/home/hospital/region?level&code` : 상위 지역 코드(code)와 지역 단계(level)에 따라 하위 지역 목록을 조회

<br>

## 기술 스택

🔹 Backend
- 🐍 **Python** (FastAPI)
- 🐘 **PostgreSQL**

<br>

🔹 Frontend
- <a href="https://github.com/HyunGyeong77/petgo">레포지토리 방문하기</a>

<br>

### 실행 방법

❗ 실행 전 주의 사항   

`.env`
```.env
SUPABASE_URL=<your-supabase-url>
SUPABASE_KEY=<your-supabase-key>
CORS_ORIGINS=<your-cors-origins> # url,url (쉼표로 분리)
```

<br>

**bash 기준**

1. 레포지토리 클론
```bash
git clone <레포지토리 URL>
cd <클론한 폴더>
```

2. 가상환경 생성
```bash
python -m venv .venv
```

3. 가상환경 활성화
```bash
source .venv/Scripts/Activate
```

4. 의존성 설치
```bash
pip install -r requirements.txt
```

5. 서버 실행
```bash
uvicorn main:app --reload
```