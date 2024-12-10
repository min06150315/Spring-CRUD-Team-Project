<%@ page language="java" contentType="text/html; charset=UTF-8"
         isELIgnored="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../layout/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
  <title>CodeShareHub</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script>
    function delete_ok(id){
      var a = confirm("정말로 삭제하겠습니까?");
      if(a) location.href='./delete/' + id;
    }
  </script>
</head>
<body class="bg-light">
<div class="container my-4">

  <table class="table table-bordered table-striped text-center">
    <thead class="table-primary">
    <tr>
      <th scope="col">No</th>
      <th scope="col">Title</th>
      <th scope="col">Language</th>
      <th scope="col">Difficulty</th>
      <th scope="col">Writer</th>
      <th scope="col">Created Date</th>
      <th scope="col">Edit</th>
      <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="u" varStatus="status">
      <tr>
        <td>${totalcnt-status.index}</td>
        <td><a href="./view/${u.id}">${u.title}</a></td>
        <td>${u.language}</td>
        <td>${u.difficulty}</td>
        <td>${u.writer}</td>
        <td>${u.created_at}</td>
        <td><a href="edit/${u.id}" class="btn btn-warning btn-sm">Edit</a></td>
        <td><button class="btn btn-danger btn-sm" onclick="delete_ok('${u.id}')">Delete</button></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <div class="text-end">
    <a href="./add" class="btn btn-success">Add New Post</a>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="../layout/footer.jsp" %>
