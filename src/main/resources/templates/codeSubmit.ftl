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
		 width:100%; 
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

	
    <title> <h2>CSS Code Submission Form </title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
</style>
</head>

<body>    
   
    <div id="header">
    	<h2> CSS Code Submission form! </h2>              
    </div>
    <br><br><br><br><br><br><br><br>
    
	<table>
		<tr>
			<th> Week </th>
			<th> ProbemID </th>
			<th> Description </th>
			<th> Status </th>
			<th> Date </th>
			<th> Score </th>
		</tr>
		
		<tr>
			<td> 1 </td>
			<td> 1235 </td>
			<td> LinkedList </td>
			<td> N/A </td>
			<td> - </td>
			<td> - </td>
		</tr>
		
		<tr>
			<td> 2 </td>
			<td> 222 </td>
			<td> -- </td>
			<td> -- </td>
			<td> -- </t>
			<td> - </td>
		</tr>
	</table>

	<
    <form method="POST" action=" " name="submit">    
    	<p>	Please specify a file
    	   		
    		<input type="file" name="datafile" size="40">
    		<input type="submit" onclick="submitCode()" value="Submit">
    		    	
    	</P    
    </form>
    
    <div id="footer">
    	Copypright © PolyPower	
    </div>	   
</body>

</html>