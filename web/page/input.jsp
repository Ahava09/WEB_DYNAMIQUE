<%-- 
    Document   : input
    Created on : 18 avr. 2023, 06:07:46
    Author     : itu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import ="etu1985.model.Student"%>
<% Student   ob = (Student)request.getAttribute("object");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <label> <%= ob.getFirstName() %> </label></br>
        <label> <%= ob.getId() %> </label></br>
        <label> <%= ob.getMyfile().getFileName() %> </label></br>

    </body>
</html>
