<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<script type="text/javascript">
$(function() {
	// 하나의 글을 선택(tr tag를 클릭)하면 글번호를 받아내서 글보기로 보낸다.
	$(".dataRow").click(function() {
//			alert("main 글 클릭");
		// 이미지는 class로 no를 지정해 놨으므로 찾는다.
		var no = $(this).find(".no").text();
//			var uri = "/image/view.do";
		var uri = "";
//			alert(no);
		// 공지사항에서 클릭한 경우
		if (no == ""){
			no = $(this).data("no1");
//				uri = "/notice/view.do";
		}
		
		// 이동해야할 uri 결정
		if($(this).hasClass("image"))
			uri = "/image/view.do";
		else if($(this).hasClass("notice"))
			uri = "/notice/view.do";
			
// 			alert(no+"/"+uri);
		location = uri+"?no=" + no
				+ "&page=1"
				+ "&perPageNum=10";
	});
});

</script>


</head>
<body>

<h2>메인페이지</h2>
<div>
  <div class="panel panel-default">
  <div class="panel-heading">공지사항</div>
  <div class="panel-body"></div>
	<table class="table">
	<!-- 테이블의 한줄 -->
	<!-- 항목의 제목 -->
	<tr>
		<th>제목</th>
		<th>시작일</th>
		<th>종료일</th>
	</tr>
	<!-- 데이터 표시줄 : 데이터가 있는 만큼 반복 처리한다. -->
	<c:forEach items="${noticelist }" var="dto">
		<tr class="dataRow notice"  data-no1="${dto.no }" >
			<td>${dto.title }</td>
			<td>${dto.startDate }</td>
			<td>${dto.endDate }</td>
		</tr>
	</c:forEach>
	
	
	</table>
</div>
</div>
<!-- 이미지 8개를 나타내는 부분 -->
 <div class="panel panel-default">
  <div class="panel-heading">이미지 갤러리</div>
  <div class="panel-body">
  
  		<!-- 이미지 겔러리 리스트 작성 -->
		<div class="row">
		 <!-- 데이터가 있는 만큼 반복문처리 시작. -->
		 <c:forEach items="${imagelist }" var="dto">
			<!-- col 시작 : no, title, id, writeDate, fileName -->
		    <div class="col-md-3 dataRow image" >
		      <div class="thumbnail">
		          <img src="${dto.fileName }" alt="${dto.fileName }" style="width:100%">
		          <div class="caption">
		            <p><span class="no">${dto.no }</span>. ${dto.title }</p>
		            <p>${dto.id } (${dto.writeDate })
		            </p>
		          </div>
		      </div>
		    </div>
		    <!-- col의 끝 -->
		   </c:forEach>
		 <!-- 데이터가 있는 만큼 반복문처리 끝. -->
		   
		</div>
		<!-- row의 끝 -->
  
  
  </div>
</div>

</body>
</html> 