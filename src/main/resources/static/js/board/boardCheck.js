/**
 * 
 */
var addrRexp = /^[가-힣a-zA-Z0-9+-\_.\s]{4,}$/;
var salesPayRexp = /^[0-9]{1,}$/;
var buildInfoRexp = /^[0-9]{1,3}$/;
var passRexp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,}$/;
var nameRexp = /^[가-힣]{2,4}$/;
var msg;
var check = [false, false, false, false, false, false, false, false];
$(function() {
	monthCheck();
	$(".checkPay").change(monthCheck);
	$("#boardDeposit").blur(depositBlured);
	$("#boardPaymonth").blur(paymonthBlured);
	$("#sample2_detailAddress").blur(addrBlured);
	$("#area1").blur(area1Blured);
	$("#area2").blur(area2Blured);
	$("#area3").blur(area3Blured);
	$("#area4").blur(area4Blured);
	$("#write-check").change(writeCheck);
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
	var in_deposit = $("#boardDeposit").val();//input태그에 입력된 값(value)을 읽어올때
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
function monthCheck(){
	if($("#month").prop("checked")==true){
		const fileHtml =
	
		 `
			<ul>
				<input type="text" id="boardPaymonth" name="boardPaymonth" placeholder="월세를 입력해주세요.(단위 : 만원)">
				<li id="paymonthmsg" class="msg"></li>
			</ul>
		`;
		check[2] = false;
		$('#payMonthUl').before(fileHtml);
	}else {
		$("#boardPaymonth").detach();
		check[2] = true;
	}
	submitCheck();
}
function paymonthBlured() {
	var in_paymonth = $("#boardPaymonth").val();//input태그에 입력된 값(value)을 읽어올때
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
/*
구현실패
for(i=0;i<4;i++){
	var a= "area"+i+"Blured";
	function areaBlured() {
	var in_area = $("#area"+i).val();//input태그에 입력된 값(value)을 읽어올때
	if (salesPayRexp.test(in_area.trim())) {
		if(buildInfoRexp.test(in_area.trim())){
			msg = "";
			$("#area"+i+"msg").css("color", "green");
			check[i+3] = true;
		} else {
			msg = "입력값을 확인하세요.";
			$("#area"+i+"msg").css("color", "red");
			check[i+3] = false;
		}
	} else {
		msg = "* 숫자만 입력하세요.";
		$("#area"+i+"msg").css("color", "red");
		check[i+3] = false;
	}
	$("#area"+i+"msg").html(msg);
	submitCheck();
	}
}
*/
function area1Blured() {
	var in_area1 = $("#area1").val();//input태그에 입력된 값(value)을 읽어올때
	if (salesPayRexp.test(in_area1.trim())) {
		if(buildInfoRexp.test(in_area1.trim())){
			msg = "";
			$("#area1msg").css("color", "green");
			check[3] = true;
		} else {
			msg = "입력값을 확인하세요.";
			$("#area1msg").css("color", "red");
			check[3] = false;
		}
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
		if(buildInfoRexp.test(in_area2.trim())){
			msg = "";
			$("#area2msg").css("color", "green");
			check[4] = true;
		} else {
			msg = "입력값을 확인하세요.";
			$("#area2msg").css("color", "red");
			check[4] = false;
		}
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
		if(buildInfoRexp.test(in_area3.trim())){
			msg = "";
			$("#area3msg").css("color", "green");
			check[5] = true;
		} else {
			msg = "입력값을 확인하세요.";
			$("#area3msg").css("color", "red");
			check[5] = false;
		}
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
		if(buildInfoRexp.test(in_area4.trim())){
			msg = "";
			$("#area4msg").css("color", "green");
			check[6] = true;
		} else {
			msg = "입력값을 확인하세요.";
			$("#area4msg").css("color", "red");
			check[6] = false;
		}
		
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
	var write_checked=$(this).prop("checked");
	if(write_checked==true){
		check[7] = true;
	}else{
		check[7] = false;
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