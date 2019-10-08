<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.company.web.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- html이외의 추가적인 tag를 작성 가능토록 하되 접두어에 c를 붙이여라 -->
<!-- 즉 트리거로서 c를 붙이여 사용할 수 있도록 한것이다. -->
<!-- 추가될 tag들은 "http://java.sun.com/jsp/jstl/core"에 소속되어 있다. -->
<!-- 이전의 getBoardList와 달리 html만으로 작성이 가능하다. -->
<!-- jstl이 maven을 통하여 사용할 수 있는 상태여야 한다. -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 위의 것과 추가적으로 format에 대한 설정이 추가된것이다. -->

<h5>Board List( Total Content Number : ${totalNo})</h5>

<table class="table table-sm">
	<thead>
		<tr>
			<th scope="col">Number</th>
			<th scope="col">Title</th>
			<th scope="col">Writer</th>
			<th scope="col">Date</th>
			<th scope="col">Content</th>
			<th scope="col">HitCount</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${boardList}">
		<!-- foreach문 처럼 items=배열 var=쪼갠것을 사용할 변수 -->
		<!-- for(Ch05Board board : boardList) 와 같은 의미이다. -->
		<tr>
			<th scope="row">${board.bno}</th>
			<td>${board.title}</td>
			<td>${board.writer}</td>
			<td><fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd"/></td>
			<!-- 이와 같이 날짜가 나오는 형식의 format을 정할 수 있다. -->
			<td>${board.content}</td>
			<td>${board.hitcount}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>