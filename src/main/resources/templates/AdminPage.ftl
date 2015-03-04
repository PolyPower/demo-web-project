
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
	#touchArea {
       position: absolute;
       top: 85px;
       right: 30px;
       width: 100px;
       height:30px;
       background:#FFFFF;
       font-size:20px;
       color: #FFFFFF;
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
    <div id="touchArea">
    <p><a href = "http://localhost:8080/cs480/AdminHome"> List All</p> </div>
    <br><br><br><br><br><br><br><br>
    


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