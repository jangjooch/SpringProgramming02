<%@ page contentType="application/json; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
{
	"number":${board.number},
	"title":"${board.title}",
	"content":"${board.content}",
	"writer":"${board.writer}",
	"date":"<fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd"/>",
	"hitcount":${board.hitcount}
}