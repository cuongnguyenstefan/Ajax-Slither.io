$(function() {
	//Canvas stuff
	var canvas = $("#canvas")[0];
	var ctx = canvas.getContext("2d");
	var w = $("#canvas").width();
	var h = $("#canvas").height();
	
	//cell
	var cw = $("input:hidden[name='cellPx']").val();
	
	// game speed
	var gameSpeed = $("input:hidden[name='gameSpeed']").val();
	
	// links
	var getDataURL = "Watch";
	var changeDirectionURL = "GamePlay";
	
	// colors
	var foodColor = "#4286f4";
	
	// interval id
	var intervalId = "";
	
	//others
	var type = $("input:hidden[name='type']").val();
	
	function initCanvas() {
		ctx.fillStyle = "white";
		ctx.fillRect(0, 0, w, h);
		ctx.strokeStyle = "black";
		ctx.strokeRect(0, 0, w, h);
	}
	
	function paint_cell(x, y, color)
	{
		ctx.fillStyle = color;
		ctx.fillRect(x*cw, y*cw, cw, cw);
		ctx.strokeStyle = "white";
		ctx.strokeRect(x*cw, y*cw, cw, cw);
	}
	
	function paint(data) {
		ctx.clearRect(0, 0, w, h);
		initCanvas();
		$.each(data.foods, function(idx, val){
			paint_cell(val.x, val.y, foodColor);
		});
		
		$.each(data.players, function(idx, val){
			$.each(val.snake.body, function(bodyIdx, body){
				paint_cell(body.x, body.y, val.color);
			});
			paint_cell(val.snake.head.x, val.snake.head.y, val.color);
		});
	}
	
	var changeDirection = function(direction) {
		$.post(changeDirectionURL, {
			'direction' : direction
		});
	};
	
	var liveScoreTable = function(data) {
		let body = $("#scoreBody");
		body.empty();
		$.each(data, function(idx, player){
			body.append(player.tableRow());
		});
	};
	
	var jsonDataToPlayerSortedList = function(data) {
		var list = [];
		$.each(data, function(idx, player) {
			let p = new Player(player.name, player.snake.body.length, player.color);
			list.push(p);
		});
		list.sort(function(p1, p2) {
			if (p1.score > p2.score) return -1;
			if (p1.score < p2.score) return 1;
			return 0;
		});
		return list;
	}
	
	var counterTable = 0;
	
	var whenLost = function(player) {
		$("#lost").show();
		$("#finalScore").text(player.snake.body.length);
	}
	
	var intervalSuccess = function(data) {
		if (type === 'play' && data.current.status === "LOST") {
			whenLost(data.current);
		}
		paint(data);
		if (counterTable === 0) {
			counterTable += 1;
			liveScoreTable(jsonDataToPlayerSortedList(data.players));
		} else {
			counterTable = (counterTable + 1) % 24;
		}
	};
	
	var intervalFail = function() {
		clearInterval(intervalId);
		$("#reload").show();
	}
	
	var interval = function() {
		$.get(getDataURL).done(intervalSuccess).fail(intervalFail);
	};
	
	$(document).keydown(function(e){
		var key = e.which;
		var direction = "";
		if(key == "37") direction = "left";
		else if(key == "38") direction = "up";
		else if(key == "39") direction = "right";
		else if(key == "40") direction = "down";
		if (direction !== "") {
			changeDirection(direction);
		}
	});
	
	var initPage = function() {
		$('input:button[value="Reload"]').on('click', function() {
			location.reload();
		});
		$("#reload").hide();
		$("#lost").hide();
		initCanvas();
		intervalId = setInterval(interval, gameSpeed);
	};
	initPage();
})