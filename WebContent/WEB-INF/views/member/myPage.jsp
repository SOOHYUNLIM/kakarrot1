<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${member}
<c:if test="${not empty member.pic}">
	<img src="/board/img?fname=${member.pic}" width="100px" height="auto">
</c:if>
</body>
</html>