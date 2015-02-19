
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

    <br><br><br><br><br><br><br><br>
    
    <table width="100">
		<tr>
			<td>AdminID: </td>
			<td> <input type="text" id="input_userId" autofocus> </td>	
		</tr>
		<tr>
			<td>ProblemID: </td>
			<td> <input type="text" id="input_problemId"> </td>			
		</tr>	

	</table>
	  <br><br><br>
	<table 	 width="100%">
		<tr>
			<th> Week </th>
			<th> UserID </th>
			<th> ProbemID </th>
			<th> Description </th>
			<th> File </th>
			<th> Status </th>
			<th> Date </th>
			<th> Score </th>
		</tr>
		
		
		</table>

	</div>
    
 
<br><br><br><br><br><br><br><br><br>
    <div id="footer">
    	Copypright © PolyPower	
    </div>	   
</body>

</html>