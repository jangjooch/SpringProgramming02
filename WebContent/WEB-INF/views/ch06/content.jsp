<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js">
	
</script>
<link rel="stylesheet" tpye="text/css"
	href="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<!-- bootstrap의 css를 가져옴 -->
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<!-- bootstrap을 사용하기 위해서는 css파일과 js 파일 두가지 다 필요하다 -->

<script type="text/javascript">
	function btnLogin() {
		if ($("#memberId").val == "")
			return false;
		if ($("#memberPassword").val == "")
			return false;
		// 입력값이 공백이 아니라면
		return true;
	}

	function btnLogout() {
		window.location.href = "logout";
		// context-root/ch06/logout 으로 요청
	}
</script>
</head>
<body>
	<h5>HTTP SESSION</h5>
	<p>
	<div>
		<c:if test="${result != 'success'}">
			<!-- test의 값이 true이면 실행한다.
				 그렇지 않으면 실행하지 않는다. -->
			<form id="loginForm" method="post" action="login">
				<div class="form-group">
					<label for="memberId">ID</label>
					<!-- for는 강조효과하기 위함이다. -->
					<input type="text" class="form-control" id="memberId"
						name="memberId">
					<c:if test="${result =='wrongId' }">
						<span style="color: red;">No Login ID</span>
					</c:if>
					<!-- 내부 접근은 id로 파라미터 key값은 name으로 -->
				</div>
				<div class="form-group">
					<label for="memberPassword">PW</label> <input type="password"
						class="form-control" id="memberPassword" name="memberPassword">
					<c:if test="${result=='wrongPassword'}">
						<span style="color: red;">No Login Password</span>
					</c:if>
				</div>
				<input onclick="return btnLogin()" type="submit"
					class="btn btn-primary" value="Log In">
				<!-- form내부에서 일반 button으로 생성 시 무조건 submit효과이기에 input으로 생성하여
				  	   onclick 시 다른 형태의 액션이 작동되도록 함이다. 
				  	   이를 위하여 onclick의 결과에 따라 submit을 진행할 지 결정한다. -->
				<!-- 또한 검사 결과에 따른 onclick="return btnLogin()" 을 통하여 action이
				  	    작동 될지 되지 않을 지 설정이 가능하다 -->
			</form>
		</c:if>
		<c:if test="${result == 'success'}">
			<!-- 만약 result의 value가 success라면 실행되도록 -->
			<div id="logoutDiv">
				<!-- style="display: none;" 처음 시작 시 보이지 않도록 하는 것이다. 단 없어지는 것이 아니다.
				 	존재하지만 일시적으로 작동하지 않도록 하는 것이다. -->
				<button onclick="btnLogout()" class="btn btn-danger">Log
					Out</button>
			</div>
		</c:if>
	</div>
	<h5>OUTPUTSTREAM FOR FILE EDITTING</h5>
	<div>
		<!-- Get 방식으로 parameter 전송 -->
		<a href="fileDownload?filename=Desert.jpg">File Download</a> <img
			alt="None Image" src="fileDownload?filename=Lighthouse.jpg"
			width="100">
		<!-- img 태그의 src는 해당 위치의 이미지를 가져온다는 의미도 있지만 이와 같이  
				 get방식으로의 요청을 통해 Controller를 실행할 수 있다. -->
		<br>
	</div>
	<h5>DownLoading Json Data Using Writer</h5>
	<div>
		<script type="text/javascript">
			function jsonDownLoad01() {
				$.ajax({
					url: "jsonDownLoad01",
					// ch06/jsonDownLoad01로 이동
					success:function(data){
/* 						$("#number").html(data.number);
						$("#title").html(data.title);
						$("#content").html(data.content);
						$("#writer").html(data.writer);
						$("#date").html(data.date);
						$("#hitcount").html(data.hitcount); */
						var html = "";
						html += "<tr>";
						html += "<td>"+ data.number + "</td>";
						html += "<td>"+ data.title + "</td>";
						html += "<td>"+ data.content + "</td>";
						html += "<td>"+ data.writer + "</td>";
						html += "<td>"+ data.date + "</td>";
						html += "<td>"+ data.hitcount + "</td>";
						html += "</tr>";
						$("#boardList").append(html);
						// id가 boardList인 곳에 생성한 html을 이어붙인다.
					}
				});
			}
			function jsonDown02() {
				$.ajax({
					url: "jsonDownLoad02",
					success:function(data){
						var html = "";
						html += "<tr>";
						html += "<td>"+ data.number + "</td>";
						html += "<td>"+ data.title + "</td>";
						html += "<td>"+ data.content + "</td>";
						html += "<td>"+ data.writer + "</td>";
						html += "<td>"+ data.date + "</td>";
						html += "<td>"+ data.hitcount + "</td>";
						html += "</tr>";
						$("#boardList").append(html);
					}
				});
			}
		</script>
		<a href="javascript:jsonDownLoad01()">Json By JSP</a><br> <a
			href="javascript:jsonDown02()">Json By Controller</a>
	</div>
	<div>
		<table class="table table-sm">
			<thead>
				<tr>
					<th scope="col">Number</th>
					<th scope="col">Title</th>
					<th scope="col">Writer</th>
					<th scope="col">Date</th>
					<th scope="col">Content</th>
					<th scope="col">HitCount</th>
				</tr>
			</thead>
			<tbody id="boardList">
				<!-- <tr>
					<td id = "number"></td>
					<td id = "title"></td>
					<td id = "writer"></td>
					<td id = "date"></td>
					<td id = "content"></td>
					<td id = "hitcount"></td>
				</tr> -->
			</tbody>
		</table>
	</div>

</body>
</html>