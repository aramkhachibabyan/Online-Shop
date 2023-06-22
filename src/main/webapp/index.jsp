<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/19/2023
  Time: 9:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shop</title>
</head>
<body>

<h1>Login</h1>

<%= request.getAttribute("message") == null ? "" : request.getAttribute("message")%>

<form method="post" action="/login">
    email: <input type="text" name="email"/><br><br>
    password: <input type="password" name="password"/><br><br>
    <input type="submit"/><br><br>
</form>
Don't have an account? <a href="http://localhost:8080/register.jsp">Sign up</a><br><br>

</body>
</html>