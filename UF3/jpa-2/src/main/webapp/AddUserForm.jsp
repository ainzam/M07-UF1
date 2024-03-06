<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Add User</h1>
        <form action="AddUserServlet" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required>
                <c:forEach items="${violations}" var="violation">
                    <c:if test="${violation.getPropertyPath().toString() eq 'username'}">
                        <div class="text-danger">${violation.getMessage()}</div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
                <c:forEach items="${violations}" var="violation">
                    <c:if test="${violation.getPropertyPath().toString() eq 'name'}">
                        <div class="text-danger">${violation.getMessage()}</div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
                <c:forEach items="${violations}" var="violation">
                    <c:if test="${violation.getPropertyPath().toString() eq 'email'}">
                        <div class="text-danger">${violation.getMessage()}</div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
                <c:forEach items="${violations}" var="violation">
                    <c:if test="${violation.getPropertyPath().toString() eq 'password'}">
                        <div class="text-danger">${violation.getMessage()}</div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="form-group">
                <label for="rank">Rank:</label>
                <input type="number" class="form-control" id="rank" name="rank" required>
                <c:forEach items="${violations}" var="violation">
                    <c:if test="${violation.getPropertyPath().toString() eq 'rank'}">
                        <div class="text-danger">${violation.getMessage()}</div>
                    </c:if>
                </c:forEach>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>
