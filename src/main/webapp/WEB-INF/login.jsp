<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登入</title>
    <link href="resources/css/login.css" rel="stylesheet">
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
      <div>
      <label for="rememberMe">记住我</label>
      <input type="checkbox" id="rememberMe" name="rememberMe">
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
