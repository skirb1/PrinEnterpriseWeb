<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>BHC JSP</title>
    <jsp:useBean id="rates" class="com.bhc.Rates" scope="session"/>
  </head>
  <body>
  
<center>
	<h1>Welcome to Beartooth Hiking Company</h1>
	<div style="padding-bottom:20px">
		<p>The Beartooth Hiking Company (BHC) provides premier tours in the Absaroka-Beartooth Wilderness. Click <a href="https://www.fs.usda.gov/recarea/shoshone/recarea/?recid=80899">here</a> for more information on the Absaroka-Beartooth Wilderness.</p>
	</div>
	
	<table>
	<tr>
		<td>
			<img src="./Images/clay_butte_06.jpg" width="230" height="170" hspace="20">
		</td>
		<td valign="middle">
			<table width="600" cellpadding="2" border="1">
				<tr>
					<th>Tour</th>
					<th>Difficulty</th>
					<th>Cost per day*</th>
					<th>Trip length</th>
				</tr>
				<tr>
					<td>Gardiner Lake</td>
					<td>Intermediate</td>
					<td>$40</td>
					<td>3 or 5 days</td>
				</tr>
				<tr>
					<td>The Hellroaring Plateau</td>
					<td>Easy</td>
					<td>$35</td>
					<td>2, 3, or 4 days</td>
				</tr>
				<tr>
					<td>The Beaten Path</td>
					<td>Difficult</td>
					<td>$45</td>
					<td>5 or 7 days</td>
				</tr>
			</table>
			<p><small>*All hikes have a 50% surcharge for Sat/Sun hikes</small></p>
		</td>
	</tr>
	</table>
<table>
<tr>
<td>
	<div style="text-align:left;border:1px solid black;width:450px;margin:20px;padding-left: 20px;padding-bottom:20px;">
		<h3>Rate Calculator:</h3>
		<form action="/kirby_JSP_mod10/BhcController" method=GET>
		  Hike:
		  <input type="radio" name="hike" value="GARDINER" onclick="setDuration([3,4])" required>Gardiner
		  <input type="radio" name="hike" value="HELLROARING" onclick="setDuration([2,3,4])">Hellroaring
		  <input type="radio" name="hike" value="BEATEN" onclick="setDuration([5,7])">Beaten
		  <br /><br />
		  Days:
		  <select id="duration" name="duration" required></select>
		  <br /><br />
		  Start date:
		  <input type="date" name="startDate" required>
		  <br /><br />
		  Number of participants:
		  <input type="number" name="participants" min="1" max="10" value="1" required>
		  <br /><br />
		  <input type="SUBMIT">
		</form>
	</div>
</td>
<td valign="top">
<%if(request.getAttribute("isRequestValid") != null) {%>
	<div style="text-align:left;border:1px solid black;width:450px;margin:20px;padding-left: 20px;">
<%
if((boolean)request.getAttribute("isRequestValid")){
double cost = rates.getCost();
if(cost >= 0){
%>
<h3>Cost of trip: $${rates.cost}</h3>
<%
} else {
%>
<h3>Error: ${rates.details}</h3>
<%
}
%>
	Hike: ${rates.hike}<br/>
	Days: ${rates.selectedDuration}<br/>
	Start Date: ${rates.beginBookingDay}<br/>
	End Date: ${rates.endBookingDay}<br/>
	Participants: ${rates.participantCount}<br/><br/>
<%
}
else {
%>
<h3>Error: Invalid request</h3>
<%
}
%>
	
	</div>
<%
}
%>

</td>
</tr>
</table>

</center>

 <script>
 	function setDuration(duration_arr){
 		var select = document.getElementById("duration");
 		var options = select.options;
 		
 		while(options.length){
 			options.remove(0);
 		}
		for(var i=0; i < duration_arr.length; i++){
			var option = document.createElement("option");
			option.text = duration_arr[i];
			option.value = duration_arr[i];
 			options.add(option);
 		}
		select.selectedIndex = 0;
 	}
 </script>
 
</body>
</html>{