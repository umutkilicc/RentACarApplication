<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:import url="/resources/templates/CustomerHeader.jsp"/>

<div class="car-container">
    <h1>Order Your Favourite Cars!</h1>
    <h2 class="category-title" id="category-title">
        Category:
        <label onclick="fetchCars('Burger')" id="Mercedes">Burger</label>
        <label onclick="fetchCars('Pizza')" id="Bmw">Pizza</label>
        <label onclick="fetchCars('Drinks')" id="Fiat">Drinks</label>
        <label onclick="fetchCars('Coffee')" id="Toyota">Coffee</label>
        <label onclick="fetchCars('Desert')" id="Seat">Desert</label>
        <label onclick="fetchCars('Sides')" id="Ferrari">Sides</label>
    </h2>
    <table id="cars-table"></table>
</div>

<script src="<c:url value="/resources/static/js/CarBox.js"/>"></script>
<script src="<c:url value="/resources/static/js/Home.js"/>"></script>
<c:import url="/resources/templates/Footer.jsp"/>