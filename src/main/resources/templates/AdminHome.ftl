
<html>


<head>

<style>
	#header
	{
		background-color:black;
		color:red;
		text-align:center;
		padding:5px;
	}
	#footer
	{
		background-color:black;
		color:red;
		text-align:center;
		padding:5px;
	}
	
	table
	{		 
		 align="center";
		 border: 1px solid black; 
	}	
	th
	{
		padding: 5px;
    	text-align: left;
    	color: white;
       	border: 1px solid black;
       	background-color: DimGray;       				 
	}
	td
	{
		padding: 5px;
    	text-align: left;
       	border: 1px solid black;	
       	color: DimGray;	
    }

    <title> <h2><> Administrator </title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
</style>
</head>

<body>    
    <div id="header">
    	<h2>CSS Administration Form</h2>  
    </div> <br><br><br><br><br><br>
    
      	<input type="submit" id="submit" value="Release a problem" ><br><br><br>
		   	
		<form method="POST" enctype = "multipart/form-data" action="/cs480/AdminHome">  
		
    	<table width="90">
    		<tr>
				<td>ProblemID: </td>
				<td><input type="text" id="probId" name="ProblemID" required pattern="[0-9]{1,}" required> </td>
			</tr>
			   
			    <td>Week: </td>
			    <td><select  id="mySelect"name="Weeks" onchange="selectWeek()" required>
			    
				    <option value="0">      </option>
				    <option value="1">Week 1</option>
				    <option value="2">Week 2</option>
				    <option value="3">Week 3</option>
				    <option value="4">Week 4</option>
				    <option value="5">Week 5</option>
				    
			    </select></td>  
			</tr>
			
			<tr>				
	    		<input type="file" id="file" name="file" size="40" required accept="file_extension.docx|.pdf">
	    	
    			<input type="submit" id="release" value="Release" onclick="releasePro()">
    		</tr>
    			
        </table>
    </form>
		   	

	  <br><br><br>
	<table 	 width="100%">
		<tr>
			<th> Week </th>
			<th> UserID </th>
			<th> ProbemID </th>
			<th> File </th>
			<th> Status </th>
			<th> Date </th>
			<th> Score </th>
			<th> setScore </th>
		</tr>
		<#list users as user>
			<tr>
			     <td>${user.week}</td>
				 <td>${user.id}</td>
				 <td>${user.prob}</td>
				 <td><a href = "http://localhost:8080/user/${user.id}/download ">${user.fileName}</td> 
	             <td>${user.stat}</td>
				 <td>${user.creationTime}</td>
				 <td>${user.score}</td>
				 <td>
			        <form method = "POST" enctype = "multipart/form-data" action = "/cs480/score/${user.id}/setScore">
		     		<input type ="text" name = "score" id = "score" required pattern = "[0-9]{2}" title = "Invalid Value" >
		     		<input type = "submit" value = "Submit" >
		     		</form>
		     	</td>
			</tr>
		</#list>
		
		</table>

	</div>
    
 
<br><br><br><br><br><br><br><br><br>
    <div id="footer">
    	Copypright © PolyPower	
    </div>	   
</body>
<script>
	function releasePro()
	{
		document.getElementById("submit").disabled = true;
    }
</script>
</html>