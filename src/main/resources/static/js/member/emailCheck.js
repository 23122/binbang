/**
 * 
 */
$(function() {
	$("#email").keyup(function() {
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
				} else {
					$("#email").parent().siblings("#emsg")
						.text("사용가능").css("color", "green");
				}
			}
		});
	});

});