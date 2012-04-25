<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>Interventions</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<c:choose>
		<c:when test="${action=='listintervention-ok'}">
			<h1>Interventions from <c:out value="${campus.name}"/></h1>
		</c:when>
		<c:when test="${action=='listintervention-all'}">
			<h1>All interventions from all campus</h1>
		</c:when>
	</c:choose>
	<c:choose>
			<c:when test="${empty listIntervention}">
				<p>No Interventions to display.</p>
			</c:when>
			<c:otherwise>
			<table class="list-intervention">
			<tr><th>Subject</th><th>Begin Date</th><th>End Date</th><th>Status</th></tr>
				<c:forEach items="${listIntervention}" var="intervention">
					<tr>
						<td><a href="<%= contextPath %>/intervention?id=<c:out value="${intervention.id}"/>" ><c:out value="${intervention.subject}"/></a></td>
						<td><c:out value="${sdf.format(intervention.beginDate)}"/></td>
						<td><c:out value="${sdf.format(intervention.endDate)}"/></td>
						<td><c:choose>
							<c:when test="${intervention.status == 1}">
								Not started
							</c:when>
							<c:when test="${intervention.status == 2}">
								In progress
							</c:when>
							<c:when test="${intervention.status == 3}">
								Done
							</c:when>
						</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>