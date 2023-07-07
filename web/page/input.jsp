<%-- 
    Document   : input
    Created on : 18 avr. 2023, 06:07:46
    Author     : itu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Object[] ob = (Object[])request.getAttribute("object");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% for(Object o : ob){%>
        <label> <%= o %> </label>
        <% } %>
    </body>
</html>
