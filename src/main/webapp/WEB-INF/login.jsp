<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登入</title>
    <link href="resources/css/login.css" rel="stylesheet">
</head>
<body>


  <div class="login-container">

    <form class="login-form">
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
        <button id="submit" type="submit">提交</button>
      </div>
    </form>

  </div>

<script>

  function encrypt(str, key) {
    let encrypted = '';
    for (let i = 0; i < str.length; i++) {
      encrypted += String.fromCharCode(str.charCodeAt(i) ^ key); // XOR 运算
    }
    return encrypted;
  }

  document.getElementById("submit").addEventListener("click",(event)=>{
    event.preventDefault(); // 防止表单刷新页面
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    username = encrypt(username,10086)
    password = encrypt(password,10086)

    const formData = new FormData();
    formData.append("username", username);
    formData.append("password", password);



    fetch('sign-in', {
      method: 'POST',
      body: formData
    })
            .then(response => {
              console.log("111111111")
              // 检查返回状态
              if (response.ok) {
                // 将浏览器重定向到返回的页面
                window.location.href = response.url;  // 使用 response.url 获取重定向目标
              } else {
                alert("提交失败，请重试");
              }
            })
            .catch(error => {
              console.error('提交失败:', error);
              alert('提交失败，请重试');
            });

  });
</script>

</body>
</html>
