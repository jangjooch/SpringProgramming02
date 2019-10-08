<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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
	function getBoardList(pageNo){
		$.ajax({
			url: "getBoardList",
			data: "pageNo="+pageNo,
			method: "get",
			success: function(data){
				$("#boardListDiv").html(data);
			}
		});
	}
	$(document).ready(function(){
		getBoardList(1);
	});
	
	// 위의 $(function(){}) 의 코드의 내용을 
	// html을 구현한 뒤에 실행한다는 뜻이다.
	// getBoardList(1);
</script>
</head>
<body>
<!-- body가 다 해석된 후에 init()을 실행시킨다. -->
	<a href="content">Get Board List</a>
	<div class="btn-toolbar" role="toolbar"
		aria-label="Toolbar with button groups">
		<div class="btn-group mr-2" role="group" aria-label="First group">
			<%for(int i = 1 ; i <= 10 ; i++){ %>
				<!-- <a href="getBoardList?pageNo=<%=i%>" type="button" class="btn btn-success"><%--=i--%></a> -->
				<!-- pageNo=<%--%=i--%> 를 이용하여 get방식으로 i의 값을 전달한다. -->
				<a href="javascript:getBoardList(<%=i%>)" type="button" class="btn btn-success"><%=i%></a>
				<!-- href="javascript:getBoardList(<%--=i--%>)"를
					 통하여 javascript의 함수 getBoardList(pageNo)를 사용한다 정의한다. -->
			<%} %>
		</div>
	</div>
	
	<div id="boardListDiv">
	</div>
	
	<img alt="No Image" src="<%=application.getContextPath()%>/resources/image/Desert.jpg">
	<img alt="No Image" src="<%=application.getContextPath()%>/resources/image/Lighthouse.jpg">
	<img alt="No Image" src="<%=application.getContextPath()%>/resources/image/Tulips.jpg">
</body>
</html>