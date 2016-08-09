<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<title>部门管理</title>
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
// 				alert("异步请求成功，请查看结果");
				}
			}
		}
		//3.连接服务器
		xhr.open("GET", "/HRMS/servlet/BranchListServlet?time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
</script>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		部门管理 <span class="c-gray en">&gt;</span> 部门管理 <a
			class="btn btn-success radius r mr-20"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<div class="text-c">
		<form action="">
			日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}'})"
				id="logmin" class="input-text Wdate" style="width:120px;" name="startDate"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d'})"
				id="logmax" class="input-text Wdate" style="width:120px;" name="endDate"> <input
				type="text" name="branchName" id="branchName" placeholder=" 输入部门名称" style="width:250px"
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
				onclick="picture_add('添加部门','branch-add.jsp')" href="javascript:;"><i
					class="Hui-iconfont">&#xe600;</i> 添加部门</a></span> <span class="r">共有数据：
					<strong>${branchs.size() }</strong>条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="40"><input name="" type="checkbox" value=""></th>
						<th width="80">ID</th>
						<th width="100">部门名称</th>
						<th width="100">负责人</th>
						<th width="100">部门照片</th>
						<th>部门描述</th>
						<th width="100">部门人数</th>
						<th width="100">部门最大人数</th>
						<th width="150">成立时间</th>
						<th width="60">业绩</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" begin="1" end="${branchs.size() }">
					<tr class="text-c">
						<td><input name="checkBranch" type="checkbox" value="${branchs.get(i-1).branchId }"></td>
						<td>${branchs.get(i-1).branchId }</td>
						<td>${branchs.get(i-1).branchName }</td>
						<td><u style="cursor:pointer" title="查看信息"class="text-primary"
					onclick="member_show('${branchs.get(i-1).branchChargeName}','employee-show.jsp','${branchs.get(i-1).branchChargeId}','360','400')"><span
							class="label label-secondary radius">${branchs.get(i-1).branchChargeName }</span></u></td>
						<td class="text-l"><a class="maincolor" href="javascript:;"
							onClick="picture_edit('图库浏览','picture-show.jsp','10001')"
							title="查看图片">${branchs.get(i-1).branchPhotoName }</a></td>
						<td>${branchs.get(i-1).branchDescribe }</td>
						<td class="text-c"><span
							class="label label-primary radius">${branchs.get(i-1).branchPeople }</span></td>
						<td class="text-c"><span
							class="label label-secondary radius">${branchs.get(i-1).branchMaxPeople }</span></td>
						<td>${branchs.get(i-1).branchStartTime }</td>
						<td class="td-status"><span
							class="label label-success radius">${branchs.get(i-1).branchPerform }</span></td>
						<td class="td-manage"> <a
							style="text-decoration:none" class="ml-5"
							onClick="picture_edit('部门编辑','branch-add.jsp','10001')"
							href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
							<a style="text-decoration:none" class="ml-5"
							onClick="picture_del(this,'10001')" href="javascript:;"
							title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr>
					</c:forEach>
					<c:remove var="branchs" scope="session"/>
				</tbody>
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
				"aTargets" : [ 9 ]
			} // 制定列不参与排序
			]
		});
		/*用户-查看*/
		function member_show(title,url,id,w,h){
			url = url + "?userId=" + id;
			layer_show(title,url,w,h);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-查看*/
		function picture_show(title, url, id) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-审核*/
		function picture_shenhe(obj, id) {
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
												'<a class="c-primary" onClick="picture_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
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
												'<a class="c-primary" onClick="picture_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
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
		/*图片-下架*/
		function picture_stop(obj, id) {
			layer
					.confirm(
							'确认要下架吗？',
							function(index) {
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a style="text-decoration:none" onClick="picture_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
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

		/*图片-发布*/
		function picture_start(obj, id) {
			layer
					.confirm(
							'确认要发布吗？',
							function(index) {
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a style="text-decoration:none" onClick="picture_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
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
		/*图片-申请上线*/
		function picture_shenqing(obj, id) {
			$(obj).parents("tr").find(".td-status").html(
					'<span class="label label-default radius">待审核</span>');
			$(obj).parents("tr").find(".td-manage").html("");
			layer.msg('已提交申请，耐心等待审核!', {
				icon : 1,
				time : 2000
			});
		}
		/*图片-编辑*/
		function picture_edit(title, url, id) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-删除*/
		function picture_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				$(obj).parents("tr").remove();
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			});
		}
	</script>
</body>
</html>