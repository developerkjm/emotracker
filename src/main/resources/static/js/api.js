// frontend/js/api.js
const API_BASE = "http://localhost:8080/api";

async function fetchPosts() {
  const res = await fetch(`${API_BASE}/posts`);
  if (!res.ok) throw new Error("게시글을 불러오는 데 실패했어요!");
  return res.json();
}