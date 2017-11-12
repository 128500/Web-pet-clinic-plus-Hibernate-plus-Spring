<%--
  Created by IntelliJ IDEA.
  User: ALEKSANDR KUDIN
  Date: 22.03.2017
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session ="true"%>
<html>
<head>
    <title>RESULTS OF SEARCHING</title>
    <link href="${pageContext.servletContext.contextPath}/views/css/resultOfSearchingUsersStyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/views/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/views/js/sendMessageScript.js"></script>
</head>
<body>
<div id="header">
    <img src="${pageContext.servletContext.contextPath}/views/images/logo.png" alt="OMG" id="logo_img">
    <div id="header_sign">
        <div id="purple">WEB</div>
        <div id="gray">clinic</div>
    </div>
</div>

<div id="sidebar">
    <a href="${pageContext.servletContext.contextPath}/user/create_account">&raquo; Add user into the database</a>
    <a href="${pageContext.servletContext.contextPath}/views/pages/admin/find_user.jsp">&raquo; Find user or pet</a>
    <a href="${pageContext.servletContext.contextPath}/admin/view_users">&raquo; Return to viewing page</a>
</div>

<div id="content">
<table>
    <tr id="usersTableHeader">
        <th>ID number</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Pet's kind</th>
        <th>Pet's name</th>
        <th>Pet's age</th>
        <th>Options</th>
    </tr>
    <c:forEach items="${foundUsers}" var="user" varStatus="status">
        <tr vlign="top">
            <td>${user.getId()}</td>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
            <td>${user.getAddress()}</td>
            <td>${user.getPhoneNumber()}</td>
            <td>${user.getPet().getKind()}</td>
            <td>${user.getPet().getName()}</td>
            <td>${user.getPet().getAge()}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/user/add_photo/${user.getId()}" title="Add photo of the pet" class="hovertip"><img src="${pageContext.servletContext.contextPath}/views/images/add.png" alt=""></a>
                <a href="${pageContext.servletContext.contextPath}/user/edit_profile/${user.getId()}" title="Edit profile" class="hovertip"><img src="${pageContext.servletContext.contextPath}/views/images/edit.png" alt=""></a>
                <a href="${pageContext.servletContext.contextPath}/user/delete/${user.getId()}" title="Delete profile" class="hovertip"><img src="${pageContext.servletContext.contextPath}/views/images/delete.png" alt=""></a>
                <a href="${pageContext.servletContext.contextPath}/user/view_messages/${user.getId()}" title="View user messages" class="hovertip"><img src="${pageContext.servletContext.contextPath}/views/images/edit.png" alt=""></a>
                <a href="${pageContext.servletContext.contextPath}/user/send_message/${user.getId()}" title="Send message" class="hovertip"><img src="${pageContext.servletContext.contextPath}/views/images/edit.png" alt=""></a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>

<diV id="footer">FOOTER</diV>
</body>
</html>
