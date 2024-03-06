<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Users</title>
</head>
<body>
	<div class="container">
		<h1>Active Users</h1>
        <div class="row">
            <div class="col-md-2">
                <a href="/jpa-2/AddUserServlet" class="btn btn-success">Add User</a>
            </div>
            <div class="col-md-5">
                <form action="/jpa-2/UsersServlet" method="post" class="form-inline">
                    <div class="form-group">
                        <label for="searchUsername">Search by Username:</label>
                        <input type="text" class="form-control" id="searchUsername" name="username">
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
            <div class="col-md-5">
                <form action="/jpa-2/UsersServlet" method="post" class="form-inline">
                    <div class="form-group">
                        <label for="searchEmail">Search by Email:</label>
                        <input type="email" class="form-control" id="searchEmail" name="email">
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
            	<c:if test="${not empty requestScope.message}">
				    <div class="alert alert-warning" role="alert">
				        <c:out value="${requestScope.message}" />
				    </div>
				</c:if>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Rank</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${requestScope.users}">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.rank}</td>
                                <td>
                                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmDelete${user.username}">
                                        Delete
                                    </button>
                                    <div id="confirmDelete${user.username}" class="modal fade" role="dialog">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h4 class="modal-title">Confirm Deletion</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Are you sure you want to delete user '${user.username}'?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <form action="/jpa-2/DeleteUserServlet" method="post">
                                                        <input type="hidden" name="username" value="${user.username}">
                                                        <button type="submit" class="btn btn-danger">Delete</button>
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>
