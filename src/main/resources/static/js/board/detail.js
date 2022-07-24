/**
 * 
 */
$(function() {
	
});
function detailDel(boardNo) {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var result = confirm("게시물을 삭제할까요??");
			if (!result)
				return;
			$.ajax({
				beforeSend: function(xhr){xhr.setRequestHeader(header, token);},
				url: "/board/list/"+boardNo,
				type: "delete",
				success: function() {
					location.href = "/customer/board/list";
				}
			});
		}