<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<title>用户管理</title>
<script type="text/javascript">
window.onload = function() {
//请求statisticsServlet
		//1.获得XmlHttpRequest对象
		var xhr = getXmlHttpRequest();
//			alert(xhr);
		//2.注册事件监听器
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4) { //4表示准备好
				if(xhr.status == 200) { //200表示服务器请求正常
// 				alert("status:" + xhr.status);
// 					document.getElementById("tips").innerHTML = xhr.responseText;
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
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		系统管理 <span class="c-gray en">&gt;</span> 用户管理 <a
			class="btn btn-success radius r mr-20"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
	<form action="">
	<div class="text-c"> 
		日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
				id="datemin" class="input-text Wdate" style="width:120px;" name="startDate"> - <input
				type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})"
				id="datemax" class="input-text Wdate" style="width:120px;" name="endDate"> <input
				type="text" class="input-text" style="width:250px"
				placeholder="输入用户姓名" id="username" name="username">
			<button type="submit" class="btn btn-success radius" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 查询
			</button>
	</div>
	</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a href="javascript:;"
				onclick="member_add('添加用户','employee-add.jsp','','510')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加用户</a></span><span class="r">共有数据：<strong>${users.size()}</strong> 条
			</span>
		</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
		<tr>
			<th scope="col" colspan="9">员工列表</th>
		</tr>
			<tr class="text-c">
				<th width="30"><input type="checkbox" name="" value=""></th>
				<th width="180">ID</th>
				<th width="100">用户名</th>
				<th >权限</th>
				<th width="150">登记时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
				<tbody>
					<c:forEach var="i" begin="1" end="${users.size()}">
					<tr class="text-c">
						<td><input type="checkbox" value="${users.get(i-1).userId}" name="userCheck"></td>
						<td>${users.get(i-1).userId}</td>
						<td><u style="cursor:pointer" title="查看信息"class="text-primary"
							onclick="member_show('${users.get(i-1).userName}','employee-show.jsp','${users.get(i-1).userId}','360','400')">
							<span class="label label-primary radius">${users.get(i-1).userName}</span></u></td>
						<c:choose>
						<c:when test="${users.get(i-1).userRoleName == '超级管理员'}">
							<td class="td-status"><span
							class="label label-success radius">${users.get(i-1).userRoleName}</span></td>
						</c:when>
						<c:otherwise>
							<td class="td-status"><span
							class="label label-primary radius">${users.get(i-1).userRoleName}</span></td>
						</c:otherwise>
						</c:choose>	
						<td>${users.get(i-1).userStartTime}</td>
						<td class="td-manage"><a title="编辑" href="javascript:;"
							onclick="member_edit('编辑','employee-add.jsp','${users.get(i-1).userId}','','510')"
							class="ml-5" style="text-decoration:none"><i
								class="Hui-iconfont">&#xe6df;</i></a> <a
							style="text-decoration:none" class="ml-5"
							onClick="change_password('修改密码','change-password.jsp','${users.get(i-1).userId}','600','270')"
							href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a>
							<a title="删除" href="javascript:;" onclick="member_del(this,'1')"
							class="ml-5" style="text-decoration:none"><i
								class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr>
					</c:forEach>
					<c:remove var="users" scope="session"/>
				</tbody>
			</table>
	</div>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[5]}// 制定列不参与排序
		]
	});
	$('.table-sort tbody').on( 'click', 'tr', function () {
		if ( $(this).hasClass('selected') ) {
			$(this).removeClass('selected');
		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
});
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	url = url + "?userId=" + id;
	layer_show(title,url,w,h);
}

/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
	url = url + "?userId=" + id;
	layer_show(title,url,w,h);	
}
/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
</script> 
</body>
</html>