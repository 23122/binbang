/**
 * 
 */
var fileIdx = 0;
/*
$(function() {
	//파일 업로드처리하기 위한 이벤트
	$("#file_"+fileIdx).change(fileUpload);
});
*/
function fileUpload() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	var file = $("#file_"+fileIdx)[0].files[0];

	if (!file.type.includes("image")) {
		alert("이미지 파일이 아닙니다.");
		return;
	}
	if (file.size > (1024 * 1024 * 5)) {
		alert("이미지 용량은 1MB 까지만 적용하세요");
		return;
	}

	var formData = new FormData();
	formData.append("file", file);
	//formData.append("prevImgName",$("#file-change-name_"+fileIdx).val());
	$.ajax({
		beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
		url: "/board/fileupload",
		type: "post",
		data: formData,
		processData: false,
		contentType: false,
		success: function(result) {
			$("#img-disp_"+fileIdx).css("background-image", "url(/img/board/temp/" + result + ")")
			$("#file-change-name_"+fileIdx).val(result);
			addFile();
			
		},
		error: function(err) {
			alert("파일업로드 오류 서버의 용량이나 경로를 확인하세요");
		}
	});
}
function addFile() {

	const fileDivs = $('div[data-name="file-form"]');
	if (fileDivs.length > 14) {
		alert('마지막파일입니다.(파일은 최대 15 개까지 업로드 할 수 있습니다.)');
		return false;
	}

	fileIdx++;

	const fileHtml = `
		<div data-name="file-form">
			<label id="img-disp_${fileIdx}" class="add-file" for="file_${fileIdx}">+</label>
			<input type="file" id="file_${fileIdx}" name="file" class="fileInput" multiple="multiple" style="display: none;"onchange="fileUpload()">
			<input type="text" id="file-change-name_${fileIdx}" name="boardFilesChangeName"style="display: none;">
		</div>
	`;

	$('#fileAreaDiv').before(fileHtml);
}
/*
function fileUpload() {
	target = $(this);
	//파일데이터 전송
	file = target[0].files[0];
	fileName = file.name;
	if (!file.type.includes("image")) {
		alert("이미지 파일이 아닙니다.");
		return;
	}
	if (file.size > (1024 * 1024 * 5)) {
		alert("이미지 용량은 1MB 까지만 적용하세요");
		return;
	}
	data = new FormData();
	data.append("file", file);

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$.ajax({
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},////csrf 적용한경우..아니면 제거
		data: data,
		type: "POST",
		url: "/admin/goods/fileupload",
		contentType: false,
		//enctype: 'multipart/form-data',
		processData: false,
		success: function(fileUrl) {

			target.next().css("background-image", "url(" + fileUrl + ")");
			//style="background-image: url('');"
			target.siblings("[type=hidden]").val(fileName);
		},
		error: function(err) {
			alert("파일업로드 오류 서버의 용량이나 경로를 확인하세요");
			//console.log(err);
		}
	});


}
*/