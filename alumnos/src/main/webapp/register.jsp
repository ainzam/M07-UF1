<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Students</title>
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

	<div class="jumbotron text-center">
		<h1>Students</h1>
		<p>An Institut Marianao application</p>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<h2>${cycle}</h2>
			</div>
			<div class="col-sm-6">
				<h1>Register</h1>
				<form action="register" method="POST">
					<div class="form-group">
						<label for="dni">DNI:</label> <input type="text"
							class="form-control" id="dni" name="dni" required>
					</div>
					<div class="form-group">
						<label for="name">Name:</label> <input type="text"
							class="form-control" id="name" name="name" required>
					</div>
					<div class="form-group">
						<label for="surname">Surname:</label> <input type="text"
							class="form-control" id="surname" name="surname" required>
					</div>
					<div class="form-group">
						<label for="email">Email:</label> <input type="email"
							class="form-control" id="email" name="email">
					</div>
					<div class="form-group">
						<label for="cycle">Cycle:</label> <input type="text"
							class="form-control" id="cycle" name="cycle" value="${cycle}"
							readonly>
					</div>
					<div class="form-group">
						<label for="modules">Modules:</label> <select class="form-control"
							id="modules" name="modules" multiple required>
							<c:forEach var="module" items="${modules}">
								<option value="${module}">${module}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<a class="btn btn-default" href="StudentsServlet">Cancel</a>
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>

				</form>
			</div>
			<div class="col-sm-3">
				<!-- Puedes agregar contenido adicional en esta columna si es necesario -->
			</div>
		</div>
	</div>

</body>
</html>
