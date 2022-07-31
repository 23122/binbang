/**
 * 
 */
var firstSpeed = 3000;
var moveSpeed = 5000;
var speed = 1000;
var flag = 0;
$(function() {
	atention();
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
	$(".area, #img-btn .next, #img-btn .prev, #img-btn .bullet ol").hover(stop, function() { myTimeout = setTimeout(start, firstSpeed); });
	//처음로딩시 타이머로 이미지 시작
	myTimeout = setTimeout(start, firstSpeed);
	$(".bullet ol").eq(0).addClass("target");
	$("#img-btn .next").click(nextClicked);
	$("#img-btn .prev").click(prevClicked);
	$("#img-btn .bullet ol").click(bulletClicked);
	$("#atention").change(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var atention1 = $("#atention").prop("checked");
		var boardNo1= $("#boardNo").val();
		var memberNo1=$("#memberNo").val();
		
		if (atention1 == true) {
			$.ajax({
				beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
				url: "/atention/addAtention",
				type: "post",
				data: {
					boardNo:boardNo1,
					atention:atention1,
					memberNo:memberNo1
				},
				success: function() {
					location.href = "/board/detail/"+boardNo1+"/"+memberNo1;
				}
			});
		} else {
			$.ajax({
				beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
				url: "/atention/removeAtention",
				type: "delete",
				data: {
					boardNo:boardNo1,
					atention:atention1,
					memberNo:memberNo1
				},
				success: function() {
					location.href = "/board/detail/"+boardNo1+"/"+memberNo1;
				}
			});
		}
	});
	$("#report-btn").click(function() {
		var boardNo = $("#boardNo").val();
		$.ajax({
			url: "/report/write/" + boardNo,
			type: "get",
			success: function() {
				location.href = "/report/write";
			}

		});
	});
});
function atention(){
	if($("#atention").prop("checked")==true){
		$(".check-at").css("background-color","green").css("color","#ffffff").css("border","5px solid green");
	};
}
function bulletClicked() {
	//클릭한 블잇의 인덱스 번호
	var idx = $(this).index();
	//블릿의 인덱스랑 이미지들의 value랑 일치
	var lis = $(".fimg li");
	var pos;
	for (var i = 0; i < lis.length; i++) {
		var v = lis.eq(i).val();
		if (v == idx) {
			//인덱스와 이미지가 일치하는 이미지
			pos = i;//i=img위치
			break;
		}
	}
	//이미지 이동
	if (flag == 0) {
		move(pos);
		var blis = $(".bullet ol");
		blis.removeClass("target");
		blis.eq(idx).addClass("target");
	}
}

function stop() {
	clearTimeout(myTimeout);
	console.log("timmer stop");
}
function start() {
	next();
	myTimeout = setTimeout(start, moveSpeed);
	console.log("timmer start");
}
function move(_pos) {
	var imgs = $(".fimg");
	for (var i = 0; i < _pos; i++) {
		var first = $(".fimg li:first-child");
		var last = $(".fimg li:last-child");
		imgs.css("left", { marginLeft: "-100%" });
		last.after(first);//마지막이미지->맨앞으로 보내기
		imgs.css("margin-left", 0);
	}
}
function next() {
	flag = 1;
	var first = $(".fimg li:first-child");
	var last = $(".fimg li:last-child");
	var blis = $(".bullet ol");
	//$(".vimg-wrap").css("margin-left", "-100%");
	$(".fimg-wrap").animate({ marginLeft: "-100%" }, speed, function() {
		last.after(first);//첫번째이미지->맨뒤로 보내기
		$(".fimg-wrap").css("margin-left", 0);
		var li_fv = $(".fimg li:first").val();
		blis.removeClass("target");
		blis.eq(li_fv % blis.length).addClass("target");
		flag = 0;
	});

}
function prev() {
	flag = 1;
	var first = $(".fimg li:first-child");
	var last = $(".fimg li:last-child");
	var li_fv = $(".fimg li:first").val();
	var blis = $(".bullet ol");
	first.before(last);//마지막이미지->맨앞으로 보내기
	$(".fimg-wrap").css("margin-left", "-100%");
	$(".fimg-wrap").animate({ marginLeft: 0 }, speed, function() {
		$(".fimg-wrap").css("margin-left", 0);
		blis.removeClass("target");
		blis.eq(li_fv % blis.length - 1).addClass("target");
		flag = 0;
	});
}
function nextClicked() {
	if (flag == 0) next();
}

function prevClicked() {
	if (flag == 0) prev();
}
function visualResize() {
	var wow = 800;/* window.innerWidth; */
	var imgs = $(".fimg");
	$(".fimg-wrap").css("width", wow * imgs.length);
	$(".fimg").css("width", wow); //이미지1장의 크기 설정
	$(".area").css("width", wow); //화면영역
}

function detailDel(boardNo) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var result = confirm("게시물을 삭제할까요??");
	if (!result)
		return;
	$.ajax({
		beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
		url: "/board/detail/" + boardNo,
		type: "delete",
		success: function() {
			location.href = "/customer/board/list";
		}
	});
}
