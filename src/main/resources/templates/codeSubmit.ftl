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
<<<<<<< HEAD
    	<h2><h2>CodeSubmit<span style="color:while;font-weight:bold"><sub>.com</sub></span></h2>              
    </div><br><br><br>
=======
    	<h2>CSS Code Submission form! </h2>              
    </div>
    <br><br><br><br><br><br><br>
  
>>>>>>> Design Pattern : Observer Pattern
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
							<#list submissions as submission>
           						<tr>
           						     <td>${submission.weekNo}</td>
             						 <td>${submission.userId}</td>
             						 <td>${submission.problemId}</td>
            						 <td><a href = "http://localhost:8080/user/${submission.userId}/download ">${submission.fileName}</td> 
 						             <td></td>
             						 <td>${submission.creationTime}</td>
             						 <td>${submission.score}</td}
      						  
          						</tr>
       					</#list>
       					
               </table>
               </div>
             
               
              <hr>
            <div>
     	<br><br>
		
	<form method="POST" enctype = "multipart/form-data" action="/cs480/codeSubmit">  
    	<table width="120">
    		<tr>
				<td>UserID: </td>
				<td><input type="text" id="userId" name="UserID" required pattern="[a-zA-Z0-9._%+-]\w+@[a-zA-Z]+\.[edu]{3}$"> </td>
			<tr>	
			   
			    <td>Week: </td>
			    <td><select  id="mySelect"name="Weeks" onchange="selectWeek()">
			    
				    <option value="0">      </option>
				    <option value="1">Week 1</option>
				    <option value="2">Week 2</option>
				    <option value="3">Week 3</option>
				    <option value="4">Week 4</option>
				    <option value="5">Week 5</option>
				    
			    </select></td>  
			</tr>
			<tr>
				<td>ProblemID: </td>	
				<td><textarea rows="" cols="7" id="prob" name="ProblemID" readonly> </textarea></td>
			</tr> 
				
    		<input type="file" id="file" name="file" size="40" required accept="file_extension|.exe|.docx|.pdf|.java|.cpp">
    		<input type="submit" value="Upload">
    		
        
    </form>
	
	</table>
	</div>
    
      
<script>

	function displayMsg(){
		alert("You successfully uploaded!");
	}
	
	function selectWeek()
	{
		var probId = ["", "2323", "45415", "4566", "12345","98989"];
		var x =  document.getElementById("mySelect").selectedIndex;
		document.getElementById("prob").innerHTML = probId[x];
	}
</script>
    
    <div id="footer">
    	Copypright © PolyPower	
    </div>	   
</body>

</html>