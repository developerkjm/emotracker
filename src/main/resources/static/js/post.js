// frontend/js/post.js
document.addEventListener("DOMContentLoaded", async () => {
  const postList = document.getElementById("postList");

  try {
    const posts = await fetchPosts();
    if (posts.length === 0) {
      postList.innerHTML = "<p>등록된 게시글이 없어요 🥲</p>";
      return;
    }

    posts.forEach(post => {
      const div = document.createElement("div");
      div.innerHTML = `
        <h3>${post.title}</h3>
        <p>작성자: ${post.writer} | 작성일: ${new Date(post.createdAt).toLocaleString()}</p>
        <hr/>
      `;
      postList.appendChild(div);
    });
  } catch (err) {
    postList.innerHTML = `<p style="color: red;">오류 발생: ${err.message}</p>`;
  }
});
