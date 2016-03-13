<%-- 
    Document   : index
    Created on : 7/03/2016, 11:03:11 PM
    Author     : Mauro
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="models.Queries"%>
<%@page import="models.Conection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Conection conection = new Conection(); // class conection instance
Queries q = new Queries(); // class queries instance
boolean stateConection = conection.conect(); // get state conection
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marathon Skills 2016</title>
        <link rel="stylesheet" href="stylesheets/main.css">
    </head>
    <body>
        <%
            if(!stateConection){ %>
                <script>document.write('No conected to database <a onclick="location.reload()">Reload</a>');</script>
            <% }else{ %>
                <h1 class="title">Marathon Skills 2016</h1>
                <h2 class="subtitle">Runner Results</h2>
                <form action="" method="POST" class="formFindRunner">
                    <label for="nameRunner">Name</label>
                    <input id="nameRunner" type="text" name="nameRunner" required>
                    <button type="submit" name="btnFind">Find</button>
                </form>
                <div class="center">
                    This list show the event results of the marathon in the you are participed. <br>
                    The general place is in relation to all runners from event. <br>
                    The place of category  is in relation to all runners from the same gender <br>
                    and age category.
                </div>
                <footer>
                    <button type="button" name="btnUnderstandMarathon" onclick="location.href='./understandMarathon.jsp'">Undertand Marathon</button>
                </footer>
            <% }
            
            /* validate if the user is no empty */
            if(request.getParameter("nameRunner") != null && request.getParameter("nameRunner") != ""){
                String nameRunner = request.getParameter("nameRunner");
                
                String[] dataRunner = q.getDataRunner(nameRunner);
                ResultSet dataRunners = q.getDataRunners(nameRunner);
            
                if(dataRunner.length > 0 || dataRunner != null){ %>
                    <div class="resultMarathon">
                    <% if(dataRunner[0] == null && dataRunner[1] == null){ %>
                        <div class="center mid-padding">
                            <b>No found data</b>
                        </div>   
                    <% }else { %>
                        <div class="center mid-padding">
                            <b>Gender: </b> <span><%= dataRunner[0] %></span>&nbsp;&nbsp;<b>Category: </b> <span><%= q.calculateCategory(dataRunner[1]) %></span>
                        </div>
                    <% } %>
                    </div>
                <% }
            }
        %>
    </body>
</html>
