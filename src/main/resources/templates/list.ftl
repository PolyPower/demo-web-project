<html>

<head>
    <title>CS480 Demo Web Service</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
</head>

<body>    
    
    <div>
        This is a simple page to demonstrate the web UI development. 
        The key tools and techniques used include:
        <ul>
            <li>HTML - Obviously</li>
            <li><a href="http://freemarker.org/">FreeMarker</a></li>
            <li><a href="http://jquery.com/">JQuery</a></li>
            <li><a href="http://api.jquery.com/jquery.ajax/">JQuery - AJAX</a></li>
        </ul>
    </div>

    <hr>
    
    <div>
       <table border = "1">
          <tr> 
             <td>UserName : </td>
             <td>
			        <form method = "POST" enctype = "multipart/form-data" action = "/list/user">
		     		<input type ="text" name = "userId" required>
		     		<input type = "submit" value = "Submit" >
		     		</form>
		     </td>
       </table>
    </div>

    <div>
        <div>
            <table border="1">            
                <tr>
                    <td>Username</td>
                    <td>Week Number</td> 
                    <td>UVA Problem ID</td> 
                    <td>Source Code</td>
                    <td>Status</td>
                    <td>Creation Time</td>
                </tr>
                <#list submissions as submission>
                        <tr>
                            <td>${submission.userId}</td>
                            <td>${submission.weekNo}</td>
                            <td>${submission.uvaID}</td>
                            <td><a href = "http://localhost:8080/user/${submission.userId}/download ">${submission.fileName}</td>
                            <td>${submission.status?c}</td>
                            <td>${submission.creationTime}</td>
                            <td>${submission.score}</td>
                        </tr>
                </#list>
            </table>
        </div>
        
        

    
    
</body>

</html>