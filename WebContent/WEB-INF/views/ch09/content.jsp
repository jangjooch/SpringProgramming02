<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript" src = "<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js">
		</script>
		<link rel = "stylesheet" tpye = "text/css" href = "<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">
		<!-- bootstrap의 css를 가져옴 -->
		<script type="text/javascript" src ="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js" ></script>
		<!-- bootstrap을 사용하기 위해서는 css파일과 js 파일 두가지 다 필요하다 -->
	</head>
	<body>	
		<a href="getInsert">getInsert 요청</a><br>
		<a href="getInsert2">getInsert2 요청</a><br>
		<a href="getInsert3">getInsert3 요청</a><br>
		<a href="showData">get String Data</a><br><br>
		<a href="getInsert4">@Component로 설정됨</a>
		<a href="getInsert5">@Component 및 @AutoWired로 설정됨</a>
	</body>
</html>