<%--
  Created by IntelliJ IDEA.
  User: amar
  Date: 16/5/28
  Time: 下午12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/static/jquery-1.12.2.min.js"></script>
    <link rel="stylesheet" href="/static/bootstrap-3.3.6-dist/css/bootstrap.min.css" type="text/css">
    <script type="text/javascript" src="/static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    <script>
        function login()
        {

        }

        function toRegist()
        {
        }

    </script>
</head>
<body>



<form>
    <input id="username" name="username" type="text" />
    <input id="pw" name="pw" type="text"/>
    <button id="submit" onclick="login()">登录</button>
    <button id="tologin" onclick="toRegist()">注册</button>
</form>
</body>
</html>