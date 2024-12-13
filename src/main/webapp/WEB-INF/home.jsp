<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
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
    <jc:forEach var="msg" items="${messageList}">
        <div class="singleMessage">
            <a href="single-page?id=${msg.id}" target="_blank">${msg.title}</a>
            | 发送时间：${msg.time} | 发送用户：${msg.username} -
            <jc:if test="${fn:contains(usernameSet, msg.username)}">
                在线
            </jc:if>
            <jc:if test="${!fn:contains(usernameSet, msg.username)}">
                离线
            </jc:if>
        </div>
    </jc:forEach>
</div>
<hr>
<jc:if test="${!empty username}">
    <div class="submit">
        <form>
            <label for="title">标题</label><input type="text" id="title" name="title">
            <label for="content">内容</label>
            <textarea id="content" name="content" placeholder="请输入内容"></textarea>
            <button id="submit">提交</button>
        </form>
    </div>
    <script>
        document.getElementById("submit").addEventListener("click",(event)=>{
            event.preventDefault(); // 防止表单刷新页面
            const title = document.getElementById('title').value;
            const content = document.getElementById('content').value;
            const formData = new FormData();
            formData.append("title", title);
            formData.append("content", content);



            fetch('main-page', {
                method: 'POST',
                body: formData
            })
                .then(response => {
                    alert("已提交")
                    window.location.reload();
                })  // 假设服务器返回 JSON 响应

        });
    </script>

</jc:if>
</body>
</html>