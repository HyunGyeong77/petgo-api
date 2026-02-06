from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles
from routers import recommends

app = FastAPI()
app.mount("/static", StaticFiles(directory="static"), name="static")

# routers 연결
app.include_router(recommends.router)