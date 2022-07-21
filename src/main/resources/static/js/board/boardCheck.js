/**
 * 
 */
var addrRexp = /^[가-힣a-zA-Z0-9+-\_.]/;
var salesPayRexp = /^[0-9]/;
var passRexp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,}$/;
var nameRexp = /^[가-힣]{2,4}$/;
var msg;
var check = [false, false, false, false, false, false, false, false];
$(function() {
	$("#salesDeposit").blur(depositBlured);
	$("#salesPaymonth").blur(paymonthBlured);
	$("#sample2_detailAddress").blur(addrBlured);
	$("#area1").blur(area1Blured);
	$("#area2").blur(area2Blured);
	$("#area3").blur(area3Blured);
	$("#area4").blur(area4Blured);
	$("#write-check").click(writeCheck);
	$("#passCheck").blur(pwdCheck);
	$("#name").blur(nameBlured);
	$("#isMaintenance").click(function(){
		$("#isnMaintenance").removeClass("ckecked")
	});
});
////////////////////////////위치정보 정규표현식///////////////////////
function addrBlured() {
	var in_addr = $("#sample2_detailAddress").val();//input태그에 입력된 값(value)을 읽어올때
	if (addrRexp.test(in_addr.trim())) {
		msg = "";
		$("#addrmsg").css("color", "green");
		check[0] = true;
	} else {
		msg = "* 상세주소를 입력하세요.";
		$("#addrmsg").css("color", "red");
		check[0] = false;
	}
	$("#addrmsg").html(msg);
	submitCheck();
}
////////////////////////////거래정보 정규표현식///////////////////////
function depositBlured() {
	var in_deposit = $("#salesDeposit").val();//input태그에 입력된 값(value)을 읽어올때
	if (salesPayRexp.test(in_deposit.trim())) {
		msg = "";
		$("#depositmsg").css("color", "green");
		check[1] = true;
	} else {
		msg = "* 숫자만 입력하세요. (단위 : 만원)";
		$("#depositmsg").css("color", "red");
		check[1] = false;
	}
	$("#depositmsg").html(msg);
	submitCheck();
}
function paymonthBlured() {
	var in_paymonth = $("#salesPaymonth").val();//input태그에 입력된 값(value)을 읽어올때
	if (salesPayRexp.test(in_paymonth.trim())) {
		msg = "";
		$("#paymonthmsg").css("color", "green");
		check[2] = true;
	} else {
		msg = "* 숫자만 입력하세요. (단위 : 만원)";
		$("#paymonthmsg").css("color", "red");
		check[2] = false;
	}
	$("#paymonthmsg").html(msg);
	submitCheck();
}
//////////////////////건물 정보 정규표현식///////////////////////
function area1Blured() {
	var in_area1 = $("#area1").val();//input태그에 입력된 값(value)을 읽어올때
	if (salesPayRexp.test(in_area1.trim())) {
		msg = "";
		$("#area1msg").css("color", "green");
		check[3] = true;
	} else {
		msg = "* 숫자만 입력하세요.";
		$("#area1msg").css("color", "red");
		check[3] = false;
	}
	$("#area1msg").html(msg);
	submitCheck();
}
function area2Blured() {
	var in_area2 = $("#area2").val();//input태그에 입력된 값(value)을 읽어올때
	if (salesPayRexp.test(in_area2.trim())) {
		msg = "";
		$("#area1msg").css("color", "green");
		check[4] = true;
	} else {
		msg = "* 숫자만 입력하세요.";
		$("#area2msg").css("color", "red");
		check[4] = false;
	}
	$("#area2msg").html(msg);
	submitCheck();
}
function area3Blured() {
	var in_area3 = $("#area3").val();//input태그에 입력된 값(value)을 읽어올때
	if (salesPayRexp.test(in_area3.trim())) {
		msg = "";
		$("#area3msg").css("color", "green");
		check[5] = true;
	} else {
		msg = "* 숫자만 입력하세요.";
		$("#area3msg").css("color", "red");
		check[5] = false;
	}
	$("#area3msg").html(msg);
	submitCheck();
}
function area4Blured() {
	var in_area4 = $("#area4").val();//input태그에 입력된 값(value)을 읽어올때
	if (salesPayRexp.test(in_area4.trim())) {
		msg = "";
		$("#area4msg").css("color", "green");
		check[6] = true;
	} else {
		msg = "* 숫자만 입력하세요.";
		$("#area4msg").css("color", "red");
		check[6] = false;
	}
	$("#area4msg").html(msg);
	submitCheck();
}
/////////////////////////규정동의체크////////////////////////////
function writeCheck(){
	var write_checked=$("#write-check").val();
	if(write_checked==true){
		check[6] = true;
		alert(write_checked);
	}else{
		check[6] = false;
		alert(write_checked);
	}
	submitCheck();
}
//////////////////////////매물등록버튼///////////////////////////
function submitCheck(){
	for(i=0; i<check.length; i++){
		if(check[i]==false){
			$("#btn-write").attr("disabled", true);
			return;
		}
	}
	$("#btn-write").attr("disabled", false);
}