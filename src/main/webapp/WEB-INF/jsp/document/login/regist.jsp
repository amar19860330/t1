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
    <script src="/static/bootstrap-3.3.6-dist/js/validator.min.js"></script>

    <style>
        form div{
            margin-bottom: 5px !important;
        }

    </style>
    <script>
        
        function regist() {
            $('#registForm').submit();
        }

    </script>
</head>
<body>


<div class="container-fluid">
    <div class="row">
        <div class="col-md-4" style="padding: 0">
        </div>
        <div class="col-md-4" style="padding: 0;">
            <form id='registForm' class="form-horizontal" data-toggle="validator" role="form">
                 <div class="form-group">
                    <label for="loginname" class="col-sm-4 control-label"></label>
                    <div class="col-sm-8">
                        <h2>请填写注册信息</h2>
                    </div>
                </div>

                <div class="form-group has-feedback">
                    <label for="loginname" class="col-sm-3 control-label">登录名:</label>
                    <div class="col-sm-9" >
                        <input type="text" class="form-control" id="loginname" placeholder="请输入登录名称"
                               pattern="^[a-zA-Z0-9]{5,10}$"
                               data-error="请输入5-10位数字或英文字母" required>
                        <span class="glyphicon form-control-feedback" aria-hidden="true">
                        </span>
                        <div class="help-block with-errors" style="margin-bottom: 0"></div>
                    </div>


                </div>
                <div class="form-group has-feedback" >
                    <label for="realname" class="col-sm-3 control-label">真实姓名:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="realname" placeholder="输入姓名"
                            pattern="^[a-zA-Z0-9]{5,10}$" data-error="请输入5-10位数字或英文字母" required>
                        <span class="glyphicon form-control-feedback" aria-hidden="true">
                        </span>
                        <div class="help-block with-errors" style="margin-bottom: 0">
                        </div>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="email" class="col-sm-3 control-label">邮箱:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="email" placeholder="请输入邮箱地址">
                        <span class="glyphicon form-control-feedback" aria-hidden="true">
                        </span>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="phone" class="col-sm-3 control-label">手机号码:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="phone" placeholder="请输入手机号码"
                            pattern="^[0-9]{3,20}$" data-error="请输入纯数字的手机号码"
                        >
                        <span class="glyphicon form-control-feedback" aria-hidden="true">
                        </span>
                        <div class="help-block with-errors" style="margin-bottom: 0">
                        </div>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="pw" class="col-sm-3 control-label">密码:</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="pw" data-minlength="6" required
                               data-error="请输入6-20位数字或英文字母密码" >
                        <span class="glyphicon form-control-feedback" aria-hidden="true">
                        </span>
                        <div class="help-block with-errors" style="margin-bottom: 0">
                        </div>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="pw2" class="col-sm-3 control-label">确认密码:</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="pw2" data-match="#pw"
                               data-error="密码不匹配"
                               data-match-error="密码不匹配" required
                        >
                        <span class="glyphicon form-control-feedback" aria-hidden="true">
                        </span>
                        <div class="help-block with-errors" style="margin-bottom: 0">
                        </div>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="address" class="col-sm-3 control-label">地址:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="address" placeholder="请输入联系地址">
                        <span class="glyphicon form-control-feedback" aria-hidden="true">
                        </span>
                    </div>

                </div>

                <div class="form-group" style="text-align: right;">
                    <label for="address" class="col-sm-3 control-label"></label>
                    <div class="col-sm-9">
                        <button id="submit" onclick="login()" class="btn btn-primary" type="button">
                            去登录
                        </button>
                        <button id="tologin" onclick="regist()" class="btn btn-primary" type="button">
                            注册
                        </button>
                    </div>
                </div>

            </form>
        </div>
        <div class="col-md-4" style="padding: 0">
        </div>
    </div>
</div>





</body>
</html>
