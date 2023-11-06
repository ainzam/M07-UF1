<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

    <div class="container mt-4">
        <ul class="list-group list-group-horizontal">
            <c:forEach var="module" items="${cycles}">
                <li class="list-group-item"><a style="text-decoration:none;" href="register?cycle=${module}"><i class="bi bi-plus-square"></i> New ${module} register </a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <h2>Students</h2>
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
                                <td>
                                    <ul>
                                        <c:forEach var="module" items="${student.modules}">
                                            <li>${module}</li>
                                        </c:forEach>
                                    </ul>
                                <td>
                                    <!-- Botón para abrir el modal de confirmación de eliminación -->
                                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmationModal-${student.dni}">Eliminar</button>
                                </td>
                            </tr>
                            <!-- Modal de confirmación de eliminación para cada estudiante -->
                            <div class="modal fade" id="deleteConfirmationModal-${student.dni}" tabindex="-1" role="dialog" aria-labelledby="deleteConfirmationModalLabel-${student.dni}" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="deleteConfirmationModalLabel-${student.dni}">Confirmar Eliminación</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                        </div>
                                        <div class="modal-body">
                                            <p>¿Seguro que deseas eliminar al estudiante con DNI: ${student.dni}?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                            <a href="remove?dni=${student.dni}" class="btn btn-danger">Eliminar</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
