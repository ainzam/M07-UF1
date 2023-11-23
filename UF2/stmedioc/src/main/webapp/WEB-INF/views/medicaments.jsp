
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ca">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/
css/bootstrap.min.css">
<title>Medicaments</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Medicaments</h1>
				<p>Llista de medicaments en magatzem</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<c:forEach items="${medicaments}" var="medicament">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">
						<div class="caption">
							<h3>${medicament.name}</h3>
							<p>${medicament.description}</p>
							<p>${medicament.price}</p>
							<p>Hi ha ${medicament.stockQuantity} unitats en magatzem</p>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</section>
</body>
</html>