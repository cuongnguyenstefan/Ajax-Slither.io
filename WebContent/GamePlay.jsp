<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Snakes</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="css/game.css" rel="stylesheet" />
	<script src="js/player.js"></script>
	<script src="js/game.js"></script>
</head>
<body>
	<div id="hiddenConfig">
		<input type="hidden" name="cellPx" value="${GameConfig.cellPx }" />
		<input type="hidden" name="boundaryXMax" value="${GameConfig.boundaryXMax }" />
		<input type="hidden" name="boundaryYMax" value="${GameConfig.boundaryYMax }" />
		<input type="hidden" name="gameSpeed" value="${GameConfig.gameSpeed }" />
		<input type="hidden" name="type" value="${type }" />
		<input type="hidden" name="playerId" value="${playerId }" />
	</div>
	<canvas id="canvas" width="${GameConfig.boundaryXMax }" height="${GameConfig.boundaryYMax }"></canvas>
	<div id="highScore">
		<h1>Players</h1>
		<table id="liveScore" class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Color</th>
					<th>Score</th>
				</tr>
			</thead>
			<tbody id="scoreBody"></tbody>
		</table>
		<div id="lost">
			<form action="Lost" method="get">
				<label for="finalScore" class="finalScore">Your Score:&nbsp;&nbsp;</label><span id="finalScore"></span> <br />
				<input type="submit" class="btn btn-lg btn-danger" value="You Lost!" />
			</form>
		</div>
		<div id="reload">
			<input type="button" class="btn btn-lg btn-danger" value="Reload" />
		</div>
	</div>
</body>
</html>