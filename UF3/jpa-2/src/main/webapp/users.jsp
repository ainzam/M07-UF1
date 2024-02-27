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
		<h1>Users</h1>
		<form method="post" action="listActiveUsers">
			<input type="submit" value="List Active Users">
		</form>
		<form method="post" action="searchUser">
			<input type="text" name="searchQuery" placeholder="Enter username or email">
			<input type="submit" value="Search User">
		</form>
		<form method="post" action="addUser">
			<!-- Form fields to add a new user -->
			<input type="submit" value="Add User">
		</form>
		<form method="post" action="deleteUser">
			<input type="text" name="usernameOrEmail" placeholder="Enter username or email">
			<input type="submit" value="Delete User">
		</form>
		<hr>
		<h2>User List</h2>
		<table class="table">
			<tr>
				<th>Username</th>
				<th>Name</th>
				<th>Email</th>
				<th>Rank</th>
			</tr>
			<c:forEach var="user" items="${requestScope.users}">
				<tr>
					<td>${user.username}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.rank}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
