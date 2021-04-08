<%-- 
    Document   : viewHistoryDetail
    Created on : Feb 4, 2021, 7:17:53 PM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Detail Page</title>
    </head>
    <body>
        <h1>History Detail</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Question Content</th>
                    <th>A</th>
                    <th>B</th>
                    <th>C</th>
                    <th>D</th>
                    <th>Your answer</th>
                    <th>Correct Answer</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach begin="1" end="5" var="count">
                    <tr>
                        <td>${count}</td>
                        <td>${sessionScope.listHistoryQuestion.get(count - 1).questionContent}</td>
                        <td>${sessionScope.listHistoryQuestion.get(count - 1).answer1}</td>
                        <td>${sessionScope.listHistoryQuestion.get(count - 1).answer2}</td>
                        <td>${sessionScope.listHistoryQuestion.get(count - 1).answer3}</td>
                        <td>${sessionScope.listHistoryQuestion.get(count - 1).answer4}</td>
                        <td>${sessionScope.historyDetailDTO.listUserAnswer[count - 1]}</td>
                        <td>${sessionScope.historyDetailDTO.listCorrectAnswer[count - 1]}</td>
                        <td>${sessionScope.historyDetailDTO.listStatus[count - 1]}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="BackHistoryController">Back to history page</a>
    </body>
</html>
