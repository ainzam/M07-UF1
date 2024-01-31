<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%><%@ taglib
	prefix="fn" uri="jakarta.tags.functions"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="sections/head.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="sections/header.jsp" />
		<form action="<c:url value= "/login"/>" method="post">

			<div class="container g-2">
				<div class="row justify-content-center overflow-hidden">
					<div class="col-sm-9 col-md-7 col-lg-5 col-xl-4 gy-5">

						<div class="card">

							<div class="card-header">
								<h5 class="card-title"><spring:message code="login.give.credentials" /></h5>
							</div>
							<div class="card-body">
								<c:if test="${not empty error}">
									<div class="alert alert-danger alert-dismissible" role="alert">
										<span><spring:message code="login.credentials.failure" /></span>
										<button type="button" class="btn-close"
											data-bs-dismiss="alert"></button>
									</div>
								</c:if>

								<div class="input-group py-1">
									<span class="input-group-text">
										<i class="bi bi-person-fill"></i>
									</span>
									<input class="form-control" placeholder="<spring:message code='login.username' />"
										name="username" type="text">
								</div>

								<div class="input-group py-1">
									<span class="input-group-text">
										<i class="bi bi-key-fill"></i>
									</span>
									<input class="form-control" placeholder="<spring:message code='login.password' />"
										name="password" type="password">
								</div>

							</div>

							<div class="card-footer d-grid">
								<input class="btn btn-secondary" type="submit" value="<spring:message code='login.login' />">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>