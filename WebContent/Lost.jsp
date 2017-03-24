<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="css/lost.css" rel="stylesheet" />
	<title>You Lost!</title>
<title>High Score</title>
</head>
<body>
	<div class="container">
		<h1>High Scores</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Score</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ele" items="${scores }">
					<tr>
						<td>${ele.name }</td>
						<td>${ele.score }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="Lost" method="post">
			<input type="submit" class="btn btn-lg btn-success" value="Play Again!" />
		</form>
		<form action="Login" method="get">
			<input type="submit" class="btn btn-lg btn-info" value="Another User" />
		</form>
	</div>
</body>
</html>