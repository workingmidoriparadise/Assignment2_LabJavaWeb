<%-- 
    Document   : viewHistory
    Created on : Feb 4, 2021, 10:52:54 AM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <h1>HISTORY</h1>
        <form action="SearchHistoryController" method="POST">
            Search by Category: 
            <select name="searchHistoryCategory">
                <option>True</option>
                <option>False</option>
            </select>
            <br/>
            Search by Subject: 
            <select name="searchHistorySubject">
                <option>Prj311- Java Desktop</option>
                <option>Prj321- Java Web</option>
            </select>
            <br/>
            Choose Type: 
            <select name="searchHistoryType">
                <option>Search by Category</option>
                <option>Search by Subject</option>
            </select><br/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <br/>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Submitted Time</th>
                    <th>Score</th>
                    <th>Status</th>
                    <th>Subject</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty sessionScope.listSearchHistory}">
                    <c:forEach items="${sessionScope.listHistory}" var="dto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.submittedTime}</td>
                            <td>${dto.score}</td>
                            <td><c:if test="${dto.status == 'true'}">Passed</c:if><c:if test="${dto.status == 'false'}">Failed</c:if></td>
                            <th><c:if test="${dto.subjectID == 1}">Prj311- Java Desktop</c:if><c:if test="${dto.subjectID == 2}">Prj321- Java Web</c:if></th>
                            <td><a href="HistoryDetailController?historyID=${dto.historyID}">Detail</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${not empty sessionScope.listSearchHistory}">
                    <c:forEach items="${sessionScope.listSearchHistory}" var="dto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.submittedTime}</td>
                            <td>${dto.score}</td>
                            <td><c:if test="${dto.status == 'true'}">Passed</c:if><c:if test="${dto.status == 'false'}">Failed</c:if></td>
                            <th><c:if test="${dto.subjectID == 1}">Prj311- Java Desktop</c:if><c:if test="${dto.subjectID == 2}">Prj321- Java Web</c:if></th>
                            <td><a href="HistoryDetailController?historyID=${dto.historyID}">Detail</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table><br/>
        <c:forEach begin="1" end="${sessionScope.searchPageCount}" var="counter">
            <a href="HistoryChangePageController?page=${counter}">${counter}</a>
        </c:forEach><br/><br/>
        <a href="BackMemberController">Back to member page</a>
    </body>
</html>
