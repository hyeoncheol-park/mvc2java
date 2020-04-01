<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문하기 폼</title>

<!-- bootstrap 라이브러리 등록 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>
<body>
<h1> 로그인 처리 페이지</h1>
<% if(session.getAttribute("login") == null){ %>
<h2>로그인 </h2>
<p>
로그인 먼저 해주세요
</p>
<button onclick="location='loginForm.jsp'">로그인 페이지 이동</button>
<%} else { %>
<div class="container">
<h1>질문하기</h1>
<!-- 많은 데이터를 넘길때 form -->
<form action="write.jsp" method="post">
<!-- 제목 입력 : 한줄 키보드 입력-->
  <div class="form-group">
    <label for="title">제목:</label>
<!--      id한개 태그 화면단 컨트롤 하기 위해서 붙인다./name:넘어가는 데이터 키에 해당된다. -->
    <input type="text" class="form-control" id="title" name="title">
  </div>
  
<!-- 제목 입력: 여러줄- 키보드 입력 -->
<div class="form-group">
  <label for="content">내용</label>
  <textarea class="form-control" rows="5" id="content" name="content"></textarea>
</div>
<!--   button type : submit - submit() 호출 데이터 넘겨준다. -->
<!--    :reset - value 속성이 없으면 비워진 상태, value 속성이 있으면 value 의 값으로 셋팅 -->
<!--    button -형태만 버튼이고 아무런 동작을 하지 않는다. 동작은 따로 정의해준다. -->
  <button type="submit" class="btn btn-default">등록</button>
  <button type="reset" class="btn btn-default">다시입력</button>
  <button type="button" class="btn btn-default"
  onclick="history.back()">취소</button>
<%} %>
</form> 

</div>
</body>
</html>