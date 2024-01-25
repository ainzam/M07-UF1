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
			<table>
				<thead>
					<tr>
						<th>Reference</th>
						<th>Client</th>
						<th>State</th>
						<th>Delivery Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.reference}</td>
							<td>${order.client.username}</td>
							<td>
								<form action="/admin/setState" method="get">
									<input type="hidden" name="reference"
										value="${order.reference}" /> <select name="state"
										onchange="$(this).closest('form').submit();">
										<c:forEach var="state" items="${states}">
											<option value="${state}"
												${order.state eq state ? 'selected' : ''}>${state}</option>
										</c:forEach>
									</select>
								</form>
							</td>
							<td>
								<form action="/admin/setDeliveryDate" method="get">
									<input type="hidden" name="reference"
										value="${order.reference}" /> <input type="date"
										name="deliveryDate" value="${order.deliveryDate}"
										onchange="$(this).closest('form').submit();" />
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</sec:authorize>

		<sec:authorize access="hasRole('ROLE_USER')">
			<!-- Display orders for user role -->
			<table>
				<thead>
					<tr>
						<th>Reference</th>
						<th>State</th>
						<th>Delivery Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.reference}</td>
							<td>${order.state}</td>
							<td>${order.deliveryDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</sec:authorize>
	</div>
</body>
</html>