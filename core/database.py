from supabase import create_client
from core.config import supabase_url, supabase_key

supabase = create_client(supabase_url, supabase_key)

