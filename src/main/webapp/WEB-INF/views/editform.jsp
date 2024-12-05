<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Form</title>
</head>
<body>
<h1>Edit Form</h1>
<form:form modelAttribute="problemVO" action="../editok" method="post">
    <form:hidden path="id"/>
    <table>
        <tr><td>Title:</td><td><form:input path="title"/></td></tr>
        <tr><td>Description:</td><td><form:input path="description"/></td></tr>
        <tr><td>Language:</td><td><form:input path="language"/></td></tr>
        <tr><td>Difficulty:</td><td><form:input path="difficulty"/></td></tr>

        <tr>
            <td colspan="2"><input type="submit" value="Edit Post"/>
                <input type="button" value="Cancel" onclick="history.back()"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>