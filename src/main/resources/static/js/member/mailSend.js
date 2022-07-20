/**
 * 
 */
 $(function(){
	$("#btn-email").click(function(){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajax({
			beforeSend: function(xhr){xhr.setRequestHeader(header, token);},
			url:"/request-key/mail",
			type: "post",
			data: {memberEmail:$("#email").val()},
			success:function(result){
				if(result){
					var ss="인증번호 발송!";
					$("#btn-emailCheck").attr("disabled", false);
					alert(ss);
				}
			}
		});
	});
});