<%--
  Created by IntelliJ IDEA.
  User: PavelGrudina
  Date: 20.06.2017
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library</title>
    <link rel="stylesheet" href="/static/css/libraryStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<div class="table-container">
    <div>
        <h3>Poems</h3>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>№</th>
                <th>Name</th>
                <th>Lines</th>
                <th>Shortest word</th>
                <th>Longest word</th>
                <th>Average word length</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reportList}" var="report" varStatus="count">
            <tr>
                <th scope="row">${count.index + 1}</th>
                <td>
                    <c:url value="/report?id=${report.id}" var="reportStat"/>
                    <a href="${reportStat}">${report.name}</a>
                </td>
                <td class="alignment-center">${report.reportStatistics.linesCount}</td>
                <td class="alignment-center">${report.reportStatistics.shortestWord}</td>
                <td class="alignment-center">${report.reportStatistics.longestWord}</td>
                <td class="alignment-center">${report.reportStatistics.averageWordLength}</td>

            </tr>
            </c:forEach>
        </table>

        <%--<c:url value="/client/edit" var="createUrl"/>--%>
        <%--<a href="${createUrl}" class="btn btn-success" role="button">Create new Client</a><br>--%>
    </div>
</div>

</body>
</html>
