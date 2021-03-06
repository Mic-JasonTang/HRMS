<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <script type="text/javascript" src="lib/PIE_IE678.js"></script>
    <![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
	type="text/css" />
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
	type="text/css" />
<!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
<title>添加奖惩</title>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="form-article-add">
			<div class="row cl">
			<label class="form-label col-2">当前奖惩人员:</label>
			<a class="label label-success radius" href="employee-show.jsp" class="c-green"><span name="name">Jason</span></a>
			<br />
			<br />
			<hr>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>奖惩性质：</label>
				<div class="formControls col-2">
					<span class="select-box"> <select name="reward_publish"
						class="select">
							<option value="0">选择奖惩性质</option>
							<option value="奖励">奖励</option>
							<option value="惩罚">惩罚</option>
					</select>
					</span>
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>奖惩名称：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="输入名称"
						id="reward_publish_Name" name="reward_publish_Name">
				</div>

			</div>

			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>奖惩原因：</label>
				<div class="formControls col-10">
					<textarea name="reward_publish_reason" cols="" rows="" class="textarea"
						placeholder="原因" datatype="*10-100" dragonfly="true"
						nullmsg="原因不能为空！" onKeyUp="textarealength(this,200)"></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/200
					</p>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>奖惩金额：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="输入金额(元)"
						id="reward_publish_money" name="reward_publish_money">
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
</body>
</html>