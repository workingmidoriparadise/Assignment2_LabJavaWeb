<%-- 
    Document   : doQuest
    Created on : Jan 30, 2021, 7:48:45 PM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Do Quest Page</title>
        <style>
            .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }
        </style>
    </head>

    <body>
        <script>
            function cal(timer) {
                var minutes = parseInt(timer / 60, 10);
                var seconds = parseInt(timer % 60, 10);

                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;

                document.querySelector('#timer').textContent = minutes + ":" + seconds;

            }

            function startTimer(duration) {
                var timer = duration;
                cal(timer);
                var intervalCount = setInterval(function () {
                    cal(timer);

                    if (--timer < 0) {
                        document.getElementById("submitQuiz").submit();
                        clearInterval(intervalCount);
                    }

                }, 1000);
            }

            window.onload = function () {
                var endTime = ${sessionScope.endTime};
                var curTime = ${sessionScope.curTime};
                var diff = Math.round((endTime - curTime) / 1000);
                var fiveMinutes = diff;
                startTimer(fiveMinutes);
            };
        </script>

        <h1>Quiz Online</h1>
        <div>
            Time left: <span id="timer"></span>
            <br>

            <form id="submitAnswer" action="SubmitAnswerController" method="POST">
                <input type="hidden" name="txtQuestionID" value="${requestScope.curQuestionID}"/>
                <input type="hidden" name="page" value="${requestScope.page}"/>
                Question: ${requestScope.curQuestion}<br/>
                <input type="radio" name="rdAnswer" value="a" <c:if test="${requestScope.selectedAnswer == 'a'}">checked</c:if> onclick="document.getElementById('submitAnswer').submit();"/>${requestScope.curAnswer1}<br/>
                <br/>
                <input type="radio" name="rdAnswer" value="b" <c:if test="${requestScope.selectedAnswer == 'b'}">checked</c:if> onclick="document.getElementById('submitAnswer').submit();"/>${requestScope.curAnswer2}<br/>
                <br/>
                <input type="radio" name="rdAnswer" value="c" <c:if test="${requestScope.selectedAnswer == 'c'}">checked</c:if> onclick="document.getElementById('submitAnswer').submit();"/>${requestScope.curAnswer3}<br/>
                <br/>
                <input type="radio" name="rdAnswer" value="d" <c:if test="${requestScope.selectedAnswer == 'd'}">checked</c:if> onclick="document.getElementById('submitAnswer').submit();"/>${requestScope.curAnswer4}<br/>
            </form>
        </div>
                <br/>
                <c:if test="${requestScope.page != 0}"><a href="ChangeQuestionController?page=${requestScope.page - 1}">   Previous  </a></c:if>         
                ------------
                <c:if test="${requestScope.page != 4}"><a href="ChangeQuestionController?page=${requestScope.page + 1}">   Next  </a></c:if>
                <br/>
                <br/>
        <c:forEach var="count" begin="0" end="4">
            <a href="ChangeQuestionController?page=${count}">${count + 1}</a>
        </c:forEach>
        <br/>
        <br/>
        <form action="SubmitExamController" id="submitQuiz" method="POST">
            <input type="submit" name="action" value="Finish"/>
        </form>
    </body>
</html>
