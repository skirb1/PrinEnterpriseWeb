<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>BHC JSP</title>
    <jsp:useBean id="rates" class="com.rbevans.Rates" scope="session"/>
  </head>
  <body>
<h1>Beartooth Hiking Company Booking</h1><br />
Please enter trip details:<br /><br />
<div style="padding-left:20px;">
<form action="http://localhost:8080/MVC2/BhcController" method=GET>
  Hike:
  <input type="radio" name="hike" value="GARDINER" onclick="setDuration([3,4])">Gardiner Lake
  <input type="radio" name="hike" value="HELLROARING" onclick="setDuration([2,3,4])">Hellroaring Plateau
  <input type="radio" name="hike" value="BEATEN" onclick="setDuration([5,7])">Beaten Path
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
<p id="message"></p>

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
</html>