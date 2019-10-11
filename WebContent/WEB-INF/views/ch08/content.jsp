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
</head>
<body>
	<form action="fileUpload" method="post" enctype="multipart/form-data">
	<!-- 파일을 전송하는 것은 Header에 포함시키지 못하기 때문에 post방식으로 보내어야 한다. -->
	<!-- enctype="multipart/form-data"는 여러 파트로 나누어 데이터를 보낸다는 뜻이다. -->
	<!-- 즉 body의 부분이 input의 type특성에 따라 나뉘어 저장되고 보낸다는 것이다. -->
	<!-- 즉 input단위로 파트를 나누어 body에 저장하고 전송하게 된다. -->
	<!-- 즉 여기서는 text형태의 Description과 file형태의 attach가 전송되는 것이다. -->
	<!-- 파일 업로드를 위해서 위 2가지 
		 method="post" enctype="multipart/form-data"
		 는 필수적이다. -->
		<div class="input-group mb-3">
		<!-- Text파트 -->
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">Title</span>
			</div>
			<input name="title" type="text" class="form-control" placeholder="Title">
		</div>
		
		<div class="input-group mb-3">
		<!-- Text파트 -->
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">Description</span>
			</div>
			<input name="description" type="text" class="form-control" placeholder="Description">
		</div>
		
		<!-- File 타입 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="inputGroupFileAddon01">File01</span>
			</div>
			
			<div class="custom-file">
				<input name="attach01" type="file" class="custom-file-input"
					id="inputGroupFile01">
				<label class="custom-file-label" for="inputGroupFile01">Choose
					file</label>
			</div>
		</div>
		<!-- File 타입 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="inputGroupFileAddon01">File02</span>
			</div>
			
			<div class="custom-file">
				<input name="attach02" type="file" class="custom-file-input"
					id="inputGroupFile01">
				<label class="custom-file-label" for="inputGroupFile01">Choose
					file</label>
			</div>
		</div>
		<input type="submit" class="btn btn-secondary" value="File Upload">
		
	</form>
</body>
</html>