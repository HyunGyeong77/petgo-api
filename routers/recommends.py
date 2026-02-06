from fastapi import APIRouter
import services.recommend_service as recommend_service

router = APIRouter(prefix="/recommend", tags=["recommend"])

@router.get("/")
def read_category_tree():
  return recommend_service.get_category_tree()