<%-- 
    Document   : all
    Created on : 30 mars 2023, 21:52:41
    Author     : itu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mpianatra</h1>
        <form action="saveStudent" method="post" enctype="multipart/form-data">
            <label>Name: </label><input name="name" type="text" value="Mino"/>
            <label>id: </label><input name="id" type="text" value="1"/>
            <label>Firstame: </label><input name="firstName" type="text" value="Fitahiana"/>
            <label>Date de Naissance: </label><input name="dateOfBirth" type="date" value="2023-02-02"/>
            <label>File: </label><input name="myfile" type="file"/>
            <input  type="submit" value="Send"/>
        </form>
        <a href="/tets/addStudent?id=1&name=Mino&oui=2"> Lien </a>
    </body>
</html>
