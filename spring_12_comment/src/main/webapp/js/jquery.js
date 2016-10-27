$(document).ready(function() {
	$("#replyAddBtn").on("click", process);
});

function process() {
	var bno = '${boardDTO.title}';
	alert('${boardDTO.title}');
	$.ajax({
		type : "GET", // 메소드 get, post
		dataType : "json", // 응답형식
		url : "replyInsertList.do?bno="+bno, // 요청 주소
		success : reply
	});
}

function reply(data) {
	$(".timeline").empty();
	$(".timeline").append('<li class="time-label" id="repliesDiv"><span class="bg-green">Replies List <small id="replycntSmall">[${fn:length(boardDTO.replyList)} ]</small></span></li>');
}
