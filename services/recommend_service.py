from core.database import supabase

def get_category_tree():
  response = supabase.table("category_tree").select("result").single().execute()
  return response.data['result']