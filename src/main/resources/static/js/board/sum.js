/**
 * 
 */
var myTimeout;
var firstSpeed = 3000;
var nextSpeed = 5000;
var speed = 1000;
$(function() {
	visualResize();
	$(window).resize(function() {
		visualResize();
	});
	//화면최소화시 타이머 스탑
	document.addEventListener("visibilitychange", function() {
		console.log(document.visibilityState);
		var state = document.visibilityState;
		if (state == "hidden") {
			stop();
		} else if (state == "visible") {
			myTimeout = setTimeout(start, firstSpeed);//타이머로 시작
		}

	});
	//비주얼 이미지에 마우스 올라갈때
	$(".area").hover(stop, function() { myTimeout = setTimeout(start, firstSpeed); });

	//처음로딩시 타이머로 이미지 시작
	myTimeout = setTimeout(start, firstSpeed);
});

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
		var first = $("#fimg_" + i + ">li:first-child");
		var last = $("#fimg_" + i + ">li:last-child")
		$("#fimg_" + i).animate({ marginLeft: "-100%" }, speed, function() {
			first.insertAfter(last);
			$(".fimg").css("margin-left", 0);
		});
	};

}

function visualResize() {
	for (var i = 0; i <= $(".fimg").length - 1; i++) {
		var wow = 300;/* window.innerWidth; */
		var imgs = $("#fimg_" + i + " a");
		console.log(imgs.length);
		$("#fimg_" + i).css("width", wow * imgs.length);
		$(".bg").css("width", wow); //이미지1장의 크기 설정
		$(".area").css("width", wow); //화면영역
	};

}