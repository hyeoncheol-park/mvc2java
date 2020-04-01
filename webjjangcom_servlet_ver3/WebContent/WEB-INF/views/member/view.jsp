<%@page import="com.webjjang.main.Execute"%>
<%@page import="com.webjjang.member.dto.LoginDTO"%>
<%@page import="com.webjjang.bean.Beans"%>
<%@page import="com.webjjang.main.Service"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
 Service service = Beans.getService("memberLoginService");  
     
    //한글처리
    request.setCharacterEncoding("utf-8");
    //데이터 받기
  
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 처리 페이지</title>
</head>
<body>

<h2>로그인 정보 출력</h2>
<p>
아이디: ${login.id}<br/>
이름: ${login.name}<br/>
등급번호: ${login.grade}<br/>
등급이름: ${login.gradeName}<br/>
</p>
<button onclick="location='logout.jsp'">로그아웃</button>

</body>
</html>