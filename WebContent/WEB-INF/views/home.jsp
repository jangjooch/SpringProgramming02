<%@ page contentType="text/html; charset=UTF-8" %>

<!-- charset 설정을 위함. 기존에는 이상하게 되어있다. -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			html, body{
				height: 100%;
			}
			#wrapper{
				width: 100%;
				height: 100%;
				display: flex;
				flex-direction: column;
				/* position: fixed;
				top: 0px;
				right: 0px;
				bottom: 0px;
				left: 0px; */
			}
			#header{
				border-bottom: 3px solid black;
				margin-bottom: 15px
			}
			#content{
				flex-grow: 1;
				height: 95%;
				display: flex;
				min-height: 0;
				/* flex-direction: row ê° ëí´í¸ì´ë¤.*/
			}
			#sideBar{
				width: 300px;
				height : 500px;
				background-color: gray;
				padding-right: 15px;
				border-right: 3px solid black;
				overflow-y: scroll;
				<%-- 간혹 height 정보가 없으므로 인하여 오류가 발생하기도 한다. --%>
			}
			#center{				
				flex-grow: 1;
				padding : 20px;
				
			}
			#center iframe{
				margin-top: 0px;
				width: 100%;
				height: 100%;
			}
			#footer{
				border-top: 3px solid black;
				margin-top: 15px
			}
		</style>
		
</head>
<body>

	<div id = "wrapper">
		<div id = "header">
			<h3>SpringProgramming02</h3>
		</div>
		<div id = "content">
			<div id = "sideBar">
				<ul>
					<li><a href = "" target="iframe">HOME</a></li>
						<p>요청 매핑 info()</p>
					<li><a href = "info" target = "iframe">To Info Controller 생성</a></li>
						<p>요청 매핑 ch02/content
					<li><a href = "ch02/content" target = "iframe">Ch02 content 요청</a></li>
					<li>
						<p>Ch02. 요청 매핑</p>
						<a href = "ch02/content" target = "iframe">ch02 content</a><br>
						<a href = "ch02/getMethod" target = "iframe">ch02/getMethod</a>
						<form action="ch02/postMethod" method = "post" target = "iframe">
							<input type = "submit" value = "/ch02/postMethod">
						</form>						
					</li>
					<li><a href = "ch03/content" target = "iframe">요청 파라미터</a>
					</li>
					<li><a href = "ch04/content" target = "iframe">요청 헤더와 Cookie</a>
					</li>
					<li><a href = "ch05/content" target = "iframe">Controller에서 View로 데이터 전잘</a>
					</li>
					<li><a href = "ch06/content" target = "iframe">매개변수와 리턴 타입</a>
					</li>
					
				</ul>
			</div>
			<div id = "center">
				<iframe src="http://tomcat.apache.org/" name="iframe" frameborder="0"></iframe>
			</div>
		</div>
		<div id = "footer">2019. Iot. JSH</div>
	</div>
	
</body>
</html>