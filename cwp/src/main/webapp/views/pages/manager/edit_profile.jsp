<%--
  Created by IntelliJ IDEA.
  User: дом
  Date: 22.03.2017
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session ="true"%>
<html>
<head>
    <title>Edit client's data</title>
    <link href="${pageContext.servletContext.contextPath}/views/css/editUserStyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/views/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/views/js/viewUserScript.js"></script>
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
    <a href="${pageContext.servletContext.contextPath}/admin/view_users">&raquo; Back to the start page</a>
    <a href="${pageContext.servletContext.contextPath}/user/create_account" id="refAddUser">&raquo; Add user into the database</a>
</div>

<div id="content">
    <div id="pet_image">
        <img src="${pageContext.servletContext.contextPath}/user/load_photo/${user.getPet().getId()}.jpeg" alt="Sorry, there is no photo yet" id="pet_photo">
    </div>
<form:form modelAttribute="user" action="${pageContext.servletContext.contextPath}/user/edit_profile" method="POST">

        <form:input type="hidden" path="id" value="${user.getId()}"/>
        <form:input type="hidden" path="role.name" value="${user.getRole().getName()}"/>

    <div class="form_input">
        <form:label path="firstName">First name: </form:label>
        <form:input type="text" path="firstName" value="${user.getFirstName()}"/>
    </div>

    <div class="form_input">
        <form:label path="lastName">Last name:</form:label>
        <form:input  type ="text" path = "lastName" value="${user.getLastName()}"/>
    </div>

    <div class="form_input">
        <form:label path="address">Address:</form:label>
        <form:input  type="text" path="address" value="${user.getAddress()}"/>
    </div>

    <div class="form_input">
        <form:label path="phoneNumber">Phone number:</form:label>
        <form:input  type="number" path="phoneNumber" value="${user.getPhoneNumber()}"/>
    </div>

    <div class="form_input">
        <label>Choose pet's kind: </label>
        <form:select  size='1' name = 'pet kind' path="pet.kind">
            <option disabled>Choose pet</option>
            <option value="${user.getPet().getKind()}">${user.getPet().getKind()}</option>
            <option value='pet'>pet</option>
            <option value='bunny'>bunny</option>
            <option value='canary'>canary</option>
            <option value='cat'>cat</option>
            <option value='cavy'>cavy</option><%--морская свинка--%>
            <option value='dog'>dog</option>
            <option value='ferret'>ferret</option>
            <option value='fish'>fish</option>
            <option value='frog'>frog</option>
            <option value='hamster'>hamster</option>
            <option value='iguana'>iguana</option>
            <option value='parrot'>parrot</option>
            <option value='rat'>rat</option>
            <option value='turtle'>turtle</option>
        </form:select>
    </div>

    <div class="form_input">
        <form:label path="pet.name">Pet's name:</form:label>
        <form:input type = 'text' path="pet.name" value="${user.getPet().getName()}"/>
    </div>

    <div class="form_input">
        <form:label path="pet.age">Pet's age: </form:label>
        <form:input type="number" path="pet.age" value="${user.getPet().getAge()}"/>
    </div>

    <div class="form_input">
        <input type = 'reset' value = 'Reset all fields' class="rounded" id="reset">
    </div>

    <div class="form_input">
        <input type = 'submit' name = 'add' value = 'Finish and submit' class="rounded" id="submit"/>
    </div>

</form:form>
</div>

<div id="footer">FOOTER</div>

</body>
</html>