from fastapi import APIRouter
import services.home.recommend_service as recommend_service

router = APIRouter(prefix="/home/recommend", tags=["/home/recommend"])

@router.get("/")
def read_category_tree():
  return recommend_service.get_category_tree()