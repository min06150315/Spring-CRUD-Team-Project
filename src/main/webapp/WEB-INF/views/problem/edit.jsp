<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Post</title>
</head>
<body>

<h1>Edit Post</h1>
<form modelAttribute="problemVO" action="${pageContext.request.contextPath}/problem/editok" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><label for="title">Title: </label></td>
            <td><input path="title" id="title" required="true"/></td>
        </tr>
        <tr>
            <td><label for="description">Description: </label></td>
            <td><input path="description" id="description" required="true"/></td>
        </tr>
        <tr>
            <td><label for="language">Language: </label></td>
            <td><input path="language" id="language" required="true"/></td>
        </tr>
        <tr>
            <td><label for="file">File Upload: </label></td>
            <td><input path="file" id="file" type="file"/></td>
        </tr>
        <tr>
            <td><label for="difficulty">Difficulty: </label></td>
            <td>
                <select path="difficulty" id="difficulty">
                    <option value="" label="Select the Difficulty" disabled="true" />
                    <option value="Easy" label="Easy"/>
                    <option value="Medium" label="Medium"/>
                    <option value="Hard" label="Hard"/>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Edit Post"/>
                <input type="button" value="Cancel" onclick="history.back()"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
