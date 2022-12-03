<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/8/19
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script>
        function Register() {
            var data = $("form").serialize();
            $.post(
                "http://localhost:8080/register", data, function (data) {
                    if (data == 'success') {
                        alert("注册成功");
                        location.href = "http://localhost:8080/login.jsp";
                    } else {
                        alert("注册失败");
                        $("#msg").text("注册失败");
                    }
                }
            );
            return false;
        };
    </script>
</head>
<body>
<form>
    <input type="text" name="username" id="exampleInputEmail1" placeholder="用户名">
    <input type="password" name="password" id="exampleInputPassword1" placeholder="密码">
    <button id="registerBtn" type="submit" onclick="return Register()"> 注 册</button>
</form>
<button id="back" type="button" onclick="window.location.href='login.jsp'">返 回</button>
</body>
</html>
