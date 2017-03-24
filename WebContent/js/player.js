function Player(name, score, color) {
	this.name = name;
	this.score = score;
	this.color = color;
}

Player.prototype.tableRow = function() {
	let tr = $('<tr>');
	let tdName = $('<td>');
	tdName.text(this.name);
	tdName.css("color", this.color);
	let tdColor = $('<td>');
	let div = $('<div>');
	div.addClass('snake-color-box');
	div.css('background-color', this.color);
	tdColor.append(div);
	let tdScore = $('<td>');
	tdScore.css("color", this.color);
	tdScore.append(this.score);
	tr.append(tdName, tdColor, tdScore);
	return tr;
};