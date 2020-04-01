<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- bootstrap 라이버리 등록 : cdn 방식(네트워크 통해서 받는다) --> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

   <script>
  $( function() {
	  //생년월일 날짜 캘린더 사용
    $( "#birth" ).datepicker();   
      $( "#birth" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
   
      // 이벤트 처리
      $("#id").on("keyup", function(){
//       	alert("아이디 입력");
  		// 입력한 아이디의 값을 가져와서 앞 뒤 공백을 제거한다.
  		var id = $("#id").val().trim();
  		// 공백을 제거한 아이디를 다시 아이디 입력란에 셋팅해 준다.
  		 $("#id").val(id);
  		if(id.length == 0){
  			$("#idCheck").html("아이디를 입력하셔야만 합니다.");
  		} else if(id.length < 4 || id.length > 20) {
  			$("#idCheck").html("아이디는 4자이상 20자 이내로 작성하셔야 합니다.");
  		} else {
  			// Ajax를 이용해서 서버에 갔다가 오면서 결과를 idCheck에 넣는다.\
  			$("#idCheck").load("/ajax/idCheck.do?id="+id);
  		}
      }); // 아이디 중복체크의 끝
      
      // 데이터를 넘기기 전 데이터확인 처리
      $("#joinForm").on("submit", function(){
      	if($("#idCheck").text().indexOf("사용 가능한") <= -1){
      		alert("사용 가능한 아이디를 입력해 주세요.");
      		$("#id").focus();
      		return false;
      	}
      });
      
    } );
  </script>
</head>
<body>
<div class="container">

<h2>회원가입</h2>
<!-- hearder -> h1: 첫번쨰 나오는 제목 /자동 줄바꿈 -->
<form action="join.do"method="post" id ="joinForm">
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
       title="4-20자 영숫자, 맨 앞자는 영문자 " autocomplete="off" 
       />
       <div id="idCheck">아이디를 입력하셔야만 합니다.</div>
    </div>
<!--     비밀번호입력 -->
    <div class="form-group">
      <label for="pw">비밀번호:</label>
      <input type="password" class="form-control" id="pw"  name="pw"
      maxlength="20" required="required"
     pattern="^.{4,20}$" title="4-20이내의글자입력" placeholder="비밀번호 입력">
    </div>
<!--     비밀번호 확인입력 -->
    <div class="form-group">
      <label for="pw2">비밀번호 확인:</label>
      <input type="password" class="form-control" id="pw2"  name="pw2"
      maxlength="20" required="required"
     pattern="^.{4,20}$" title="4-20이내의글자입력" placeholder="비밀번호 확인입력">
    </div>
<!--     이름입력 -->
   <div class="form-group">
      <label for="name">이름:</label>

      <input type="text" class="form-control" id="id" placeholder="이름입력" name="name"
       maxlength="20" required="required" 
       pattern="^[가-힣]{2,10}$"
       title="2-10한글" 
       />
    </div>
    	    <div class="form-group">
      <label for="gender">성별:</label>
    <label class="radio-inline">
    <input type="radio" name="gender" checked
     value="남자" >남자
    </label>
    <label class="radio-inline">
    <input type="radio" name="gender" checked 
    value="여자"> 여자
    </label>
    </div>
<!--     생년월일 -->
   <div class="form-group">
      <label for="birth">생년월일:</label>
<!-- pattern : yyyy-mm-dd -->
      <input type="text" class="form-control" id="birth" placeholder="생년월일을 입력 - 클릭" name="birth"
       maxlength="10" required="required" 
       pattern="^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$"   
       title="날짜를 선택해주세요"  
       />
    </div>
<!-- 	    전화번호 입력 -->
	    <div class="form-group row">
	      <div class="col-md-2">
	      <label for="tel">핸드폰 : </label>
	      </div>
	      <div class="col-md-2">
		      <select class="form-control" id="tel1" name="tel">
			    <option selected="selected">010</option>
			    <option>011</option>
			    <option>016</option>
			    <option>017</option>
			    <option>018</option>
			    <option>019</option>
			  </select>
		  </div>
	      <div class="col-md-2">
			<!--  maxlength : 최대 입력 글자수, pattern : yyyy-mm-dd 
			title : 마우스를 올려 놨을때 메시지, 또는 형식에 안맞을 때 사용하는 메세지  -->
		      <input type="number" class="form-control" id="tel2"
		       placeholder="3-4자리 숫자" name="tel"
		       maxlength="4" required="required" 
			   pattern="^[0-9]{3,4}$" title="숫자로 3-4자리입력"
		       />
	       </div>
	      <div class="col-md-2">
			<!--  maxlength : 최대 입력 글자수, pattern : yyyy-mm-dd 
			title : 마우스를 올려 놨을때 메시지, 또는 형식에 안맞을 때 사용하는 메세지  -->
		      <input type="number" class="form-control" id="tel3"
		       placeholder="4자리 숫자" name="tel"
		       maxlength="4" required="required" 
			   pattern="^[0-9]{4}$" title="숫자로 4자리입력"
	       		/>
	       </div>
	    </div>
  
 
<!--     이메일 입력 -->
   <div class="form-group">
      <label for="email">이메일:</label>
<!-- 타입을 이메일로 작성하면 이메일 형식에 맞는지도 검사한다. -->
      <input type="email" class="form-control" id="email" placeholder="email입력" name="email"
       maxlength="50" required="required" 
       pattern="^[a-zA-Z][_0-9a-zA-Z-]{3,19}+@[0-9a-zA-Z-]+\\.[0-9a-zA-Z-]*$"
       title="이메일주소 이메일은 맨앞에 영문자로 시작하고 중간에 '@'이 들어가야 합니다  " 
       /><br/>
        이메일 형식 - 예:iqeqpc@naver.com 
    </div>
  
  
    <button type="submit" class="btn btn-default">회원가입</button>


</form>
</div>
</body>
</html>