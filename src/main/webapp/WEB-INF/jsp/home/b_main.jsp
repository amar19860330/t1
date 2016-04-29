<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="./static/jquery-easyui-1.4.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./static/jquery-easyui-1.4.5/themes/icon.css">
	<script type="text/javascript" src="/static/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="/static/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
</head>
<body>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region

		<span style="position:absolute;bottom:5px;right: 10px;">你好!退出！</span>
		


	</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">west content</div>
	
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'"></div>
</body>

</body>
</html>
