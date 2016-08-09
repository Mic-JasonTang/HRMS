<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
	type="text/css" />
<title>增加员工</title>
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
// 				alert(xhr.responseText);
				var data = xhr.responseText;
				var employeeId = data.substring(0, 11); //前11位是员工编号
				data = data.substring(11, data.length); //截取
// 				alert("第一次截取data:" + data);
				var size = data.substring(0,1);        //接着的一位是部门数
// 				alert("size:" + size);
				data = data.substring(1, data.length); //截取
// 				alert("第二次截取data:" + data);
				var branchs = data.split(";"); //对剩下的部门信息进行分割
// 				alert(branchs);
				var branchIds = new Array();
				var branchNames = new Array();
				var branchHTML = "";
				for(var i=0; i<size; i++) {
					branchIds[i] = branchs[i].split(",")[0];
					branchNames[i] = branchs[i].split(",")[1];
					branchHTML += "<option value='" +branchIds[i]+ "'>" +branchNames[i]+ "</option>";
// 					alert(branchIds[i]);
// 					alert(branchNames[i]);
				}
				document.getElementById("employeeId").value = employeeId;
				document.getElementById("employeeBranchName").innerHTML = branchHTML;
				}
			}
		}
		//3.连接服务器
		xhr.open("GET", "/HRMS/servlet/NewEmployeeIdServlet?time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};
</script>
</head>
<body>
	<div class="pd-20">
		<form action="servlet/AddEmployeeServlet" method="post" class="form form-horizontal"
			id="form-employee-add">
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>员工编号：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text"
						placeholder="正在创建员工编号" id="employeeId" name="employeeId" readonly="readonly">
				</div> 
				<span class="label label-success radius">&lt; 此编号由系统自动生成 &gt;</span>
			</div>
			<div class="row cl">

				<label class="form-label col-2"><span class="c-red">*</span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="输入姓名"
						datatype="*" nullmsg="姓名不能为空！" id="employeeName" name="employeeName">
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
				<div class="formControls col-2">
					<input type="password" class="input-text" value="" placeholder="输入密码"
						datatype="s6-18" nullmsg="密码不能为空！" id="employeePass1" name="employeePass1">
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>确认密码：</label>
				<div class="formControls col-2">
					<input type="password" class="input-text" value="" placeholder="再次输入密码"
						datatype="s6-16" nullmsg="确认密码不能为空！" recheck="employeePass1" datatype="s6-18" errormsg="您两次输入的密码不一致！"
						id="employeePass" name="employeePass">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
				<div class="formControls col-2 skin-minimal">
					<div class="radio-box">
						<input type="radio" id="sex-1" name="employeeSex" 
							datatype="*" nullmsg="性别不能为空！" value="男"> <label for="sex-1">男</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="sex-2"  value="女" name="employeeSex"> <label
							for="sex-2">女</label>
					</div>
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="输入年龄"
						datatype="n" nullmsg="年龄不能为空！"id="employeeAge" name="employeeAge">
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>身份证号：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value=""
						datatype="idcard" nullmsg="身份证号不能为空！"placeholder="输入身份证号码" id="employeeIdCard" name="employeeIdCard">
				</div>

			</div>

			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>出生日期：</label>
				<div class="formControls col-2">
				<input datatype="*" nullmsg="出生日期不能为空！"type="text" name="employeeBirth" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate">
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>手&nbsp;&nbsp;机&nbsp;&nbsp;号：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="输入手机号"
						datatype="m" nullmsg="手机号不能为空！"id="employeePhone" name="employeePhone">
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="输入邮箱"
						datatype="e" nullmsg="邮箱不能为空！"id="employeeEmail" name="employeeEmail">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</label>
				<div class="formControls col-10">
					<textarea name="employeeAddress" cols="" rows=""
						class="textarea" placeholder="填写详细的住址" datatype="*5-100"
						dragonfly="true" nullmsg="住址不能为空！"
						onKeyUp="textarealength(this,200)"></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/100
					</p>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>所属部门：</label>
				<div class="formControls col-2">
					<span class="select-box"> <select id="employeeBranchName" name="employeeBranchId"
						datatype="*" nullmsg="部门不能为空！"class="select">
					</select>
					</span>
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>工作状态：</label>
				<div class="formControls col-2">
					<span class="select-box"> <select id="employeeStatus" name="employeeStatus"
						datatype="*" nullmsg="工作状态不能为空！"class="select">
						<option value="工作中">工作中</option>
					</select>
					</span>
				</div>
				<label class="form-label col-2"><span class="c-red">*</span>入职日期：</label>
				<div class="formControls col-2">
				<input datatype="*" nullmsg="入职日期不能为空！"type="text" name="employeeEnterTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemax" class="input-text Wdate">
				</div>
			</div>
			<!--
            <div class="row cl">
                <label class="form-label col-2">缩略图：</label>
                <div class="formControls col-10">
                    <div class="uploader-thum-container">
                        <div id="fileList" class="uploader-list"></div>
                        <div id="filePicker">选择图片</div>
                        <button id="btn-star" class="btn btn-default btn-uploadstar radius ml-10">开始上传</button>
                    </div>
                </div>
            </div>
            -->
			<div class="row cl">
				<label class="form-label col-2">证件照上传：</label>
				<div class="formControls col-10">
					<div class="uploader-list-container">
						<div class="queueList">
							<div id="dndArea" class="placeholder">
								<div id="filePicker-2"></div>
								<p>或将照片拖到这里，可选多张照片</p>
							</div>
						</div>
						<div class="statusBar" style="display:none;">
							<div class="progress">
								<span class="text">0%</span> <span class="percentage"></span>
							</div>
							<div class="info"></div>
							<div class="btns">
								<div id="filePicker2"></div>
								<div class="uploadBtn">开始上传</div>
							</div>
						</div>
					</div>
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
	<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script>
	<script type="text/javascript" src="js/H-ui.js"></script>
	<script type="text/javascript" src="js/H-ui.admin.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#form-employee-add").Validform({
			tiptype:4,
			callback:function(form){
				form[0].submit();
//	 			var index = parent.layer.getFrameIndex(window.name);
//	 			parent.layer.close(index);
			}
		});
		
	});
	
    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
        
        $list = $("#fileList"),
        $btn = $("#btn-star"),
        state = "pending",
        uploader;
    
        var uploader = WebUploader.create({
            auto: true,
            swf: 'lib/webuploader/0.1.5/Uploader.swf',
        
            // 文件接收服务端。
            server: 'http://lib.h-ui.net/webuploader/0.1.5/server/fileupload.php',
        
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker',
        
            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            resize: false,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        uploader.on( 'fileQueued', function( file ) {
            var $li = $(
                '<div id="' + file.id + '" class="item">' +
                    '<div class="pic-box"><img></div>'+
                    '<div class="info">' + file.name + '</div>' +
                    '<p class="state">等待上传...</p>'+
                '</div>'
            ),
            $img = $li.find('img');
            $list.append( $li );
        
            // 创建缩略图
            // 如果为非图片文件，可以不用调用此方法。
            // thumbnailWidth x thumbnailHeight 为 100 x 100
            uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }
        
                $img.attr( 'src', src );
            }, thumbnailWidth, thumbnailHeight );
        });
        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                $percent = $li.find('.progress-box .sr-only');
        
            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress-box"><span class="progress-bar radius"><span class="sr-only" style="width:0%"></span></span></div>').appendTo( $li ).find('.sr-only');
            }
            $li.find(".state").text("上传中");
            $percent.css( 'width', percentage * 100 + '%' );
        });
        
        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader.on( 'uploadSuccess', function( file ) {
            $( '#'+file.id ).addClass('upload-state-success').find(".state").text("已上传");
        });
        
        // 文件上传失败，显示上传出错。
        uploader.on( 'uploadError', function( file ) {
            $( '#'+file.id ).addClass('upload-state-error').find(".state").text("上传出错");
        });
        
        // 完成上传完了，成功或者失败，先删除进度条。
        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress-box').fadeOut();
        });
        uploader.on('all', function (type) {
            if (type === 'startUpload') {
                state = 'uploading';
            } else if (type === 'stopUpload') {
                state = 'paused';
            } else if (type === 'uploadFinished') {
                state = 'done';
            }
    
            if (state === 'uploading') {
                $btn.text('暂停上传');
            } else {
                $btn.text('开始上传');
            }
        });
    
        $btn.on('click', function () {
            if (state === 'uploading') {
                uploader.stop();
            } else {
                uploader.upload();
            }
        });
    
    });
    
    (function( $ ){
        // 当domReady的时候开始初始化
        $(function() {
            var $wrap = $('.uploader-list-container'),
    
                // 图片容器
                $queue = $( '<ul class="filelist"></ul>' )
                    .appendTo( $wrap.find( '.queueList' ) ),
    
                // 状态栏，包括进度和控制按钮
                $statusBar = $wrap.find( '.statusBar' ),
    
                // 文件总体选择信息。
                $info = $statusBar.find( '.info' ),
    
                // 上传按钮
                $upload = $wrap.find( '.uploadBtn' ),
    
                // 没选择文件之前的内容。
                $placeHolder = $wrap.find( '.placeholder' ),
    
                $progress = $statusBar.find( '.progress' ).hide(),
    
                // 添加的文件数量
                fileCount = 0,
    
                // 添加的文件总大小
                fileSize = 0,
    
                // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,
    
                // 缩略图大小
                thumbnailWidth = 110 * ratio,
                thumbnailHeight = 110 * ratio,
    
                // 可能有pedding, ready, uploading, confirm, done.
                state = 'pedding',
    
                // 所有文件的进度信息，key为file id
                percentages = {},
                // 判断浏览器是否支持图片的base64
                isSupportBase64 = ( function() {
                    var data = new Image();
                    var support = true;
                    data.onload = data.onerror = function() {
                        if( this.width != 1 || this.height != 1 ) {
                            support = false;
                        }
                    }
                    data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                    return support;
                } )(),
    
                // 检测是否已经安装flash，检测flash的版本
                flashVersion = ( function() {
                    var version;
    
                    try {
                        version = navigator.plugins[ 'Shockwave Flash' ];
                        version = version.description;
                    } catch ( ex ) {
                        try {
                            version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                    .GetVariable('$version');
                        } catch ( ex2 ) {
                            version = '0.0';
                        }
                    }
                    version = version.match( /\d+/g );
                    return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
                } )(),
    
                supportTransition = (function(){
                    var s = document.createElement('p').style,
                        r = 'transition' in s ||
                                'WebkitTransition' in s ||
                                'MozTransition' in s ||
                                'msTransition' in s ||
                                'OTransition' in s;
                    s = null;
                    return r;
                })(),
    
                // WebUploader实例
                uploader;
    
            if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {
    
                // flash 安装了但是版本过低。
                if (flashVersion) {
                    (function(container) {
                        window['expressinstallcallback'] = function( state ) {
                            switch(state) {
                                case 'Download.Cancelled':
                                    alert('您取消了更新！')
                                    break;
    
                                case 'Download.Failed':
                                    alert('安装失败')
                                    break;
    
                                default:
                                    alert('安装已成功，请刷新！');
                                    break;
                            }
                            delete window['expressinstallcallback'];
                        };
    
                        var swf = 'expressInstall.swf';
                        // insert flash object
                        var html = '<object type="application/' +
                                'x-shockwave-flash" data="' +  swf + '" ';
    
                        if (WebUploader.browser.ie) {
                            html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                        }
    
                        html += 'width="100%" height="100%" style="outline:0">'  +
                            '<param name="movie" value="' + swf + '" />' +
                            '<param name="wmode" value="transparent" />' +
                            '<param name="allowscriptaccess" value="always" />' +
                        '</object>';
    
                        container.html(html);
    
                    })($wrap);
    
                // 压根就没有安转。
                } else {
                    $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
                }
    
                return;
            } else if (!WebUploader.Uploader.support()) {
                alert( 'Web Uploader 不支持您的浏览器！');
                return;
            }
    
            // 实例化
            uploader = WebUploader.create({
                pick: {
                    id: '#filePicker-2',
                    label: '点击选择图片'
                },
                formData: {
                    uid: 123
                },
                dnd: '#dndArea',
                paste: '#uploader',
                swf: 'lib/webuploader/0.1.5/Uploader.swf',
                chunked: false,
                chunkSize: 512 * 1024,
                server: 'http://lib.h-ui.net/webuploader/0.1.5/server/fileupload.php',
                // runtimeOrder: 'flash',
    
                // accept: {
                //     title: 'Images',
                //     extensions: 'gif,jpg,jpeg,bmp,png',
                //     mimeTypes: 'image/*'
                // },
    
                // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
                disableGlobalDnd: true,
                fileNumLimit: 300,
                fileSizeLimit: 200 * 1024 * 1024,    // 200 M
                fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
            });
    
            // 拖拽时不接受 js, txt 文件。
            uploader.on( 'dndAccept', function( items ) {
                var denied = false,
                    len = items.length,
                    i = 0,
                    // 修改js类型
                    unAllowed = 'text/plain;application/javascript ';
    
                for ( ; i < len; i++ ) {
                    // 如果在列表里面
                    if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                        denied = true;
                        break;
                    }
                }
    
                return !denied;
            });
    
            uploader.on('dialogOpen', function() {
                console.log('here');
            });
    
            // uploader.on('filesQueued', function() {
            //     uploader.sort(function( a, b ) {
            //         if ( a.name < b.name )
            //           return -1;
            //         if ( a.name > b.name )
            //           return 1;
            //         return 0;
            //     });
            // });
    
            // 添加“添加文件”的按钮，
            uploader.addButton({
                id: '#filePicker2',
                label: '继续添加'
            });
    
            uploader.on('ready', function() {
                window.uploader = uploader;
            });
    
            // 当有文件添加进来时执行，负责view的创建
            function addFile( file ) {
                var $li = $( '<li id="' + file.id + '">' +
                        '<p class="title">' + file.name + '</p>' +
                        '<p class="imgWrap"></p>'+
                        '<p class="progress"><span></span></p>' +
                        '</li>' ),
    
                    $btns = $('<div class="file-panel">' +
                        '<span class="cancel">删除</span>' +
                        '<span class="rotateRight">向右旋转</span>' +
                        '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
                    $prgress = $li.find('p.progress span'),
                    $wrap = $li.find( 'p.imgWrap' ),
                    $info = $('<p class="error"></p>'),
    
                    showError = function( code ) {
                        switch( code ) {
                            case 'exceed_size':
                                text = '文件大小超出';
                                break;
    
                            case 'interrupt':
                                text = '上传暂停';
                                break;
    
                            default:
                                text = '上传失败，请重试';
                                break;
                        }
    
                        $info.text( text ).appendTo( $li );
                    };
    
                if ( file.getStatus() === 'invalid' ) {
                    showError( file.statusText );
                } else {
                    // @todo lazyload
                    $wrap.text( '预览中' );
                    uploader.makeThumb( file, function( error, src ) {
                        var img;
    
                        if ( error ) {
                            $wrap.text( '不能预览' );
                            return;
                        }
    
                        if( isSupportBase64 ) {
                            img = $('<img src="'+src+'">');
                            $wrap.empty().append( img );
                        } else {
                            $.ajax('lib/webuploader/0.1.5/server/preview.php', {
                                method: 'POST',
                                data: src,
                                dataType:'json'
                            }).done(function( response ) {
                                if (response.result) {
                                    img = $('<img src="'+response.result+'">');
                                    $wrap.empty().append( img );
                                } else {
                                    $wrap.text("预览出错");
                                }
                            });
                        }
                    }, thumbnailWidth, thumbnailHeight );
    
                    percentages[ file.id ] = [ file.size, 0 ];
                    file.rotation = 0;
                }
    
                file.on('statuschange', function( cur, prev ) {
                    if ( prev === 'progress' ) {
                        $prgress.hide().width(0);
                    } else if ( prev === 'queued' ) {
                        $li.off( 'mouseenter mouseleave' );
                        $btns.remove();
                    }
    
                    // 成功
                    if ( cur === 'error' || cur === 'invalid' ) {
                        console.log( file.statusText );
                        showError( file.statusText );
                        percentages[ file.id ][ 1 ] = 1;
                    } else if ( cur === 'interrupt' ) {
                        showError( 'interrupt' );
                    } else if ( cur === 'queued' ) {
                        percentages[ file.id ][ 1 ] = 0;
                    } else if ( cur === 'progress' ) {
                        $info.remove();
                        $prgress.css('display', 'block');
                    } else if ( cur === 'complete' ) {
                        $li.append( '<span class="success"></span>' );
                    }
    
                    $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
                });
    
                $li.on( 'mouseenter', function() {
                    $btns.stop().animate({height: 30});
                });
    
                $li.on( 'mouseleave', function() {
                    $btns.stop().animate({height: 0});
                });
    
                $btns.on( 'click', 'span', function() {
                    var index = $(this).index(),
                        deg;
    
                    switch ( index ) {
                        case 0:
                            uploader.removeFile( file );
                            return;
    
                        case 1:
                            file.rotation += 90;
                            break;
    
                        case 2:
                            file.rotation -= 90;
                            break;
                    }
    
                    if ( supportTransition ) {
                        deg = 'rotate(' + file.rotation + 'deg)';
                        $wrap.css({
                            '-webkit-transform': deg,
                            '-mos-transform': deg,
                            '-o-transform': deg,
                            'transform': deg
                        });
                    } else {
                        $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                        // use jquery animate to rotation
                        // $({
                        //     rotation: rotation
                        // }).animate({
                        //     rotation: file.rotation
                        // }, {
                        //     easing: 'linear',
                        //     step: function( now ) {
                        //         now = now * Math.PI / 180;
    
                        //         var cos = Math.cos( now ),
                        //             sin = Math.sin( now );
    
                        //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                        //     }
                        // });
                    }
    
    
                });
    
                $li.appendTo( $queue );
            }
    
            // 负责view的销毁
            function removeFile( file ) {
                var $li = $('#'+file.id);
    
                delete percentages[ file.id ];
                updateTotalProgress();
                $li.off().find('.file-panel').off().end().remove();
            }
    
            function updateTotalProgress() {
                var loaded = 0,
                    total = 0,
                    spans = $progress.children(),
                    percent;
    
                $.each( percentages, function( k, v ) {
                    total += v[ 0 ];
                    loaded += v[ 0 ] * v[ 1 ];
                } );
    
                percent = total ? loaded / total : 0;
    
    
                spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
                spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
                updateStatus();
            }
    
            function updateStatus() {
                var text = '', stats;
    
                if ( state === 'ready' ) {
                    text = '选中' + fileCount + '张图片，共' +
                            WebUploader.formatSize( fileSize ) + '。';
                } else if ( state === 'confirm' ) {
                    stats = uploader.getStats();
                    if ( stats.uploadFailNum ) {
                        text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                            stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                    }
    
                } else {
                    stats = uploader.getStats();
                    text = '共' + fileCount + '张（' +
                            WebUploader.formatSize( fileSize )  +
                            '），已上传' + stats.successNum + '张';
    
                    if ( stats.uploadFailNum ) {
                        text += '，失败' + stats.uploadFailNum + '张';
                    }
                }
    
                $info.html( text );
            }
    
            function setState( val ) {
                var file, stats;
    
                if ( val === state ) {
                    return;
                }
    
                $upload.removeClass( 'state-' + state );
                $upload.addClass( 'state-' + val );
                state = val;
    
                switch ( state ) {
                    case 'pedding':
                        $placeHolder.removeClass( 'element-invisible' );
                        $queue.hide();
                        $statusBar.addClass( 'element-invisible' );
                        uploader.refresh();
                        break;
    
                    case 'ready':
                        $placeHolder.addClass( 'element-invisible' );
                        $( '#filePicker2' ).removeClass( 'element-invisible');
                        $queue.show();
                        $statusBar.removeClass('element-invisible');
                        uploader.refresh();
                        break;
    
                    case 'uploading':
                        $( '#filePicker2' ).addClass( 'element-invisible' );
                        $progress.show();
                        $upload.text( '暂停上传' );
                        break;
    
                    case 'paused':
                        $progress.show();
                        $upload.text( '继续上传' );
                        break;
    
                    case 'confirm':
                        $progress.hide();
                        $( '#filePicker2' ).removeClass( 'element-invisible' );
                        $upload.text( '开始上传' );
    
                        stats = uploader.getStats();
                        if ( stats.successNum && !stats.uploadFailNum ) {
                            setState( 'finish' );
                            return;
                        }
                        break;
                    case 'finish':
                        stats = uploader.getStats();
                        if ( stats.successNum ) {
                            alert( '上传成功' );
                        } else {
                            // 没有成功的图片，重设
                            state = 'done';
                            location.reload();
                        }
                        break;
                }
    
                updateStatus();
            }
    
            uploader.onUploadProgress = function( file, percentage ) {
                var $li = $('#'+file.id),
                    $percent = $li.find('.progress span');
    
                $percent.css( 'width', percentage * 100 + '%' );
                percentages[ file.id ][ 1 ] = percentage;
                updateTotalProgress();
            };
    
            uploader.onFileQueued = function( file ) {
                fileCount++;
                fileSize += file.size;
    
                if ( fileCount === 1 ) {
                    $placeHolder.addClass( 'element-invisible' );
                    $statusBar.show();
                }
    
                addFile( file );
                setState( 'ready' );
                updateTotalProgress();
            };
    
            uploader.onFileDequeued = function( file ) {
                fileCount--;
                fileSize -= file.size;
    
                if ( !fileCount ) {
                    setState( 'pedding' );
                }
    
                removeFile( file );
                updateTotalProgress();
    
            };
    
            uploader.on( 'all', function( type ) {
                var stats;
                switch( type ) {
                    case 'uploadFinished':
                        setState( 'confirm' );
                        break;
    
                    case 'startUpload':
                        setState( 'uploading' );
                        break;
    
                    case 'stopUpload':
                        setState( 'paused' );
                        break;
    
                }
            });
    
            uploader.onError = function( code ) {
                alert( 'Eroor: ' + code );
            };
    
            $upload.on('click', function() {
                if ( $(this).hasClass( 'disabled' ) ) {
                    return false;
                }
    
                if ( state === 'ready' ) {
                    uploader.upload();
                } else if ( state === 'paused' ) {
                    uploader.upload();
                } else if ( state === 'uploading' ) {
                    uploader.stop();
                }
            });
    
            $info.on( 'click', '.retry', function() {
                uploader.retry();
            } );
    
            $info.on( 'click', '.ignore', function() {
                alert( 'todo' );
            } );
    
            $upload.addClass( 'state-' + state );
            updateTotalProgress();
        });
    
    })( jQuery );
    </script>
</body>
</html>