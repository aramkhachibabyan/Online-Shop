<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/20/2023
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Product</title>
</head>
<body>
<a style="display: flex;justify-content: right" href="http://localhost:8080/secure/logout">Log out</a><br><br>
<%= request.getAttribute("message") == null ? "" : request.getAttribute("message")%>
<%= request.getAttribute("id") == null ? "" : "Product with id " + request.getAttribute("id") + " deleted"%>

<form method="post" action="/secure/deleteProduct">

    id: <input type="number" name="id"/><br><br>
    <input type="submit"/><br><br>

    <a href="http://localhost:8080//secure/product.jsp">Product Page</a><br><br>

</form>


</body>
</html>