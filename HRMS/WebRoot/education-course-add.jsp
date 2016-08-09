<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
	type="text/css" />
<title>增加培训</title>
<script type="text/javascript">

var speakerIds = new Array();
var speakerNames = new Array();
var speakerBranchNames = new Array();


var employeeIds = new Array();
var employeeNames = new Array();
var employeeBranchNames = new Array();

var group; //选择分组
var selobj; //courseSpeaker菜单

window.onload = function() {
// 	alert("asdadasd");
		//1.获得XmlHttpRequest对象
		var xhr = getXmlHttpRequest();
// 			alert(xhr);
		//2.注册事件监听器
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4) { //4表示准备好
				if(xhr.status == 200) { //200表示服务器请求正常
// 					alert("status:" + xhr.status);
					var datas = xhr.responseText;
					var speakersData = datas.split("#")[0];
					var employeesData = datas.split("#")[1];
					
// 					console.log("speakersData:" + speakersData);
// 					console.log("employeesData:" + employeesData);
					
					var speakers = new Array();
					var employees = new Array();
					speakers = speakersData.split(";");
					
// 					console.log("speakers" + speakers);
					
					
					var speakerHTML = "";
					var branchNameHTML = "";
					//把id和名字分别装入数组
					for(var i=0; i<speakers.length; i++) {
						speakerIds[i] = speakers[i].split(",")[0];
						speakerNames[i] = speakers[i].split(",")[1];
						speakerBranchNames[i] = speakers[i].split(",")[2];
						branchNameHTML += "<option value='" + speakerBranchNames[i] + "'>"+ speakerBranchNames[i] +"</option>";
					}
					speakerHTML += "<option value='"+ speakerIds[0] +"'>"+ speakerNames[0] +"</option>";
					document.getElementById("courseBranch").innerHTML = branchNameHTML;
					document.getElementById("courseSpeaker").innerHTML = speakerHTML;
// 					console.log("speakerIds" + speakerIds);
// 					console.log("speakerNames" + speakerNames);
// 					console.log("speakerBranchNames" + speakerBranchNames);
					
				//下面是员工信息
					
					
					employees = employeesData.split(";");
					
// 					console.log("employees" + employees);
					
// 					var educationBranchNameHTML = "<dl class='permission-list'> "; 
					
// 					for(var i=0; i<speakerBranchNames.length; i++) {
// 						educationBranchNameHTML +=
// 							"<dt>" +
// 							"<label>" + 
// 							"<input type='checkbox' value='' name='project-0' id='technology-1'> " +
// 							speakerBranchNames[i] + "</label>" + 
// 							"</dt>';" +
// 							"<dd >" + 
// 							"<dl class='cl permission-list2'>";
							
// 					}
					var educationEmployeeNameHTML = "";
					
					//培训人数名单
					for(var i=0; i<employees.length; i++) {
						employeeIds[i] = employees[i].split(",")[0];
						employeeNames[i] = employees[i].split(",")[1];
						
						educationEmployeeNameHTML += "<label class=''>"+
						"<input type='checkbox' value='"+ employeeIds[i] +"' name='educationEmployeeName' id='educationEmployeeName'> " +
						employeeNames[i] + "</label> ";
						
						employeeBranchNames[i] = employees[i].split(",")[2];
					}
// 					educationBranchNameHTML += educationEmployeeNameHTML + 
// 					"</dl>" + 
// 					"</dd>" + 
// 					"</dl>" ;
// 					document.getElementById("educationBranchName").innerHTML = educationBranchNameHTML;
					document.getElementById("educationEmployeeName").innerHTML = educationEmployeeNameHTML;
// 					console.log(educationBranchNameHTML);
// 					console.log("employeeIds" + employeeIds);
// 					console.log("employeeNames" + employeeNames);
// 					console.log("employeeBranchNames" + employeeBranchNames);
					
					var len = document.getElementById("courseBranch").options.length;
					//取得部门的选项数目
					group = new Array(len);
					//根据这个数目创建数组
					for (var i = 0; i < len; i++) {
						//上一个数组的每一个元素也是数组
						group[i] = new Array();
					}
						
					selobj = document.getElementById("courseSpeaker"); //设定需要动态改变的对象是第二下拉菜单
					//接下来创建选项，为第二下拉菜单定义内容
					for(var i=0; i<len; i++) {
						group[i][0] = new Option(employeeNames[i], speakerIds[i]); 
					}
				}
			}
		}
		//3.连接服务器
		xhr.open("GET", "/HRMS/servlet/GetBranchNameAndUserName?time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
	function set_list(x) //x表示第一菜单被选定的内容
	{
		
			//先清除第二菜单的选项
		document.getElementById("courseSpeaker").innerHTML = "";
	
		for (var i = 0; i < group[x].length; i++) {
			//根据第一菜单的选定序号，丛控件数组中提取菜单选项放进第二菜单
			selobj.options[i] = group[x][i];
			//这里也可以创建新的对象，例如：selobj.options[i]=new Option(group[x][i].text,group[x][i].value) 
		}
		selobj.options[0].selected = true;
		//选定第0号选项
	}
</script>
</head>
<body>
	<div class="pd-20">
		<form action="servlet/AddEducationServlet" method="post" class="form form-horizontal"
			id="form_course_add">
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>课程名称：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="输入名称"
						datatype="*" nullmsg="课程名不能为空！" id="courseName" name="courseName">
				</div>

			</div>

			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>课程内容：</label>
				<div class="formControls col-10">
					<textarea name="courseContent" cols="" rows="" class="textarea"
						placeholder="培训内容" datatype="*10-100" dragonfly="true"
						nullmsg="内容不能为空！" onKeyUp="textarealength(this,200)"></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/200
					</p>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>日期范围：</label>
				<div class="formControls col-2">
				<input type="text" datatype="*" nullmsg="开始时间不能为空！"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}'})"
				id="logmin" class="input-text Wdate" style="width:120px;" name="startDate"> -
				<input type="text" datatype="*" nullmsg="结束时间不能为空！"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}'})"
				id="logmax" class="input-text Wdate" style="width:120px;" name="endDate">
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>上课时间：</label>
				<div class="formControls col-2">
					<span class="select-box"> 
					<select name="startTime" datatype="*" nullmsg="上课时间不能为空！"class="select">
							<option value="0">选择周几上课</option>
							<option value="周一">周一</option>
							<option value="周二">周二</option>
							<option value="周三">周三</option>
							<option value="周四">周四</option>
							<option value="周五">周五</option>
							<option value="周六">周六</option>
							<option value="周日">周日</option>
					</select>
					</span>
				</div>
				<div class="formControls col-2">
					<span class="select-box">
					 <select name="endTime" datatype="*" nullmsg="结课时间不能为空！"class="select">
							<option value="0">选择周几结课</option>
							<option value="周一">周一</option>
							<option value="周二">周二</option>
							<option value="周三">周三</option>
							<option value="周四">周四</option>
							<option value="周五">周五</option>
							<option value="周六">周六</option>
							<option value="周日">周日</option>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>所属部门：</label>
				<div class="formControls col-2">
					<span class="select-box"> <select id="courseBranch" name="courseBranch"
						datatype="*" nullmsg="部门不能为空！"class="select" onchange="set_list(this.options.selectedIndex)">
						
					</select>
					</span>
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>主讲人：</label>
				<div class="formControls col-2">
					<span class="select-box"> <select id="courseSpeaker" name="courseSpeaker"
						datatype="*" nullmsg="主讲人不能为空！"class="select">
							

					</select>
					</span>
				</div>
				
				<label class="form-label col-2"><span class="c-red">*</span>课程人数：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="输入最大人数"
						datatype="*" nullmsg="课程人数不能为空！" id="courseMaxNumber" name="courseMaxNumber">
				</div>

			</div>
			<div class="row cl">
			</div>
            <div class="row cl">
                <label class="form-label col-2">培训人员：</label>
                <div class="formControls col-10" id = "educationBranchName">
                
