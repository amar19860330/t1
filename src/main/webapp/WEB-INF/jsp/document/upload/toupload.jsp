<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">

<title>
上传
</title>

    <script type="text/javascript" src="/static/ckeditor-4.5.8/ckeditor.js"></script>
    <script type="text/javascript" src="/static/ckeditor-4.5.8/samples/js/sample.js"></script>
	<script type="text/javascript" src="/static/jquery-1.12.2.min.js"></script>

	<script type="text/javascript">
		function upload()
		{
			document.forms[0].submit();
		}	
    </script>

</head>
<body>

<form  method="post" action="upload" enctype="multipart/form-data">
<input type="file" name="files" id="file_input" multiple webkitdirectory="" >
<br/>
<button onclick="upload()">上传</button>
</form>
</body>
</html>
