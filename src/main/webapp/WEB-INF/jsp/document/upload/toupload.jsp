<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">

<title>
上传
</title>
    <link rel="stylesheet" href="/static/bootstrap-3.3.6-dist/css/bootstrap.min.css" type="text/css">

    <script type="text/javascript" src="/static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/static/ckeditor-4.5.8/ckeditor.js"></script>
    <script type="text/javascript" src="/static/ckeditor-4.5.8/samples/js/sample.js"></script>
	<script type="text/javascript" src="/static/jquery-1.12.2.min.js"></script>


	<link rel="stylesheet" href="/static/tree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<style type="text/css">
    .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
    ul.ztree {margin-top: 10px;border: 0 solid #617775;background: #f0f6e4;width:auto;height:auto;min-height:600px;overflow-x:auto;}
    body{margin:10px;}
    /*th {
    background-color:#eee
    }*/ 
	</style>

	<script type="text/javascript" src="/static/tree/js/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="/static/tree/js/jquery.ztree.excheck.min.js"></script>
	<script type="text/javascript" src="/static/tree/js/jquery.ztree.exedit.min.js"></script>


	<script type="text/javascript">
		function upload()
		{
			document.forms[0].submit();
		}	
    </script>

    <SCRIPT type="text/javascript">
    		<!--
    		var setting = {
    			view: {
    				addHoverDom: addHoverDom,
    				removeHoverDom: removeHoverDom,
    				selectedMulti: false
    			},
    			edit: {
    				enable: true,
    				editNameSelectAll: true,
    				showRemoveBtn: showRemoveBtn,
    				showRenameBtn: showRenameBtn
    			},
    			data: {
    				simpleData: {
    					enable: true
    				}
    			},
    			callback: {
    				beforeDrag: beforeDrag,
    				// beforeEditName: beforeEditName,
    				// beforeRemove: beforeRemove,
    				// beforeRename: beforeRename,
    				onRemove: onRemove,
    				onRename: onRename,
    				onClick:onClick,
    			}
    		};

    		var log, className = "dark";
    		var zNodes = ${data};

    		var log, className = "dark";
    		function onClick(event, treeId, treeNode, clickFlag) {
                $("#pathDiv").text( treeNode.path+"\\"+treeNode.name);
    			//alert("[ "+getTime()+" onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag===1 ? "普通选中": (clickFlag===0 ? "<b>取消选中</b>" : "<b>追加选中</b>")) + ")");
    		}

    		function beforeDrag(treeId, treeNodes) {
    			return false;
    		}
    		function beforeEditName(treeId, treeNode) {
    			className = (className === "dark" ? "":"dark");
    			showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
    			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    			zTree.selectNode(treeNode);
    			return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
    		}
    		function beforeRemove(treeId, treeNode) {
    			className = (className === "dark" ? "":"dark");
    			showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
    			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    			zTree.selectNode(treeNode);
    			return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
    		}
    		function onRemove(e, treeId, treeNode) {
    			showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
    		}
    		function beforeRename(treeId, treeNode, newName, isCancel) {
    			className = (className === "dark" ? "":"dark");
    			showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
    			if (newName.length == 0) {
    				alert("节点名称不能为空.");
    				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    				setTimeout(function(){zTree.editName(treeNode)}, 10);
    				return false;
    			}
    			return true;
    		}
    		function onRename(e, treeId, treeNode, isCancel) {
    			showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
    		}
    		function showRemoveBtn(treeId, treeNode) {
    			return true;//!treeNode.isFirstNode;
    		}
    		function showRenameBtn(treeId, treeNode) {
    			return true;//!treeNode.isLastNode;
    		}
    		function showLog(str) {
    			// if (!log) log = $("#log");
    			// log.append("<li class='"+className+"'>"+str+"</li>");
    			// if(log.children("li").length > 8) {
    			// 	log.get(0).removeChild(log.children("li")[0]);
    			// }
    		}
    		function getTime() {
    			var now= new Date(),
    			h=now.getHours(),
    			m=now.getMinutes(),
    			s=now.getSeconds(),
    			ms=now.getMilliseconds();
    			return (h+":"+m+":"+s+ " " +ms);
    		}

    		var newCount = 1;
    		function addHoverDom(treeId, treeNode) {
    			// var sObj = $("#" + treeNode.tId + "_span");
    			// if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    			// var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
    			// 	+ "' title='add node' onfocus='this.blur();'></span>";
    			// sObj.after(addStr);
    			// var btn = $("#addBtn_"+treeNode.tId);
    			// if (btn) btn.bind("click", function(){
    			// 	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    			// 	zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
    			// 	return false;
    			// });
    			return false;
    		};
    		function removeHoverDom(treeId, treeNode) {
    			$("#addBtn_"+treeNode.tId).unbind().remove();
    		};
    		function selectAll() {
    			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    			zTree.setting.edit.editNameSelectAll = true;// $("#selectAll").attr("checked");
    		}

    		$(document).ready(function(){
    			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
    			$("#selectAll").bind("click", selectAll);
    		});

    		//-->
    	</SCRIPT>

</head>
<body>

<form  method="post" action="upload" enctype="multipart/form-data">
<input type="file" name="files" id="file_input" multiple webkitdirectory="" >
<br/>
<button onclick="upload()">上传</button>

<br/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 panel panel-default" style="padding: 0">
            <div class="panel-heading">Panel heading without title</div>
            <div class="panel-body" style="padding: 0">
	 	         <ul id="treeDemo" class="ztree" style="margin: 0"></ul>
            </div>
    	</div>
	    <div class="col-md-10 " >

        <div class="panel panel-default">
          <div id="pathDiv" class="panel-heading">Panel heading without title</div>
          <div class="panel-body" >
            <table class="table table-hover">
            <thead>
                <tr>
                    <th>1</th>
                    <th>2</th>
                    <th>3</th>
                    <th>4</th>
                </tr>
            </thead>
            <tbody >
                <tr class="active">
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td>d</td>
                </tr>
                <tr class="warning">
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td>d</td>
                </tr>
                <tr class="active">
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td>d</td>
                </tr>
                <tr class="warning">
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td>d</td>
                </tr>
                <tr class="active">
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td>d</td>
                </tr>
                <tr class="warning">
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td>d</td>
                </tr>
                <tr class="active">
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td>d</td>
                </tr>
                <tr class="warning">
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td>d</td>
                </tr>
                <tr class="active">
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td>d</td>
                </tr>

            </tbody>
          </table>
          </div>
        </div>


	      
	    </div>
	</div>
</div>
</form>

</div>
</form>
</body>
</html>
