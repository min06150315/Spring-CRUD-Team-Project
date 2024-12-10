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
    <input type="hidden" name="id" value="${problemVO.id}" />
    <table>
        <tr>
            <td><label for="title">Title: </label></td>
            <td><input type="text" id="title" name="title" value="${problemVO.title}" required /></td>
        </tr>
        <tr>
            <td><label for="description">Description: </label></td>
            <td><input type="text" id="description" name="description" value="${problemVO.description}" required /></td>
        </tr>
        <tr>
            <td><label for="language">Language: </label></td>
            <td><input type="text" id="language" name="language" value="${problemVO.language}" required /></td>
        </tr>
        <tr>
            <td><label for="file">File Upload: </label></td>
            <td><input type="file" id="file" name="file" required /></td>
        </tr>
        <tr>
            <td><label for="difficulty">Difficulty: </label></td>
            <td>
                <select id="difficulty" name="difficulty" required>
                    <option disabled>Select the Difficulty</option>
                    <option value="Easy" ${problemVO.difficulty == 'Easy' ? 'selected' : ''}>Easy</option>
                    <option value="Medium" ${problemVO.difficulty == 'Medium' ? 'selected' : ''}>Medium</option>
                    <option value="Hard" ${problemVO.difficulty == 'Hard' ? 'selected' : ''}>Hard</option>
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
