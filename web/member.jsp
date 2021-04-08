<%-- 
    Document   : member
    Created on : Jan 25, 2021, 2:15:42 PM
    Author     : KHAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Page</title>
    </head>
    <body>
        <h1>Welcome ${sessionScope.email}</h1>
        <form action="DoQuestController">
            <select name="rdSelectSubject">
                <option value="Prj311- Java Desktop">Prj311- Java Desktop</option>
                <option value="Prj321- Java Web">Prj321- Java Web</option>
            </select>
            <input type="submit" name="action" value="DoQuest"/>
        </form>
        <br/>
        <a href="HistoryController">View History</a><br/>
        <a href="LogOutController">Logout</a>
    </body>
</html>
