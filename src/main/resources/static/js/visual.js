/**
 * 
 */
 
$(function(){
	$.ajax({
		url:"/customer/visuals",
		success:function(result){
			$(".addvisual").html(result);
			
		}
	});
});