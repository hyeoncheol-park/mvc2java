<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>
<style type="text/css">
#pwDiv{display: none;}
.chat{list-style: none;}
</style>

<script type="text/javascript">
// 댓글 객체 - 속성, 함수
var replyService = (function(){

	// getList를 저장하는 프로그램 작성 -> 필요한 데이터 param(no, page), callback-처리되는 함수, error-오류가 났을때 객체
	function getList(param, callback, error){
		var no = param.no;
		var page = param.page;

		// $.getJSON
		$.getJSON(
				// 데이터 요청 URI
				"/reply/pages/" + no + "/" + page + ".json",
				// 데이터 가져오기를 성공하면 처리되는 함수. data : 서버에서 넘어오는 데이터
				function(data){
					// 데이터를 가져오기를 성공하면 실행되는 함수를 밖에서 선언해서 넣어주는 경우 처리
					if(callback){
						callback(data);
					}
				}
		// 데이터 가져오는 것을 실패했을 때의 처리
		).fail(function(xhr, status, err){
			if(err){
				err();
			}
		});
	}

	// 날짜 timestamp 숫자를 받아서 날짜 형식을 리턴해 주는 함수. -> json데이터로 받을때 timestamp로 전달된다.
	function displayDate(timeValue){
		var today = new Date(); // 오늘 날짜 셋팅
		// today.getTime() - timeValue // 현재 날짜시간과 댓글 작성일의 차이
		// 차이가 24시간이 지나지 않았으면 시간을 지났으면 날짜를 표시할수 있도록 할수 있다.
		var dateObj = new Date(timeValue); // 댓글을 작성한 날짜 객체
		var str ="";
		str += dateObj.getFullYear() + ".";
		str += (dateObj.getMonth() + 1) + "."; // month 는 0~ 11까지 사용하기 때문에 +1 처리한다.
		str += dateObj.getDate();

		// [yy, mm, dd].join("."); - 중간에 .을 이용해서 이어 붙인다.
		return str;
	}


	// 댓글 등록 처리 함수 선언
	function add(reply, callback, error){
		alert("댓글 등록:reply:"+JSON.stringify(reply));
		// ajax를 이용해서 데이터 넘기기 처리 - load(), $.getJSON, $.ajax()
		$.ajax({
			type : "post",
			url : "/reply/new",
			// data : ?뒤에 쿼리를 의미 k=v&&k=v : JSON.stringify(json)로 변환
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			// 등록이 성공했을 때 처리 -> 댓글 리스트를 다시 불러와서 표시한다.
			success : function(result, status, xhr){
						if(callback)
							callback(result);
					},
			// 등록 오류가 발생된 경우 처리
			error : function(xhr, status,er){
						if(error)
							error(er);
					}
		});
	}


	// 댓글 수정 처리 함수 선언
	function update(reply, callback, error){
		alert("댓글 수정:reply:"+JSON.stringify(reply));
		// ajax를 이용해서 데이터 넘기기 처리 - load(), $.getJSON, $.ajax()
		$.ajax({
			type : "patch",
			url : "/reply/" + reply.rno,
			// data : ?뒤에 쿼리를 의미 k=v&&k=v : JSON.stringify(json)로 변환
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			// 등록이 성공했을 때 처리 -> 댓글 리스트를 다시 불러와서 표시한다.
			success : function(result, status, xhr){
						if(callback)
							callback(result);
					},
			// 등록 오류가 발생된 경우 처리
			error : function(xhr, status,er){
						if(error)
							error(er);
					}
		});
	}
	
	return {
		// 댓글 리스트가 처리되 결과를 만들어 내는 함수
		getList : getList,
		displayDate :displayDate,
		add : add,
		update : update
	}
})();

