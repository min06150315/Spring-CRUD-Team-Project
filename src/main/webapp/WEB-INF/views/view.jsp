<%--
  Created by IntelliJ IDEA.
  User: kangsan
  Date: 12/6/24
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>글 보기</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f9f9f9;
    }
    .container {
      width: 90%;
      max-width: 600px;
      margin: 50px auto;
      background: white;
      border-radius: 8px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      padding: 20px;
    }
    h1 {
      font-size: 1.5em;
      color: #333;
      margin-bottom: 20px;
    }
    .detail {
      margin-bottom: 15px;
    }
    .detail strong {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }
    .detail p {
      margin: 0;
      padding: 5px 0;
      background: #f0f0f0;
      border-radius: 4px;
      padding-left: 10px;
    }
    button {
      display: inline-block;
      margin-top: 20px;
      padding: 10px 15px;
      background-color: #4CAF50;
      color: white;
      font-size: 16px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    button:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>글 세부 정보</h1>
  <div id="details">
    <div class="detail">
      <strong>글 제목:</strong>
      <p id="view-title">제목이 표시됩니다</p>
    </div>
    <div class="detail">
      <strong>프로그래밍 언어:</strong>
      <p id="view-language">언어가 표시됩니다</p>
    </div>
    <div class="detail">
      <strong>난이도:</strong>
      <p id="view-difficulty">난이도가 표시됩니다</p>
    </div>
    <div class="detail">
      <strong>작성자:</strong>
      <p id="view-author">작성자가 표시됩니다</p>
    </div>
  </div>
  <button onclick="goBack()">돌아가기</button>
</div>

<script>
  // 입력 데이터를 가져와서 표시하는 함수
  document.addEventListener("DOMContentLoaded", () => {
    // 예제 데이터 (로컬 스토리지나 서버에서 가져온 데이터로 교체 가능)
    const postData = JSON.parse(localStorage.getItem("postData")) || {
      title: "입력된 제목이 없습니다",
      language: "입력된 언어가 없습니다",
      difficulty: "입력된 난이도가 없습니다",
      author: "입력된 작성자가 없습니다"
    };

    // HTML에 데이터 삽입
    document.getElementById("view-title").textContent = postData.title;
    document.getElementById("view-language").textContent = postData.language;
    document.getElementById("view-difficulty").textContent = postData.difficulty;
    document.getElementById("view-author").textContent = postData.author;
  });

  // 돌아가기 버튼 동작
  function goBack() {
    window.history.back();
  }
</script>
</body>
</html>
