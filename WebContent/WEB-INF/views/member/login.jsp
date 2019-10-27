<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${msg}
<h1>test</h1>
<form action="/member/login" method="POST">
	<input type="text" name="id">
	<input type="text" name="pw">
	<button>로그인</button>
</form>

</body>
</html>