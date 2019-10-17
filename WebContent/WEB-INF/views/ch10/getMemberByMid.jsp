<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.company.web.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h5>Member 정보(Select By Mid)</h5>
<!-- ajax를 위한 html 조각 형성 -->
<table class="table table-sm">
	<thead>
		<tr>
			<th scope="col">Mid</th>
			<th scope="col">Name</th>
			<th scope="col">Password</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${member.mid}</td>
			<td>${member.mname}</td>
			<td>${member.mpassword}</td>
			<!-- model.addAttribute를 통해 member객체가 전달되었기 때문에
				 중괄호를 통해 getter로 접근한다. -->
		</tr>
	</tbody>
</table>