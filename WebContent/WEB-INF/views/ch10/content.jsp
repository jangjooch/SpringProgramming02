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
		<script type="text/javascript">
			function btnClick01(){
				$.ajax({
					url:"connectionTest",
					success: function(data){
						if(data.result){
							$("#div01").html("Connection success");
						}
						else{
							$("#div01").html("Connection Fail");
						}
					}
				});
				
			}
			
			function btnClick02(){
				$.ajax({
					url:"getMemberByMid",
					data:{mid: "KIM"},
					success: function(data){
						$("#div02").html(data);
						// data에 html조각에 담았으니까
						// ch10/getMemberByMid로 컨트롤러에서 받아 작업을 처리하고
						// 이후 컨트롤러에 의해 getMemberByMid.jsp로 이동하여
						// html을 작성하여 data로 전달된것이다.
					}
				});
				
			}
			
		</script>
	</head>
	<body>
		<p>
			<button id="btn01" onclick="btnClick01()" type="button" class="btn btn-primary">Connection Test</button><br>			
			<div id="div01">
			</div>
		</p>
		<p>
			<button id="btn02" onclick="btnClick02()" type="button" class="btn btn-primary">Get Data from member</button><br>			
			<div id="div02">				
			</div>
		</p>
		<p>
			<a href="boardList?pageNumber=1">Get Board Data</a>
			<!-- 실행 시 가장 첫 페이지가 뜰 수 있도록 함. -->
			<div id="div03">
			</div>
		</p>
	</body>
</html>