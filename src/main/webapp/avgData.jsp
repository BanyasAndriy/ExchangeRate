<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.01.2020
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AVG</title>
</head>
<body>
<form method="get" action="/avg" >
<p>Input start date . Use format "dd.mm.YYYY" </p>
<input type="text" name="startDate">



<p>Input end date . Use format "dd.mm.YYYY" </p>
<input type="text" name="endDate">

<input type="submit">

  <p>Average dollar exchange rate by period :   </p>
    <c:out value = "${requestScope.avg}" default="null"/>


    <a href="index.html">Main menu</a>
</form>
</body>
</html>
