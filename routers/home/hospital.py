from fastapi import Query, APIRouter
import services.home.hospital_service as hospital_service

router = APIRouter(prefix="/hospital", tags=["hospital"])

@router.get("/sido")
def read_sido_tree():
  return hospital_service.get_sido_tree()

@router.get("/region")
def read_region_tree(level: str = Query(...), code: str = Query(...)):
  return hospital_service.get_region_tree(level, code)
