<!-- sitemesh 사용을 위한 설정 파일 -->
<!-- 작성자 : 박현철 -->
<!-- 작성일 : 2020-02-24 -->
<!-- 최종수정일 : 2020-02-24 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!-- JSTL : JSP별로 따로 설정해야 한다. -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Webjjang::<decorator:title /></title>
<!-- web 라이브러리 : 공통으로 사용 : 여기서만 선언해 주면된다. -->
  <!-- BootStrap -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <!-- jQuery UI : datepicker -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
  <!-- Icons : Awesome 5, 4, BootStrap, Google Icon -->
<!--   <script src="https://kit.fontawesome.com/yourcode.js"></script>
 -->  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  
<style type="text/css">
header, footer {
	background: black;
	color: white;
}

pre {
	background: white;
	border: 0px;
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 25px;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}

article {
	min-height: 600px;
	padding: 10px;
}

#welcome {
	color: grey;
	margin: 0 auto;
}
</style>
<!-- <script type="text/javascript" src="../js/jquery.js"></script>
 --><script type="text/javascript">
	$(document).ready(function() {
	});
</script>
<decorator:head/>
</head>
<body>

<!--  <div class="container"> -->
    <!-- 메뉴나 로고 등 -->
	<header style="background: black;">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
			    <!-- 로고 -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/main.do">Logo</a>
				</div>
				<!-- 모듈 메뉴 -->
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li><a href="/notice/list.do">공지사항</a></li>
						<li><a href="/image/list.do">이미지</a></li>
						<li><a href="/board/list.do">일반게시판</a></li>
						<li><a href="/qna/list.do">질문답변</a></li>
						<!-- 회원관리는 관리자 권한으로 로그인하면 보인다. -->
						<c:if test="${!empty login }">
							<!-- 일반회원 메뉴 -->
							<li><a href="/message/list.do">메시지
								<span class="badge" id="newMsgCnt">${login.newMsgCnt }</span></a></li>
							<!-- 관리자 메뉴 -->
							<c:if test="${login.gradeNo==9 }">
								<li><a href="/member/list.do">회원관리</a></li>
							</c:if>
						</c:if>
					</ul>
					<!-- 오른쪽에 위치하는 메뉴바 -->
					<ul class="nav navbar-nav navbar-right">
						<!-- 로그인을 안한 경우의 메뉴 -->
						<c:if test="${empty login }">
							<li><a href="/member/joinForm.do"><span
									class="glyphicon glyphicon-user"></span> 회원가입</a></li>
							<li><a href="/member/login.do"><span
									class="glyphicon glyphicon-log-in"></span> Login</a></li>
						</c:if>
						<!-- 로그인은 한 경우의 메뉴 -->
						<c:if test="${!empty login }">
							<li id="welcome"><a href="/member/view.do">
								<img src="${login.photo }" style="width:30px;height:35px;"
								 class="img-circle">
							${login.name}[${login.gradeName }]</a></li>
							<li><a href="/member/logout.do"><span
									class="glyphicon glyphicon-log-in"></span> Logout</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<article class="container">
		<decorator:body />
	</article>
	<footer class="container-fluid text-center"
	 style="background: black;">
		<h2>웹짱커뮤니티</h2>
		<p>이 홈페이지의 저작권은 박현철에게 있습니다.</p>
	</footer>
<!-- </div> -->
</body>
</html>
