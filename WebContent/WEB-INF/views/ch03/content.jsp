<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- pageEncoding="UTF-8" 파일 자체의 저장 방식을 정한다 -->
<!-- contentType="text/html; charset=UTF-8" 서버에 전송될 파일의 형식 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js">
	
</script>
<!-- src = 에서의 주소는 context-root가 생략된것이 아니다.
			 그렇기에 절대 주소에서 context-root를 포함하여 보여주어야 한다.
			 /SpringProgramming02/resources/js/jquery-3.4.1.min.js
			 상대 주소는 ../../resources/js/jquery-3.4.1.min.js
			 아니면 context-root삽입을 위해 위와같이 하여도 된다. -->
<link rel="stylesheet" tpye="text/css"
	href="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<!-- bootstrap의 css를 가져옴 -->
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<!-- bootstrap을 사용하기 위해서는 css파일과 js 파일 두가지 다 필요하다 -->
</head>
<body>
	Ch02 Content.jsp
	<hr>
	<%=application.getContextPath()%>
	<!-- context-root를 가져올 수 있다. -->
	<br>
	<p>
		<a href="join?mid=fall&mname=Kim&mpassword=12345&mage=25">Get 방식의 join</a>
		<br>
		<a href="join2?mid=fall&mname=Kim&mpassword=12345&mage=25">Get방식 Member 객체로 받기</a>
		<!-- 정보들이 저장되어 Member로 저장되고 이를 전달한다. -->
	</p>
	<p>
	<form method="post" action="join">
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">ID</span>
			</div>
			<input name="mid" type="text" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>

		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">Name</span>
			</div>
			<input name="mname" type="text" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>

		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">PW</span>
			</div>
			<input name="mpassword" type="password" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">AGE</span>
			</div>
			<input name="mage" type="number" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">BIRTH</span>
			</div>
			<input name="mbirth" value="1995-12-25" type="date" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<input type="submit" value="sign in.Saving by Parameter" class="btn btn-success">
	</form>
	
	<br>
	
	<form id="joinForm" name="joinForm" method="post" action="join2">
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">ID</span>
			</div>
			<input id="Formid" name="mid" type="text" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>

		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">Name</span>
			</div>
			<input id="Formname" name="mname" type="text" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>

		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">PW</span>
			</div>
			<input id="Formpassword" name="mpassword" type="password" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">AGE</span>
			</div>
			<input id="Formage" name="mage" type="number" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">BIRTH</span>
			</div>
			<input id="Formbirth" name="mbirth" value="1995-12-25" type="date" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<input type="submit" value="sign in. Saving by data container" class="btn btn-success">
		
	</form>
	
	<br>
	
	<p>
		<script type="text/javascript">
			function join01(){
				//window.location.href = "/join2";
				// window.location.href returns the href (URL) of the current page
				// http://localhost:8080/SpringProgramming02/ch03/ 까지가 생략된것이다.
				var id = $("#joinForm #Formid").val();
				if(id=="")
					// 아무것도 입력되지 않았을 경우
					return;
				// id 값이 joinForm인 내부의 id값이 Formid를 가져온다.
				var name = $("#joinForm #Formname").val();
				var password = $("#joinForm #Formpassword").val();
				var age = $("#joinForm #Formage").val();
				var birth = $("#joinForm #Formbirth").val();
				var params = "";
				params = params + "mid=" + id + "&";
				params = params + "mname=" + name + "&";
				params = params + "mpassword=" + password + "&";
				params = params + "mage=" + age + "&";
				params = params + "mbirth=" + birth;
				// 파라미터의 이름은 받은 곳과 같아야 하기에 mxxx의 이름으로 받기에 이렇게 준다
				location.href = "join2?" + params;
			}
			
			function join02(){
				var joinForm = document.querySelector("#joinForm");
				// id 값을 매게로 하여 querySeletor로 가져온다.
				joinForm.submit();
				// 입력받았던 것을 submit한다.
				
				// document.joinForm.submit();
				// name값이 joinForm인 것을 submit한다.
			}
		</script>
		
		<button onclick="join01()" class="btn btn-success" >Sending with Java Script in URL</button>
		<!-- 버튼이 기본적으로 form 내부에 존재하면 submit의 효과를 지긴다. -->
		<!-- onclick을 통한 함수 호출 -->
		<br>
		<button onclick="join02()" class="btn btn-success" >Sending with Java Script in Document</button>
	</p>
	<br>
	<p>
		<script type="text/javascript">
			function joinAjax(){
				$.ajax({
					url: "join3",
					// 이동할 URL. 현재 위치 즉 http://localhost:8080/SpringProgramming02/ch03 이 생략되어있다.
					// 즉 controller에서 ch03/join3 으로 받을 것이 필요하다.
					// 위의 이동을 통해 controller에서 json 파일 폼으로 전송하여 json을 만든다.
					data: {mid:"fall", mname:"kim"},
					// "mid=fall&mname=kim"이렇게 String으로 보내도 되긴하다.
					// 위와 같이 String 형태나 json형태로 보낼 수 있다.
					method: "post",
					// 디폴트는 get방식이다.
					// 보내는 방식을 설정. 대중적인 get혹은 post 같은.
					// get방식으로는 url에 정보를 넣어서 보내도 된다.
					success: function(data){
						// 성공적으로 response를 받을 경우 실행한다.
						// 위의 data에는 HTML이나 JSON을 받을 수 있다.
						// ch03/join3.jsp의 이동을 통해 생성된
						// json 파일이 data에 저장된다. 그렇기에 따로 입력받지 않은
						// mpersonal 또한 사용할 수 있는 것이다.						
						var html=
							"<span>mid : " + data.mid + "</span>" + "<br>" +
							"<span>mname : " + data.mname + "</span>" + "<br>" +
							"<span>mpersonal : " + data.mpersonal + "</span>" + "<br>";
						$("#resultDiv").html(html);
						// 아래와 같이 이를 이용하여 html파일을 생성한다.
						// 매개변수로 html형태를 주어야 하기에 json형태인 data를 바로 줄 수 없다.
					}
				});
				
			}
		</script>
		<button onclick="joinAjax()" class="btn btn-success">Sending with Ajax</button>
		<div id="resultDiv">
			
		</div>
		
	</p>
	

</body>
</html>