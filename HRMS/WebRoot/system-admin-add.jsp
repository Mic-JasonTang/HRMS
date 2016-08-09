<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<title>添加管理员</title>
<script type="text/javascript">
window.onload = function() {
		//1.获得XmlHttpRequest对象
		var xhr = getXmlHttpRequest();
//			alert(xhr);
		//2.注册事件监听器
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4) { //4表示准备好
				if(xhr.status == 200) { //200表示服务器请求正常
// 				alert("status:" + xhr.status);
// 				alert(xhr.responseText);
					var datas = new Array();
// 					var data = new Array(); //保存datas中的一个数组
					var text = xhr.responseText
					var size =text.substring(0, 1); //第一个传输的是数组大小
// 					var branchNames = new Array();
					var employeeNames = new Array();
					text = text.substring(1, text.length);
					datas = text.split(",");
					for(var i=0; i<datas.length; i++) {
// 						data = datas[i].split(":");
// 						branchNames[i] = data[0];
						employeeNames[i] = datas[i];
					}
// 					alert(branchNames);
// 					alert(employeeNames);
					for(var i=0; i<employeeNames.length; i++) {
// 						document.getElementById("branch").innerHTML += "<option value="+branchNames[i]+">" + branchNames[i] + "</option>";
						document.getElementById("employee").innerHTML += "<option value="+employeeNames[i]+">" + employeeNames[i] + "</option>";
					}
				}
			}
		}
		//3.连接服务器
		xhr.open("GET", "/HRMS/servlet/AddAdminServlet?time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
</script>
</head>
<body>
<div class="pd-20">
	<form action="servlet/InsertAdminServlet" method="post" class="form form-horizontal" id="form-admin-add">
<!-- 		<div class="row cl"> -->
<!-- 			<label class="form-label col-3">选择部门：</label> -->
<!-- 			<div class="formControls col-5"> <span class="select-box" style="width:150px;"> -->
<!-- 				<select class="select" id="branch" name="branchName" size="1"> -->
<!-- 					<option value="0">选择部门</option> -->
<!-- 				</select> -->
<!-- 				</span> </div> -->
<!-- 		</div> -->
		<div class="row cl">
		</div>
		<div class="row cl">
			<label class="form-label col-3">选择人员：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" id="employee" name="adminName" size="1">
					<option value="0">选择人员</option>
				</select>
				</span> </div>
		</div>
		<div class="row cl">
		</div>
		<div class="row cl">
			<label class="form-label col-3">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" name="adminRole" size="1">
					<option value="超级管理员">超级管理员</option>
				</select>
				</span> 
			</div>
		</div>
		<div class="row cl">
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<button class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">&nbsp;&nbsp;保存&nbsp;&nbsp; </button>
				<button class="btn btn-primary radius" onclick="layer_close();"type="button" value="&nbsp;&nbsp;关闭&nbsp;&nbsp;">&nbsp;&nbsp;关闭&nbsp;&nbsp; </button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
// 	$("#form-admin-add").Validform({
// 		tiptype:2,
// 		callback:function(form){
// 			form[0].submit();
// 			var index = parent.layer.getFrameIndex(window.name);
// 			parent.$('.btn-refresh').click();
// 			parent.layer.close(index);
// 		}
// 	});
});
</script>
</body>
</html>