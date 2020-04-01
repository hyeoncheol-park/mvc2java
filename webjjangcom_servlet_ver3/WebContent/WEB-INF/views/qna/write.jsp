<%@page import="com.webjjang.qna.dto.QnaDTO"%>
<%@page import="com.webjjang.member.dto.LoginDTO"%>
<%@page import="com.webjjang.main.Execute"%>
<%@page import="com.webjjang.bean.Beans"%>
<%@page import="com.webjjang.main.Service"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 질문다변의 질문 하기 처리 - Beans객체에 등록이 되어 있는지 확인한다.
// service가 Service 인터페이스를 상속 받고 있어야한다.
Service service = Beans.getService("qnaWriteService");
 
//이곳을 실행했다는 처리내용 출력
System.out.println("질문하기 처리 --- qna/write.jsp");
//한글처리 -> 서버의 iso-18 ~~코드를 사용하므로 utf-8과는 다르다. 한글이 깨진다.
// request.setCharacterEncoding("utf-8"); 필터 사용
//1. 데이터를 받는다.
//id 는 로그인한 후에 처리해야 하므로 session에서 부터 꺼내는 것으로 한다,.
String id = ((LoginDTO)session.getAttribute("login")).getId();

String title = request.getParameter("title");
String content = request.getParameter("content");

//2. dto객체를 만든다.
QnaDTO dto = new QnaDTO();
dto.setTitle(title);
dto.setContent(content);
dto.setId(id);
//3. service객체를 호출해서 DB에 데이터를 넣는다.
Execute.service(service, dto);
//글쓰기가 끝나면 자동으로 list로 이동한다.
response.sendRedirect("list.jsp");
%>
