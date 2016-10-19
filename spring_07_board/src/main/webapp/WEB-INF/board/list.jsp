<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	
	<form action="write.sb" id="frm" name="frm" method="get">
		<input type="submit" id="btnWrite" value="글쓰기"/>
	</form>

	<div id="bodywrap">
		<table>
			<tr>
				<th width="5%">번호</th>
				<th width="45%">제목</th>
				<th width="20%">글쓴이</th>
				<th width="5%">조회수</th>
			</tr>

			<c:forEach items="${aList }" var="dto">
				<tr>
					<td width="5%">${dto.num}</td>
					<td width="45%">
						<c:url var="content" value="view.sb">
							<c:param name="currentPage" value="${pv.currentPage}" />
							<c:param name="num" value="${dto.num}" />
						</c:url>
						<c:if test="${dto.re_level !=0 }">
							<!-- 정적 페이지 이미지 출력을 위해 web.xml servlet-mapping 등록 -->
							<img alt="" src="images/level.gif" width="${20*dto.re_level}"
								height="15" />
							<img alt="" src="images/re.gif" />
						</c:if>
						<a href="${content}"> ${dto.subject}</a>
					</td>
					<td width="20%">${dto.writer}</td>
					<td width="5%">${dto.readcount}</td>
				</tr>
			</c:forEach>

		</table>

		<div>
			<c:if test="${pv.startPage > 1 }">
				<c:url var="per" value="list.sb">
					<c:param name="currentPage" value="${pv.startPage - pv.blockPage }"></c:param>
				</c:url>
				<a href="${per}">이전</a>
			</c:if>

			<c:forEach begin="${pv.startPage}" end="${pv.endPage }" step="1"
				var="i">
				<c:url var="currPage" value="list.sb">
					<c:param name="currentPage" value="${i}" />
				</c:url>
				<a href="${currPage}"><c:out value="${i}"></c:out></a>
			</c:forEach>

			<c:if test="${pv.endPage < pv.totalPage }">
				<c:url var="next" value="list.sb">
					<c:param name="currentPage" value="${pv.startPage + pv.blockPage }"></c:param>
				</c:url>
				<a href="${next}">다음</a>
			</c:if>
		</div>

	</div>
</body>
</html>