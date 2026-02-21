from core.database import supabase

def get_sido_tree():
  response = supabase.table("regions").select("code, name").eq("level", 1).execute()
  return response.data

def get_region_tree(level: str, code: str):
  result = supabase.rpc(
    "get_region_tree", 
    {"region_level": int(level), "region_code": code}
  ).execute()
  return result.data