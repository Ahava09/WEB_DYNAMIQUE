<%-- 
    Document   : input
    Created on : 18 avr. 2023, 06:07:46
    Author     : itu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Object ob = (Object)request.getAttribute("object");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <label> <%= o.getFirstname() %> </label></br>
        <label> <%= o.getId() %> </label></br>
        <label> <%= o.getMyfile().getName() %> </label></br>

    </body>
</html>
