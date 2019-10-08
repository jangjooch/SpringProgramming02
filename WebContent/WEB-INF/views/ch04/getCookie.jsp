<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- pageEncoding="UTF-8" 파일 자체의 저장 방식을 정한다 -->
    <!-- contentType="text/html; charset=UTF-8" 서버에 전송될 파일의 형식 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript" src = "<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js">
		</script>
		<!-- src = 에서의 주소는 context-root가 생략된것이 아니다.
			 그렇기에 절대 주소에서 context-root를 포함하여 보여주어야 한다.
			 /SpringProgramming02/resources/js/jquery-3.4.1.min.js
			 상대 주소는 ../../resources/js/jquery-3.4.1.min.js
			 아니면 context-root삽입을 위해 위와같이 하여도 된다. -->
		<link rel = "stylesheet" tpye = "text/css" href = "<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">
		<!-- bootstrap의 css를 가져옴 -->
		<script type="text/javascript" src ="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js" ></script>
		<!-- bootstrap을 사용하기 위해서는 css파일과 js 파일 두가지 다 필요하다 -->
	</head>
	<body>	
		Ch04 Content.jsp
		<hr>
		<%=application.getContextPath() %>
		<!-- context-root를 가져올 수 있다. -->
		<p>
			The Browser which User using : ${browser}
		</p>
		<p>
			Read Cookie : ${mname}
		</p>
		
	</body>
</html>