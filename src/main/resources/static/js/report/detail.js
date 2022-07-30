/**
 * 
 */

$(function() {
	$("#btn-del").click(function() {
		var result = confirm("게시물을 삭제하시겠습니까?");
		/* 확인:true 취소:false */
		if (!result) return;
		var no = $("#reportNo");
		//alert(no);
		$.ajax({
			url: "/report/write/" + no,
			type: "delete",
			//data:{"_method":"delete"},
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