<!-- 技术部 			-->
                
                <dl class="permission-list">
					<dt>
						<label>
						<input type="checkbox" value="" name="project-0" id="technology-1">
						全选</label>
					</dt>
					
					<dd >
						<dl class="cl permission-list2">

							<dd id= "educationEmployeeName">
<!-- 								<label class=""> -->
<!-- 									<input type="checkbox" value="" name="project-1-1-0" id="project-1-1-0"> -->
<!-- 									组员1</label> -->
<!-- 								<label class=""> -->
<!-- 									<input type="checkbox" value="" name="project-1-1-0" id="project-1-1-1"> -->
<!-- 									组员2</label> -->
<!-- 								<label class=""> -->
<!-- 									<input type="checkbox" value="" name="project-1-1-0" id="project-1-1-2"> -->
<!-- 									组员3</label> -->
<!-- 								<label class=""> -->
<!-- 									<input type="checkbox" value="" name="project-1-1-0" id="project-1-1-3"> -->
<!-- 									组员4</label> -->
						  </dd>
						</dl>
						
					</dd>
				</dl>
				</div>
            </div>
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button onClick="article_save_submit();"
						class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>
					<!--
                    <button onClick="article_save();" class="btn btn-secondary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存草稿</button>
                    -->
					<button onClick="layer_close();" class="btn btn-default radius"
						type="reset">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript"
		src="lib/webuploader/0.1.5/webuploader.min.js"></script>
	<script type="text/javascript" src="js/H-ui.js"></script>
	<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script>
$(function(){
	$("#form_course_add").Validform({
		tiptype:4,
		callback:function(form){
			form[0].submit();
// 			var index = parent.layer.getFrameIndex(window.name);
// 			parent.layer.close(index);
		}
	});
	
});
$(function(){
	$(".permission-list dt input:checkbox").click(function(){
		$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
	});
	$(".permission-list2 dd input:checkbox").click(function(){
		var l =$(this).parent().parent().find("input:checked").length;
		var l2=$(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
		if($(this).prop("checked")){
			$(this).closest("dl").find("dt input:checkbox").prop("checked",true);
			$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
		}
		else{
			if(l==0){
				$(this).closest("dl").find("dt input:checkbox").prop("checked",false);
			}
			if(l2==0){
				$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
			}
		}
		
	});
});
</script>
</body>
</html>