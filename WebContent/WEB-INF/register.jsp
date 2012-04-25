<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>Register</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:choose>
	<c:when test="${action=='register-conf'}">
		<p>Speaker added</p>
	</c:when>
	<c:when test="${action=='register-error'}">
		<p>Error :</p>
		
		<c:forEach items="${errorMsg}" var="oneErrorMsg">
			<c:out value="${oneErrorMsg}"/><br />
		</c:forEach>
		
		<h1>Register</h1>
		
		<form action="" method="POST">
		<div>First Name : <br /><input type="text" name="speaker-firstname" value="<c:out value="${oldFirstname}"/>" /></div>
		<div>Last Name : <br /><input type="text" name="speaker-lastname" value="<c:out value="${oldLastname}"/>" /></div>
		<div>Email : <br /><input type="text" name="speaker-mail" value="<c:out value="${oldEmail}"/>" /></div>
		<div>Password : <br /><input type="password" name="speaker-password"/></div>
		<div>Password confirmation : <br /><input type="password" name="speaker-confpassword" /></div>
		<input type="hidden" name="action" value="register" />
		<input type="submit" name="submit" value="Submit" />
		</form>
	</c:when>
	<c:otherwise>
		<h1>Register a speaker</h1>
		
		<form action="" method="POST">
		<div>First Name : <br /><input type="text" name="speaker-firstname" /></div>
		<div>Last Name : <br /><input type="text" name="speaker-lastname" /></div>
		<div>Email : <br /><input type="text" name="speaker-mail" /></div>
		<div>Password : <br /><input type="password" name="speaker-password" /></div>
		<div>Password confirmation : <br /><input type="password" name="speaker-confpassword" /></div>
		<input type="hidden" name="action" value="register" />
		<input type="submit" name="submit" value="Submit" />
		</form>
	</c:otherwise>
</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>