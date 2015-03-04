
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
   	<input type = "submit" value = "Release a problem!" >
    <br><br><br><br><br><br><br><br>
    

    

    
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
		<#list submissions as submission>
			<tr>
			     <td>${submission.weekNo}</td>
			     <td>${submission.userId}</td>
                 <td>${submission.problemId}</td>
                 <td><a href = "http://localhost:8080/user/${submission.userId}/${submission.weekNo}/download ">${submission.fileName}</td>
                 <td>${submission.status?c}</td>
                 <td>${submission.creationTime}</td>
                 <td>${submission.score}</td>
                 <td>
			        <form method = "POST" enctype = "multipart/form-data" action = "/cs480/AdminHome/${submission.userId}/${submission.weekNo}/setScore">
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
	function gradeSubmitted(score){
	   var message, x;
       message = document.getElementById("message");
       message.innerHTML = "";
	
	   if(score.value == ""){
	      alert( "Please, Type the score");
	      message.innerHTML ="Please, Type the score";
	   }
	   else if(isNaN(score.value)){
	       alert("Invalid input");
	       message.innerHTML ="Invalid input";
	   }else {
	      alert("Graded");
	      message.innerHTML ="Graded";
	   }
    }
</script>
</html>