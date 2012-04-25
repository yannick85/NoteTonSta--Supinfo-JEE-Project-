<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>Modify an intervention</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:choose>
	<c:when test="${action=='modintervention-conf'}">
		<p>Intervention successfully modified</p>
	</c:when>
	<c:when test="${action=='modintervention-conf'}">
		<p>Error : No intervention Id</p>
	</c:when>
	<c:when test="${action=='modintervention-form' or action=='modintervention-error'}">
		<c:if test="${action=='modintervention-error'}">
			<p>Error : </p>
			<c:forEach items="${errorMsg}" var="oneErrorMsg">
				<c:out value="${oneErrorMsg}"/><br />
			</c:forEach>
		 </c:if>	
		<h1>Add an intervention</h1>
		
		<form action="" method="POST">
		<div>Subject : <br /><input type="text" name="modintervention-subject"  value="<c:out value="${oldSubject}"/>"/></div>
		<div>Campus : <br /><select name="modintervention-campus" >
			<c:forEach items="${listCampus}" var="campus">
					<option value="<c:out value="${campus.id}"/>" <c:if test="${oldCampus == campus.id}">selected="selected"</c:if> ><c:out value="${campus.name}" /></option>
			</c:forEach>
		</select></div>
		<div>From : (YYYY-MM-DD)<br /><input type="date" name="modintervention-from" value="<c:out value="${oldDateFrom}"/>"/></div>
		<div>To : (YYYY-MM-DD)<br /><input type="date" name="modintervention-to" value="<c:out value="${oldDateTo}"/>"/></div>
		<div>Description : <br /><textarea name="modintervention-desc" ><c:out value="${oldDescription}"/></textarea></div>
		<input type="hidden" name="id" value="intervention.id" />
		<input type="hidden" name="action" value="<c:out value="${interventionId}"/>" />
		<input type="submit" name="submit" value="Submit" />
		</form>
	</c:when>
</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>