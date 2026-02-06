from core.database import supabase

def get_all_products():
  return supabase.table("products").select("*").execute()