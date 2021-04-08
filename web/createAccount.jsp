<%-- 
    Document   : create
    Created on : Jan 24, 2021, 8:55:31 AM
    Author     : KHAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Sign up</h1>
        <form action="CreateAccountController" method="POST">
            Email: <input type="text" name="txtEmail1" value="${param.txtEmail1}"/>
            <font color="red">
            ${requestScope.INVALID.emailError}
            </font><br/>
            Name: <input type="text" name="txtName1" value="${param.txtName1}"/>
            <font color="red">
            ${requestScope.INVALID.nameError}
            </font><br/>
            Password: <input type="password" name="txtPassword1" value="${param.txtPassword1}"/>
            <font color="red">
            ${requestScope.INVALID.passwordError}
            </font><br/>
            Confirm: <input type="password" name="txtConfirm1" value="${param.txtConfirm1}"/>
            <font color="red">
            ${requestScope.INVALID.confirmError}
            </font><br/>
            <input type="submit" name="action" value="createAccount"/>
        </form>
    </body>
</html>
