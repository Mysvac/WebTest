<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jc" %>
<!DOCTYPE html>
<html>
<head>
    <title>留言板</title>
    <link href="./resources/css/home.css" rel="stylesheet">
</head>
<body>
<h1>留言板</h1>
<hr>
<div class="board">
    <jc:forEach var="msg" items="${messageList}">
        <div class="singleMessage">
            <a href="single-page?id=${msg.id}" target="_blank">${msg.title}</a>
            | 发送时间：${msg.time} - 发送用户：${msg.username}
        </div>
    </jc:forEach>
</div>
<hr>
<div class="submit">
    <form>
        <label for="title">标题</label><input type="text" id="title" name="title">
        <label for="content">内容</label><input type="text" id="content" name="content">
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
</body>
</html>