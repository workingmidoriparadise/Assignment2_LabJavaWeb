<%-- 
    Document   : login
    Created on : Jan 23, 2021, 8:49:20 PM
    Author     : KHAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="LoginController" method="POST">
            Email: <input type="text" name="txtEmail" value=""/>
            <font color="red">
            ${requestScope.INVALID.emailError}
            </font><br/>
            Password: <input type="password" name="txtPassword" value=""/>
            <font color="red">
            ${requestScope.INVALID.passwordError}
            </font><br/>
            <input type="submit" value="Login" name="btAction"/>
            <input type="reset" value="Reset"/>
        </form><br/>
        <a href="GoCreateAccountController">Click here to create new account</a>
        <br/>
        <font color="green">
        ${requestScope.createAccountNotice}
        </font>
    </body>
</html>
