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
	<%-- TODO if user has ROLE_ADMIN -> Show all orders of all users and let change state and delivery date --%>
	<%-- TODO if user has ROLE_USER -> Show all orders of the user --%>
	<div class="container">
		<jsp:include page="sections/header.jsp" />

		<%-- Use Spring Security tag library to check user roles --%>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<!-- Display orders for admin role -->
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Reference</th>
						<th>Delivery Address</th>
						<th>Order Date</th>
						<th>Delivery Date</th>
						<th>Change State</th>
						<th>Change Delivery Date</th>
						<th>Details</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.reference}</td>
							<td>${order.client.username}</td>
							<td><fmt:formatDate value="${order.startDate}"
									pattern="MMM d, yyyy" /></td>
							<td><fmt:formatDate value="${order.deliveryDate}"
									pattern="MMM d, yyyy" /></td>
							<td>
								<form action="/orders/admin/orders/setState" method="get">
									<input type="hidden" name="reference"
										value="${order.reference}" /> <select name="state"
										onchange="$(this).closest('form').submit();">
										<c:forEach var="state" items="${states}" varStatus="loop">
											<option value="${loop.index}"
												${order.state eq loop.index ? 'selected' : ''}>
												<c:choose>
													<c:when test="${loop.index eq 0}">Pending</c:when>
													<c:when test="${loop.index eq 1}">In Transit</c:when>
													<c:when test="${loop.index eq 2}">Delivered</c:when>
													<c:when test="${loop.index eq 3}">Absent</c:when>
													<c:when test="${loop.index eq 4}">Pending Collection</c:when>
													<c:when test="${loop.index eq 5}">Returned</c:when>
													<c:otherwise>Unknown</c:otherwise>
												</c:choose>
											</option>
										</c:forEach>
									</select>
								</form>
							</td>
							<td>
								<form action="/orders/admin/orders/setDeliveryDate" method="get">
									<input type="hidden" name="reference"
										value="${order.reference}" /> <input type="date"
										name="deliveryDate" value="${order.deliveryDate}"
										onchange="$(this).closest('form').submit();" />
								</form>
							</td>
							<td>
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>Reference</th>
											<th>Item</th>
											<th>Price</th>
											<th>Quantity</th>
											<th>Amount</th>
										</tr>
									</thead>
									<tbody>
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
								<p>
									<strong>Total Quantity:</strong> ${order.totalQuantity}
								</p>
								<p>
									<strong>Total Amount:</strong>
									<fmt:formatNumber value="${order.totalAmount}" type="number"
										maxFractionDigits="2" />
									€
								</p>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</sec:authorize>

		<sec:authorize access="hasRole('ROLE_USER')">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Reference</th>
						<th>Delivery Address</th>
						<th>Order Date</th>
						<th>State</th>
						<th>Delivery Date</th>
						<th>Details</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.reference}</td>
							<td>${order.deliveryAddress}</td>
							<td><fmt:formatDate value="${order.startDate}"
									pattern="MMM d, yyyy" /></td>
							<td><c:choose>
									<c:when test="${order.state eq 0}">Pending</c:when>
									<c:when test="${order.state eq 1}">In Transit</c:when>
									<c:when test="${order.state eq 2}">Delivered</c:when>
									<c:when test="${order.state eq 3}">Absent</c:when>
									<c:when test="${order.state eq 4}">Pending Collection</c:when>
									<c:when test="${order.state eq 5}">Returned</c:when>
									<c:otherwise>Unknown</c:otherwise>
								</c:choose></td>
							<td><fmt:formatDate value="${order.deliveryDate}"
									pattern="MMM d, yyyy" /></td>
							<td>
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>Reference</th>
											<th>Item</th>
											<th>Price</th>
											<th>Quantity</th>
											<th>Amount</th>
										</tr>
									</thead>
									<tbody>
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
								<p>
									<strong>Total Quantity:</strong> ${order.totalQuantity}
								</p>
								<p>
									<strong>Total Amount:</strong>
									<fmt:formatNumber value="${order.totalAmount}" type="number"
										maxFractionDigits="2" />
									€
								</p>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</sec:authorize>
	</div>
</body>
</html>