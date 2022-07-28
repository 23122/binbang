/**
 * 
 */
var myTimeout;
var firstSpeed = 3000;
var nextSpeed = 5000;
var speed = 1000;
var first=0;
var data;
$(function() {
	visualResize();
	$(window).resize(function() {
		visualResize();
	});
	//화면최소화시 타이머 스탑
	document.addEventListener("visibilitychange", function() {
		var state = document.visibilityState;
		if (state == "hidden") {
			stop();
		} else if (state == "visible") {
			myTimeout = setTimeout(start, firstSpeed);//타이머로 시작
		}

	});
	
	firstMoveCate(first);
	$(".search-area>li:first-child").css("background-color", "rgba(255,164,9,0.4)");
	$(".cataPayType").click(moveCate1);
	$("#cateBuildType").change(moveCate2);
	$("#search-btn1").click(priceSearch1);
	$("#search-btn2").click(priceSearch2);
	$(".area").hover(stop, function() { myTimeout = setTimeout(start, firstSpeed); });//비주얼 이미지에 마우스 올라갈때
	myTimeout = setTimeout(start, firstSpeed);//처음로딩시 타이머로 이미지 시작
});
function firstMoveCate(first) {
	var payType = first;
	var url = "/customer/board/sum/" + payType;
	$.get(url, function(result) {
		$("#list").html(result);
		visualResize();
	});
	var child = payType + 1;
	$(".search-area>li").css("background-color", "#fff");
	$(".search-area>li:nth-child(" + child + ")").css("background-color", "rgba(255,164,9,0.4)");
	
}
function moveCate1() {
	var payType = $(this).val();
	var url = "/customer/board/sum/" + payType;
	$.get(url, function(result) {
		$("#list").html(result);
		visualResize();
	});
	var child = payType + 1;
	$(".search-area>li").css("background-color", "#fff");
	$(".search-area>li:nth-child(" + child + ")").css("background-color", "rgba(255,164,9,0.4)");
	if(payType!=0){
		data=$("#monthPay").detach();
	}else{
		const fileHtml =
	
		 `
			<div id="monthPay">
				<span>월세(*만원단위)</span>
				<input id="monthPrice1" type="number" placeholder="만원단위로 숫자만 입력해주세요.">
				<input id="monthPrice2" type="number" placeholder="만원단위로 숫자만 입력해주세요.">
				<button id="search-btn2" type="button">찾기</button>
			</div>
		`;
		$('#deposit').after(fileHtml);
	}
}


function moveCate2() {
		console.log(">>>>>>>>>>>>>>>>>");
		var payType = $("#cate1").val();
		var buildType=$("#cateBuildType").val();
		var url = "/customer/board/sum/" + payType+"/"+buildType;
		console.log(payType);
		console.log(buildType);
		$.get(url, function(result) {
			$("#list").html(result);
			visualResize();
		});
}
function priceSearch1() {
	var payType = $("#cate1").val();
	var buildType=$("#cate2").val();
	var price1=$("#price1").val();
	var price2=$("#price2").val();
	var url = "/customer/board/sum/" + payType+"/"+buildType+"/"+price1+"/"+price2;
	$.get(url, function(result) {
		$("#list").html(result);
		visualResize();
	});
}
function priceSearch2() {
	var payType = $("#cate1").val();
	var buildType=$("#cate2").val();
	var price1=$("#price1").val();
	var price2=$("#price2").val();
	console.log(price1);
	if(price1==""){
		price1=0;
	}
	if(price2==""){
		price2=0;
	}
	var monthPrice1=$("#monthPrice1").val();
	var monthPrice2=$("#monthPrice2").val();
	var url = "/customer/board/sum/" + payType+"/"+buildType+"/"+price1+"/"+price2+"/"+monthPrice1+"/"+monthPrice2;
	$.get(url, function(result) {
		$("#list").html(result);
		visualResize();
	});
}
function stop() {
	clearTimeout(myTimeout);
	console.log("timmer stop");
}

function start() {
	next();
	myTimeout = setTimeout(start, nextSpeed);
	console.log("timmer start");
}

function next() {
	for (var i = 0; i <= $(".fimg").length - 1; i++) {
		console.log("for start");
		var tar = $("#fimg_" + i);
		var first = $("#fimg_" + i + ">li:first-child");
		var last = $("#fimg_" + i + ">li:last-child");
		tar.animate({ marginLeft: "-100%" }, 0, function() {
			$(".fimg").css("margin-left", 0);
			first.insertAfter(last);
		});
	};
}

function visualResize() {
	for (var i = 0; i <= $(".fimg").length - 1; i++) {
		var wow = 293;/* window.innerWidth; */
		var imgs = $("#fimg_" + i + " a");
		$("#fimg_" + i).css("width", wow * imgs.length);
		$(".bg").css("width", wow); //이미지1장의 크기 설정
		$(".area").css("width", wow); //화면영역
	};

}