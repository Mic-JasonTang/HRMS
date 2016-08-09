<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<title>考勤管理</title>
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
		xhr.open("GET", "/HRMS/servlet/AttendanceListServlet?time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
</script>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 员工管理 <span class="c-gray en">&gt;</span> 考勤管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	<div class="text-c">
	<form action="">
			<span class="select-box inline"> <select name="branchName"
				class="select">
					<option value="0">全部部门</option>
					<option value="1">技术部</option>
					<option value="2">财务部</option>
					<option value="3">采购部</option>
					<option value="4">市场部</option>
					<option value="5">总经理</option>
			</select>
			</span> 日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
				id="datemin" class="input-text Wdate" style="width:120px;" name="startDate"> - <input
				type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})"
				id="datemax" class="input-text Wdate" style="width:120px;" name="endDate"> <input
				type="text" class="input-text" style="width:250px"
				placeholder="输入员工姓名" id="employeeName" name="employeeName">
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 查询
			</button>
		</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a></span> <span class="r">共有数据：<strong>${attendances.size() }</strong> 条
			</span>
		</div>
		<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="50">ID</th>
					<th width="80">姓名</th>
					<th width="80">所属部门</th>
					<th width="190">上班时间</th>
					<th width="190">下班时间</th>
					<th>备注</th>
                    <th width="80">状态</th>
					<th width="60">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" begin="1" end="${attendances.size() }">
				<tr class="text-c">
					<td><input type="checkbox" value="${attendances.get(i-1).attendanceId }" name="checkAttendance"></td>
					<td>${attendances.get(i-1).attendanceId }</td>
					<c:choose>
					<c:when test="${attendances.get(i-1).attendanceStatus == '正常'}">
                   <td><u style="cursor:pointer" title="查看信息" class="text-primary"
							onclick="member_show('${attendances.get(i-1).attendanceEmployeeName }','employee-show.jsp','${attendances.get(i-1).attendanceId }','360','400')">
							<span class="label label-secondary radius">${attendances.get(i-1).attendanceEmployeeName }</span></u></td>
					</c:when>
					<c:otherwise>
					<td><u style="cursor:pointer" title="查看信息" class="text-primary"
							onclick="member_show('${attendances.get(i-1).attendanceEmployeeName }','employee-show.jsp','${attendances.get(i-1).attendanceId }','360','400')">
							<span class="label label-waring radius">${attendances.get(i-1).attendanceEmployeeName }</span></u></td>
					</c:otherwise>
					</c:choose>
					<td>${attendances.get(i-1).attendanceEmployeeBranchName }</td>
					<td>${attendances.get(i-1).attendanceStart }</td>
					<td>${attendances.get(i-1).attendanceEnd }</td>
					<td>${attendances.get(i-1).attendanceDesc }</td>
					<c:choose>
					<c:when test="${attendances.get(i-1).attendanceStatus == '正常'}">
                    <td class="td-status"><span class="label label-success radius">${attendances.get(i-1).attendanceStatus }</span></td>
					</c:when>
					<c:otherwise>
					<td class="td-status"><span class="label label-danger radius">${attendances.get(i-1).attendanceStatus }</span></td>
					</c:otherwise>
					</c:choose>
					<td class="f-14"><a title="删除" href="javascript:;" onclick="user_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</c:forEach>
				<c:remove var="attendances" scope="session"/>
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
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[8]}// 制定列不参与排序
	]
});
/*用户-查看*/
function member_show(title,url,id,w,h){
	url = url + "?userId=" + id;
	layer_show(title,url,w,h);
}
</script>
</body>
</html>