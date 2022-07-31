/**
 * 
 */

$(function() {
	$(".btn-del").click(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var result = confirm("게시물을 삭제하시겠습니까?");
		/* 확인:true 취소:false */
		if (!result) return;
		var no = $("#reportNo").val();
		//alert(no);
		$.ajax({
			beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
			url: "/report/detail/" + no,
			type: "delete",
			success: function() {
				location.href = "/report/list";
			}

		});

	});

	$("#btn-edit").click(function() {
		$(".detail-view").hide();
		$("#edit-form").show();
	});
	$("#btn-cancel").click(function() {
		$(".detail-view").show()
		$("#edit-form").hide();
	});

});
