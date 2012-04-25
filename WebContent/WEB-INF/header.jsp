<div id="header-wrapper">
<div id="header">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page import="java.text.SimpleDateFormat" %>
<%
	boolean logged = request.getSession().getAttribute("speaker-id") != null;
	request.setAttribute("logged", logged);
	if(logged){
		String loggedName = (String) request.getSession().getAttribute("speaker-firstname")+" "+ (String) request.getSession().getAttribute("speaker-lastname");
		request.setAttribute("loggedName", loggedName);
	}
	String contextPath = getServletContext().getContextPath();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	request.setAttribute("sdf", sdf);
	String today = sdf.format(new java.util.Date(System.currentTimeMillis()));
	request.setAttribute("today", today);
%>
<div id="logo"><a href="<%= contextPath %>/index">Note ton Sta</a></div>

<c:choose>
		<c:when test="${logged}">
		<div id="welcome">Welcome <c:out value="${loggedName}"/></div>
			<ul class="menu">
				<li><a href="<%= contextPath %>/auth/addintervention">Add an intervention</a></li>
				<li><a href="<%= contextPath %>/auth/myintervention">My intervention</a></li>
				<li><a href="<%= contextPath %>/logout">Log out</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul class="menu">
				<li><a href="<%= contextPath %>/log">Login</a></li>
				<li><a href="<%= contextPath %>/register">Register</a></li>
			</ul>
		</c:otherwise>
</c:choose>
</div>
</div>
<div id="wrapper">
	<div id="page">