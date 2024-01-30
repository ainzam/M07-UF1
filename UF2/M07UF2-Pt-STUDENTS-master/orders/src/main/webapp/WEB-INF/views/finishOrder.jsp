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
		<%-- TODO finish order form with address fields --%>
		<form:form modelAttribute="order" action="/orders/users/orders/newOrder/finishOrder" method="post" class="row">
			<div class="col-md-6">
                <h2>Shipment Data</h2>

                <div class="form-group">
                    <label for="deliveryAddress.address">Address:</label>
                    <form:input path="deliveryAddress.address" id="deliveryAddress.address" class="form-control" />
                    <form:errors path="deliveryAddress.address" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="deliveryAddress.zipCode">Zip Code:</label>
                    <form:input path="deliveryAddress.zipCode" id="deliveryAddress.zipCode" class="form-control" />
                    <form:errors path="deliveryAddress.zipCode" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="deliveryAddress.city">City:</label>
                    <form:input path="deliveryAddress.city" id="deliveryAddress.city" class="form-control" />
                    <form:errors path="deliveryAddress.city" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="deliveryAddress.state">State/Province:</label>
                    <form:input path="deliveryAddress.state" id="deliveryAddress.state" class="form-control" />
                    <form:errors path="deliveryAddress.state" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="deliveryAddress.country">Country:</label>
                    <form:input path="deliveryAddress.country" id="deliveryAddress.country" class="form-control" />
                    <form:errors path="deliveryAddress.country" cssClass="error" element="div" />
                </div>
            </div>
			<div class="row">
				<div class="col-md-6">
					<h2>Items</h2>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>Reference</th>
								<th>Item Name</th>
								<th>Price</th>
								<th>Quantity</th>
								<th>Amount</th>
							</tr>
						</thead>
						<tbody>
							<!-- Iterate over items and display details -->
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
						<!-- Add a submit button for the form -->
						<button type="submit" class="btn btn-primary">Purchase</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
