<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>Welcome to ""Note Ton Sta"</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Welcome to "Note Ton Sta"</h1>
<p>This website propose you to evaluate interventions of SUPINFO speakers.
<br />You can also see statistics by speakers or by campus</p>
	<c:choose>
			<c:when test="${empty listCampus}">
				<p>No campus to display.</p>
			</c:when>
			<c:otherwise>
			<form action="campus" method="POST">
				<p>Choose your Campus :</p>
				<select class="campus-list" name="cid">
				<c:forEach items="${listCampus}" var="campus">
					<option value="<c:out value="${campus.id}"/>"><c:out value="${campus.name}"/></option>
				</c:forEach>
				</select>
				<input value="Go to campus" type="submit">
			</form>
		</c:otherwise>
	</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>