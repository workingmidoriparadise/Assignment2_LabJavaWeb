<%-- 
    Document   : showResult
    Created on : Jan 31, 2021, 6:32:12 PM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Result Page</title>
    </head>
    <body>
        Your Score: ${requestScope.score}<c:if test="${empty requestScope.score}">0 <br/><font color="red">FAILED</font></c:if>
        <br/>
        <c:if test="${requestScope.examStatus == 'PASSED'}">
            <font color="green">
            PASSED
            </font>
        </c:if>
        <c:if test="${requestScope.examStatus == 'FAILED'}">
            <font color="red">
            FAILED
            </font>
        </c:if>
        <br/>
        <a href="BackMemberController">Done</a>
    </body>
</html>
