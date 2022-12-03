<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/8/19
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script>
        function validateLogin() {
            var data = $("form").serialize();
            $.post(
                "http://localhost:8080/login", data, function (data) {
                    if (data["Msg"] == 'success') {
                        location.href = "http://localhost:8080/user";
                    } else {
                        $("#msg").text("用户名密码错误，请检查后输入");
                    }
                }
            );
            return false;
        };

    </script>
</head>
<body onload="validateLogin()">

<form>
    <input type="text" name="username" id="exampleInputEmail1" placeholder="用户名">
    <input type="password" name="password" id="exampleInputPassword1" placeholder="密码">
    <button id="loginBtn" type="submit" onclick="return validateLogin()"> 登 录</button>
</form>
<button id="registerBtn" type="button" onclick="window.location.href='registerView.jsp'">注 册</button>
</body>

</html>
