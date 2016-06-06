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
    <script src="/static/bootstrap-3.3.6-dist/js/validator.min.js"></script>
    <script type="text/javascript">
        function login()
        {
            $('#form1').attr("action", "/user/login");
            $('#form1').submit();
        }

        function toRegist()
        {
            window.location.href="/user/toRegist";
        }

        function showError()
        {
            if('${error}'=='error'){
                $('#errorDiv').html( $('#errorDivCopy').html() );
                setTimeout(function(){$('[name="errorDetailDiv"]').alert('close'); },5000);
            }
        }
    </script>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12" style="height:200px"></div>
        <div class="col-md-4" style="padding: 0;">
        </div>
        <div class="col-md-4" style="padding: 0;">

<form id="form1" method="POST" class="form-horizontal" data-toggle="validator" role="form">

<div class="form-group has-feedback">
    <label for="loginname" class="col-sm-3 control-label">登录账号:</label>
    <div class="col-sm-4">
        <input id="loginname" name="loginname" type="text" class="form-control" required
        data-error="用户名不能为空"/>

        <div class="help-block with-errors" style="margin-bottom: 0">
        </div>
    </div>
</div>
<div class="form-group has-feedback">
    <label for="pw" class="col-sm-3 control-label">登录密码:</label>
    <div class="col-sm-4">
        <input id="pw" name="pw" type="password" class="form-control" required
         data-error="密码不能为空" />
        <div class="help-block with-errors" style="margin-bottom: 0">
        </div>
     </div>
</div>

<div class="form-group" id="errorDiv" style="margin-bottom: 0">
</div>

<div class="form-group">
    <label for="address" class="col-sm-3 control-label"></label>
    <div class="col-sm-4" style="text-align: right;">
        <button  onclick="login()" class="btn btn-primary" >立即登录</button>
        <button  onclick="toRegist()" class="btn btn-default">去注册</button>
    </div>
</div>
</form>

        </div>
        <div class="col-md-4" style="padding: 0;">
        </div>


<div class="form-group" id="errorDivCopy" style="display:none">
    <div class="col-sm-4 col-md-offset-3">
        <div name="errorDetailDiv" class="alert alert-danger alert-dismissible fade in"  role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
            <strong>用户名或密码有误</strong>
        </div>
     </div>
</div>

<script>
 showError();
</script>
</body>
</html>
