<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
</head>
<body>
  <div class="login-container">
    <form class="login-form" method="post" action="sign-in">
      <h2>登录</h2>

      <div class="input-group">
        <label for="username">用户：</label>
        <input type="text" id="username" name="username" required>
      </div>

      <div class="input-group">
        <label for="password">密码：</label>
        <input type="password" id="password" name="password" required>
      </div>

      <div class="register-info">
        <p>登入自动注册</p>
      </div>

      <div class="submit-group">
        <button type="submit">提交</button>
      </div>
    </form>
  </div>
</body>
</html>
