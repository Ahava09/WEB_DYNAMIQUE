<%-- 
    Document   : all
    Created on : 30 mars 2023, 21:52:41
    Author     : itu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Object[] ob = (Object[])request.getAttribute("list");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Prof!</h1>
        
        <% for(Object o : ob){%>
        <label> <%= o.toString() %> </label>
        <% } %>
        <form action="saveStudent" method="post">
            <label>Name: </label><input name="name" type="text" value="Mino"/>
            <label>Firstame: </label><input name="firstame" type="text" value="Fitahiana"/>
            <label>Date de Naissance: </label><input name="dtn" type="date" value=""/>
            <input  type="submit" value="Send
                    "/>
        </form>
        <a href="/WebDynamique/TestFramework/addStudent?id=1&name=Mino&oui=2"> Lien </a>
    </body>
</html>
