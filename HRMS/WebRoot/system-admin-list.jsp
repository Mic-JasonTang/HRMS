<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
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
					swal({
						  title: "Good Job!",
						  text: xhr.responseText,
						  timer: 1500
						});
				}
			}
		}
		//3.连接服务器
		xhr.open("GET", "/HRMS/servlet/UserListServlet?time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
</script>
<title>角色管理</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		系统管理 <span class="c-gray en">&gt;</span> 管理员列表 <a
			class="btn btn-success radius r mr-20"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
	<form action="">
	<div class="text-c"> 日期范围： <input type="text"
					onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
					id="datemin" class="input-text Wdate" style="width:120px;" name="startDate"> - <input
					type="text"
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})"
					id="datemax" class="input-text Wdate" style="width:120px;" name="endDate"> <input
					type="text" class="input-text" style="width:250px"
					placeholder="输入管理员名称" id="adminName" name="adminName">
				<button type="submit" class="btn btn-success" id="" name="">
					<i class="Hui-iconfont">&#xe665;</i> 查询
				</button>
	</div>
	</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a href="javascript:;"
				onclick="admin_add('添加管理员','system-admin-add.jsp','400','500')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加管理员</a></span> <span class="r">共有数据：<strong>${admins.size() }</strong> 条
			</span>
		</div>
		<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr>
				<th scope="col" colspan="9">管理员列表</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="90">ID</th>
				<th width="150">登录名</th>
				<th width="90">手机</th>
				<th width="150">邮箱</th>
				<th>角色</th>
				<th width="130">加入时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" begin="1" end="${admins.size()}">
			<tr class="text-c">
				<td><input type="checkbox" value="${admins.get(i-1).adminId}" name="adminCheck"></td>
				<td>${admins.get(i-1).adminId}</td>
				<td><u style="cursor:pointer" title="查看信息"class="text-primary"
					onclick="member_show('${admins.get(i-1).adminName}','employee-show.jsp','${admins.get(i-1).adminId}','360','400')">
					<span class="label label-primary radius">${admins.get(i-1).adminName}</span></u></td>
				<td>${admins.get(i-1).adminPhone}</td>
				<td>${admins.get(i-1).adminEmail}</td>
				<td class="td-status"><span class="label label-success radius">${admins.get(i-1).adminRoleName}</span></td>
				<td>${admins.get(i-1).adminStartTime}</td>
					<td class="td-manage"><a style="text-decoration:none"
						onClick="admin_stop(this,'10001')" href="javascript:;" title="停用"><i
							class="Hui-iconfont">&#xe631;</i></a> <a title="编辑"
						href="javascript:;"
						onclick="admin_edit('管理员编辑','system-admin-add.jsp','1','800','500')"
						class="ml-5" style="text-decoration:none"><i
							class="Hui-iconfont">&#xe6df;</i></a> <a title="删除"
						href="javascript:;" onclick="admin_del(this,'1')" class="ml-5"
						style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
			</c:forEach>
			<c:remove var="admins" scope="session"/>
		</tbody>
	</table>
	</div>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	url = url + "?userId=" + id;
	layer_show(title,url,w,h);
}

/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">超级管理员</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}
</script>
</body>
</html>