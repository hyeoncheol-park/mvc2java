<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 폼</title>
<!--  jquery lib 는 default_ decoratior.jsp 에서 등록 -->
 <script type="text/javascript" src="/js/regExUtil.js"></script>
 <script type="text/javascript">
 $(function(){
// 	 js 패턴 객체
	 
// 	 title_RegEx= /^.{4,100}$/;
// 	 content_RegEx =/^.{4,1000}$/ ;
// 	 writer_RegEx= /^.{2,10}$/;
	 
// 	 //writeForm안에 데이터 넘어갈때(sumbit) 데이터 testing 
	 $("#writeForm").submit(function(){
// 		alert("데이터가 넘어 가기전 확인 함수.");
// 		// title 체크
// 		//앞뒤 공백제거
// 		$("#title").val($("#title").val().trim());
// // 		alert("["+ $("#title").val()+"]");
// 		if(!title_RegEx.test($("#title").val())){
// 			//경고창을 뛰운다.
// 			alert("제목은  4글자 이상 100글자 이내로 작성하셔야 합니다.");
// // 	        다시 제목을 입력할 수 있도록 제목 입력란에 커서를 위치 시킨다.
// 			$("#title").focus();
// 			return false;
// 		}
// 		//앞뒤 공백제거
// 		$("#content").val($("#content").val().trim());
// 		// content 체크
// 		if(!content_RegEx.test($("#content").val())){
// 			//경고창을 뛰운다.
// 			alert("내용은  4글자 이상 1000글자 이내로 작성하셔야 합니다.");
// // 	        다시 제목을 입력할 수 있도록 제목 입력란에 커서를 위치 시킨다.
// 			$("#content").focus();
// 			return false;
// 		}
// 		//앞뒤 공백제거
// 		$("#writer").val($("#writer").val().trim());
// 		// writer 체크
// 		if(!writer_RegEx.test($("#writer").val())){
// 			//경고창을 뛰운다.
// 			alert("작성자는  2글자 이상 10글자 이내로 작성하셔야 합니다.");
// // 	        다시 제목을 입력할 수 있도록 제목 입력란에 커서를 위치 시킨다.
// 			$("#writer").focus();
// 			return false;
// 		}
		//데이터를 검사하는 regExUtil.js 파일  사용한 데이터 검사
		//제목 - 데이터가 유효하지 않으면(!) 더이상 진행하지 않고 false 를 리턴한다.	
		if(!inputDataCheck(title_RegEx, "#title", title_err_msg))
			return false;
		//내용- 데이터가 유효하지 않으면(!) 더이상 진행하지 않고 false 를 리턴한다.	
		if(!inputDataCheck(content_RegEx,"#content",content_err_msg))		 
			return false;
		//작성자 - 데이터가 유효하지 않으면(!) 더이상 진행하지 않고 false 를 리턴한다.	
		if(!inputDataCheck(writer_RegEx,"#writer",writer_err_msg))
			return false;
		 
	 });
	 
 });
 
 </script>
</head>

<body>
<div class="container">
<h1>글쓰기</h1>
<!--  uri 작성시 *.do  spring 3.1가지의 기본  url에 *.do pattern 기본으로 사용 -->
<!-- 많은 데이터를 넘길때 form -->
<form action="write.do" method="post" id="writeForm">			
	  <div class="form-group">
    <label for="title">제목</label>
    
    <input type="text" class="form-control" id="title" name="title"
    title="제목입력">
<!--     required="required" pattern="^.{4,100}$"  -->
<!--     title="제목은 4자부터 100글자 사이로 입력하셔야 합니다."> -->
	
	</div>
	<div class="form-group">
  <label for="content">내용</label>
  <textarea class="form-control" rows="5" id="content" name="content"></textarea>
<!--   name="content" required="required" > -->
 
   </div> 
	
	  <div class="form-group">
<!-- 	  입력한 데이터의 앞뒤 공백문자는 제거 (trim()) -->
<!--           id,class:화면 컨트롤을 위해서(빠른선택),name : 넘어가는 데이터 이름 -->
    <label for="writer">작성자</label>
    <input type="text" class="form-control" id="writer" name="writer">
<!--     required="required" pattern="^.{2,10}$" title="작성자는 2~10 글자 사이로  작성해야합니다"> -->
	</div>
	<button>등록</button>
	<button type="reset">다시입력</button>
	<button onclick="history.back()">취소</button>
	
</form>
</div>
</body>
</html>