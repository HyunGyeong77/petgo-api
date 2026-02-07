import os
from dotenv import load_dotenv

load_dotenv()

# DB 인증
supabase_url = os.getenv("SUPABASE_URL")
supabase_key = os.getenv("SUPABASE_KEY")

# CORS URL
cors_origins = os.getenv("CORS_ORIGINS").split(",")