/**
 * 
 */
$(function() {
	$('#summernote').summernote(setting); 
});
toolbar=[
			['fontname',['fontname']],
			['fontsize',['fontsize']],
      		['style', ['bold', 'underline', 'clear','italic', 'strikethrough']],
      		//['font', ['bold', 'underline', 'clear']],
      		['color', ['forecolor','color']],
      		//글머리기호
      		['para', ['ul', 'ol', 'paragraph']],
      		['table', ['table']],
      		['insert', ['link', 'picture', 'video']],
      		['view', ['fullscreen', 'codeview', 'help']]
    ];

setting={
    placeholder:`
    <br>
    상세설명 작성 주의사항
    <br>
    <br>
    -허위신고시 무경고 탈퇴처리 됩니다.
    <br>
    <br>
    -위반한 사실을 자세히 명시해주세요.
    <br>
    <br>
    *주의사항 위반시 글삭제 및 이용의 제한이 있을 수 있습니다.`,
    tabsize: 2,
    width: 1048,
    height: 235,                 // set editor height
    minHeight: null,             // set minimum height of editor
    maxHeight: 235,             // set maximum height of editor
    focus: false ,
    lang: 'ko-KR',
    toolbar: toolbar,
    callbacks:{
    	//이미지 선택하면 이벤트 적용할 예정
    	onImageUpload:function(files, editor, welEditable){
    		for(var i=files.length-1; i>=0; i--){
    			uploadSummernoteImg(files[i], this);
    		}
    	}
    }//callbacks{}
  }//setting{}
function uploadSummernoteImg(file, el){
	data=new FormData();
	data.append("file", file);
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajax({
		beforeSend: function(xhr){xhr.setRequestHeader(header, token);},
		url:"/admin/uploadSummernoteImg",
		type:"post",
		data: data,
		contentType: false,
		processData: false,
		//enctype: 'multipart/form-data',
		success:function(data){
			if(data==""){
				alert("이미지파일이 아닙니다.");return;
			}
			$(el).summernote('editor.insertImage', data);
		}
	});
}