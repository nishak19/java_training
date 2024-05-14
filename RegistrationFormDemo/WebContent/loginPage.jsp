<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Customer Login<br><br>
<%
String error = (String) session.getAttribute("error");
if(error != null){
	out.print(error);
	session.setAttribute("error",null);
} 
%>
</h1>
 <form action="loginServlet" method="post">
 UserName: <input type="text" name="username" required="required"/><br><br>
 Password: <input type="password" name="password" required="required"/><br><br>
 <input type="submit" value="Login"/>
 </form>
</body>
</html>