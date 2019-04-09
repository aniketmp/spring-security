<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/css/main.css" rel="stylesheet">
<script src="/js/main.js"></script>
<title>Welcome Page</title>
</head>
<body>	
	<form method="post" action="/logout">
	  <input type="submit" value="logout" /> 
	  <input type="hidden"                        6
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
	</form>
	 <h2 class="hello-title">Hello ${name}</h2>
	 <h2>Your user details is as follows:</h2>
	 <h2>Password  ${password}</h2>
	 <h2>Bank Name ${companyName}</h2>
	 <h2>Roles ${roles}</h2>	 
</body>
</html>