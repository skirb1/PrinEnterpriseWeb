<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>BHC JSP</title>
    
  </head>
  <body>
  
<center>
	<h1>Beartooth Hiking Company Records</h1>

	<div style="text-align:left;width:450px;margin:20px;padding-left: 20px;padding-bottom:20px;">
		<form action="/kirby_mod11/BhcRecords" method=GET> 
		  Select a date to view records:
		  <input type="date" name="startDate" required>
		  <input type="SUBMIT">
		</form>
	</div>

<c:if test="${records.size() > 0}">
<table>
	<c:forEach var="record" items="${records}">
		<tr>
			<td>${record.firstName}</td>
			<td>${record.lastName}</td>
		</tr>
	</c:forEach>
</table>
</c:if>

</center>

</body>
</html>{