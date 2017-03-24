<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link href="css/login.css" rel="stylesheet" />
<script src="js/login.js"></script>
<title>Login</title>
</head>
<body>
	<div class="container">
		<h1 class="form-signin-heading">Login</h1>
		<form action="Login" method="POST" class="form-signin">
			<input type="radio" id="player" name="type" value="play" required="required" checked="checked" /> 
			<label for="player">
				<input type="text" placeholder="Name" name="name" class="form-control" autofocus="autofocus" />
			</label>
			<label for="color">Color: </label> <input id="color" type="color" name="color" /> 
			<input type="checkbox" name="cheat" id="cheat" value="cheat" title="Cheat Enable"/>
			<br /> 
			<input type="radio" id="watcher" name="type" value="watch" /> <label for="watcher">Watch</label>
			<div class="go-button"><input type="submit" class="btn btn-lg btn-primary" value="Go!" /></div>
		</form>
	</div>
</body>
</html>