$(function(){

	// 댓글을 처리하는 프로그램 작성
	var no = ${dto.no};
	// 댓글 리스트를 표시할 객체 저장
	var replyUL = $(".chat")
	
	// 댓글 리스트를 가져와서 보여주는 함수 호출
	showList(1);

	// 댓글 리스트를 보여 주는 function: 글보기를 호출하면 바로 보여주는 부분이므로 페이지는 1페이지이다.
	function showList(page){
		// getList({no,page}, callback function(data), error)
		replyService.getList(
			{no:no, page:page},
			function(list){
				var str = "";
				// 댓글이 없는 경우의 처리
				if(list == null || list.length ==0){
					replyUL.html("<li class='left clearfix'>댓글이 존재하지 않습니다.</li>");
					return;
				}
				// 댓글이 있는 경우의 처리
				for(var i = 0; i < list.length; i++){
					var dto = list[i];
					str += "<li class='left clearfix' data-rno='"+dto.rno+"'>";
					str += "<div>";
					str += "<div class='header'>";
					str += "<strong class='primary-font writer'>"+ dto.writer +"</strong>";
					str += "<small class='pull-right text-muted'>" 
						+ replyService.displayDate(dto.writeDate) + "</small>";
					str += "</div>";
					str += "<p class='content'>" + dto.content + "</p>";
					str += "<spen class='pull-right'>"
						+"<button class='btn btn-default btn-xs replyUpdateBtn'>수정</button>"
						+"<button class='btn btn-default btn-xs replyDeleteBtn'>삭제</button>"
						+"</span>";
					str += "</div>";
					str += "<hr>";
					str += "</li>";
				}
				replyUL.html(str);
			}
		);
	}

	// 댓글 처리에 대한 이벤트 처리
	// 댓글 달기 버튼 처리 -> 모달 창을 보이게 한다.
	$("#writeReplyBtn").click(function(){
		// 댓글 등록으로 제목과 버튼이름을 바꿔야 한다.
		$("#updateModalTitle").text("댓글 등록");
		$("#updateModal_updateBtn").text("등록");
		//등록을 위해서 submit이 일어나도록 버튼을 변경
		$("#updateModal_updateBtn").attr("type", "submit");
				// 모달 창을 보여주자.
		$("#updateModal").modal("show");
	});

	// 댓글 등록 버튼 클릭 이벤트 -> 폼의 데이터를 넘기는 이벤트
	$("#modal_form").submit(function(){
		// Ajax로 넘길 데이터를 가져온다.
		var reply ={
			no :  $("#modal_no").val(),
			content :  $("#modal_content").val(),
			writer: $("#modal_writer").val(),
			pw : $("#modal_pw").val()
		}
// 		alert(JSON.stringify(reply));
		replyService.add(
			reply,
			function(result){
				// 결과를 경고창으로 보여준다. -> ReplyController에서 리턴해준다.
				alert(result);
				// 사용을한 모달창의 입력란을 비워둔다.
				$("#updateModal").find("input, textarea").val("");
				// 모달 창은 숨긴다.
				$("#updateModal").modal("hide");
				
				// 댓글 리스트를 다시 뿌린다.
				showList(1);
			}
		);
		// submit을 무시 시킨다. -> Ajax처리를 하기 위해서
		return false;
	});

	// 댓글의 수정 / 삭제 버튼 처리 
	// -> Ajax 후에 나타나는 버튼이므로 여기서 직접 찾을 때는 Ajax 전이므로 적용불가.
	// 그래서 on(이벤트, 선택자, 함수) : 선택자는 find()에 의해 찾기가 진행이 된다.
	$(".chat").on("click", ".replyUpdateBtn", function(){
// 		alert("댓글의 수정 버튼이 클릭됨.");
		var replyRow = $(this).closest("li");
		var rno = replyRow.data("rno");
		var content = replyRow.find(".content").text();
		var writer = replyRow.find(".writer").text();
// 		alert(rno+"/"+content+"/"+writer);

		// 모달 창에 데이터 셋팅
		$("#modal_rno").val(rno);
		$("#modal_content").val(content);
		$("#modal_writer").val(writer);
		// 작성자는 수정 불가로 처리
		$("#modal_writer").attr("readonly","readonly");

		// 수정을 위해서 submit에서 동작이되지 않는 button으로 변경
		$("#updateModal_updateBtn").attr("type", "button");
		// 버튼 이름을 수정으로 변경
		$("#updateModal_updateBtn").text("수정");
		// 비밀번호는 확인용이므로 입력된 내용이 화면에 나타나지 않는다.
		$("#modal_pw").attr("type", "password");

		// 모달창을 화면에 표시한다.
		$("#updateModal").modal("show");
		
	});

	$("#updateModal_updateBtn").click(function(){
		// 같은 버튼을 등록과 삭제에서도 사용하기 때문에 등록과 삭제인 경우는 처리를 안하도록 시킨다.
		if($(this).text()=="등록" || $(this).text()=="삭제")
			return;
// 		alert("수정처리");
		// Ajax로 넘길 데이터를 가져온다.
		var reply ={
			rno :  $("#modal_rno").val(),
			content :  $("#modal_content").val(),
// 			writer: $("#modal_writer").val(),
			pw : $("#modal_pw").val()
		}
		alert(JSON.stringify(reply));
		replyService.update(
			reply,
			function(result){
				// 결과를 경고창으로 보여준다. -> ReplyController에서 리턴해준다.
				alert(result);
				// 사용을한 모달창의 입력란을 비워둔다.
				$("#updateModal").find("input, textarea").val("");
				// 모달 창은 숨긴다.
				$("#updateModal").modal("hide");
				
				// 댓글 리스트를 다시 뿌린다.
				showList(1);
			}
		);
	});
	
	
	// 게시판 글보기의 이벤트처리
	$("#deleteBtn").click(function(){
		$("#pwDiv").show();
		// href="" -> 자신을 다시 호출한다. => false : 호출하는 것을 무시
		return false;
	});
});
</script>
</head>
<body>
<!-- 데이터 표시하는 부분 : Bootstrap 쉽게 표시 : 라이브러리 필요 -> sitemesh에서 처리 -->
<!-- <div class="container"> -->
	<!-- 제목 -->
	<h1>게시판 글보기</h1>
	<!-- 데이터 테이블 -->
	<!-- bootstrap 적용 : w3schools.com 참조
		: 1. 라이브러리 등록 , 2.body안에 container 3. 그외필요한 객체 => tag 안에 class -->
	<table class="table">
		<tr>
			<th>글번호</th>
			<!-- dto안에 no property를 사용했다. => getNo()를 사용한 것 -->
			<td>${dto.no }</td>
		</tr>
		<tr>
			<th>제목</th>
			<!-- dto안에 no property를 사용했다. => getTitle()를 사용한 것 -->
			<td>${dto.title }</td>
		</tr>
		<tr>
			<th>글내용</th>
			<!-- dto안에 no property를 사용했다. => getContent()를 사용한 것
				출력만 하면 HTML에서 줄바꿈을 무시한다. => pre tag사용 -->
			<td><pre>${dto.content }</pre></td>
		</tr>
		<tr>
			<th>작성자</th>
			<!-- dto안에 no property를 사용했다. => getWriter()를 사용한 것 -->
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<!-- dto안에 no property를 사용했다. => getWriteDate()를 사용한 것 -->
			<td>
				<fmt:formatDate value="${dto.writeDate }"
				 pattern="yyyy.MM.dd" />
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<!-- dto안에 no property를 사용했다. => getHit()를 사용한 것 -->
			<td>${dto.hit }</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="update.do?no=${dto.no }"
					role="button" class="btn btn-default">수정</a>
				<a href="" id="deleteBtn"
					role="button" class="btn btn-default">삭제</a>
				<div id="pwDiv">
					<form action="delete.do" method="post">
						<input type="hidden" name="no"
						 value="${dto.no}" />
						<!-- 비밀번호 : 확인용 비밀번호는 안보이게 처리 -->
						<div class="form-group">
							<label for="pw">비밀번호:자신글 확인용</label>
							<input type="password" class="form-control" id="pw" name="pw"
								autocomplete="off"
								title="비밀번호는 4~20 글자 사이로 입력하셔야 합니다." pattern="^.{4,20}$">
						</div>
						<button>삭제</button>
					</form>
				</div>
				<a href="list.do?page=${param.page }&perPageNum=${param.perPageNum }&key=${param.key }&word=${param.word}">리스트</a>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-comments fa-fw"></i> Reply
						<button id="writeReplyBtn"
						 class="btn btn-primary btn-xs pull-right">new Reply</button>
					</div>
					<div class="panel-body">
						<ul class="chat">
							<li class="left clearfix" data-rno="4">
								<div>
									<div class="header">
										<strong class="primary-font">writer</strong>
										<small class="pull-right text-muted">2020.03.15</small>
									</div>
									<p>jjang jjang</p>
								</div>
								<hr>
							</li>
							<li class="left clearfix">
								댓글 존재하지 않습니다.
							</li>
						</ul>
					</div>
				</div>
			</td>
		</tr>
	</table>
