<%--
  Created by IntelliJ IDEA.
  User: kangsan
  Date: 12/6/24
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="${pageContext.request.contextPath}/css/view.css" rel="stylesheet" />
  <!-- Prism.js CSS -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/themes/prism.css" rel="stylesheet" />
  <title>View Page</title>
</head>
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

  <!-- Source Code Preview -->
  <div class="code-block">
    <h2>소스 코드</h2>
    <pre><code id="code" class="language-${problemVO.language.toLowerCase()}">${codeContent}</code></pre>

  </div>
  <button onclick="returnToList()">Return to list</button>
  <button id="copy-button" class="btn btn-primary mt-2" onclick="copyToClipboard()">Copy Code</button>
  <button id="run-button" onclick="redirectToCompilerSite()">Run Code!</button>
  <a href="../edit/${problemVO.id}" class="button edit">Edit</a>
  <a href="../delete/${problemVO.id}" class="button delete">Delete</a>
</div>

<!-- Prism.js JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/prism.min.js"></script>
<!-- Language Plugins -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/components/prism-java.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/components/prism-c.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/components/prism-cpp.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/components/prism-python.min.js"></script>
<script>
  function returnToList() {
    location.href = '../list';
  }

  function redirectToCompilerSite() {
    websiteURL = '${url}';
    copyToClipboard();
    window.open(websiteURL, '_blank');
  }

  function copyToClipboard() {
    try {
      const text = document.getElementById("code").innerText; // 소스 코드 텍스트 가져오기
      navigator.clipboard.writeText(text).then(() => {
        alert("코드가 클립보드에 복사되었습니다!");
        console.log("Code copied to clipboard!");
      }).catch(err => {
        alert("복사 실패: 브라우저가 복사 기능을 지원하지 않습니다. (" + err.message + ")");
        console.error("Clipboard error:", err);
      });
    } catch (e) {
      alert("복사 실패: 예기치 않은 오류가 발생했습니다. (" + e.message + ")");
      console.error("Unexpected error:", e);
    }
  }
</script>
</body>
</html>
