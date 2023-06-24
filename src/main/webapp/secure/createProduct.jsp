<%@ page import="am.smartCode.shop.util.constants.Keyword" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/20/2023
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Create Product</title>
</head>
<body>
<a style="display: flex;justify-content: right" href="http://localhost:8080/secure/logout">Log out</a><br><br>
<%= request.getAttribute(Keyword.MESSAGE) == null ? "" : request.getAttribute(Keyword.MESSAGE)%>
<%= request.getAttribute(Keyword.NAME) == null ? "" : "Product " + request.getAttribute(Keyword.NAME) + " created"%>

<form method="post" action="/secure/createProduct">

  Category: <input type="text" name="category"/><br><br>
  Name: <input type="text" name="name"/><br><br>
  Published year: <input type="text" name="publishedYear"/><br><br>
  Price: <input type="number" name="price"/><br><br>
  <input type="submit"/><br><br>

  <a href="http://localhost:8080/secure/product.jsp">Product Page</a><br><br>

</form>
</body>
</html>