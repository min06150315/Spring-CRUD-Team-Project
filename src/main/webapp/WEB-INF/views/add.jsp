<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<h1>Add New Post</h1>
<form action="<%= request.getContextPath() %>/problem/addok" method="post" enctype="application/x-www-form-urlencoded">
    <table>
        <tr>
            <td><label for="title">Title: </label></td>
            <td><input type="text" name="title" id="title" required /></td> <!-- name="title" 확인 -->
        </tr>
        <tr>
            <td><label for="description">Description: </label></td>
            <td><input type="text" name="description" id="description" required /></td>
        </tr>
        <tr>
            <td><label for="language">Language: </label></td>
            <td><input type="text" name="language" id="language" required /></td>
        </tr>
        <tr>
            <td><label for="filePath">FilePath: </label></td>
            <td><input type="text" name="filePath" id="filePath" required /></td>
        </tr>
        <tr>
            <td><label for="difficulty">Difficulty: </label></td>
            <td>
                <select name="difficulty" id="difficulty" required>
                    <option disabled selected value="">Select the Difficulty</option>
                    <option>Easy</option>
                    <option>Medium</option>
                    <option>Hard</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="writer">Writer: </label></td>
            <td><input type="text" name="writer" id="writer" required /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add Post" />
                <input type="button" value="Cancel" onclick="history.back()" />
            </td>
        </tr>
    </table>
</form>


</body>
</html>