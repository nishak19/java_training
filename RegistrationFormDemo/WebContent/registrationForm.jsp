<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h1>Customer Register Form<br><br>
 <%
String error = (String) session.getAttribute("error");
if(error != null){
	out.print(error);
	session.setAttribute("error",null);
} 
%>
 </h1>
<form action="register" method="post">
FirstName:  <input type="text" name="firstname" required="required"/><br><br>
LastName :  <input type="text" name="lastname" required="required"/><br><br>
Email :     <input type="text" name="email" required="required"/><br><br>
UserName:   <input type="text" name="username" required="required"/><br><br>
Password:   <input type="password" name="password" required="required"/><br><br>
<input type="submit"  value="Submit"/>
<input type="reset"  value="clear"/>
</form>
</body>
</html>