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

	form
	{
	    width: 300px;
	    margin: 0 auto;
    }
    <title> <h2> Login </h2> </title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
</style>
</head>

<body>    
   
    <div id="header">
    	<h2> LogIn to our Website </h2>              
    </div>
    <br><br><br><br><br>
	   
 
    <div>
       <table border = "1">
          <tr> 
             <td>UserName : </td>
             <td>
			        <form method = "POST" enctype = "multipart/form-data" action = "/cs480/codeSubmit/login">
		     		<input type ="text" id = "userId" name = "userId" required>
		     		<input type = "submit" value = "Submit" >
		     		</form>
		     </td>
       </table>
    </div>
       <#if userId?has_content>
       <p>User "userId"  does not exist. You want to <a href = "/cs480/codeSubmit/signUp"> sign up? </p>        
    </#if>
 
    
    <script language="javascript">
    	function check(form)
    	{
    		
    		if(form.userId.value == "hs")
    		{
    			window.open('codeSubmit.ftl')
    		}
    		else{
    		alert("Invalid userid")
    		}
    
    </script>
      
</body>

</html>