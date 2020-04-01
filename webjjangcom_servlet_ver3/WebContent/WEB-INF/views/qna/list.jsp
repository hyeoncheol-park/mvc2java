<%@page import="com.webjjang.main.Execute"%>
<%@page import="com.webjjang.bean.Beans"%>
<%@page import="com.webjjang.main.Service"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
// 여기가 자바 입니다.
// DB에서 데이터 가져오기 :[controller] - service - dao : List<BoardDTO>
Service service = Beans.getService("qnaListService");
request.setAttribute("list", Execute.service(service, 1));
%> 

<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>질문답변 리스트</title>
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
<!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> -->
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
// 	alert("start");
	// 이벤트 처리
	$(".dataRow").click(function(){
// 		alert("dataRow click");
		no = $(this).find(".no").text();
// 		alert(no);
		location = "view.jsp?no=" + no + "&cnt=1";
	});
});
</script>

</head>

<body>
<div class="container">
	<h1>질문답변 리스트</h1>
	<table class="table">
	<!-- 테이블의 한줄 -->
	<!-- 항목의 제목 -->
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자(ID)</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<!-- 데이터 표시줄 : 데이터가 있는 만큼 반복 처리한다. -->
	<c:forEach items="${list }" var="dto">
<%-- 		<tr onclick="location='view.jsp?no=${dto.no}'" class="dataRow"> --%>
		<tr class="dataRow">
			<td class="no">${dto.no }</td>
			<td>
				${(dto.levNo > 0)? "":"" }
				<c:forEach begin="1" end="${dto.levNo * 5 }">&nbsp;</c:forEach>
				<i class="material-icons">${(dto.levNo > 0)?"&#xe5da;":"" }</i>
				${dto.title }</td>
			<td>${dto.name }[${dto.id }]</td>
			<td>${dto.writeDate }</td>
			<td>${dto.hit }</td>
		</tr>
	</c:forEach>
	
	<!-- 글쓰기 버튼 -->
	<tr>
		<td colspan="6">
			<a href="writeform.jsp"><button>질문하기</button></a>
		</td>
	</tr>
	</table>
</div>
</body>
</html>