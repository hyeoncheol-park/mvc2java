<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 보기</title>
<script type="text/javascript">
$(function(){
	// 버튼 이벤트 처리
	$("#deleteBtn").click(function(){
		if(!confirm("정말 삭제하시겠습니까?"))
			return false; // a tag의 href를 취소 시킨다. -> location.href를 변경하는 태그 a
		
	});
});
</script>
</head>
<body>
<h1>공지사항 글보기</h1>
<table class="table">
<tr>
	<th>글번호</th><td>${dto.no}</td>
</tr>
<tr>
	<th>제목</th><td>${dto.title}</td>
</tr>
<tr>
	<th>내용</th><td><pre>${dto.content}</pre></td>
</tr>
<tr>
	<th>공지시작일</th><td>${dto.startDate}</td>
</tr>
<tr>
	<th>공지종료일</th><td>${dto.endDate}</td>
</tr>
<tr>
	<th>작성일</th><td>${dto.writeDate}</td>
</tr>
<tr>
	<th>수정일</th><td>${dto.updateDate}</td>
</tr>
<tr>
<td colspan="2">
<a href="updateForm.do?no=${dto.no }"></a><button>수정</button>
<a href="delete.do?no=${dto.no }" id="deleteBtn"><button>삭제</button></a>
<a href="list.do"></a><button>목록</button>
</td>
</tr>
</table>
</body>
</html>