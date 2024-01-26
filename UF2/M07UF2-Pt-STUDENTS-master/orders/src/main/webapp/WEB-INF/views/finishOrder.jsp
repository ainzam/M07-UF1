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
		<form action="/orders/users/orders/newOrder/finishOrder" method="post">
			<div class="row">
				<div class="col-md-6">
					<h2>Client Data</h2>
					<p>
						<strong>First name:</strong> ${order.client.firstName}
					</p>
					<p>
						<strong>Last name:</strong> ${order.client.lastName}
					</p>

					<h2>Shipment Data</h2>
					<!-- Add input fields for address, zip code, city, state/province, and country -->
					<label for="address">Address:</label><br>
                    <input type="text" id="address" name="address" required>

                    <label for="zipCode">Zip Code:</label><br>
                    <input type="text" id="zipCode" name="zipCode" required>

                    <label for="city">City:</label><br>
                    <input type="text" id="city" name="city" required>

                    <label for="state">State/Province:</label><br>
                    <input type="text" id="state" name="state" required>

                    <label for="country">Country:</label><br>
                    <input type="text" id="country" name="country" required>
					<!-- Add other address fields as needed -->

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
									<td>${item.reference}</td>
									<td>${item.name}</td>
									<td>${item.price}</td>
									<td>${item.quantity}</td>
									<td>${item.amount}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<!-- Add a submit button for the form -->
					<button type="submit" class="btn btn-primary">Purchase</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
