<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Register</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="jumbotron container">
		<h1>Students</h1>
		<p>An Institut Marianao application</p>
	</div>
	<div class="container">
		<h1>New ${cycle} register</h1>
		<form action="register" method="POST">
			<div class="row">
				<div class="col-sm-6">
					<div
						class="form-group ${errorsMap['dni'] != null ? 'has-error' : ''}">
						<label for="dni">DNI:</label> <input type="text"
							class="form-control" id="dni" name="dni" value="${dni}" >
						<c:if test="${errorsMap['dni'] != null}">
							<span class="help-block">${errorsMap['dni']}</span>
						</c:if>
					</div>
					<div
						class="form-group ${errorsMap['name'] != null ? 'has-error' : ''}">
						<label for="name">Name:</label> <input type="text"
							class="form-control" id="name" name="name" value="${name}">
						<c:if test="${errorsMap['name'] != null}">
							<span class="help-block">${errorsMap['name']}</span>
						</c:if>
					</div>
					<div
						class="form-group ${errorsMap['surname'] != null ? 'has-error' : ''}">
						<label for="surname">Surname:</label> <input type="text"
							class="form-control" id="surname" name="surname"
							value="${surname}" >
						<c:if test="${errorsMap['surname'] != null}">
							<span class="help-block">${errorsMap['surname']}</span>
						</c:if>
					</div>
					<div
						class="form-group ${errorsMap['email'] != null ? 'has-error' : ''}">
						<label for="email">Email:</label> <input type="text"
							class="form-control" id="email" name="email" value="${email}">
						<c:if test="${errorsMap['email'] != null}">
							<span class="help-block">${errorsMap['email']}</span>
						</c:if>
					</div>

					<div class="form-group">
						<label for="cycle">Cycle:</label> <input type="text"
							class="form-control" id="cycle" name="cycle" value="${cycle}"
							readonly>
					</div>
				</div>
				<div class="col-sm-6">
					<div
						class="form-group ${errorsMap['modules'] != null ? 'has-error' : ''}">
						<label>Modules:</label>
						<c:forEach var="module" items="${modules}">
							<div class="checkbox">
								<label> <c:if
										test="${selectedModules != null && selectedModules.contains(module)}">
										<input type="checkbox" name="modules" value="${module}"
											checked>
									</c:if> <c:if
										test="${selectedModules == null || !selectedModules.contains(module)}">
										<input type="checkbox" name="modules" value="${module}">
									</c:if> ${module}
								</label>
							</div>
						</c:forEach>
						<c:if test="${errorsMap['modules'] != null}">
							<span class="help-block">${errorsMap['modules']}</span>
						</c:if>
					</div>
				</div>
			</div>
			<div class="form-group">
				<a class="btn btn-default" href="StudentsServlet">Cancel</a>
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>
