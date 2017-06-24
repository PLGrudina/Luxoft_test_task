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

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    <script type="text/javascript" src="/static/hint.js"></script>
</head>
<body>

<div class="table-container">
    <div>
        <h3>Poems</h3>

        <c:url value="/library" var="filter"/>
        <form action="${filter}" method="get">
            <p>Filter:
                <input type="submit" class="btn btn-success" name="filter" value="30">
            </p>
        </form>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>№</th>
                <th>Name</th>
                <th>Lines</th>
                <th>Shortest</th>
                <th>Longest</th>
                <th>Average</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reportList}" var="report" varStatus="count">
            <tr>
                <th scope="row">${count.index + 1}</th>
                <td>
                    <span data-tooltip="Lines count = ${report.reportStatistics.linesCount} ||
                                        Shortest word = '${report.reportStatistics.shortestWord}' ||
                                        Longest word = '${report.reportStatistics.longestWord}' ||
                                        Average word length = ${report.reportStatistics.averageWordLength}">${report.name}
                    </span>
                </td>
                <td>${report.reportStatistics.linesCount}</td>
                <td>${report.reportStatistics.shortestWord}</td>
                <td>${report.reportStatistics.longestWord}</td>
                <td>${report.reportStatistics.averageWordLength}</td>

            </tr>
            </c:forEach>
        </table>

        <c:url value="/edit" var="addText"/>
        <a href="${addText}" class="btn btn-success" role="button">+ Text</a><br>
    </div>
    <div id="tooltip"></div>
</div>

</body>
</html>
