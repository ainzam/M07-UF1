<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Medicaments</title>
</head>
<body>
    <section>
        <div class="jumbotron">
			<a href="<c:url value="/j_spring_security_logout" />"
				class="btn btndanger btn-mini pull-right">desconnectar</a>
			<div class="container">
                <h1>Medicament</h1>
                <p>Afegir medicament</p>
            </div>
        </div>
    </section>
    <section class="container">
        <form:form modelAttribute="newMedicament" class="form-horizontal">
            <fieldset>
                <legend>Afegir medicament</legend>
                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="medicamentId">Codi</label>
                    <div class="col-lg-10">
                        <form:input id="medicamentId" path="medicamentId" type="text" class="form:input-large"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="name">Nom</label>
                    <div class="col-lg-10">
                        <form:input id="name" path="name" type="text" class="form:input-large"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="price">Preu</label>
                    <div class="col-lg-10">
                        <form:input id="price" path="price" type="text" class="form:input-large"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="producer">Laboratori</label>
                    <div class="col-lg-10">
                        <form:input id="producer" path="producer" type="text" class="form:input-large"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="category">Categoria</label>
                    <div class="col-lg-10">
                        <form:input id="category" path="category" type="text" class="form:input-large"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="stockQuantity">Unitats en estoc</label>
                    <div class="col-lg-10">
                        <form:input id="stockQuantity" path="stockQuantity" type="text" class="form:input-large"/>
                    </div>
                </div>
                <%--<div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="stockInOrder">Unitats en comandes</label>
                    <div class="col-lg-10">
                        <form:input id="stockInOrder" path="stockInOrder" type="text" class="form:input-large"/>
                    </div>
                </div>--%>
                <div class="form-group">
                    <label class="control-label col-lg-2" for="description">Descripció</label>
                    <div class="col-lg-10">
                        <form:textarea id="description" path="description" rows ="2"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-2" for="active">actiu</label>
                    <div class="col-lg-10">
                        <form:radiobutton path="active" value="true" />Si
                        <form:radiobutton path="active" value="false" />No
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <input type="submit" id="btnAdd" class="btn btn-primary" value ="Crear"/>
                    </div>
                </div>
            </fieldset>
        </form:form>
    </section>
</body>
</html>
