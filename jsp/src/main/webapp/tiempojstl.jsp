<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Tiempo</title>
</head>
<body style="background-color: #BBC7F5">
    <c:import url="http://localhost:8080/jsp/xmltiempo.xml" var="tiempoXML" />
    <x:parse var="doc" xml="${tiempoXML}" />
    <h1 style="text-align: center">Tiempo de Barcelona</h1>
    <h2 style="text-align: center">
        Fecha:
        <c:out value="${doc.root.elaborado}" />
    </h2>

    <p style="margin: auto; width: 50%; text-align: center;">
        <c:out value="${doc.root.prediccion.txt_prediccion}" />
    </p>
    <br>
    <br>
    <table style="margin: auto; width: 70%; text-align: center; border-collapse: collapse;">
        <tr style="border-bottom: 1px solid #fff;">
            <th>Nombre de ciudad</th>
            <th>Temperatura mínima</th>
            <th>Temperatura máxima</th>
        </tr>
        <c:forEach items="${doc.root.prediccion.ciudad}" var="ciudad">
            <tr style="border-bottom: 1px solid #fff;">
                <td><c:out value="${ciudad.nombre}" /></td>
                <td><c:out value="${ciudad.minima}" /></td>
                <td><c:out value="${ciudad.maxima}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
