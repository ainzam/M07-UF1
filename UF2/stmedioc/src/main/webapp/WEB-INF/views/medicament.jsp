<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Medicament</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Medicament</h1>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-md-5">
				<h3>${medicament.name}</h3>
				<p>${medicament.description}</p>
				<p>
					<strong>Codi : </strong>${medicament.medicamentId}
				</p>
				<p>
					<strong>Laboratori</strong> : ${medicament.producer}
				</p>
				<p>
					<strong>Categoria</strong> : ${medicament.category}
				</p>
				<p>
					<strong>Unitats en estoc </strong> : ${medicament.stockQuantity}
				</p>
				<h4>${medicament.price}USD</h4>
				<a href="<spring:url value="/medicaments/all" />"
					class="btn btn-default"> <span
					class="glyphicon-hand-left glyphicon"></span> tornar
				</a>

			</div>
		</div>
	</section>
</body>
</html>
