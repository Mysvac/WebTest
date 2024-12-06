<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jc" %>
<!DOCTYPE html>
<html>
<head>
    <title>留言板</title>
    <link href="resources/css/home.css" rel="stylesheet">
</head>
<body>
<h1>留言板</h1>
<hr>
<div class="board">

        <div class="singleMessage">
            ${msg.title}
        </div>
        <div class="singleMessage">
            <pre>${msg.content}</pre>
        </div>
        <div class="singleMessage">
            ${msg.time} | ${msg.username}
        </div>
</div>
<hr>
</body>
</html>