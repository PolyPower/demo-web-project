﻿<html>

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

    <title> <h2><> CSS Code Submission </title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
</style>
</head>

<body>    
    <div id="header">
    	<h2>CSS Code Submission form! </h2>              
    </div>
    <br><br><br><br><br><br><br>
    <div>
          <div>    
	           <table 	 width="100%">
		                <tr>
			                <th> Week </th>
							<th> UserID </th>
							<th> ProbemID </th>
							<th> File </th>
							<th> Status </th>
							<th> Date </th>
							<th> Score </th>
						</tr>
							<#list users as user>
           						<tr>
           						     <td>${user.week}</td>
             						 <td>${user.id}</td>
             						 <td>${user.prob}</td>
            						 <td>${user.fileName}</td>
 						             <td>${user.stat}</td>
             						 <td>${user.creationTime}</td>
        						     <td>${user.score}</td>
          						</tr>
       					</#list>
               </table>
               </div>
               
               <hr>
               
               <div>
               	<br><br>
		<table width="120">
			<tr>
				<td>UserID: </td>
			    <td>ProblemID: </td>
			    <td>Week: </td>
			    
  
			</tr>
			<tr>
		     	<td> <input type="text" name="prob"></td>
		     	<td> <input type="text" name="id"> </td>
				
			</tr>	
	
	<tr>
		<td>	<form method="POST" enctype = "multipart/form-data" action="/upload" onSubmit="displayMsg()">    
    	<p>	Please specify a file

    		<input type="file" name="file" size="40">
    	</td>
    	<td>
    		<input type="submit" onClick="diplayMsg()" value="Upload">
    	</td>	    	
    	</P>    
    </form>
	
	</tr>
	</table>
	</div>
    
      
<script>

	function displayMsg(){
		alert("You successfully uploaded!");
	}
</script>
    
    <div id="footer">
    	Copypright © PolyPower	
    </div>	   
</body>

</html>