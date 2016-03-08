<%-- 
    Document   : index
    Created on : 7/03/2016, 11:03:11 PM
    Author     : Mauro
--%>

<%@page import="models.Conection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Conection conection = new Conection();
boolean stateConection = conection.conect();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marathon Skills 2016</title>
        <link rel="stylesheet" href="stylesheets/main.css">
    </head>
    <body>
        <h1 class="title">Marathon Skills 2016</h1>
        <h2 class="subtitle">Runner Results</h2>
        <form action="" method="POST" class="formFindRunner">
            <label for="nameRunner">Name</label>
            <input id="nameRunner" type="text" name="nameRunner" required>
            <button type="submit" name="btnFind">Find</button>
        </form>
        <%
            if(!stateConection)
                out.print("<script>alert('No conected to database');</script>");
        %>
    </body>
</html>
