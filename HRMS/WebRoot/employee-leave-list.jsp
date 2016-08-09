<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<title>员工离职</title>
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
		xhr.open("GET", "/HRMS/servlet/EmployeeListServlet?time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
</script>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 人事变动 <span class="c-gray en">&gt;</span> 员工离职 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
<form action="">
		<div class="text-c">
			日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
				id="datemin" class="input-text Wdate" style="width:120px;" name="startDate">
			- <input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})"
				id="datemax" class="input-text Wdate" style="width:120px;" name="endDate">
			<input type="text" class="input-text" style="width:250px"
				placeholder="输入员工姓名" id="employeeName" name="employeeName">
			<button type="submit" class="btn btn-success radius" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 查询
			</button>
			</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a href="javascript:;"
				onclick="member_add('添加员工','employee-add.jsp','','510')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
<!-- 					添加员工</a></span> <span class="r">共有数据：<strong>${employees.size() - count}</strong> 条 -->
					添加员工</a></span> <span class="r">共有数据：<strong>${employees.size()}</strong> 条
			</span>
		</div>
		<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="80">ID</th>
				<th width="100">姓名</th>
				<th width="40">性别</th>
				<th width="90">手机号</th>
				<th width="180">身份证号</th>
				<th width="">地址</th>
				<th width="130">加入时间</th>
				<th width="70">状态</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="i" begin="1" end="${employees.size() }">
		<c:choose>
		<c:when test="${employees.get(i-1).employeeStatus == '工作中'}">
			<tr class="text-c">
				<td><input type="checkbox" value="${employees.get(i-1).employeeId }" name="checkLeave"></td>
				<td>${employees.get(i-1).employeeId }</td>
				<td><u style="cursor:pointer" title="查看信息" class="text-primary"
					onclick="member_show('${employees.get(i-1).employeeName }','employee-show.jsp','${employees.get(i-1).employeeId }','360','400')">
					<span class="label label-secondary radius">${employees.get(i-1).employeeName }</span></u></td>
				<td>${employees.get(i-1).employeeSex }</td>
				<td>${employees.get(i-1).employeePhone }</td>
				<td>${employees.get(i-1).employeeIdCard }</td>
				<td class="text-l">${employees.get(i-1).employeeAddress }</td>
				<td>${employees.get(i-1).employeeEnterTime }</td>
				<td class="td-status"><span class="label label-success radius">
				${employees.get(i-1).employeeStatus }</span></td>
						<td class="td-manage"><a style="text-decoration:none"
							onClick="member_stop(this,'10001')" href="javascript:;"
							title="离职"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑"
							href="javascript:;"
							onclick="member_edit('离职信息编辑','employee-add.jsp','4','','510')"
							class="ml-5" style="text-decoration:none"><i
								class="Hui-iconfont">&#xe6df;</i></a> 
							<a title="删除" href="javascript:;" onclick="member_del(this,'1')"
							class="ml-5" style="text-decoration:none"><i
								class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr>
				</c:when>
					
				<c:otherwise>
<!-- 					<c:set var="count" scope="session" value="${count + 1}"></c:set> -->
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:remove var="employees" scope="session"/>
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
		  {"orderable":false,"aTargets":[9]}// 制定列不参与排序
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
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要离职吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="恢复"><i class="Hui-iconfont">&#xe6e1;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-primary radius">已离职</span>');
		$(obj).remove();
		layer.msg('已离职!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要恢复吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="离职"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">工作中</span>');
		$(obj).remove();
		layer.msg('已恢复!',{icon: 6,time:1000});
	});
}
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
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