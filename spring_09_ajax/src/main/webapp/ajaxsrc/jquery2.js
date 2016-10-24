$(document).ready(function() {
	$("#btn").on("click", process);

	
});

function process() {
	$.ajax({
		type : "POST", // 메소드 get, post
		dataType : "text", // 응답형식
		url : "loginPro.do", // 요청 주소
		data : "id=" + $("#id").val()+"&pass="+$("#pass").val(),
		success : function(res) {
			$("div").html(res);
		}
	});
}
