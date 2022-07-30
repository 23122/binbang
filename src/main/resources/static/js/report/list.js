/**
 * 
 */
var first=0;
var data;
$(function() {
	firstMoveCate(first);
	$(".cataPayType").click(moveCate1);
	$(".search-area>li:first-child").css("background-color", "rgba(255,164,9,0.4)");
});
function firstMoveCate(first) {
	var reportType = first;
	var url = "/report/list/" + reportType;
	$.get(url, function(result) {
		$("#list").html(result);
	});
	var child = reportType + 1;
	$(".search-area>li").css("background-color", "#fff");
	$(".search-area>li:nth-child(" + child + ")").css("background-color", "rgba(255,164,9,0.4)");
	
}
function moveCate1() {
	var payType = $(this).val();
	var url = "/report/list/" + payType;
	
	$.get(url, function(result) {
		$("#list").html(result);
	});
	var child = payType + 1;
	$(".search-area>li").css("background-color", "#fff");
	$(".search-area>li:nth-child(" + child + ")").css("background-color", "rgba(255,164,9,0.4)");
}