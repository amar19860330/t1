<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
<base href=" ${basePath}">  
<title></title>
<script src="static/jquery-1.12.2.min.js"></script>
<link href="static/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
</head>
<body>


<script type="text/javascript">

    $(document).ready(function(){
        var saveDataAry=[];
        var data1={"userName":"test","address":"gz"};
        var data2={"userName":"ququ","address":"gr"};
        saveDataAry.push(data1);
        saveDataAry.push(data2);
        alert( JSON.stringify(saveDataAry));
        $.ajax({
            type:"POST",
            url:"test/recJson/",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(saveDataAry),
            success:function(data){

            }
        });
    });
</script>
</form>
</body>
</html>
