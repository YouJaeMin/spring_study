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
<style type="text/css">
.modifyShow {
	display: block;
	position: absolute;
	top: 150px;
	left: 200px;
	width: 400px;
	height: 150px;
	z-index: 1000;
	border: 1px solid black;
	background-color: yellow;
	text-align: center;
}

.modifyHide {
	visibility: hidden;
	width: 0px;
	height: 0px;
}

.fileDrop{
	width: 200px;
	height: 50px;
	border: 1px dotted blue;
	overflow: auto;
}

</style>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script type="text/javascript">
	var urno = "";
	var fileList = "";

	Handlebars.registerHelper("newDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
		// 		return new Date(timeValue);
	});
	
	Handlebars.registerHelper("newUpload", function(rno, filename) {
		if(filename != null){
// 			return filename.substring(filename.indexOf("_")+1);
			return new Handlebars.SafeString
			("<a href=download.do?rno="+rno+">" + filename.substring(filename.indexOf("_")+1) + "</a>");
			
		}else{
			return "파일없음";
		}
	});

	

	$(document).ready(function() {
				// 숨기기
				$("#modifyModal").addClass("modifyHide");
				
				
				//리플 저장
				$("#replyAddBtn").on("click",function() {
					var form_data = new FormData();
					form_data.append("bno", "${boardDTO.bno}");
					form_data.append("replyer", $("#newReplyWriter").val());
					form_data.append("replytext", $("#newReplyText").val());
					
// 					console.log("filename", $("#filename")[0].files[0]);
// 					if ($("#filename")[0].files[0] != undefined) {
// 						form_data.append("filename", $("#filename")[0].files[0]);
// 					}
					if(fileList != ""){
						form_data.append("filename", fileList);					
					}

					$.ajax({
							type : "POST", // 메소드 get, post
							dataType : "json", // 응답형식
							// 세트로
							contentType : false,
							entype : "multipart/form-data",
							processData : false,
							//
							url : "replyInsertList.do", // 요청 주소
							data : form_data,
							success : reply_list_result
						});
					
					$("#newReplyWriter").val("");
					$("#newReplyText").val("");
					$(".fileDrop").empty();
					fileList="";
					});

				// 리플 수정 삭제
				$(document).on("click",	".timeline button",	function() {
							switch ($(this).text()) {
							case "delete":
								$.ajax({
									type : "POST",
									dataType : "json",
									url : "replyDelete.do",
									data : "bno=${boardDTO.bno}&rno="
											+ $(this).prop("id"),
									success : reply_list_result
								});
								break;

							case "update":
								urno = $(this).prop("id");
								$("#modifyModal").removeClass("modifyHide");
								$("#modifyModal").addClass("modifyShow");
								break;
							}
						});

				$('#btnClose').on('click', function() {
					$('#modifyModal').removeClass('modifyShow');
					$('#modifyModal').addClass('modifyHide');
					urno = "";
				});
				
				$('#btnModify').on('click', function() {
					
					var form_data = new FormData();
					form_data.append("bno", "${boardDTO.bno}");
					form_data.append("rno", urno);
					form_data.append("replytext", $("#updateReplyText").val());
					if ($("#filename2")[0].files[0] != undefined) {
						form_data.append("filename", $("#filename2")[0].files[0]);
					}
					
					$.ajax({
						type : "POST",
						dataType : "json",
						// 세트로
						contentType : false,
						entype : "multipart/form-data",
						processData : false,
						//
						url : "replyUpdate.do",
						data : form_data,
						success : reply_list_result
					});
					$("#updateReplyText").val('');
					$('#modifyModal').removeClass('modifyShow');
					$('#modifyModal').addClass('modifyHide');
					urno = "";
				});

				// 내PC 
				var userfile = "";
				$("#userpc").on("click", function() {
					userfile = $("<input type='file' id='userfile' />");
					userfile.click();
					userfile.change(function(e) {
						console.log($(userfile[0]).val());
						var temp = $(userfile[0]).val();
						var partname =  temp.substring(temp.lastIndexOf("\\")+1);
						str = "<p style='background-color:yellow'>"+partname+"</p>";
						$(".fileDrop").append(str);
						var dataList = userfile[0].files[0];
						fileList = dataList;
					});
				});
				
				// 삭제
				$("#filecencle").on("click", function() {
					$(".fileDrop").empty();
					fileList="";
				});
				
				
				
				//드래그 파일
				var obj = $(".fileDrop");
				obj.on("dragenter", function (e) {
					e.preventDefault();
					$(this).css("border", "2px solid #8885A1");
				});
				
				obj.on("dragover", function(e) {
					e.preventDefault();
					
				});
				
				obj.on("drop", function(e) {
					e.preventDefault();
					// 파일로 변환
					var files = e.originalEvent.dataTransfer.files;
					
					for (var i = 0; i < files.length; i++) {
						str = "<p style='background-color:yellow'>"+files[i].name+"</p>";
						obj.append(str);
						fileList = files[i];
					}
					
					
				});				
				
			});// end ready

	// 댓글 리스트 부르기
	function reply_list_result(data) {
		$(".timeline").empty();
		$(".timeline")
				.append(
						'<li class="time-label" id="repliesDiv"><span class="bg-green"> Replies List <small id="replycntSmall"> [ '
								+ data.length + ' ] </small>	</span></li>');
		$.each(data,function(index, value) {
// 			$(".timeline").append("<li class='time_sub' id='"+value.rno+"'>"+"<p>"+value.replyer+"</p>"
// 			+"<p>"+value.replytext+"</p>"
// 			+"<p>"+value.regdate+"</p>"
// 			+"<p><button id='"+value.rno+"'>delete</button>  <button id='"+value.rno+"'>update</button></p>"
// 			+"</li>");

			var source = "<li class='time_sub' id='{{rno}}'><p>{{replyer}}</p>"
					+ "<p>{{replytext}}</p><p>{{newDate regdate}}</p>"
// 					+ "<p><a href='download.do?rno={{rno}}'>{{newUpload rupload}}</a></p>"
					+ "<p>{{newUpload rno rupload}}</p>"
					+ "<p><button id='{{rno}}'>delete</button> <button id='{{rno}}'>update</button></p></li>";
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
					
<!-- 					<label for="filename">Upload</label> -->
<!-- 					 <input type="file"	id="filename" name="filename"> -->

					<div>
						<span id="userpc" style="cursor: pointer">내PC</span>
						<span id="filecencle" style="cursor: pointer">초기화</span>
					</div>
					<div class="fileDrop"></div>


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
					<p>
						<fmt:formatDate value="${replyDTO.regdate}" pattern="yyyy/MM/dd"
							dateStyle="short" />
					</p>
					<p>
						<c:choose>
							<c:when test="${replyDTO.rupload != null}">
							 <a href="download.do?rno=${replyDTO.rno}">
								${fn:substringAfter(replyDTO.rupload,"_")}
		          			 </a>
							</c:when>
							<c:otherwise>
								파일없음
							</c:otherwise>
						</c:choose>
					</p>
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

		<!-- Modal -->
		<div id="modifyModal">
			<p>
				<label for="updateReplyText">Reply Text</label> 
				<input
					class="form-control" type="text" placeholder="REPLY TEXT"
					id="updateReplyText">
			</p>
			<p>
				<label for="filename2">Upload2</label>
					 <input type="file"	id="filename2" name="filename2">
			</p>
			<p>
				<button id="btnModify">Modify</button>
				<button id="btnClose">Close</button>
			</p>
		</div>


	</div>
</body>
</html>