<html>

<head>
  <title>Upload Form</title>
  <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
  <script src="/js/home-control.js"></script>
</head>

<body>    
  <div>	
    <form method="POST" enctype = "multipart/form-data" action="/submit">  
    <table width="200">
    <tr>
	  <td>UserID: </td>
	  <td><input type="text" name="userId"> </td>
    </tr>
    <tr>
	  <td>ProblemID: </td>	
	  <td><input type="text" name="uvaID"></td>
	</tr>    
	<tr>
	  <td>Week: </td>
	  <td><select name="weekNo" width="200">
	    <option value="1">Week 1</option>
		<option value="2">Week 2</option>
		<option value="3">Week 3</option>
		<option value="4">Week 4</option>
		<option value="5">Week 5</option>
	  </select></td>
    </tr>
    <tr>
      <input type="file" name="file" size="40">
    </tr>
    </table>
	
    <input type="submit" onClick="diplayMsg()" value="Upload">
        
    </form>
  </div>

    
</body>

</html>