/**
 * 
 */
//정규표현식
var emailRexp = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
var passRexp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,}$/;
var nameRexp = /^[가-힣]{2,4}$/;
//input 하단메세지
var msg;
//필수입력확인
var check = [false, false, false, false];
//이메일 입력확인
var emailcheck = false;

var createdDate;
var myTimeout;
//레디펑션
$(function() {
	$("#keyArea").hide();
	$("#pass").blur(passBlured);
	$("#passCheck").blur(pwdCheck);
	$("#name").blur(nameBlured);
	$("#email").keyup(mailCkech);
	$("#btn-email").click(mailSend);
	$("#emailKey").blur(keyCheck);
});
///////////////////////////이메일중복확인//////////////////////////////
function mailCkech() {
	var in_email = $(this).val();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	//비동기통신
	$.ajax({
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		url: "/customer/emailCheck",
		data: { memberEmail: in_email },
		type: "post",
		success: function(result) {
			if (result) {
				$("#email").parent().siblings("#emsg")
					.text("사용불가").css("color", "red");
					emailcheck=false;
			} else {
				emailBlured();
			}
			emailCheck();
		}
	});
}
/////////////////////////인증번호발송////////////////////////
function mailSend() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.get("/request-key/remove",function(){});
	$.ajax({
		beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
		url: "/request-key/mail",
		type: "post",
		data: { memberEmail: $("#email").val() },
		success: function(result) {
			var ss = "인증번호 발송!";
			alert(ss);
			$("#keyArea").show();
			$("#emailKey").hide();
			$("#timer").show();
			start(result+(1000*60*5));
		}
	});
}
////////////////////////인증번호확인/////////////////////////////////
function keyCheck() {
	var inputKey = $(this).val();
	$.get("/request-key/getKey", function(code) {
		if (code == inputKey) {
			msg = "* 인증번호확인 완료!";
			$("#ecmsg").css("color", "green");
			$("#timer").hide();
			$("#emailKey").hide();
			$("#emsg").hide();
			clearTimeout(myTimeout);
			$.get("/request-key/remove",function(){});
			check[3] = true;
		} else {
			msg = "인증번호를 확인해주세요.";
			$("#ecmsg").css("color", "red");
			check[3] = false;
		}
		$("#ecmsg").html(msg);
		submitCheck();
	});
}
////////////////////////////인증타이머////////////////////////////
function start(targetTime) {
	var seconds = (targetTime - new Date().getTime())/1000;
	var minute = Math.floor(seconds / 60);
	var second = Math.floor(seconds % 60);
	if (seconds > 1) {
		$("#timer").text(minute +":"+second);
		myTimeout = setTimeout(start, 1000, targetTime);
	} else {
		clearTimeout(myTimeout);
		$("#timer").text("00:00");
		$.get("/request-key/remove", function(){});
	}
}
////////////////////////////이메일입력////////////////////////////
function emailBlured() {
	check[3] = false;
	var in_email = $("#email").val();//input태그에 입력된 값(value)을 읽어올때
	if (emailRexp.test(in_email.trim())) {
		msg = "사용가능한 이메일입니다. 인증번호를 발송해주세요.";
		$("#emsg").css("color", "green");
		emailcheck = true;
	} else {
		msg = "* 이메일 형식으로 입력하여야합니다.";
		$("#emsg").css("color", "red");
		emailcheck = false;
	}
	$("#emsg").html(msg);
	emailCheck();
}
////////////////////////////비밀번호입력////////////////////////////
function passBlured() {
	var in_pass = $("#pass").val();
	if (passRexp.test(in_pass)) {
		msg = "사용가능한 비밀번호입니다.";
		$("#pmsg").css("color", "green");
		check[0] = true;
	} else {
		msg = "* 최소 8자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자를 포함해야합니다.";
		$("#pmsg").css("color", "red");
		check[0] = false;
	}
	$("#pmsg").html(msg);
	submitCheck();
}
////////////////////////////비밀번호확인입력////////////////////////////
function pwdCheck() {
	var in_pass = $("#pass").val();
	var in_passCheck = $("#passCheck").val();
	if (passRexp.test(in_passCheck)) {
		if (in_pass != in_passCheck) {
			msg = "* 비밀번호를 확인하세요.";
			$("#pcmsg").css("color", "red");
			check[1] = false;
		} else {
			msg = "비밀번호 확인완료";
			$("#pcmsg").css("color", "green");
			check[1] = true;
		}
	} else {
		msg = "* 비밀번호를 확인하세요.";
		$("#pcmsg").css("color", "red");
	}
	$("#pcmsg").html(msg);
	submitCheck();
}
////////////////////////////이름입력확인////////////////////////////
function nameBlured() {
	var in_name = $("#name").val();
	if (nameRexp.test(in_name)) {
		msg = "입력 완료";
		$("#nmsg").css("color", "green");
		check[2] = true;
	} else {
		msg = "* 이름을 확인하세요.";
		$("#nmsg").css("color", "red");
		check[2] = false;
	}
	$("#nmsg").html(msg);
	submitCheck();
}
////////////////////////////이메일인증번호활성////////////////////////////
function emailCheck() {
	if (emailcheck == false) {
		$("#btn-email").attr("disabled", true);
		check[3] = false;
		return;
	}
	$("#btn-email").attr("disabled", false);
	check[3] = true;
	submitCheck();
}
////////////////////////////회원가입 활성////////////////////////////
function submitCheck() {
	for (i = 0; i < check.length; i++) {
		if (check[i] == false) {
			$("#btn-join").attr("disabled", true);
			return;
		}
	}
	$("#btn-join").attr("disabled", false);
}