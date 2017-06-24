<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PavelGrudina
  Date: 24.06.2017
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        .container{
            width: 80%;
        }
    </style>
</head>
<body>

<div class="container">
    <h3>Add text:</h3>

    <c:url value="/edit" var="addText"/>
    <form action="${addText}" method="post">

        <div class="form-group">
            <label for="name">Name:</label>
            <input id="name" type="text" class="form-control" name="name">
        </div>

        <div class="form-group">
            <label for="text">Text:</label>
            <textarea id="text" class="form-control" name="text"></textarea>
        </div>

        <input type="submit" class="btn btn-success" value="Submit">
    </form>
</div>

</body>
</html>
