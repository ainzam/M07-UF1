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
	<div class="jumbotron container">
		<h1>Students</h1>
		<p>An Institut Marianao application</p>
	</div>

	<div class="container">
		<ul class="list-inline">
			<c:forEach var="module" items="${cycles}">
				<li><a href="register?cycle=${module}">${module}</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="container">
		<div class="row">
			<!-- Contenedor de estudiantes registrados -->
			<div class="col-sm-12">
				<h2>Alumnos Registrados</h2>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>DNI</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Email</th>
							<th>Ciclo</th>
							<th>Módulos</th>
							<th>Acción</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="student" items="${students}">
							<tr>
								<td>${student.dni}</td>
								<td>${student.name}</td>
								<td>${student.surname}</td>
								<td>${student.email}</td>
								<td>${student.cycle}</td>
								<td>${student.modules}</td>
								<td><a href="remove?dni=${student.dni}"
									class="btn btn-danger">Eliminar</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>
