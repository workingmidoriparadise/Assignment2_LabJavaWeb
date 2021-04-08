<%-- 
    Document   : error
    Created on : Jan 23, 2021, 9:06:41 PM
    Author     : KHAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>
            <font color="red">
            ${requestScope.ERROR}
            </font>
        </h1>

        <a href="BackLoginController">Back to login page</a><br/>
        <a href="GoCreateAccount">Create new account</a>
    </body>
</html>
