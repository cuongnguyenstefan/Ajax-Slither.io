$(function() {
	$('input:text').on('focus', function() {
		$('input:radio[value="play"]').prop("checked", true)
	});
	$('input:color').on('click', function() {
		$('input:radio[value="play"]').prop("checked", true)
	});
})