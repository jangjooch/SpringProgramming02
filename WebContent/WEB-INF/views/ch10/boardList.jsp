<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- java standard library -->
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

</head>
<body>
	<h5>Board List</h5>
	<table class="table table-sm">
		<thead>
			<tr>
				<th scope="col">Number</th>
				<th scope="col">Title</th>
				<th scope="col">Writer</th>				
				<th scope="col">HitCount</th>
				<th scope="col">Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList}">
				<!-- foreach문 처럼 items=배열 var=쪼갠것을 사용할 변수 -->
				<!-- for(Ch05Board board : boardList) 와 같은 의미이다. -->
				<!-- model을 통해 받은 것에서 key값 boardList를 받는다. -->
				<tr>
					<th scope="row">${board.bnumber}</th>
					<td><a href="boardDetail?bnumber=${board.bnumber}" onclick="hitCountUp()"> ${board.btitle} </a></td>
					<!-- a 태그를 통해 get방식으로 board.bnumber를 전달한다. -->					
					<td>${board.bwriter}</td>
					<td>${board.bhitcount}</td>
					<td><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd" /></td>
					<!-- 이와 같이 날짜가 나오는 형식의 format을 정할 수 있다. -->					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="display: flex;">
		<div style="flex-grow: 1">
		
				<a href="boardList?pageNumber=1" class="btn btn-primary">[First]</a>
				
				<c:if test="${groupNo > 1}">
				<!-- 현재 그룹 번호가 1 초과일 경우 나타나도록 -->
					<a href="boardList?pageNumber=${startPageNo-1}" class="btn btn-success">[Pre]</a>
				</c:if>
				
				<div style="display: inline-block;" class="btn-toolbar"
					role="toolbar" aria-label="Toolbar with button groups">
				  <div class="btn-group mr-2" role="group" aria-label="First group">
				  	<c:forEach begin="${startPageNo}" end="${endPageNo}" var="i">
				  		<c:if test="${pageNumber==i}">
				  			<a href="boardList?pageNumber=${i}" class="btn btn-secondary active">${i}</a>
				  		</c:if>
				  		<c:if test="${pageNumber!=i}">
				  			<a href="boardList?pageNumber=${i}" class="btn btn-secondary">${i}</a>
				  		</c:if>
				  	</c:forEach>
				  </div>
				</div>				
				
				<c:if test="${groupNo < totalGroupNum}">
				<!-- 현재 그룹 번호가 전체 그룹수 보다 작을 떄 나타나도록 -->
					<a href="boardList?pageNumber=${endPageNo+1}" class="btn btn-success">[Next]</a>
				</c:if>
				<a href="boardList?pageNumber=${totalPageNum}" class="btn btn-primary">[End]</a>
							
		</div>
		<div>
			<a class="btn btn-success" href="write100Board">write100Board</a>
			<a class="btn btn-success" href="writeBoardForm">WriteForm</a>
			<c:if test="${mid==null}">
				<a href = "loginForm" class = "btn btn-primary">Login</a>
				<a href = "joinIn" class = "btn btn-primary">Join</a>
			</c:if>
			<c:if test="${mid != null}">
				<a class="btn btn-danger" href="logOut">LogOut</a>
			</c:if>
		</div>
	</div>
</body>
</html>