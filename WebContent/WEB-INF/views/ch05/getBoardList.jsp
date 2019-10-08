<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, com.company.web.dto.*"%>


<!-- Ajax를 통한 html 작성 -->

<%
	List<Ch05Board> boardList = (List<Ch05Board>) request.getAttribute("boardList");
%>
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
		<%
			for (Ch05Board board : boardList) {
		%>
		<tr>
			<th scope="row"><%=board.getBno() %></th>
			<td><%=board.getTitle() %></td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getDate() %></td>
			<td><%=board.getContent() %></td>
			<td><%=board.getHitcount() %></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>