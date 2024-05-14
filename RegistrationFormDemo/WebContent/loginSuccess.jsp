<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> Welcome <%= session.getAttribute("firstName") %>!
<br><br><center>Your Order Details are:-</center>
</h1>
<%
Connection connection=null;
try{
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
PreparedStatement ps=connection.prepareStatement("select * from order_details");
ResultSet result=ps.executeQuery();
%>
<table cellpadding="15" border="1" style="background-color: #ffffcc;" align="center">
 <tr>
<td><b>Order_Id</b></td>
<td><b>Order_Name</b></td>
<td><b>Order_Quantity</b></td>
<td><b>Order_Price</b></td>
</tr>
    <%
    while (result.next()) {
    %>
    <tr>
        <td><center><%=result.getInt(1)%></center></td>
        <td><center><%=result.getString(2)%></center></td>
        <td><center><%=result.getString(3)%></center></td>
        <td><center><%=result.getInt(4)%></center></td>
    </tr>
    <%   }    %>
    <%
    result.close();
    ps.close();
    connection.close();
} catch (Exception ex) {
    %>
    </font>
    <font size="+3" color="red"></b>
        <%
            out.println("Unable to connect to database.");
            }
        %>
    </table>
</body>
</html>