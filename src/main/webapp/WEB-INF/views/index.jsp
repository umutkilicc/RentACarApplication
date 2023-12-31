<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="icon" type="image/png" href="<c:url value="/resources/static/img/logo.svg"/>"/>
    <link rel="preload" href="<c:url value="/resources/static/img/background.jpg"/>" as="image">
    <link href="<c:url value="/resources/static/css/style.css"/>" rel="stylesheet" type="text/css">
    <title>Burger Factory</title>
</head>

<body>

<header>
    <%--    <a class="logo" href=""><img src="<c:url value="/resources/static/img/nav-logo.svg"/>" alt="logo"></a>--%>
    <nav>
        <ul class="nav-links">
            <li><a href="https://tr.linkedin.com/">Linkedln</a></li>
            <li><a href="https://www.github.com">GitHub</a></li>
            <li><a href="./project-details">Project Details</a></li>
        </ul>
    </nav>
</header>

<div class="welcome" id="welcome">
    <img alt="logo" class="welcome-logo" src="<c:url value="/resources/static/img/logo.svg"/>">
    <div class="welcome-message">
        <h1 class="welcome-title">Eat and Order<br>Do It Easy</h1>
        <p class="margin-bottom">Choose your favourite cars and get it to taste the amazing taste, enjoy it with your
            family or friends.</p>
        <a class="button" href="./home">Get Started</a>
    </div>
</div>

<c:import url="/resources/templates/Footer.jsp"/>