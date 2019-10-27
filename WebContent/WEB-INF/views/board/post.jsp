<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <Style>
        tr td img{width: 100px; height: auto;}
    </Style>
</head>
<body>
<h1>post</h1>
${postData}

<div>
 <table>
 <tr>
<c:forEach var="fname" items="${postData.fnames}">
	<td><img src="/board/img?fname=${fname}" ></td> 
	<td><a href="/board/download?fname=${fname}">${fname}</a></td>
</c:forEach>
  </tr>
      </table>
   </div>
<a href="/board/list?page=${param.page}&amount=${param.amount}&category=${param.category}&val=${param.val}">목록가기</a>
</body>
</html>