﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<title>培训管理</title>
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
					swal({
						  title: "Good Job!",
						  text: xhr.responseText,
						  timer: 1500
						});
				}
			}
		}
		//3.连接服务器
		xhr.open("GET", "/HRMS/servlet/EducationListServlet?time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
</script>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		培训管理 <span class="c-gray en">&gt;</span> 培训课程管理 <a
			class="btn btn-success radius r mr-20"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
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
					onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}'})"
					id="logmin" class="input-text Wdate" style="width:120px;"
					name="startDate"> - <input type="text"
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d'})"
					id="logmax" class="input-text Wdate" style="width:120px;"
					name="endDate"> <input type="text" name="courseName"
					id="courseName" placeholder=" 输入培训课程" style="width:250px"
					class="input-text">
				<button name="" id="" class="btn btn-success" type="submit">
					<i class="Hui-iconfont">&#xe665;</i> 查询
				</button>
			</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a class="btn btn-primary radius"
				onclick="article_add('添加课程','education-course-add.jsp')"
				href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加课程</a></span> <span
				class="r">共有数据：<strong name="data_number">${educations.size() }</strong> 条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="80">ID</th>
						<th width="120">培训题目</th>
						<th>培训内容</th>
						<th width="80">所属部门</th>
						<th width="80">开课星期</th>
						<th width="120">开始日期</th>
						<th width="120">结束时间</th>
						<th width="60">培训状态</th>
						<th width="120">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="i" begin="1" end="${educations.size() }">
					<tr class="text-c">
						<td><input type="checkbox" value="${educations.get(i-1).educationId }" name="checkEducation"></td>
						<td>${educations.get(i-1).educationId }</td>
						<td class="text-c"><u style="cursor:pointer"
							class="text-primary"
							onClick="article_edit('查看','education-employee-show.jsp','${educations.get(i-1).educationId }')"
							title="查看培训名单"><span
							class="label label-default radius">${educations.get(i-1).educationCourseName }</span></u></td>
						<td>${educations.get(i-1).educationCourseContent }</td>
						<td>${educations.get(i-1).educationCourseBranchName }</td>
						<td>${educations.get(i-1).educationCourseDay }</td>
						<td>${educations.get(i-1).educationCourseStart }</td>
						<td>${educations.get(i-1).educationCourseEnd }</td>
						<c:choose>
						<c:when test="${educations.get(i-1).educationStatus == '正在进行中'}">
						<td class="td-status"><span
							class="label label-success radius">${educations.get(i-1).educationStatus }</span></td>
						</c:when>
						<c:otherwise>
						<td class="td-status"><span
							class="label label-warning radius">${educations.get(i-1).educationStatus }</span></td>
						</c:otherwise>
						</c:choose>
						<td class="f-14 td-manage"><a style="text-decoration:none"
							class="ml-5"
							onClick="article_edit('信息编辑','education-course-add.jsp','${educations.get(i-1).educationId }')"
							href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
							<a style="text-decoration:none" class="ml-5"
							onClick="article_del(this,'10001')" href="javascript:;"
							title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr>
					</c:forEach>
					<c:remove var="educations" scope="session"/>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="js/H-ui.js"></script>
	<script type="text/javascript" src="js/H-ui.admin.js"></script>
	<script type="text/javascript">
		$('.table-sort').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [9]
			} // 制定列不参与排序
			]
		});

		/*资讯-添加*/
		function article_add(title, url, w, h) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*资讯-编辑*/
		function article_edit(title, url, id, w, h) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*资讯-删除*/
		function article_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				$(obj).parents("tr").remove();
				layer.msg('已删除!', 1);
			});
		}
		/*资讯-审核*/
		function article_shenhe(obj, id) {
			layer
					.confirm(
							'审核文章？',
							{
								btn : [ '通过', '不通过' ],
								shade : false
							},
							function() {
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
								$(obj)
										.parents("tr")
										.find(".td-status")
										.html(
												'<span class="label label-success radius">已发布</span>');
								$(obj).remove();
								layer.msg('已发布', {
									icon : 6,
									time : 1000
								});
							},
							function() {
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a class="c-primary" onClick="article_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
								$(obj)
										.parents("tr")
										.find(".td-status")
										.html(
												'<span class="label label-danger radius">未通过</span>');
								$(obj).remove();
								layer.msg('未通过', {
									icon : 5,
									time : 1000
								});
							});
		}
		/*资讯-下架*/
		function article_stop(obj, id) {
			layer
					.confirm(
							'确认要下架吗？',
							function(index) {
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a style="text-decoration:none" onClick="article_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
								$(obj)
										.parents("tr")
										.find(".td-status")
										.html(
												'<span class="label label-defaunt radius">已下架</span>');
								$(obj).remove();
								layer.msg('已下架!', {
									icon : 5,
									time : 1000
								});
							});
		}

		/*资讯-发布*/
		function article_start(obj, id) {
			layer
					.confirm(
							'确认要发布吗？',
							function(index) {
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a style="text-decoration:none" onClick="article_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
								$(obj)
										.parents("tr")
										.find(".td-status")
										.html(
												'<span class="label label-success radius">已发布</span>');
								$(obj).remove();
								layer.msg('已发布!', {
									icon : 6,
									time : 1000
								});
							});
		}
		/*资讯-申请上线*/
		function article_shenqing(obj, id) {
			$(obj).parents("tr").find(".td-status").html(
					'<span class="label label-default radius">待审核</span>');
			$(obj).parents("tr").find(".td-manage").html("");
			layer.msg('已提交申请，耐心等待审核!', {
				icon : 1,
				time : 2000
			});
		}
	</script>
</body>
</html>