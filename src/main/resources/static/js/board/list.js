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
	$("#cataPayType").change(moveCate1);
	$("#cateBuildType").change(moveCate2);
	$("#search-btn1").click(priceSearch1);
	$("#search-btn2").click(priceSearch2);
	$("#search-btn3").click(priceSearch3);
});
function moveCate1() {
	var payType = $(this).val();
	var url = "/customer/board/list/" + payType;
	
	$.get(url, function(result) {
		$("#list").html(result);
	});
	if(payType!=0){
		console.log(">>>>>>:"+payType);
		$("#monthPay").detach();
	}else{
		$("#monthPay").detach();
		console.log(">>>>>>2:"+payType);
		const fileHtml =
	
		 `
			<li id="monthPay" class="flex">
				<span style=" width:60px;margin: 30px 0px;text-align: center;line-height:40px; ">월세</span>
				<span class="boil-wrap flex">
                   	<input style="width: 70px;margin: 30px 0px;" id="price1" type="number" placeholder="0">
					<input style="width: 70px;margin: 30px 0px;" id="price2" type="number" placeholder="0">
					<button style="margin-left: 5px;" id="search-btn1" type="button">월세찾기</button>
              	</span>
			</li>
		`;
		$('#deposit').after(fileHtml);
	}
	
}


function moveCate2() {
		var payType = $("#cate1").val();
		var buildType=$("#cateBuildType").val();
		var url = "/customer/board/list/" + payType+"/"+buildType;
		console.log(payType);
		console.log(buildType);
		$.get(url, function(result) {
			$("#list").html(result);
		});
}
function priceSearch1() {
	var payType = $("#cate1").val();
	var buildType=$("#cate2").val();
	var price1=$("#price1").val();
	var price2=$("#price2").val();
	var url = "/customer/board/list/" + payType+"/"+buildType+"/"+price1+"/"+price2;
	$.get(url, function(result) {
		$("#list").html(result);
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
		price2=99999999;
	}
	var monthPrice1=$("#monthPrice1").val();
	var monthPrice2=$("#monthPrice2").val();
	var url = "/customer/board/list/" + payType+"/"+buildType+"/"+price1+"/"+price2+"/"+monthPrice1+"/"+monthPrice2;
	$.get(url, function(result) {
		$("#list").html(result);
	});
}
function priceSearch3() {
	var payType = $("#cate1").val();
	var buildType=$("#cate2").val();
	var price1=$("#price1").val();
	var price2=$("#price2").val();
	var monthPrice1=$("#monthPrice1").val();
	var monthPrice2=$("#monthPrice2").val();
	var address=$("#addr").val();
	if(price1==""){
		price1=0;
	}
	if(price2==""){
		price2=99999999;
	}
	if(monthPrice1==""){
		monthPrice1=0;
	}
	if(monthPrice2==""){
		monthPrice2=99999999;
	}
	console.log(monthPrice1);
	console.log(monthPrice2);
	var url = "/customer/board/list/" + payType+"/"+buildType+"/"+price1+"/"+price2+"/"+monthPrice1+"/"+monthPrice2+"/"+address;
	$.get(url, function(result) {
		$("#list").html(result);
	});
}
