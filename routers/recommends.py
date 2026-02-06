from fastapi import APIRouter
import services.recommend_service as recommend_service

router = APIRouter(prefix="/recommend", tags=["recommend"])

@router.get("/")
def read_all_product():
  return recommend_service.get_all_products()