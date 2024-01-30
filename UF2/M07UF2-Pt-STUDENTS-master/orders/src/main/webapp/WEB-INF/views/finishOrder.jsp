<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="sections/head.jsp" />
</head>
<body>
    <div class="container">
        <jsp:include page="sections/header.jsp" />

        <form:form modelAttribute="order"
            action="/orders/users/orders/newOrder/finishOrder" method="post"
            class="row">
            <div class="col-md-6">
                <h2><spring:message code="finishOrder.shipment.data" /></h2>

                <div class="form-group">
                    <label for="recipientName"><spring:message code="finishOrder.recipient" /></label>
                    <form:input path="deliveryAddress.recipientName" id="recipientName"
                        class="form-control" />
                    <form:errors path="deliveryAddress.recipientName" cssClass="error" element="div" />
                </div>
                
                <div class="form-group">
                    <label for="deliveryAddress.address"><spring:message code="finishOrder.address" /></label>
                    <form:input path="deliveryAddress.address"
                        id="deliveryAddress.address" class="form-control" />
                    <form:errors path="deliveryAddress.address" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="deliveryAddress.zipCode"><spring:message code="finishOrder.zip.code" /></label>
                    <form:input path="deliveryAddress.zipCode"
                        id="deliveryAddress.zipCode" class="form-control" />
                    <form:errors path="deliveryAddress.zipCode" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="deliveryAddress.city"><spring:message code="finishOrder.city" /></label>
                    <form:input path="deliveryAddress.city" id="deliveryAddress.city"
                        class="form-control" />
                    <form:errors path="deliveryAddress.city" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="deliveryAddress.state"><spring:message code="finishOrder.state.province" /></label>
                    <form:input path="deliveryAddress.state" id="deliveryAddress.state"
                        class="form-control" />
                    <form:errors path="deliveryAddress.state" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="deliveryAddress.country"><spring:message code="finishOrder.country" /></label>
                    <form:input path="deliveryAddress.country"
                        id="deliveryAddress.country" class="form-control" />
                    <form:errors path="deliveryAddress.country" cssClass="error" element="div" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <h2><spring:message code="finishOrder.items" /></h2>
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th><spring:message code="finishOrder.reference" /></th>
                                <th><spring:message code="finishOrder.item" /></th>
                                <th><spring:message code="finishOrder.price" /></th>
                                <th><spring:message code="finishOrder.quantity" /></th>
                                <th><spring:message code="finishOrder.amount" /></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Itera sobre los elementos y muestra los detalles -->
                            <c:forEach var="item" items="${order.items}">
                                <tr>
                                    <td>${item.key.reference}</td>
                                    <td>${item.key.name}</td>
                                    <td>${item.key.price}€</td>
                                    <td>${item.value}</td>
                                    <td>${item.value * item.key.price}€</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <!-- Agrega un botón de envío para el formulario -->
                        <button type="submit" class="btn btn-primary">
                            <spring:message code="finishOrder.purchase" />
                        </button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</body>
</html>
