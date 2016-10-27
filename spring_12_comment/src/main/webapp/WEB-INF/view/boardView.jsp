<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<!-- <script src="https://cdnjs.com/libraries/handlebars.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script type="text/javascript">
	Handlebars.registerHelper("newDate", function(timeValue) {
		var dateObj=new Date(timeValue);
		var year=dateObj.getFullYear();
		var month=dateObj.getMonth()+1;
		var date=dateObj.getDate();
		return year+"/"+month+"/"+date;
// 		return new Date(timeValue);
	});

	$(document).ready(function() {
		$("#replyAddBtn").on("click",function() {
				$.ajax({
					type : "GET", // 메소드 get, post
					dataType : "json", // 응답형식
					url : "replyInsertList.do?bno="
							+ '${boardDTO.bno}' + "&replytext="
							+ $("#newReplyWriter").val()
							+ "&replyer="
							+ $("#newReplyText").val(), // 요청 주소
					success : reply_list_result
			});
		});

		$(document).on("click", ".timeline button", function() {
			switch ($(this).text()) {
			case "delete":
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "replyDelete.do",
					data : "bno=${boardDTO.bno}&rno="+$(this).prop("id"),
					success : reply_list_result
				});
				break;

			case "update":

				break;
			}			
		});
	});

	function reply_list_result(data) {
		$(".timeline").empty();
		$(".timeline")
				.append(
						'<li class="time-label" id="repliesDiv"><span class="bg-green"> Replies List <small id="replycntSmall"> [ '+data.length+' ] </small>	</span></li>');
		$.each(data,function(index, value) {
							// 			$(".timeline").append("<li class='time_sub' id='"+value.rno+"'>"+"<p>"+value.replyer+"</p>"
							// 			+"<p>"+value.replytext+"</p>"
							// 			+"<p>"+value.regdate+"</p>"
							// 			+"<p><button id='"+value.rno+"'>delete</button>  <button id='"+value.rno+"'>update</button></p>"
							// 			+"</li>");

							var source = "<li class='time_sub' id='{{rno}}'><p>{{replyer}}</p>"
							+"<p>{{replytext}}</p><p>{{newDate regdate}}</p>"
							+"<p><button id='{{rno}}'>delete</button> <button id='{{rno}}'>update</button></p></li>";
							var template = Handlebars.compile(source);
							$(".timeline").append(template(value));
						});

	}
</script>
</head>
<body>
	<div class="wrap">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Title</label> <input type="text"
					name='title' class="form-control" value="${boardDTO.title}"
					readonly="readonly">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Content</label>
				<textarea class="form-control" name="content" rows="3"
					readonly="readonly">${boardDTO.content}</textarea>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Writer</label> <input type="text"
					name="writer" class="form-control" value="${boardDTO.writer}"
					readonly="readonly">
			</div>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
			<button type="submit" class="btn btn-danger" id="removeBtn">REMOVE</button>
			<button type="submit" class="btn btn-primary" id="goListBtn">GO
				LIST</button>
		</div>
		<hr />

		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">ADD NEW REPLY</h3>
			</div>
			<div class="box-body">
				<label for="exampleInputEmail1">Writer</label> <input
					class="form-control" type="text" placeholder="USER ID"
					id="newReplyWriter"> <label for="exampleInputEmail1">Reply
					Text</label> <input class="form-control" type="text"
					placeholder="REPLY TEXT" id="newReplyText">

			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<button type="button" class="btn btn-primary" id="replyAddBtn">ADD
					REPLY</button>
			</div>
		</div>


		<!-- The time line -->
		<ul class="timeline">
			<!-- timeline time label -->
			<li class="time-label" id="repliesDiv"><span class="bg-green">
					Replies List <small id='replycntSmall'> [
						${fn:length(boardDTO.replyList)} ] </small>
			</span></li>

			<c:forEach items="${boardDTO.replyList}" var="replyDTO">
				<li class="time_sub" id="${replyDTO.rno}">
					<p>${replyDTO.replyer}</p>
					<p>${replyDTO.replytext }</p>
					<p><fmt:formatDate value="${replyDTO.regdate}" pattern="yyyy/MM/dd" dateStyle="short" /></p>
					<p>
						<button id="${replyDTO.rno }">delete</button>
						<button id="${replyDTO.rno }">update</button>
					</p>

				</li>
			</c:forEach>
		</ul>


		<div class='text-center'>
			<ul id="pagination" class="pagination pagination-sm no-margin ">

			</ul>
		</div>



	</div>
</body>
</html>