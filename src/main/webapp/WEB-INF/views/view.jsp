<%--
  Created by IntelliJ IDEA.
  User: kangsan
  Date: 12/6/24
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
<!-- TODO: Make Code Preview Feature -->
<body>
<div class="container">
  <h1>글 세부 정보</h1>
  <div id="details">
    <div class="detail">
      <strong>글 제목:</strong>
      <p id="view-title">${problemVO.title}</p>
    </div>
    <div class="detail">
      <strong>프로그래밍 언어:</strong>
      <p id="view-language">${problemVO.language}</p>
    </div>
    <div class="detail">
      <strong>난이도:</strong>
      <p id="view-difficulty">${problemVO.difficulty}</p>
    </div>
    <div class="detail">
      <strong>작성자:</strong>
      <p id="view-author">${problemVO.writer}</p>
    </div>
  </div>
  <button onclick="returnToList()">Return to list</button>
</div>
<script>
  function returnToList() {
    location.href = '../list';
  }
</script>
</body>
</html>
