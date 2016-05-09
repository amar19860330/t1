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
        function preview()
        {
            var title = "";
            var content = CKEDITOR.instances.editor1.getData();
            var htmlPrefix = "<html><head><meta charset='utf-8'><meta http-equiv='Content-Type' content ='text/html; charset=UTF-8'><title>" + title + "</title></head><body>";
            var htmlSuffix = "</body></html>";
            var html = htmlPrefix + content + htmlSuffix;
            var f = document.getElementById('TheForm');
            f.info.value = html;
            window.open('', 'TheWindow');
            f.submit();
        }
    </script>

</head>
<body>
<!-- http://ckeditor.com/builder -->
<!-- http://docs.ckeditor.com/#!/guide/dev_file_upload -->
<h2>upload</h2>
<button onClick="preview()">预览</button>
<div id="editor1" style="visibility: hidden; display: none;">
					<h1>Hello world!</h1>
					<p>I'm an instance of <a href="http://ckeditor.com">CKEditor</a>.</p>
</div>

<script>
	initSample();


</script>

<form id="TheForm" method="post" action="preview" target="TheWindow">
            <input type="hidden" name="info" value="" />
</form>
</body>
</html>
