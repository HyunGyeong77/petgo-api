from fastapi import APIRouter
from .recommends import router as recommends_router
from .hospital import router as hospital_router

router = APIRouter(prefix="/home")

router.include_router(recommends_router)
router.include_router(hospital_router)