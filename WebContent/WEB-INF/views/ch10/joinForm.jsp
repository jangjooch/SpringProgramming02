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
	function checkForm() {
		var result = true;
		// 모든 span의 error의 내용 지우기
		$(".error").text("");
		$(".error").css("color","red");
		// class는 .클래스 명으로 접근
		// 내부에 html이 있는 것이 아닌 String이기에 text("")으로 초기화
		// 입력값 검사
		if ($("#mid").val() == "") {
			$("#mIdError").text("* Input ID *");
			result = false;
		}
		if ($("#mpassword").val() == "") {
			$("#mPasswordError").text("* Input PW *");
			result = false;
		}
		if ($("#mname").val() == "") {
			$("#mNameError").text(" * Input Name *");
			result = false;
		}
		return result;
	}
	
	function checkMid(){
		$(".error").text("");
		if ($("#mid").val() == "") {
			$("#mIdError").text("* Input ID *");
			result = false;
		}
		else{
			$.ajax({
				url: "checkMid",
				// Controller checkMid를 통해 결과를 얻는다.
				data: {
					mid:$("#mid").val()
					},
				success: function(data){
					if(data.result){
						$("#mIdError").text("Available to Use");
						$("#mIdError").css("color","green");
					}else{
						$("#mIdError").text("It's Occupied");
						$("#mIdError").css("color","red");
					}
				}
			});	
		}
	}
		
</script>
</head>
<body>
	<h5>Sign In</h5>

	<form method="post" action="joinIn" onsubmit="return checkForm()">
		<!-- 전달되는 내용을 body에 저장되어 전달되어야 하기 때문에 post방식으로 -->
		<!-- checkForm()이 true가 되어야 action을 작동한다. -->
		<!-- action이 #인 경우 자기 자신으로 되돌아 온다는 것이다. -->
		<div class="form-group">
			<label for="mid" style="display: block;">ID</label>
			<div class="input-group mb-3">
				<input id="mid" name="mid"type="text" class="form-control" placeholder="Input ID">
				<div class="input-group-append">
					<input class="btn btn-danger" type="button" onclick="checkMid()" value="ID Check">
				</div>
			</div>
			<span class="error" id="mIdError" style="color: red;"> </span>
		</div>
		<%-- <div class="form-group">
			<label for="mid">ID</label>
			<!-- 해당 라벨을 클릭하면 해당 title로 이동 -->
			<input id="mid" name="mid" type="text" class="form-control"
				placeholder="Input ID"> <input type="button"
				onclick="checkMid()" value="ID CHECK" class="btn btn-secondary">
			<!-- 그냥 button으로 주면 submit되서 내용이 사라진다. -->
			<span class="error" id="mIdError" style="color: red;">
				${Id_error} </span>
			<!-- model에서 전달받은 속성 값 Id_error를 출력 -->
		</div> --%>
		<div class="form-group">
			<label for="mpassword">PW</label>
			<!-- 해당 라벨을 클릭하면 해당 title로 이동 -->
			<input id="mpassword" name="mpassword" type="password"
				class="form-control" placeholder="Input PW">
				<span class="error" id="mPasswordError" style="color: red;"> </span>
		</div>
		<div class="form-group">
			<label for="mname">NAME</label>
			<!-- 해당 라벨을 클릭하면 해당 title로 이동 -->
			<input id="mname" name="mname" type="text" class="form-control"
				placeholder="Input Name"> 
			<span class="error"	id="mNameError" style="color: red;"> </span>
		</div>
		<div class="form-group">
			<input type="submit" value="SignIn" class="btn btn-success" />
		</div>
	</form>
</body>
</html>