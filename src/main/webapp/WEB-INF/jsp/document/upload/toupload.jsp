<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">

<title>
上传
</title>
    <link rel="stylesheet" href="/static/bootstrap-3.3.6-dist/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="/static/tree/css/zTreeStyle/zTreeStyle.css" type="text/css">

	<script type="text/javascript" src="/static/jquery-1.12.2.min.js"></script>
    <script type="text/javascript" src="/static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/static/ckeditor-4.5.8/ckeditor.js"></script>
    <script type="text/javascript" src="/static/ckeditor-4.5.8/samples/js/sample.js"></script>
    <script type="text/javascript" src="/static/tree/js/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="/static/tree/js/jquery.ztree.excheck.min.js"></script>
    <script type="text/javascript" src="/static/tree/js/jquery.ztree.exedit.min.js"></script>

	<style type="text/css">
    .ztree li span.button.add {
        margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle
    }
    ul.ztree {
        margin-top: 10px;border: 0 solid #617775;background: #f0f6e4;width:auto;height:auto;min-height:600px;overflow-x:auto;
    }
    body{
        margin:10px;
    }
    div.modal-backdrop {
    --    background-color:#0f0;
    }
	</style>




	<script type="text/javascript">
		function upload()
		{
			document.forms[0].submit();
		}

		$(function () {
          $('[data-toggle="popover"]').popover();
        });
        $(function () {
          $('[data-toggle="tooltip"]').tooltip()
        });
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

            var currentDir = "";
    		var log, className = "dark";
    		function onClick(event, treeId, treeNode, clickFlag) {
                //$("#pathDiv").text( treeNode.path+"/"+treeNode.name);
                $("#pathDiv").html( "当前选择的文件夹：<u><strong>"+treeNode.name+"</strong></u>");
                currentDir = treeNode.path;
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
            <div class="panel-heading">文件列表</div>
            <div class="panel-body" style="padding: 0">
	 	         <ul id="treeDemo" class="ztree" style="margin: 0"></ul>
            </div>
    	</div>
	    <div class="col-md-10 " >

        <div class="panel panel-default">
          <div id='pathDiv' class="panel-heading">请选择文件夹</div>
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

<br/>

<!--
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>

<!-- Modal -->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">Modal title</h4>
        </div>
        <div class="modal-body">
          <h4>Text in a modal</h4>
          <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula.</p>

          <h4>Popover in a modal</h4>
          <p>This <a href="#" role="button" class="btn btn-default popover-test" title="A Title" data-toggle="popover"  data-content="And here's some amazing content. It's very engaging. right?">button</a> should trigger a popover on click.</p>
            <button type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="Popover title" data-content="And here's some amazing content. It's very engaging. Right?">点我弹出/隐藏弹出框</button>
          <h4>Tooltips in a modal</h4>
          <p>
          <a href="#" class="tooltip-test" data-toggle="tooltip" title="Tooltip" >This link</a> and
          <a href="#" class="tooltip-test" title="Tooltip" data-placement="bottom" data-toggle="tooltip">that link</a> should have tooltips on hover.</p>

          <hr>

          <h4>Overflowing text to show scroll behavior</h4>
          <p>Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
          <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.</p>
          <p>Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla.</p>
          <p>Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
          <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.</p>
          <p>Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla.</p>
          <p>Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
          <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.</p>
          <p>Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>

      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

</body>
</html>
