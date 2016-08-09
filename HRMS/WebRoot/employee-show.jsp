<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String userId = request.getParameter("userId");
%>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<title>用户查看</title>
<script type="text/javascript">
var xhr;
window.onload = function() {
		
		//1.获得XmlHttpRequest对象
		xhr = getXmlHttpRequest();
// 	alert(xhr);
		//2.注册事件监听器
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4) { //4表示准备好
				if(xhr.status == 200) { //200表示服务器请求正常
//  					alert("status:" + xhr.status);
//   					alert("异步请求成功，请查看结果");
					var data = new Array();
					data = xhr.responseText.split(",");
// 					alert(data.toString());
					document.getElementById("userName").innerHTML      =  data[1];
					document.getElementById("userAge").innerHTML       =  data[2];
					document.getElementById("userSex").innerHTML       =  data[3];
					document.getElementById("userPhone").innerHTML     =  data[4];
					document.getElementById("userEmail").innerHTML     =  data[5];
					document.getElementById("userAddr").innerHTML      =  data[6];
					document.getElementById("userStatus").innerHTML    =  data[7];
					document.getElementById("userStartTime").innerHTML =  data[8];
				}
			}
		}
		//3.连接服务器
		xhr.open("GET", "/HRMS/servlet/EmployeeShowServlet?userId=" + <%=userId%> + "&time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
//4.发送请求(post)
</script>
</head>
<body>
<div class="cl pd-20" style=" background-color:#5bacb6">
  <img class="avatar size-XL l" src="images/user.png">
  <dl style="margin-left:80px; color:#fff">
    <dt><span class="f-18" id="userName"></span> <span class="pl-10 f-12" id="userAge">年龄：</span></dt>
    <dd class="pt-10 f-12" style="margin-left:0">这家伙很懒，什么也没有留下</dd>
  </dl>
</div>
<div class="pd-20">
  <table class="table">
    <tbody>
      <tr>
        <th class="text-r" width="80">性别：</th>
        <td id="userSex"></td>
      </tr>
      <tr>
        <th class="text-r">手机：</th>
        <td id="userPhone"></td>
      </tr>
      <tr>
        <th class="text-r">邮箱：</th>
        <td id="userEmail"></td>
      </tr>
      <tr>
        <th class="text-r">地址：</th>
        <td id="userAddr"></td>
      </tr>
      <tr>
        <th class="text-r">工作状态：</th>
        <td id="userStatus"></td>
      </tr>
      <tr>
        <th class="text-r">注册时间：</th>
        <td id="userStartTime"></td>
      </tr>
    </tbody>
  </table>
</div>
<script type="text/javascript" src="js/jquery.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
</body>
</html>