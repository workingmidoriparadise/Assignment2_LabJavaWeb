<%-- 
    Document   : search
    Created on : Jan 29, 2021, 12:46:03 PM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search</h1>
        <form action="SearchController" method="POST">
            Search By Question Name: <input type="text" name="txtSearchByName" value="${param.txtSearchByName}"/><br/>
            Search By Status: 
            <select name="rdSearchByStatus">
                <option value="true" <c:if test="${param.rdSearchByStatus == 'true'}">selected</c:if>>True</option>
                <option value="false" <c:if test="${param.rdSearchByStatus == 'false'}">selected</c:if>>False</option>
                </select><br/>
                Search By Subject:
                <select name="rdSearchBySubject">
                    <option value="Prj311- Java Desktop" <c:if test="${param.rdSearchBySubject == 'Prj311- Java Desktop'}">selected</c:if>>Prj311- Java Desktop</option>
                <option value="Prj321- Java Web" <c:if test="${param.rdSearchBySubject == 'Prj321- Java Web'}">selected</c:if>>Prj321- Java Web</option>
                </select><br/>
                Choose search type:
                <select name="searchType">
                    <option value="searchByQuestionName" <c:if test="${param.searchType == 'searchByQuestionName'}">selected</c:if>>Search By Question Name</option>
                <option value="searchByStatus" <c:if test="${param.searchType == 'searchByStatus'}">selected</c:if>>Search By Status</option>
                <option value="searchBySubject" <c:if test="${param.searchType == 'searchBySubject'}">selected</c:if>>Search By Subject</option>
                </select><br/>
                <input type="submit" name="search" value="search"/>
            </form>

        <c:if test="${empty sessionScope.listPRJ311 && empty sessionScope.listPRJ321}">
            <font color="red">
            No result found
            </font>
        </c:if>
        <c:if test="${not empty sessionScope.listPRJ311}">
            <font color="red">
            <h2>Prj311- Java Desktop</h2>
            </font>
            <c:forEach items="${sessionScope.listPRJ311}" var="ques" varStatus="counter">
                <form action="UpdateQuestionController" method="POST">
                    Question: <input type="text" name="txtUpdateQuestion" value="${ques.questionContent}"/><font color="red">${requestScope.updateInvalid.questionError}</font><br/>
                    <input type="radio" name="rdUpdateCorrectAnswer" value="a" <c:if test="${'a' == ques.answerCorrect}">checked</c:if>/><input type="text" name="txtUpdateAnswer1" value="${ques.answer1}"/><font color="red">${requestScope.updateInvalid.answer1Error}</font><br/>
                    <input type="radio" name="rdUpdateCorrectAnswer" value="b" <c:if test="${'b' == ques.answerCorrect}">checked</c:if>/><input type="text" name="txtUpdateAnswer2" value="${ques.answer2}"/><font color="red">${requestScope.updateInvalid.answer2Error}</font><br/>
                    <input type="radio" name="rdUpdateCorrectAnswer" value="c" <c:if test="${'c' == ques.answerCorrect}">checked</c:if>/><input type="text" name="txtUpdateAnswer3" value="${ques.answer3}"/><font color="red">${requestScope.updateInvalid.answer3Error}</font><br/>
                    <input type="radio" name="rdUpdateCorrectAnswer" value="d" <c:if test="${'d' == ques.answerCorrect}">checked</c:if>/><input type="text" name="txtUpdateAnswer4" value="${ques.answer4}"/><font color="red">${requestScope.updateInvalid.answer4Error}</font><br/>
                        <select name="rdSearchBySubject">
                            <option value="Prj311- Java Desktop" <c:if test="${ques.subjectID == 1}">selected</c:if>>Prj311- Java Desktop</option>
                        <option value="Prj321- Java Web" <c:if test="${ques.subjectID == 2}">selected</c:if>>Prj321- Java Web</option>
                        </select><br/>
                        <input type="submit" name="action" value="Update"/><input type="submit" name="action" value="<c:if test="${ques.status}" var="changeState">Delete</c:if><c:if test="${not changeState}">Restore</c:if>" onclick="return confirm('Are you sure?')"/><br/>
                    <input type="hidden" name="searchType" value="${requestScope.searchType}"/>
                    <input type="hidden" name="rdSearchBySubject" value="${requestScope.rdSearchBySubject}"/>
                    <input type="hidden" name="txtSearchByName" value="${requestScope.txtSearchByName}"/>
                    <input type="hidden" name="rdSearchByStatus" value="${requestScope.rdSearchByStatus}"/>
                    <input type="hidden" name="updateQuestionID" value="${ques.questionID}"/>
                    **********************************<br/>
                </form>
            </c:forEach>
        </c:if>
        <c:if test="${not empty sessionScope.listPRJ321}">
            <font color="red">
            <h2>Prj321- Java Web</h2>
            </font>
            <c:forEach items="${sessionScope.listPRJ321}" var="ques" varStatus="counter">
                <form action="UpdateQuestionController" method="POST">
                    Question: <input type="text" name="txtUpdateQuestion" value="${ques.questionContent}"/><font color="red">${requestScope.updateInvalid.questionError}</font><br/>
                    <input type="radio" name="rdUpdateCorrectAnswer" value="a" <c:if test="${'a' == ques.answerCorrect}">checked</c:if>/><input type="text" name="txtUpdateAnswer1" value="${ques.answer1}"/><font color="red">${requestScope.updateInvalid.answer1Error}</font><br/>
                    <input type="radio" name="rdUpdateCorrectAnswer" value="b" <c:if test="${'b' == ques.answerCorrect}">checked</c:if>/><input type="text" name="txtUpdateAnswer2" value="${ques.answer2}"/><font color="red">${requestScope.updateInvalid.answer2Error}</font><br/>
                    <input type="radio" name="rdUpdateCorrectAnswer" value="c" <c:if test="${'c' == ques.answerCorrect}">checked</c:if>/><input type="text" name="txtUpdateAnswer3" value="${ques.answer3}"/><font color="red">${requestScope.updateInvalid.answer3Error}</font><br/>
                    <input type="radio" name="rdUpdateCorrectAnswer" value="d" <c:if test="${'d' == ques.answerCorrect}">checked</c:if>/><input type="text" name="txtUpdateAnswer4" value="${ques.answer4}"/><font color="red">${requestScope.updateInvalid.answer4Error}</font><br/>
                        <select name="rdSearchBySubject">
                            <option value="Prj311- Java Desktop" <c:if test="${ques.subjectID == 1}">selected</c:if>>Prj311- Java Desktop</option>
                        <option value="Prj321- Java Web" <c:if test="${ques.subjectID == 2}">selected</c:if>>Prj321- Java Web</option>
                        </select><br/>
                        <input type="submit" name="action" value="Update"/><input type="submit" name="action" value="<c:if test="${ques.status}" var="changeState">Delete</c:if><c:if test="${not changeState}">Restore</c:if>" onclick="return confirm('Are you sure?')"/><br/>
                    <input type="hidden" name="searchType" value="${requestScope.searchType}"/>
                    <input type="hidden" name="rdSearchBySubject" value="${requestScope.rdSearchBySubject}"/>
                    <input type="hidden" name="txtSearchByName" value="${requestScope.txtSearchByName}"/>
                    <input type="hidden" name="rdSearchByStatus" value="${requestScope.rdSearchByStatus}"/>
                    <input type="hidden" name="updateQuestionID" value="${ques.questionID}"/>
                    **********************************<br/>
                </form>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.checkAction == 'success'}">
            <font color="green">
            ${requestScope.NOTE}
            </font>
        </c:if>
        <c:if test="${requestScope.checkAction == 'failed'}">
            <font color="red">
            ${requestScope.NOTE}
            </font>
        </c:if>
        <br/>
        <c:if test="${sessionScope.pageCount != 1 && sessionScope.pageCount != 0}">
            <c:forEach begin = "1" end="${sessionScope.pageCount}" var="counter">
                <a href="SearchChangePageController?page=${counter}&&searchType=${requestScope.searchType}&&rdSearchBySubject=${requestScope.rdSearchBySubject}&&txtSearchByName=${requestScope.txtSearchByName}&&rdSearchByStatus=${requestScope.rdSearchByStatus}">${counter}</a>
            </c:forEach>
        </c:if>
        <br/>
        <a href="BackAdminController">Back to admin page</a>
    </body>
</html>
