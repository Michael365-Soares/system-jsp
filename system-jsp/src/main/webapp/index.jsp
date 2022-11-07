<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<title>Login do Sistema</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" 
		integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
		<style type="text/css">
		    form{
		       position:absolute;
		       height:500px;
		       width:500px;
		       margin: 100px 250px 100px;
		       border:3px outset blue;
		       border-radius:5px;s
		    }
		    
		    h1{
		       height:250;
		       width:250;
		       margin:100px 50px 50px;
		       border:3px outset red;
		       text-align: center;
		    }
		    
		    .alert alert-primary{
		        margin: 125px 225px 125px;
		        color: blue;
		        background-color: orange;
		        border-color: green;
		    }
		    
		</style>
	</head>
<body>
     <!-- Latest compiled and minified JavaScript -->
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" 
	  integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous">
	 </script>
     <form action="ServletLogin" method="post">
        <h1>LOGIN</h1>
        <div class="row mb-3">
	         <input type="hidden" value="<%= request.getParameter("url")%>" name="url">
	         <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
	         <div class="col-sm-10">
	           <input type="text" class="form-control" name="login" required>
	         </div>
	         <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
	         <div class="col-sm-10">
	           <input type="password" class="form-control" name="senha" required>
	         </div>  
	     </div>
	     <button type="submit" class="btn btn-primary">Login</button>
	     <div class="alert alert-primary" role="alert">
            ${msg}
         </div>
     </form>    
</body>
</html>