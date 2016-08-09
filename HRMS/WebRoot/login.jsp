<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!-- 美化对话框 -->
<script src="js/sweet-alert.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/sweet-alert.css">
<!-- AJAX支持 -->
<script type="text/javascript" src="js/getHttpRequest.js"></script>
<title>后台登录 - 人事管理系统</title>
<script type="text/javascript">
window.onload = function() {
	SetFocus();
//验证登陆
	document.getElementById("login").onclick = function() {
		var username = document.getElementById("username").value;
		if (document.myform.username.value == '') {
			sweetAlert("Sorry...", "用户名不能为空!", "error");
			formName.username.focus();
			return false;
		}
		var password = document.getElementById("password").value;
		if (document.myform.password.value == '') {
			sweetAlert("Sorry...", "密码不能为空!", "error");
			formName.password.focus();
			return false;
		}
		var checkCode = document.getElementById("checkCode").value;
		if (document.myform.checkCode.value == '') {
			sweetAlert("Sorry...", "验证码不能为空!", "error");
			formName.checkCode.focus();
			return false;
		}
		var msg = document.getElementById("msg").value;
		var xhr = getXmlHttpRequest();
//alert(xhr);
		//Post方式提交
		xhr.open("Post", "/HRMS/servlet/LoginServlet?time="
				+ new Date().getTime());
		//服务器端不知道请求类型，告知服务器端MIME类型
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
//alert(xhr.status);
		xhr.send("username=" + username + "&password=" + password+ "&checkCode=" + checkCode);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
//alert(xhr.status);
				if (xhr.status == 200) {
//alert(xhr.status);
					var responseText = xhr.responseText;
					msg = responseText;
					if(msg == "success") {   //获取success信息
					//弹出一个美化的对话框
					swal("Good job!", responseText, "success");
						top.location.href="index.jsp";
					} else {
					document.getElementById("msg").innerHTML = msg;
					}
				}
			}
		}
	}
};
function SetFocus() {
	if (document.myform.username.value == '') {
		document.myform.username.focus();
	} else {
		document.myform.username.select();
	}
}

function reload(img) {
	img.src='<%=request.getContextPath()%>/servlet/ImageServlet?n='+Math.random();
}
</script>
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
  
<!--   	移出记录 -->
  	<c:remove var="username" scope="session"/>
  	<c:remove var="isAdmin" scope="session"/>
  	
    <form name="myform" class="form form-horizontal">
     <div class="row cl">
        <label class="form-label col-3" id="msg"></label>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="username" name="username" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" type="text" id="checkCode" name="checkCode" placeholder="验证码" style="width:150px;">
          <img alt="验证码" id="imgcode" name="imgcode" src="<%=request.getContextPath()%>/servlet/ImageServlet"> <a id="kanbuq" onclick="reload(imgcode)" >看不清，换一张</a> </div>
      </div>
<!--       <div class="row"> -->
<!--         <div class="formControls col-8 col-offset-3"> -->
<!--           <label for="online"> -->
<!--             <input type="checkbox" name="online" id="online" value=""> -->
<!--             使我保持登录状态</label> -->
<!--         </div> -->
<!--       </div> -->
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input id="login" name="login" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 计科1303班 唐洋</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));

</script>
</body>
</html>