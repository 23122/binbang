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
    placeholder:'&#13;&#10;상세설명 작성 주의사항&#13;&#10;&#13;&#10;-방 정보와 관련없는 홍보성 정보는 입력하실 수 없습니다.&#13;&#10;-중개수수료를 언급한 내용은 입력할 수 없습니다.&#13;&#10;&#13;&#10;*주의사항 위반시 허위매물로 간주되어 매물 삭제 및 이용의 제한이 있을 수 있습니다.',
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