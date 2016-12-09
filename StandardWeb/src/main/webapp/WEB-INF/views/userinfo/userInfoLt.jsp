<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="/resources/js/jquery-1.9.1.min.js"></script>
<script src="/resources/js/jquery.twbsPagination.min.js"></script>
<title>회원 리스트</title>

<script>
$(function() {
	$('#pagination').twbsPagination({
        totalPages: ${pagination.pageCount},
        visiblePages: ${pagination.itemPerPage},
        onPageClick: function (event, page) {
        	$('#page').val(page);
        	selectUserInfo();
        }
    });
});

function selectUserInfo() {
	var params = "page="+$("#page").val();
	$("#contents").html("");
	
	$.ajax({
		type:"POST",
		url:"/userinfo/userInfoLt.json",
		dataType:'json',
		data:params,
		success:function(response) {
			var length = response.length;
			var html = "";
			
			for(var i=0; i<length; i++) {
				html += "<tr>";
				html += "	<td>"+response[i].userId+"</td>";
				html += "	<td>"+response[i].userName+"</td>";
				html += "	<td>"+response[i].userAge+"</td>";
				html += "	<td>"+response[i].gender+"</td>";
				html += "</tr>";
			}
			
			$("#contents").html(html);
		}
	});
}

function searchBoard() {
	$("#form").submit();
}
</script>
</head>
<body>
게시글 수 : ${pagination.totalItemCount}

<form id="form">
	<input type="hidden" name="page" id="page" value="${pagination.page}">
</form>

<table style="width:100%;" border="1">
	<colgroup>
		<col style="width:25%;">
		<col style="width:25%;">
		<col style="width:25%;">
		<col style="width:25%;">
	</colgroup>
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
		</tr>
	</thead>
	<tbody id="contents">
	</tbody>
</table>

<div id="pagination">
</div>
</body>
</html>