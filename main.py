from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles
from fastapi.middleware.cors import CORSMiddleware
from routers.home import recommends
from core.config import cors_origins

app = FastAPI()
app.mount("/static", StaticFiles(directory="static"), name="static")

app.add_middleware(
  CORSMiddleware,
  allow_origins=cors_origins,
  allow_methods=["*"],
  allow_headers=["*"]
)

# routers 연결
app.include_router(recommends.router)