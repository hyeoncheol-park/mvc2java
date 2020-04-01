<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- bootstrap 라이버리 등록 : cdn 방식(네트워크 통해서 받는다) --> 
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

<h1>로그인</h1>
<!-- hearder -> h1: 첫번쨰 나오는 제목 /자동 줄바꿈 -->
<form action="login.jsp"method="post">
<div class="form-group">
      <label for="id">아이디:</label>
<!-- 아이디 입력 – 4자 이상 20자 이내, 영문자와 숫자만 가능, 맨 앞자리는  -->
<!--                         영문자, 필수입력 -->
<!-- maxlength : 최대이력 글자수 -->
<!-- title: 마우스를 올려놨을때 메시지, 또는 형식에 암자을때 경고 메시지 -->
<!--  placeholder 흰색으로 미리 나옴   password *****오 로 나옴-->
      <input type="text" class="form-control" id="id" placeholder="아이디 입력" name="id"
       maxlength="20" required="required" 
       pattern="^[A-Za-z][A-Za-z0-9]{3,19}$"
       title="4-20자 영숫자, 맨 앞자는 영문자" 
       />
    </div>
    <div class="form-group">
      <label for="pw">비밀번호:</label>
      <input type="password" class="form-control" id="pw"  name="pw"
      maxlength="20" required="required"
     pattern="^.{4,20}$" title="4-20이내의글자입력" placeholder="비밀번호 입력">
    </div>
   
    <button type="submit" class="btn btn-default">로그인</button>


</form>
</div>
</body>
</html>