<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%><%@ taglib prefix="fn"
	uri="jakarta.tags.functions"%>

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

		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="newOrder.selected.items" /></th>
							</tr>
						</thead>
						<tbody>
							<!-- Iterate over the items in the order and display them in "Select items" table -->
							<c:forEach var="selectedItem" items="${order.items}">
								<tr>
									<td><img
										src="<c:url value='/images/${selectedItem.key.image}' />"
										alt="${selectedItem.key.name}" width="50" height="50">
										${selectedItem.key.name} x ${selectedItem.value} = ${selectedItem.value * selectedItem.key.price}€
										<br>
										<small>${item.description}</small></td>
									<!-- Decrease item button -->
									<td>
										<form action="/orders/users/orders/newOrder/decreaseItem"
											method="get">
											<input type="hidden" name="reference"
												value="${selectedItem.key.reference}" />
											<button type="submit" class="btn btn-warning">-</button>
										</form>
									</td>
									<!-- Increase item button -->
									<td>
										<form action="/orders/users/orders/newOrder/increaseItem"
											method="get">
											<input type="hidden" name="reference"
												value="${selectedItem.key.reference}" />
											<button type="submit" class="btn btn-success">+</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- Clear items button -->
					<form action="/orders/users/orders/newOrder/clearItems" method="get">
						<button type="submit" class="btn btn-danger"><spring:message
                                code="newOrder.clear" /></button>
					</form>
				</div>
				<div class="col-md-6 offset-md-3">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="newOrder.items" /></th>
							</tr>
						</thead>
						<tbody>
							<!-- Iterate over the available items and display them -->
							<c:forEach var="item" items="${items}">
								<tr>
									<td><img src="<c:url value='/images/${item.image}' />"
										alt="${item.name}" width="50" height="50"> ${item.name}<br>
										<small>${item.description}</small><br> <small>Price:
											${item.price}€</small></td>
									<!-- Increase item button -->
									<td>
										<form action="/orders/users/orders/newOrder/increaseItem"
											method="get">
											<input type="hidden" name="reference"
												value="${item.reference}" />
											<button type="submit" class="btn btn-success">+</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- Finish order button -->
					<form action="/orders/users/orders/newOrder/finishOrder"
						method="get">
						<button type="submit" class="btn btn-primary"><spring:message
                                code="newOrder.finish.order" /></button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
