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
    <title>SUCCSESS IN ADDING PHOTO</title>
    <link href="${pageContext.servletContext.contextPath}/views/css/failedResultOfSearchingStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>${message}</h2>
<br>
<br>
<div>
    <h2>
        <a href="${pageContext.servletContext.contextPath}/admin/view_users">Back to viewing page</a>
    </h2>
</div>
</body>
</html>
