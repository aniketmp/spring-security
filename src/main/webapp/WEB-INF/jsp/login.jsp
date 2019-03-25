<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link href="/css/main.css" rel="stylesheet">
</head>
<body>
<form action="/login" method="post">     
	<c:if test="${param.error != null}">       
		<p>
			Invalid Username and Password!
		</p>
	</c:if>
	<c:if test="${param.logout != null}">      
		<p>
			You have been logged out successfully!
		</p>
	</c:if>
	<p>
		<label for="username" class="hello-title">Username</label>
		<input type="text" id="username" name="username"/>	
	</p>
	<p>
		<label for="password">Password</label>
		<input type="password" id="password" name="password"/>	
	</p>
	<input type="hidden"                        6
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
	<button type="submit" class="btn">Log in</button>
</form>
</body>
</html>