<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<h1>글쓰기</h1>
<!-- 많은 데이터를 넘길때 form -->
<form action="write.do" method="post" id="writeForm">
<!-- input, select, textarea : 입력 항목 만들기 : 생략 -->
 제목: <input name="title"><br/>
 내용:<textarea rows="5" cols="100" name="content"></textarea>
 <br/>
 공지시작일: <input name="startDate"><br/>
 공지종료일: <input name="endDate"><br/>
<!--   button tag 의 기본 type 은 submit이다 그래서 생략 가능 --> --> 
 
	<button>등록</button>
	<button type="reset">다시입력</button>
<!-- 	버튼기능만 사용하고 다른 동작은 하지 않도록 하는 타입 : button -->
<!--  동작을 따로 정의 onclick(클릭할때 동작 ) /history.back() -->
	<button type="button" onclick="history.back()">취소</button>
   

	
</form>
</body>
</html>