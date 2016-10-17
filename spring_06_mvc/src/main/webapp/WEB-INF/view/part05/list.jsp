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

	<a href="memInsert.htm">글쓰기</a>
	<c:forEach var="dto" items="${aList}">
		<p>${dto.num }/ ${dto.name } / ${dto.age } / ${dto.loc }</p>
	</c:forEach>

</body>
</html>