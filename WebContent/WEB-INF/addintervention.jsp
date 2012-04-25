<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>Add an intervention</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:choose>
	<c:when test="${action=='intervention-conf'}">
		<p>Intervention successfully added</p>
	</c:when>
	<c:when test="${action=='intervention-error'}">
		<p>Error :</p>
		
		<c:forEach items="${errorMsg}" var="oneErrorMsg">
			<c:out value="${oneErrorMsg}"/><br />
		</c:forEach>
		
		<h1>Add an intervention</h1>
		
		<form action="" method="POST">
		<div>Subject : <br /><input type="text" name="intervention-subject"  value="<c:out value="${oldSubject}"/>"/></div>
		<div>Campus : <br /><select name="intervention-campus" >
			<c:forEach items="${listCampus}" var="campus">
					<option value="<c:out value="${campus.id}"/>" <c:if test="${oldCampus == campus.id}">selected="selected"</c:if> ><c:out value="${campus.name}" /></option>
			</c:forEach>
		</select></div>
		<div>From : (YYYY-MM-DD)<br /><input type="date" name="intervention-from" value="<c:out value="${oldDateFrom}"/>"/></div>
		<div>To : (YYYY-MM-DD)<br /><input type="date" name="intervention-to" value="<c:out value="${oldDateTo}"/>"/></div>
		<div>Description : <br /><textarea name="intervention-desc" ><c:out value="${oldDescription}"/></textarea></div>
		<input type="hidden" name="action" value="addintervention" />
		<input type="submit" name="submit" value="Submit" />
		</form>
	</c:when>
	<c:otherwise>
		<h1>Add an intervention</h1>
		
		<form action="" method="POST">
		<div>Subject : <br /><input type="text" name="intervention-subject" /></div>
		<div>Campus : <br /><select name="intervention-campus" >
			<c:forEach items="${listCampus}" var="campus">
					<option value="<c:out value="${campus.id}"/>"><c:out value="${campus.name}"/></option>
			</c:forEach>
		</select></div>
		<div>From : (YYYY-MM-DD)<br /><input type="date" name="intervention-from" value="<c:out value="${today}"/>"/></div>
		<div>To : (YYYY-MM-DD)<br /><input type="date" name="intervention-to" value="<c:out value="${today}"/>"/></div>
		<div>Description : <br /><textarea name="intervention-desc" ></textarea></div>
		<input type="hidden" name="action" value="addintervention" />
		<input type="submit" name="submit" value="Submit" />
		</form>
	</c:otherwise>
</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>