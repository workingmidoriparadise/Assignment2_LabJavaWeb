<%-- 
    Document   : admin
    Created on : Jan 24, 2021, 8:22:53 PM
    Author     : KHAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Welcome Admin!</h1><br/>
        <form action="LogoutController" method="POST">
            <input type="submit" name="action" value="Logout"/>
        </form><br/>
        <a href="AdminCreateController">Create Question</a><br/>
        <a href="AdminSearchController">Manage Question</a><br/>
        <font color="red">
        ${requestScope.NOTICE}
        </font>
    </body>
</html>
