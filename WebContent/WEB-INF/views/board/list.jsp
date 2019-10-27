<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.paging {
	list-style: none;
}

.paging li {
	float: left;
	margin-right: 0.3em;
	font-weight: bold;
	text-align: center;
	border: 1px solid blue;
	width: 5em;
	border-radius: 0.3em;
}

.current {
	background-color: blue;
}
</style>
</head>
<body>
	<h1>list</h1>

<div style="${login=='display:none' ? '' : 'display:none'}">
	<c:if test="${not empty member.pic}">
	<img src="/board/img?fname=${member.pic}" width="100px" height="auto">
	</c:if>
	<p>${member.name}님 환영합니다!</p>
	<a href="/member/myPage">내 정보</a>
	<a href="/member/logout">로그 아웃</a>
</div>
<div style="${login}">
<p>로그인 해주세요!</p>
<a href="/member/login">로그인</a>
	<a href="/member/register">회원가입</a>
</div>
	<ul class='list'>
		<c:forEach var="vo" items="${list}">
			<li>
				<div>
					<span>${vo.bno}</span> <span> ${vo.writer}</span> <span><a
						href="/board/post?bno=${vo.bno}&page=${pm.paging.page}&amount=${pm.paging.amount}&category=${param.category}&val=${param.val}"><c:out
								value="${vo.title}" /></a></span> <span>${vo.updatedate}</span>
				</div>
			</li>
		</c:forEach>
	</ul>


	<ul class='paging'>

		<c:if test="${pm.prev}">
			<li><a
				href="/board/list?page=${pm.start-1}&amount=${pm.paging.amount}&category=${param.category}&val=${param.val}">이전</a></li>
		</c:if>
		<c:forEach begin="${pm.start}" end="${pm.end}" var="num">
			<li class='${pm.paging.page == num ? "current" : ""}'><a
				href="/board/list?page=${num}&amount=${pm.paging.amount}&category=${param.category}&val=${param.val}">${num}</a>
			</li>
		</c:forEach>

		<c:if test="${pm.next}">
			<li><a
				href="/board/list?page=${pm.end+1}&amount=${pm.paging.amount}&category=${param.category}&val=${param.val}">다음</a></li>
		</c:if>
	</ul>
	<br>
	<br>

	<form action="/board/list" method="GET">
		<select name="category">
			<option value="title">제목</option>
			<option value="writer">작성자</option>
			<option value="content">내용</option>
		</select> <input type="text" name="val">
		<button>검색</button>
	</form>
	<a href="/board/register">등록</a>


</body>
</html>