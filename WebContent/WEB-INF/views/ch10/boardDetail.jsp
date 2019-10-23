<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<h4>Detail</h4>
	<form>
	<div class="form-group">
			<label for="bnumber">Number</label>
			<!-- 해당 라벨을 클릭하면 해당 title로 이동 --> 
			<input id="bnumber" name="bnumber" type="text" class="form-control" value="${board.bnumber}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="btitle">Title</label>
			<!-- 해당 라벨을 클릭하면 해당 title로 이동 --> 
			<input id="btitle" name="btitle" type="text" class="form-control" value="${board.btitle}" readonly="readonly">
		</div>
		<%-- <div class="form-group">
			<label for="bwriter">Writer</label>
			<!-- 해당 라벨을 클릭하면 해당 title로 이동 --> 
			<input id="bwriter" name="bwriter" type="text" class="form-control" value="${board.bwriter}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="bhitcount">HitCount</label>
			<!-- 해당 라벨을 클릭하면 해당 title로 이동 --> 
			<input id="bhitcount" name="bhitcount" type="text" class="form-control" value="${board.bhitcount}" readonly="readonly">
		</div> --%>
		<div class="form-group">
			<label for="bcontent">Content</label>
			<textarea id="bcontent" name="bcontent" class="form-control" rows="3" readonly="readonly">${board.bcontent}</textarea>
		</div>
		<div class="form-group">
			<label for="bdate">Date</label>
			<!-- 해당 라벨을 클릭하면 해당 title로 이동 --> 
			<input id="bdate" name="bdate" type="text" class="form-control" readonly="readonly"
				value='<fmt:formatDate value="${board.bdate}" pattern="yyyy년 MM월 dd일"/>'>
		</div>
	
	</form>
	
	<a href="boardList?pageNumber=${pageNumber}" class="btn btn-success">To Board</a>
	
	<c:if test="${board.bwriter == mid}">
	<!-- mid는 session에 저장된 writer 정보를 가져온다. mid는 현재 로그인 되면서 생성된 것이기 때문이다. -->
		<a href="updateBoard?bnumber=${board.bnumber}" class = "btn btn-info">EDIT</a>
		<a href="deleteBoard?bnumber=${board.bnumber}" class = "btn btn-danger">DELETE</a>
	</c:if>
	
</body>
</html>