<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; utf-8">
<title>Tiempo</title>
</head>
<body style="background-color: #BBC7F5">
	<c:import url="http://localhost:8080/jsp/xmltiempo.xml" var="tiempoXML" />
	<x:parse var="doc" xml="${tiempoXML}" />
	<h1 style="text-align: center">Tiempo de Barcelona</h1>
	<h2 style="text-align: center">
		Fecha:
		<x:out select="$doc/root/elaborado" />
	</h2>

	<p style="margin: auto; width: 50%; text-align: center;">
		<x:out select="$doc/root/prediccion/txt_prediccion" />
	</p>
	<br>
	<br>
	<table style="margin: auto; width: 70%; text-align: center;border-collapse: collapse;">

		<tr style="border-bottom: 1px solid #fff;">
			<th>Nombre de ciudad</th>
			<th>Temperatura minima</th>
			<th>Temperatura maxima</th>
		</tr>
		<x:forEach var="ciudad" select="$doc/root/prediccion/ciudad">
			<tr style="border-bottom: 1px solid #fff;">
				<td><x:out select="nombre" /></td>
				<td><x:out select="minima" /></td>
				<td><x:out select="maxima" /></td>
			</tr>
		</x:forEach>
	</table>
</body>
</html>