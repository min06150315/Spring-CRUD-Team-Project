<%--
  Created by IntelliJ IDEA.
  User: kangsan
  Date: 12/11/24
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ include file="../layout/header.jsp" %>--%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" />
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="./loginOK" method="POST">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Enter your username" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required>

        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
<%--<%@ include file="../layout/footer.jsp" %>--%>
