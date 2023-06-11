<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:import url="/resources/templates/CustomerHeader.jsp"/>

<div class="car-container">
    <h1><label for="search-input">Search Cars</label></h1>
    <input autofocus class="search-input-box" id="search-input" spellcheck="false"
           type="text" placeholder="Type anything to search">
    <table id="cars-table"></table>
</div>

<script src="<c:url value="/resources/static/js/CarBox.js"/>"></script>
<script src="<c:url value="/resources/static/js/Search.js"/>"></script>
<c:import url="/resources/templates/Footer.jsp"/>