<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//로그 아웃 처리 - session을 지우는 것으로 처리 : 처리문 이후로는 session 을 사용할수 없다.
session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그 아웃 </title>
</head>
<body>
<h1>로그 아웃 </h1>
<p>
로그 아웃 처리가 되었습니다.
</p>
<button onclick="location='loginForm.jsp'">로그인</button>
</body>
</html>