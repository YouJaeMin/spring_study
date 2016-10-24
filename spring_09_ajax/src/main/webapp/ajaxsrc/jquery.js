$(document).ready(function() {
	$("#btn").on("click", process);

	
});

function process() {
	$.ajax({
		type : "GET", // 메소드 get, post
		dataType : "text", // 응답형식
		url : "customer.do?name=" + $("#name").val(), // 요청 주소
		success : function(res) {
			$("div").html(res);
		}
	});
}
