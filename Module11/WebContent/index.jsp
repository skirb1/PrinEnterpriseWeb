<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>BHC JSP</title>
    
<style>
td { padding-left: 10px; padding-right: 10px;}
th { padding: 5px 5px 5px 5px; background-color: #99ddff;}
table { background-color: #e6f7ff;}
</style>

  </head>
  <body>
  
<center>
	<h1>Beartooth Hiking Company Records</h1>

	<div style="text-align:left;width:450px;margin:20px;padding-left: 20px;padding-bottom:20px;">
		<form action="/kirby_mod11/BhcRecords" method=GET> 
		  Select a date to view records:
		  <input type="date" name="startDate" value="${startDate}" required>
		  <input type="SUBMIT">
		</form>
	</div>
	
	<c:choose>
		<c:when test="${error != null}">
			<p style="color: RED;">${error}</p>
		</c:when>
		<c:when test="${records != null && records.size() > 0}">
			<table>
				<tr>
					<th>First</th>
					<th>Last</th>
					<th>Start Day</th>
					<th>Days</th>
					<th>Guide</th>
					<th>Location</th>
				</tr>
				<c:forEach var="record" items="${records}">
					<tr>
						<td>${record.firstName}</td>
						<td>${record.lastName}</td>
						<td>${record.startDate}</td>
						<td>${record.numberOfDays}</td>
						<td>${record.guideName}</td>
						<td>${record.location}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:when test="${records != null && records.size() == 0}">
			<p>No results found</p>
		</c:when>
	</c:choose>

</center>

</body>
</html>