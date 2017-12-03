<%--
  Created by IntelliJ IDEA.
  User: homeuser
  Date: 27.04.2017
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>ADMIN HOMEPAGE</title>
    <link href="${pageContext.servletContext.contextPath}/views/css/failedResultOfSearchingStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>WELCOME ADMIN!</h2>
<br>
<br>
<div>
    <h2>
        <a href="${pageContext.servletContext.contextPath}/views/pages/admin/find_user.jsp">FIND USER</a>
        <br/>
        <br/>
        <a href="${pageContext.servletContext.contextPath}/admin/view_users">VIEW ALL USERS</a>
        <br/>
        <br/>
        <a href="${pageContext.servletContext.contextPath}/logout">LOGOUT</a>
    </h2>
</div>
</body>
</html>
