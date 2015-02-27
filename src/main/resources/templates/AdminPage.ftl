
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
    </div>

    <div>
       <table border = "1">
          <tr> 
             <td>UserName : </td>
             <td>
			        <form method = "POST" enctype = "multipart/form-data" action = "/cs480/AdminHome/list/user">
		     		<input type ="text" name = "userId" required>
		     		<input type = "submit" value = "Submit" >
		     		</form>
		     </td>
       </table>
    </div>
    <div>
    	<table 	 width="100%">
		<tr>
			<th> UserID </th>
			<th> Total Score </th>
		</tr>
		<#list userscores as userscore>
			<tr>
			     <td><a href = "http://localhost:8080/cs480/AdminHome/list/${userscore.id}/listing">${userscore.id}</td>
			     <td>${userscore.totalScore}</td>
			</tr>
		</#list>
		
		</table>
    
    
    </body>
</html>