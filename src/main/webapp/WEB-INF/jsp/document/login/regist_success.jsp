<%--
  Created by IntelliJ IDEA.
  User: amar
  Date: 16/5/28
  Time: 下午12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/static/jquery-1.12.2.min.js"></script>
    <link rel="stylesheet" href="/static/bootstrap-3.3.6-dist/css/bootstrap.min.css" type="text/css">
    <script type="text/javascript" src="/static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

    <style>
        form div{
            margin-bottom: 5px !important;
        }

    </style>
    <script>
        

    </script>
</head>
<body>

注册成功，即将跳转<span id="countdown">5</span>秒

<script>
function gotoDashBoard()
{
    var count = $('#countdown').text() ;
    if( count > 0){
        $('#countdown').text(count-1) ;
    }
    else{
        clearInterval(countDownId);
        window.location.href="toLogin";
    }
}

var countDownId = setInterval(gotoDashBoard,1000);
</script>
</body>
</html>
