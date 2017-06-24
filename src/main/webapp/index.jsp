<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<meta charset="UTF-8">
<title>Library</title>
<link rel="stylesheet" type="text/css" href="static/css/indexStyle.css"/>

<body>
<div class="parent">
    <div class="block">
        <c:url value="/library" var="allReports"/>
        <a href="${allReports}" class="btn btn-link" role="button">Welcome to Shevchenko's library</a>
    </div>
</div>
</body>
</html>
