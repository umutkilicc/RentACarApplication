<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<c:import url="/resources/templates/AdminHeader.jsp"/>

<div class="add-user-form">
    <form:form id="add-car-form" method="POST" action="add-car-action" modelAttribute="car">
        <h2 class="admin-form-title">Add CarCars</h2>
        <fieldset>
            <div>
                <div>
                    <label class="form-label" for="category">Category</label>
                    <form:select class="form-input-box" id="category" path="category">
                        <form:option value="Burger">Burger</form:option>
                        <form:option value="Pizza">Pizza</form:option>
                        <form:option value="Salad">Salad</form:option>
                        <form:option value="Pasta">Pasta</form:option>
                        <form:option value="Drinks">Drinks</form:option>
                        <form:option value="Coffee">Coffee</form:option>
                        <form:option value="Desert">Desert</form:option>
                        <form:option value="Sides">Sides</form:option>
                    </form:select>
                </div>
                <div>
                    <label class="form-label" for="car-name">Car Name</label>
                    <form:input class="form-input-box" id="car-name" maxlength="30"
                                placeholder="Enter car name here" type="text" path="title"/>
                </div>
                <div>
                    <label class="form-label" for="price">Price</label>
                    <form:input class="form-input-box" id="price" maxlength="5"
                                placeholder="Enter price here" type="text" path="price"/>
                </div>
                <div>
                    <label class="form-label" for="description">Description</label>
                    <form:textarea id="description" name="description" class="car-description"
                                   placeholder="Enter car description here" rows="4" path="description"/>
                </div>
            </div>
        </fieldset>
        <p id="admin-prompt"></p>
        <div class="center">
            <input id="form-submit" type="submit" class="button" value="Add Car">
        </div>
    </form:form>
</div>

<script src="<c:url value="/resources/static/js/Logic.js"/>"></script>
<script src="<c:url value="/resources/static/js/AddCar.js"/>"></script>
<c:import url="/resources/templates/Footer.jsp"/>