<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>Intervention</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:choose>
		<c:when test="${action=='intervention-ok'}">
		<div class="intervention-page">
			<div class="link-addmark"><a href="<%= contextPath %>/addmark?id=<c:out value="${intervention.id}"/>">Add a mark</a></div>
			<div class="mark-zone">
				<div class="interventon-mark-number">Number of marks :<c:out value="${markNumber}"/></div>
				<c:if test="${markNumber != 0}"><div class="interventon-mark-speaker">Speaker mark : <c:out value="${speakerAverage}"/></div>
				<div class="interventon-mark-slide">Slides mark : <c:out value="${slideAverage}"/></div>
				<div class="interventon-mark-average">Global event mark :<c:out value="${generalAverage}"/></div></c:if>
			</div>
			<div class="intervention-subject"><h1><c:out value="${intervention.subject}"/></h1></div>
			<div class="intervention-campus">Campus : <c:out value="${campus.name}"/></div>
			<div class="intervention-begin">From : <c:out value="${sdf.format(intervention.beginDate)}"/></div>
			<div class="intervention-end">To : <c:out value="${sdf.format(intervention.endDate)}"/></div>
			<div class="intervention-description"><p><c:out value="${intervention.description}"/></p></div>
			<c:if test="${markNumber != 0}"><div class="pie-chart"><img alt="pie-chart" title="Pie Chart of the marks" src="<c:out value="${pieChartUrl}"/>" /></div></c:if>
			<div class="clear"></div>
		</div>
		</c:when>
		<c:when test="${action=='intervention-error'}">
			<p>Error : Intervention not found</p>
		</c:when>
	</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>