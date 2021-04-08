<%-- 
    Document   : create
    Created on : Jan 25, 2021, 10:04:27 PM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <h1>Create Question</h1>
        <form action="CreateController" method="POST">
            Category: 
            <select name="txtSubject">
                <c:forEach items="${sessionScope.LISTSUBJECT}" var="value" varStatus="counter">
                    <option value="<c:out value="${value}"/>"><c:out value="${value}"/></option>
                </c:forEach>
            </select><br/>

            Question: <input type="text" name="txtQuestionContent" value="${param.txtQuestionContent}" placeholder="Input question here"/><br/>
            <font color="red">
            ${requestScope.INVALID.questionError}
            </font><br/>
            Answer1: <input type="text" name="txtAnswer1" value="${param.txtAnswer1}" placeholder="Answer 1"/><input type="radio" name="rdCorrectAnswer" value="1" checked="checked"/><label>Is Correct</label><br/>
            <font color="red">
            ${requestScope.INVALID.answer1Error}
            </font><br/>
            Answer2: <input type="text" name="txtAnswer2" value="${param.txtAnswer2}" placeholder="Answer 2"/><input type="radio" name="rdCorrectAnswer" value="2"/><label>Is Correct</label><br/>
            <font color="red">
            ${requestScope.INVALID.answer2Error}
            </font><br/>
            Answer3: <input type="text" name="txtAnswer3" value="${param.txtAnswer3}" placeholder="Answer 3"/><input type="radio" name="rdCorrectAnswer" value="3"/><label>Is Correct</label><br/>
            <font color="red">
            ${requestScope.INVALID.answer3Error}
            </font><br/>
            Answer4: <input type="text" name="txtAnswer4" value="${param.txtAnswer4}" placeholder="Answer 4"/><input type="radio" name="rdCorrectAnswer" value="4"/><label>Is Correct</label><br/>
            <font color="red">
            ${requestScope.INVALID.answer4Error}
            </font><br/>
            <input type="submit" name="btAction" value="Create"/>
        </form>
    </body>
</html>
