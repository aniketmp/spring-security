<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Get Employees</title>

</head>

<body>

    <h3 >Get Employee Info</h3>

    <div>

        <form action="http://localhost:8901/auth/oauth/authorize" method="post">

                <label>Enter Employee Id</label>

                 <input type="text" name="response_type" value="code" /> 

                 <input type="text" name="client_id" value="springsecurity" />

                 <input type="text" name="redirect_uri" value="http://localhost:8080/oauth2/redirect" />

                 <input type="SUBMIT" value="Get Employee info" />

        </form>

    </div>

</body>

</html>