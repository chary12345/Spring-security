<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
${message}
	<form action="saveUser">
		First Name:<input type="text" name="fname" /><br /> <br /> Last
		Name:<input type="text" name="lname" /><br /> <br /> MailId:<input
			type="text" name="mailid" /><br /> <br /> Password:<input
			type="password" name="password" /><br /> <br /> Mobile No:<input
			type="text" name="mobileno" /><br /> <br /> Location:<input
			type="text" name="location" /><br /> <br /> <input type="submit"
			value="register" />
	</form>
	<a href="login.jsp">login</a>|  

</body>
</html>