
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
    
      	<input type="submit" id="submit" value="Release a problem" onclick="newProblemReleased()"><br><br><br>
		   	
		<form method="POST" enctype = "multipart/form-data" action="/cs480/AdminHome">  
		
    	<table width="90">
    		<tr>
				<td>ProblemID: </td>
				<td><input type="text" id="probId" name="ProblemID" required pattern="[0-9]{1,}" required autofocus> </td>
			</tr>
			<tr>   
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
			    <td>Quarter: </td>
			    <td><select  id="mySelectQtr"name="Quarter" required>
			    
				    <option value="0">      </option>
				    <option value="Fall">Fall</option>
				    <option value="Winter">Winter</option>
				    <option value="Spring">Spring</option>
				    <option value="Summer">Summer</option>
				    
			    </select></td>  
			</tr>
			<tr>   
			    <td>Year: </td>
			    <td><select  id="mySelectYr"name="Year" required>
			    
				    <option value="0">      </option>
				    <option value="2014">2014</option>
				    <option value="2015">2015</option>
				    <option value="2016">2016</option>
				    <option value="2017">2017</option>
				    
			    </select></td>  
			</tr>
			<tr>				
	    		<input type="file" id="file" name="file" size="40" required accept="file_extension.docx|.pdf">
	    	
    			<input type="submit" id="release" value="Release" onclick="releasePro()">
    		</tr>
    			
        </table>
    </form>
		   	
    <#if submissions?has_content>
    <#else>
        <p>The user doesnot exist</p>
    </#if>
    
    <div>
       <table border = "1">
          <tr> 
             <td>UserName : </td>
             <td>
			        <form method = "POST" enctype = "multipart/form-data" action = "/cs480/AdminHome/list/user">
		     		<input type ="text" id = "userid" name = "userId" required>
		     		<input type = "submit" value = "Submit" >
		     		</form>
		     </td>
       </table>
    </div>
    
	  <br><br><br>
	<table 	 width="100%">
		<tr>
			<th> UserID </th>
			<th> ProbemID </th>
			<th> File </th>
			<th> Status </th>
			<th> Date </th>
			<th> Score </th>

		</tr>
		<#list submissions as submission>
			<tr>
			     <td>${submission.creationTime}</td>
			     <td>${submission.userId}</td>
                 <td>${submission.problemId}</td>
                 <td><a href = "http://localhost:8080/user/${submission.userId}/${submission.problemId}/download ">${submission.fileName}</td>
                 <td>${submission.status?c}</td>
                 <td>${submission.score}</td>

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
function newProblemReleased()
{
	alert("New Probem has been released!");
}
	function releasePro()
	{
		document.getElementById("submit").disabled = true;
    }
</script>
</html>