<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 이미지 게시판 리스트</title>
<style type="text/css">
	.dataRow:hover {
	background: #eee; /* 배경 변경 */
	cursor: pointer;  /* 손가락 */
}
</style>
<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		//글번호 찾기 - 클릭한 것(this) 안에 글번호 클랙스 객체(td) 안에 글자(text))
		var no =$(this).find(".no").text()
		/* alert(no) */
		// 글보기로 이동 -글번호로 이동
		location = "view.do?no=" + no;
		});
	// 페이지 네이션의 클릭 이벤트 처리
	$(".pagination > li:not('.active')").click(
			function() {
				// 		alert("페이지 이동 클릭");
				// .data("page") ==> 속성 중에서 data-page 라는 속성의 값을 가져온다.
				var page = $(this).data("page");
//					alert(page + "-" + typeof page);

				location = "list.do?page=" + page
						+ "&perPageNum=${pageObject.perPageNum}"
						+ "&key=${pageObject.key}"
						+ "&word=${pageObject.word}";
				// a tag의 페이지 이동을 취소 시킨다.
				return false;
			});
	// 현재 페이지는 클릭이 안되게 처리
	$("li.active").click(function() {
		return false;
	});

});


</script>
</head>
<body>
<!-- 데이터 표시하는 부분:Bootstrap 쉽게 표시 :라이브러리 필요:sitemesh -->
	<!-- 제목  -->
	<h1>이미지 이미지 게시판리스트</h1>
<!--  이미지 게시판의 검색 :ㅣ 제목,내용 ,id, 그외 복합 
페이지는 1페이지,한페이제 표시할 데이터 갯수 전달.:hidden-->
<div class="row">
<div id="searchDiv">
	<form action="list.do" class="form-inline">
		<input name="page" value="1" type="hidden"/>
		<input name="perPageNum" value="${pageObject.perPageNum }" type="hidden"/>
	<div class="form-group">
		<select class="form-control" id="key" name="key">
			<option value="t" ${(param.key == "t")?"selected='selected'":"" }>제목</option>
			<option value="c" ${(param.key == "c")?"selected='selected'":"" }>내용</option>
			<option value="w" ${(param.key == "i")?"selected='selected'":"" }>아이디</option>
			<option value="tc" ${(param.key=="tc")?"selected='selected'":"" }>제목/내용</option>
			<option value="ti" ${(param.key == "ti")?"selected='selected'":""}>제목/아이디</option>
			<option value="ci" ${(param.key == "ci")?"selected='selected'":""} >내용/아이디</option>
			<option value="tci" ${(param.key == "tci")?"selected='selected'":""}>전체</option>
		</select>
	</div>
	<div class="input-group">
		<input type="text" class="form-control" placeholder="Search"
			name="word" value="${param.word }">
		<div class="input-group-btn">
			<button class="btn btn-default" type="submit">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
	<div class="input-group">
		<span class="input-group-addon">Rows/Page</span>
	  <select class="form-control" id="perPageRow">
	    <option ${(pageObject.perPageNum == 10)?"selected='selected'":"" }>10</option>
	    <option ${(pageObject.perPageNum == 15)?"selected='selected'":"" }>15</option>
	    <option ${(pageObject.perPageNum == 20)?"selected='selected'":"" }>20</option>
	    <option ${(pageObject.perPageNum == 25)?"selected='selected'":"" }>25</option>
			  </select>
			</div>
		</form>
	</div>
</div>
	
	
	<!-- 데이터 테이블 -->
	<!-- bootstrap 적용 : w3scholes.com :1. 라이브러리 등록 2.body 안에 container 3. 그외필요한 객체 ->tag안에 class -->
			<!-- 이미지 겔러리 리스트 작성 -->
		<div class="row">
		 <!-- 데이터가 있는 만큼 반복문처리 시작. -->
		 <c:forEach items="${list }" var="dto" varStatus="vs">
			<!-- col 시작 : no, title, id, writeDate, fileName -->
		    <div class="col-md-3 dataRow">
		      <div class="thumbnail">
		          <img src="${dto.fileName }" alt="${dto.fileName }" style="width:100%">
		          <div class="caption">
		            <p><span class="no">${dto.no }</span>. ${dto.title }</p>
		            <p>${dto.id } (${dto.name }) -
		            <fmt:formatDate value="${dto.writeDate}"
		            pattern="yyyy-MM-dd"/>
		            </p>
		          </div>
		      </div>
		    </div>
		    <!-- 이미지를 4개 출력하면 줄을 바꾸는 처리 -->
		    <c:if test="${vs.count % 4 == 0 }">
		    	<%="</div><div class='row'>" %>
		    </c:if>
		    <!-- col의 끝 -->
		   </c:forEach>
		 <!-- 데이터가 있는 만큼 반복문처리 끝. -->
		   </div>
		   <!--  이미지 갤러리 끝 -->
	
	<!-- 페이지 처리 :    -->
	<div>
		<p:pageNav endPage="${pageObject.endPage }" totalPage="${pageObject.totalPage }" 
		startPage="${pageObject.startPage }" 
		page="${pageObject.page }"/>
	</div>
	<!-- 버튼 처리 -->
	<div>
	<!-- get: 주소에 입력, a href, location.href  --> 
	<a href="write.do"><button>등록</button></a>
	</div>
</body>
</html>