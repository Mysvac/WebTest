<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jc" %>
<!DOCTYPE html>
<html>
<head>
    <title>留言板</title>
    <link href="resources/css/home.css" rel="stylesheet">
</head>
<body>
<h1><a href="main-page">留言板</a></h1>
<jc:if test="${!empty username}">
    <div class="login-div">你好：${username} <a class="login-a" href="sign-in"><button class="login-bt">注销</button></a></div>
</jc:if>
<jc:if test="${empty username}">
    <div class="login-div"><a class="login-a" href="sign-in"><button class="login-bt">登录</button></a></div>
</jc:if>

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