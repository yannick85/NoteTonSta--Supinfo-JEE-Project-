<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>My interventions</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<c:choose>
			<c:when test="${empty listIntervention}">
				<h1>My interventions</h1>
				<p>No Interventions to display.</p>
			</c:when>
			<c:otherwise>
			<div class="resultAjax"></div>
			<h1>My interventions</h1>
			<table class="list-myintervention">
			<tr><th>Subject</th><th>Campus</th><th>Begin Date</th><th>End Date</th><th>Status</th><th>Global Event Mark</th></tr>
				<c:forEach items="${listIntervention}" var="intervention">
					<tr class="intervention-<c:out value="${intervention.id}"/>">
					<td><a href="<%= contextPath %>/intervention?id=<c:out value="${intervention.id}"/>" ><c:out value="${intervention.subject}"/></a></td>
						<td><c:out value="${listCampusName.get(intervention.id)}"/></td>
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
						<td><c:out value="${listMark.get(intervention.id)}"/></td>
						<td class="noborder"><div class="delete-link" onclick="deleteIntervention('<c:out value="${intervention.id}')"/>">Delete</div></td>
						<td class="noborder"><div class="update-link"><a href="<%= contextPath %>/auth/updateintervention?id=<c:out value="${intervention.id}"/>">Update</a></div></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<script type="text/javascript">
		function deleteIntervention(id){
			if(confirm("Delete this intervention ?")){
				jQuery.post('<%= contextPath %>/auth/deleteintervention?id='+id, function(data) {
					if(data == 'ok'){
					  jQuery('.intervention-'+id).html('');
					  jQuery('.resultAjax').html("Intervention succesfully deleted");
					}else{
						jQuery('.resultAjax').html("Error , the intervention was not deleted");
					}
				});
			}
		}
	</script>
<%@ include file="footer.jsp" %>
</body>
</html>