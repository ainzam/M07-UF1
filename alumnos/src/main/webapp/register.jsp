<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Students</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body>
    <div class="container mt-4 p-5 bg-primary text-white rounded">
        <h1>Students</h1>
        <p>An Institut Marianao application</p>
    </div>
	<div class="container">
		<h1>New ${cycle} register</h1>
		<form action="register" method="POST">
			<div class="row">
				<div class="col-sm-6">
					<div
						class="mb-3">
						<label for="dni">DNI:</label> <input type="text"
							class="form-control ${errorsMap['dni'] != null ? 'is-invalid' : ''}" id="dni" name="dni" value="${dni}">
						<c:if test="${errorsMap['dni'] != null}">
							<span class="invalid-feedback">${errorsMap['dni']}</span>
						</c:if>
					</div>
					<div
						class="mb-3 ">
						<label for="name">Name:</label> <input type="text"
							class="form-control ${errorsMap['name'] != null ? 'is-invalid' : ''}" id="name" name="name" value="${name}">
						<c:if test="${errorsMap['name'] != null}">
							<span class="invalid-feedback">${errorsMap['name']}</span>
						</c:if>
					</div>
					<div
						class="mb-3 ">
						<label for="surname">Surname:</label> <input type="text"
							class="form-control ${errorsMap['surname'] != null ? 'is-invalid' : ''}" id="surname" name="surname"
							value="${surname}">
						<c:if test="${errorsMap['surname'] != null}">
							<span class="invalid-feedback">${errorsMap['surname']}</span>
						</c:if>
					</div>
					<div
						class="mb-3 ">
						<label for="email">Email:</label> <input type="text"
							class="form-control ${errorsMap['email'] != null ? 'is-invalid' : ''}" id="email" name="email" value="${email}">
						<c:if test="${errorsMap['email'] != null}">
							<span class="invalid-feedback">${errorsMap['email']}</span>
						</c:if>
					</div>

					<div class="mb-3">
						<label for="cycle">Cycle:</label> <input type="text"
							class="form-control" id="cycle" name="cycle" value="${cycle}"
							readonly>
					</div>
				</div>
				<div class="col-sm-6">
					<div
						class="mb-3 ${errorsMap['modules'] != null ? 'is-invalid' : ''}">
						<label>Modules:</label>
						<c:forEach var="module" items="${modules}">
							<div class="checkbox">
								<label> <c:if
										test="${selectedModules != null && selectedModules.contains(module)}">
										<input type="checkbox" class="form-check-input" name="modules" value="${module}"
											checked>
									</c:if> <c:if
										test="${selectedModules == null || !selectedModules.contains(module)}">
										<input type="checkbox" name="modules" value="${module}">
									</c:if> ${module}
								</label>
							</div>
						</c:forEach>
						<c:if test="${errorsMap['modules'] != null}">
							<span class="" style="color: red;">${errorsMap['modules']}</span>
						</c:if>
					</div>
				</div>
			</div>
			<div class="form-group">
				<a class="btn btn-default" href="students">Cancel</a>
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>
