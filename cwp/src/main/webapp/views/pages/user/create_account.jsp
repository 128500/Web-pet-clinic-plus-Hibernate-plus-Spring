<%--
  Created by IntelliJ IDEA.
  User: дом
  Date: 22.03.2017
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session ="true"%>
<html>

<head>
    <title>CREATING ACCOUNT</title>
    <link href="${pageContext.servletContext.contextPath}/views/css/createUserStyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/createUserScript.js"></script>
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
    <a href="${pageContext.servletContext.contextPath}/admin/view_users" id="refAddUser">&raquo; Back to viewing page</a>
    <a href="${pageContext.servletContext.contextPath}/views/pages/user/find_user.jsp" id="refFindUser">&raquo; Find user or pet</a>
</div>

<div id="content">
<h4>TO CREATE AN ACCOUNT PLEASE FILL THE FORM BELOW</h4>

<form:form modelAttribute="user" action="${pageContext.servletContext.contextPath}/user/add_user" method="POST">

    <div class="form_input">
        <form:label path="firstName">First name: </form:label>
        <form:input path="firstName"/>
    </div>

    <div class="form_input">
    <form:label path="lastName">Last name: </form:label>
    <form:input path = "lastName"/>
    </div>

    <div class="form_input">
        <form:label path="address">Address: </form:label>
        <form:input path="address"/>
    </div>

    <div class="form_input">
        <form:label path="phoneNumber">Phone number: </form:label>
        <form:input path="phoneNumber" />
    </div>

    <div class="form_input">
        <form:label path="pet.kind">Choose pet's kind: </form:label>
        <form:select  size='1' name = 'pet kind' path="pet.kind">
            <option disabled>Choose pet</option>
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
        <form:label path = 'pet.name'>Pet's name: </form:label>
        <form:input path = 'pet.name'/>
    </div>

    <div class="form_input">
        <form:label path="pet.age">Pet's age: </form:label>
        <form:input path="pet.age"/>
    </div>

    <form:hidden path="role" value="user"/>

    <div class="form_input">
        <input type = 'reset' value = 'Reset all fields' class="rounded" id="reset">
    </div>

    <div class="form_input">
        <input type = 'submit' name = 'add' value = 'Finish and submit' class="rounded" id="submit"/>
    </div>

</form:form>
</div>

<div id="footer">
    FOOTER
</div>

</body>
</html>
