<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	function checkForm(){
		var result = true;
		// 모든 span의 error의 내용 지우기
		$(".error").text("");
		// class는 .클래스 명으로 접근
		// 내부에 html이 있는 것이 아닌 String이기에 text("")으로 초기화
		// 입력값 검사
		if($("#btitle").val()==""){
			$("#bTitleError").text("* Input Title *");
			result = false;
		}		
		
		if($("#bcontent").val()==""){
			$("#bContentError").text("* Input Content *");
			result = false;
		}
		
		return result;
	}
</script>
</head>
<body>
	<h4>Edit Content</h4>

	<form method="post" action="updateBoard" onsubmit="return checkForm()">
	
		<!-- bnumber를 바꾸지 않지만 bnumber가 updateBoard에 전달되어야 하기때문에
			 hidden을 사용하여 board.number를 그대로 보낼 수 있도록 한다. -->
		<input type ="hidden" name = "bnumber" value="${board.bnumber}">
		
		<div class="form-group">
			<label for="btitle">Title</label>
			<!-- 해당 라벨을 클릭하면 해당 title로 이동 --> 
			<input id="btitle" name="btitle" value="${board.btitle}" type="text" class="form-control" placeholder="Input Title">
			<span class="error" id="bTitleError" style="color: red;">
							
			</span>
		</div>
				
		<div class="form-group">
			<label for="bcontent">Content</label>
			<textarea id="bcontent" name="bcontent" class="form-control" rows="3" placeholder="Input Content">${board.bcontent}</textarea>
			<span class="error" id="bContentError" style="color: red;">								
			</span>
		</div>
		<div class="form-group">
			<input type="submit" value="UPDATE" class="btn btn-primary"/>
		</div>
		
	</form>
</body>
</html>