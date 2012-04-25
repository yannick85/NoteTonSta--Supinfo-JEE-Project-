<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>Log in</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:choose>
	<c:when test="${action=='logout'}">
		<p>You're logged out</p>
	</c:when>
	<c:when test="${action=='log-conf'}">
		<p>Login successfull</p>
	</c:when>
	<c:when test="${action=='log-error'}">
		<p>Login error</p>
		<form action="" method="POST">
				<h1>Please log !</h1>
				<div>Email :<br /><input type="text" name="speaker-mail" placeholder="Your email address" /></div>
				<div>Password :<br /><input type="password" name="speaker-password" placeholder="Your password" /></div>
				<input type="hidden" name="action" value="log" />
				<input type="submit" name="submit" value="Submit" />
		</form>
	</c:when>
	<c:otherwise>
		<form action="" method="POST">
				<h1>Please log !</h1>
				<div>Email :<br /><input type="text" name="speaker-mail" placeholder="Your email address" /></div>
				<div>Password :<br /><input type="password" name="speaker-password" placeholder="Your password" /></div>
				<input type="hidden" name="action" value="log" />
				<input type="submit" name="submit" value="Submit" />
		</form>
	</c:otherwise>
</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>