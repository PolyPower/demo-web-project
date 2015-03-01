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
    nav
    {
      border:1px solid red;
      border-width:1px 0;
      list-style:none;
      margin:0;
      padding:0;
      text-align:right;
    
    }

    <title><h2> CSS CodeSubmit </h2></title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
</style>
</head>

<body>    
    <div id="header">
    	<h2><h2>CodeSubmit<span style="color:while;font-weight:bold"><sub>.com</sub></span></h2>              
    </div><br>
    <div>
   		<nav>
			<a href="/about/">About</a> |
			<a href="/archives/">Archives</a> |
			<a href="/new/">New</a> |
			<a href="/cs480/AdminHome/">AdminHome</a> |
			<a href="/register/">Register</a> |
			<a href="/cs480/codeSubmit/">User Login</a> |
		</nav>		   		
	</div><br>
    
   	<fieldset>
	  <legend>  <h2 style="color:red">Problems Archives</h2></legend>
	 	The problems archives table shows problems you would like to tackle.
		Click the description/title of the problem to view details.
	 </fieldset>
    
    
        <br><br>
    
    <div>
          <div>    
	           <table width="50%">
                <tr>
	                <th> No. </th>
	                <th> ProblemID </th>
					<th> Decription/Title </th>					
					<th> Week </th>		
				</tr>
				<#list problems as problem>
					<tr>
						<td>${problem.problemNo}</td>
						<td>${problem.problemId}</td>
						<td><a href="http://localhost:8080/problem/${problem.problemId}/download">${problem.fileName}</td> 
					  	<td>${problem.week}</td>
			        
					</tr>
			  </#list>
   		</table>
      </div><br><br>
    
    
    <div id="footer">
    	Copypright © PolyPower	
    </div>	   
</body>

</html>