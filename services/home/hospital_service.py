from core.database import supabase

def get_sido_tree():
  response = supabase.table("regions").select("code, name").eq("level", 1).execute()
  return response.data