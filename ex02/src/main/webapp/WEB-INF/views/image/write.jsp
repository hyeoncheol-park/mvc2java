<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 글쓰기</title>
</head>
<body>
<h1>이미지 게시판 글쓰기</h1>
<form action="write.do" method="post" id="writeForm"
enctype="multipart/form-data">
	<!-- bootstrap의 폼 : form -> form-group -> form-control -->
	<!-- 제목입력 -->
	<div class="form-group">
		<label for="title">제목</label>
		<!-- 입력한 데이터의 앞뒤 공백문자는 제거(trim())
   			 id, class : 화면 컨트롤을 위해서(빠른 선택), name : 넘어가는 데이터 이름 -->
<!-- 			<input type="text" class="form-control" id="title" name="title" -->
<!-- 				required="required" pattern="^.{4,100}$" -->
<!-- 				title="제목을 4~100 글자 사이로 입력하셔야 합니다."> -->
		<input type="text" class="form-control" id="title" name="title"
			title="제목을 4~100 글자 사이로 입력하셔야 합니다.">
	</div>
	<!-- 내용 입력 -->
	<div class="form-group">
		<label for="content">내용</label>
<!-- 			<textarea class="form-control" rows="5" id="content" -->
<!-- 			 name="content" required="required" ></textarea> -->
		<textarea class="form-control" rows="5" id="content"
		 name="content"></textarea>
	</div>
	<!-- 작성자 :login 정보를 사용:Controller에서 session을 이용한다. -->
	<!--  첨부파일 -->
	<div class="form-group">
		<label for="multFile">작성자</label>
		<input type="file" class="form-control" id="multiFile"
			name="multiFile" required="required">
	</div>
	
	<!-- 버튼처리 : button은 기본 type이 submit(데이터 넘기는 함수 호출)이다. -->
	<button>등록</button> 
	<button type="reset">다시입력</button>
	<button class="cancelBtn" type="button">취소</button>

</form>
</body>
</html>