<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/8/19
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exit</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script>
        function exit() {
            $.get("http://localhost:8080/logout",function (){
                window.location.href='login.jsp'
            });
        };
    </script>
</head>
<body>
<button id="registerBtn" type="button" onclick="return exit()">退出登录</button>

</body>
</html>
