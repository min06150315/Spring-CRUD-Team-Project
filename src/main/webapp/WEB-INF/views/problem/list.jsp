<%@ page language="java" contentType="text/html; charset=UTF-8"
         isELIgnored="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../layout/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>CodeShareHub</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet" />

  <script>
    function delete_ok(id) {
      var a = confirm("정말로 삭제하겠습니까?");
      if (a) location.href = './delete/' + id;
    }
  </script>
</head>
<body>
<div class="login">
  <a href="../login">Login</a>
  <a href="../logout">Logout</a>
  <p>Logined User: <span class="username-highlight">${userInfo.username}</span></p>
</div>

<div class="container my-4">
  <!-- Search Box -->
  <div class="search-box">
    <form method="get" action="${pageContext.request.contextPath}/problem/list">
      <input type="text" name="searchKeyword" placeholder="Search by Title" value="${param.searchKeyword}" />
      <select name="sorting">
        <option value="id">ID</option>
        <option value="title">Name</option>
        <option value="difficulty">Difficulty</option>
      </select>
      <button type="submit">Search</button>
    </form>
  </div>

  <!-- Data Table -->
  <table class="table table-bordered table-striped text-center">
    <thead class="table-primary">
    <tr>
      <th scope="col">No</th>
      <th scope="col">Title</th>
      <th scope="col">Language</th>
      <th scope="col">Difficulty</th>
      <th scope="col">Writer</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="u" varStatus="status">
      <tr>
        <!-- ID를 오름차순으로 표시 -->
        <td>${status.index + 1}</td>
        <td><a href="./view/${u.id}" class="text-decoration-none text-primary">${u.title}</a></td>
        <td>${u.language}</td>
        <td>${u.difficulty}</td>
        <td>${u.writer}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <!-- Add New Post Button -->
  <div class="text-end mt-3">
    <a href="./add" class="btn btn-success">Add New Post</a>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
<%@ include file="../layout/footer.jsp" %>
