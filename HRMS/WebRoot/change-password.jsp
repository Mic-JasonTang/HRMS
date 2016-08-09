<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String userId = request.getParameter("userId");
%>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<title>修改密码</title>
<script type="text/javascript">
window.onload = function() {
//请求statisticsServlet
		//1.获得XmlHttpRequest对象
		var xhr = getXmlHttpRequest();
// 			alert(xhr);
		//2.注册事件监听器
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4) { //4表示准备好
				if(xhr.status == 200) { //200表示服务器请求正常
//  				alert("status:" + xhr.status);
// 				alert(xhr.responseText);
					document.getElementById("userName").innerHTML = xhr.responseText;
// 					swal({
// 						  title: "Good Job!",
// 						  text: xhr.responseText,
// 						  timer: 1500
// 						});
				}
			}
		}
		//3.连接服务器
		xhr.open("GET", "/HRMS/servlet/GetUserNameServlet?userId="+ <%=userId%> 
				+"&time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
</script>
</head>
<body>
<div class="pd-20">
	<form action="servlet/UpdatePasswordServlet" method="post" class="form form-horizontal" id="form-change-password">
		<div class="row cl">
			<label class="form-label col-4"><span class="c-red">*</span>账户：</label>
			<div class="formControls col-4" id="userName"></div>
			<div class="col-4"> </div>
			<input type="hidden" name="userId" value="<%=userId %>" />
		</div>
		<div class="row cl">
			<label class="form-label col-4"><span class="c-red">*</span>新密码：</label>
			<div class="formControls col-4">
				<input type="password" class="input-text" autocomplete="off" placeholder="不修改请留空" name="new-password" id="new-password" datatype="*6-18" ignore="ignore" >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-4"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-4">
				<input type="password" class="input-text" autocomplete="off" placeholder="不修改请留空" name="new-password2" id="new-password2" recheck="new-password" datatype="*6-18" errormsg="您两次输入的密码不一致！" ignore="ignore" >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<div class="col-8 col-offset-4">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script>
$(function(){
	$("#form-change-password").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
// 			var index = parent.layer.getFrameIndex(window.name);
// 			parent.layer.close(index);
		}
	});
	
});
</script>
</body>
</html>