<!-- </div> -->

  <!-- 댓글 달기와 수정을 위한 Modal -->
  <div class="modal fade" id="updateModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-pencil"></span> 
          	<span id="updateModalTitle">댓글 수정</span>
          </h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
          <form role="form" method="post" id="modal_form">
          	<input type="hidden" name="rno"
          	 id="modal_rno">
            <input name="no" type="hidden" value="${dto.no }" 
             id="modal_no"/>
			<input name="page" type="hidden" value="${param.page }" />
			<input name="perPageNum" type="hidden" value="${param.perPageNum }" />
            <div class="form-group">
              <label for="modal_content">
                <span class="glyphicon glyphicon-calendar"></span>
               	내용
              </label>
              <textarea class="form-control" id="modal_content" name="content"
               rows="3"></textarea>
            </div>
            <div class="form-group">
              <label for="modal_content">
                <span class="glyphicon glyphicon-user"></span>
               	작성자
              </label>
              <input class="form-control" id="modal_writer" name="writer">
            </div>
            <div class="form-group">
              <label for="modal_content">
                <span class="fa fa-key"></span>
               	비밀번호
              </label>
              <input class="form-control" id="modal_pw" name="pw">
              <div>본인 댓글 확인 비밀번호입니다.</div>
            </div>
            <div class="btn-group right">
             <button type="submit" class="btn btn-primary"
              id="updateModal_updateBtn">수정</button>
          	 <button type="button" class="btn btn-warning" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 취소</button>
			</div> 
          </form>
        </div>
      
    </div>
  </div> 
</div>


</body>
</html>