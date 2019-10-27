<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>reg</h1>
<form action="/board/register" method="POST" enctype="multipart/form-data">
    제목 : <input type="text" name="title"><br>
    작성 : <input type="text" name="writer"><br>
    작성 : <input type="radio" name="notice"><br>
    첨부 : <input type="file" name="fs" multiple="multiple"><br>
    내용 : <textarea name="content" cols="30" rows="10"></textarea><br>
    <button>올리기</button>
    <input type="reset" value="취소">
</form>
</body>
</html>