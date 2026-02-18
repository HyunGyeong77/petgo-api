from fastapi import APIRouter
import services.home.hospital_service as hospital_service

router = APIRouter(prefix="/hospital", tags=["hospital"])

@router.get("/sido")
def read_sido_tree():
  return hospital_service.get_sido_tree()
