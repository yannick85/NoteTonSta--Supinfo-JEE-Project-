<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>Add a mark</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:choose>
	<c:when test="${action=='mark-conf'}">
		<p>Mark successfully added</p>
		<p><a href="<%= contextPath %>/intervention?id=<c:out value="${interventionId}"/>">Go to the intervention page</a></p>
		<meta http-equiv="refresh" content="2; URL=intervention?id=<c:out value="${interventionId}"/>">
	</c:when>
	<c:when test="${action=='mark-error'}">
		<p>Error :</p>
		
		<c:forEach items="${errorMsg}" var="oneErrorMsg">
			<c:out value="${oneErrorMsg}"/><br />
		</c:forEach>
		
		<h1>Add a mark</h1>
		
		<form action="" method="POST">
		<div>Id Booster :  <br /><input type="text" name="mark-idbooster" value="<c:out value="${oldIdBooster}"/>" /></div>
		
		<div>
			<table class="mark-table">
				<tr><th>About the speaker : </th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th></tr>
				<tr><td class="mark-desc">His knowledge of the subject :</td><td><input type="radio" name="mark-speaker-knowledge" value="1" /> </td><td><input type="radio" name="mark-speaker-knowledge" value="2" /> </td><td><input type="radio" name="mark-speaker-knowledge" value="3" /> </td><td><input type="radio" name="mark-speaker-knowledge" value="4" /> </td><td><input type="radio" name="mark-speaker-knowledge" value="5" /> </td></tr>
				<tr><td>His teaching abilities :</td><td><input type="radio" name="mark-speaker-abilities" value="1" /> </td><td><input type="radio" name="mark-speaker-abilities" value="2" /> </td><td><input type="radio" name="mark-speaker-abilities" value="3" /> </td><td><input type="radio" name="mark-speaker-abilities" value="4" /> </td><td><input type="radio" name="mark-speaker-abilities" value="5" /></td> </tr>
				<tr><td>The quality of answers :</td><td><input type="radio" name="mark-speaker-answers" value="1" /> </td><td><input type="radio" name="mark-speaker-answers" value="2" /> </td><td><input type="radio" name="mark-speaker-answers" value="3" /> </td><td><input type="radio" name="mark-speaker-answers" value="4" /> </td><td><input type="radio" name="mark-speaker-answers" value="5" /> </td></tr>
			</table>
		</div>
		<div>
			<table class="mark-table">
				<tr><th>About the slides : </th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th></tr>
				<tr><td class="mark-desc">The richness of the content :</td><td><input type="radio" name="mark-slide-content" value="1" /> </td><td><input type="radio" name="mark-slide-content" value="2" /> </td><td><input type="radio" name="mark-slide-content" value="3" /> </td><td><input type="radio" name="mark-slide-content" value="4" /> </td><td><input type="radio" name="mark-slide-content" value="5" /> </td></tr>
				<tr><td>The format/layout :</td><td><input type="radio" name="mark-slide-format" value="1" /> </td><td><input type="radio" name="mark-slide-format" value="2" /> </td><td><input type="radio" name="mark-slide-format" value="3" /> </td><td><input type="radio" name="mark-slide-format" value="4" /> </td><td><input type="radio" name="mark-slide-format" value="5" /> </td></tr>
				<tr><td>The examples :</td><td><input type="radio" name="mark-slide-example" value="1" /> </td><td><input type="radio" name="mark-slide-example" value="2" /> </td><td><input type="radio" name="mark-slide-example" value="3" /> </td><td><input type="radio" name="mark-slide-example" value="4" /> </td><td><input type="radio" name="mark-slide-example" value="5" /> </td></tr>
			</table>
		</div>
		<div>Comments : <br /><textarea name="mark-comment" ><c:out value="${oldComments}"/></textarea></div>
		<input type="hidden" name="id" value="<c:out value="${interventionId}"/>" />
		<input type="hidden" name="action" value="addmark" />
		<input type="submit" name="submit" value="Submit" />
		</form>
	</c:when>
	<c:when test="${action=='mark-form'}">
		<h1>Add a mark</h1>
		
		<form action="" method="POST">
		<div>Id Booster :  <br /><input type="text" name="mark-idbooster" /></div>
		
		<div>
			<table class="mark-table">
				<tr><th>About the speaker : </th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th></tr>
				<tr><td class="mark-desc">His knowledge of the subject :</td><td><input type="radio" name="mark-speaker-knowledge" value="1" /> </td><td><input type="radio" name="mark-speaker-knowledge" value="2" /> </td><td><input type="radio" name="mark-speaker-knowledge" value="3" /> </td><td><input type="radio" name="mark-speaker-knowledge" value="4" /> </td><td><input type="radio" name="mark-speaker-knowledge" value="5" /> </td></tr>
				<tr><td>His teaching abilities :</td><td><input type="radio" name="mark-speaker-abilities" value="1" /> </td><td><input type="radio" name="mark-speaker-abilities" value="2" /> </td><td><input type="radio" name="mark-speaker-abilities" value="3" /> </td><td><input type="radio" name="mark-speaker-abilities" value="4" /> </td><td><input type="radio" name="mark-speaker-abilities" value="5" /></td> </tr>
				<tr><td>The quality of answers :</td><td><input type="radio" name="mark-speaker-answers" value="1" /> </td><td><input type="radio" name="mark-speaker-answers" value="2" /> </td><td><input type="radio" name="mark-speaker-answers" value="3" /> </td><td><input type="radio" name="mark-speaker-answers" value="4" /> </td><td><input type="radio" name="mark-speaker-answers" value="5" /> </td></tr>
			</table>
		</div>
		<div>
			<table class="mark-table">
				<tr><th>About the slides : </th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th></tr>
				<tr><td class="mark-desc">The richness of the content :</td><td><input type="radio" name="mark-slide-content" value="1" /> </td><td><input type="radio" name="mark-slide-content" value="2" /> </td><td><input type="radio" name="mark-slide-content" value="3" /> </td><td><input type="radio" name="mark-slide-content" value="4" /> </td><td><input type="radio" name="mark-slide-content" value="5" /> </td></tr>
				<tr><td>The format/layout :</td><td><input type="radio" name="mark-slide-format" value="1" /> </td><td><input type="radio" name="mark-slide-format" value="2" /> </td><td><input type="radio" name="mark-slide-format" value="3" /> </td><td><input type="radio" name="mark-slide-format" value="4" /> </td><td><input type="radio" name="mark-slide-format" value="5" /> </td></tr>
				<tr><td>The examples :</td><td><input type="radio" name="mark-slide-example" value="1" /> </td><td><input type="radio" name="mark-slide-example" value="2" /> </td><td><input type="radio" name="mark-slide-example" value="3" /> </td><td><input type="radio" name="mark-slide-example" value="4" /> </td><td><input type="radio" name="mark-slide-example" value="5" /> </td></tr>
			</table>
		</div>
		<div>Comments :  <br /><textarea name="mark-comment" ></textarea></div>
		<input type="hidden" name="id" value="<c:out value="${interventionId}"/>" />
		<input type="hidden" name="action" value="addmark" />
		<input type="submit" name="submit" value="Submit" />
		</form>
	</c:when>
	<c:otherwise>
		<p>No intervention id</p>
	</c:otherwise>
</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>