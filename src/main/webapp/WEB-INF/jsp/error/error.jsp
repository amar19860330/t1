<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../common/tag.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/centerDiv.css" />
<title>错误页面</title>
</head>
<body class="commonFont">

<img alt="me" src="images/icon/me.jpg">
<font color="red">对不起,操作失败,系统出错啦,产生<font size="14"><b>${errorTitle}异常</b></font>,请将下列信息提供给管理员吧,感谢您的支持!</font>
<br/>

<textarea style='width:700px;height:160px;font-size:10'>
${errorInfo}
</textarea>

</body>
</html>