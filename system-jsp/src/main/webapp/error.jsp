<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>ERROR</title>
	</head>
	<body>
		<h1>Error:Entre em contato com o suporte...</h1>
		<% 
		   out.println(request.getAttribute("msg"));
		%>
	</body>
</html>