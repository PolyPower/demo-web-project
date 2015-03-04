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

    <title> <h2><> SignUp </h2> </title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
      </style>
   </head>
   
   
   <body>    
   
    <div id="header">
    	<h2> Making User Account </h2>              
    </div>
    <br><br><br><br><br>
	
    <div>
       <table border = "1">
          <tr> 
             <td>UserName : </td>
             <td>
			        <form method = "POST" enctype = "multipart/form-data" action = "/cs480/codeSubmit/signUp">
		     		<input type ="text" id = "userId" name = "userId" required>
		     		<input type = "submit" value = "Submit" >
		     		</form>
		     </td>
       </table>
    </div>
    
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