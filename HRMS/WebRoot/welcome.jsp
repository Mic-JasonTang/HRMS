<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<title>我的桌面</title>
<!-- AJAX支持 -->
<script type="text/javascript" src="js/getHttpRequest.js"></script>
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
				var text = xhr.responseText;
				var size = text.substring(0, 1); //第一次字符是数组大小
				text = text.substring(2, text.length); //删除第一个数字和，
				var branchNames = new Array();
				var namesHTML="<th>统计</th>";
				var branchPeoples = new Array();
				var peoplesHTML="<td>部门拥有人数统计</td>";
				var branchMaxPeoples = new Array();
				var maxPeoplesHTML="<td>拥有最大人数统计</td>";
				var normals = new Array();
				var normHTML="<td>考勤正常人数</td>";
				var lates = new Array();
				var lateHTML="<td>考勤迟到人数</td>";
				var datas = text.split(",");
// 				alert(datas);
				//数据处理
					for(var i = 0; i<datas.length; i++) {
						if(i < size * 1) {
						branchNames[i] = datas[i];
							namesHTML += "<th>" + branchNames[i] + "</th>";
						} else if(i < size * 2) {
							branchPeoples[i - size] = datas[i];
							peoplesHTML += "<td><span class='label label-secondary radius'>" + datas[i] + "</span></td>";
						} else if(i < size * 3) {
							branchMaxPeoples[i - size * 2] = datas[i];
							maxPeoplesHTML += "<td><span class='label label-primary radius'>" + datas[i] + "</span></td>";
						} else if(i < size * 4) {
							normals[i - size * 3] = datas[i];
							normHTML += "<td><span class='label label-success radius'>" + datas[i] + "</span></td>";
						} else if(i < size * 5) {
							lates[i - size * 4] = datas[i];
							lateHTML += "<td><span class='label label-warning radius'>" + datas[i] + "</span></td>";
						}
					}
//  				alert(namesHTML);
// 					alert(branchPeoples);
// 					alert(branchMaxPeoples);
// 					alert(normals);
// 					alert(lates);
					document.getElementById("branchName").innerHTML = namesHTML;
					document.getElementById("branchPeoples").innerHTML = peoplesHTML;
					document.getElementById("branchMaxPeoples").innerHTML = maxPeoplesHTML;
					document.getElementById("normals").innerHTML = normHTML;
					document.getElementById("lates").innerHTML = lateHTML;
				}
			}
		}
		//3.连接服务器
		xhr.open("GET", "/HRMS/servlet/statisticsServlet?time=" + new Date().getTime());
		//4.发送请求(post)
		xhr.send(null); //GET不请求正文
};

</script>
<style>
* {
	margin: 0;
	padding: 0;
	list-style: none;
	border: none;
}
#zzsc {
	width: 920px;
	margin: 100px auto;
}
</style>
<style type="text/css">
	${demo.css}
</style>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jscss.js"></script>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		我的桌面 <a
		class="btn btn-success radius r mr-20"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
<div class="pd-20" style="padding-top:20px;">
  <p class="f-20 text-success">欢迎使用 HRMS <span class="f-14">v1.0</span> 人事管理系统</p>
  <p>上次登录IP：${sessionScope.lastIp }  上次登录时间：${sessionScope.lastTime }</p>
  <table class="table table-border table-bordered table-bg">
    <thead>
      <tr>
        <th colspan="7" scope="col">考勤统计</th>
      </tr>
      <tr class="text-c" id = "branchName">
      	<th>统计</th>
      	
<!--       	<c:forTokens items="${sessionScope.branch_people_num.keySet()}" delims="[|,|]" var="name"> -->
<!--       	<th>${name}</th> -->
<!--       	</c:forTokens> -->
      </tr>
    </thead>
    <tbody>
      <tr class="text-c" id="branchPeoples">
        <td>部门拥有人数统计</td>
<!--         <c:forTokens items="${sessionScope.branch_people_num.values()}" delims="[|,|]" var="num"> -->
<!--       	<td><span class="label label-secondary radius">${num}</span></td> -->
<!--       	</c:forTokens> -->
      </tr>
      <tr class="text-c" id="branchMaxPeoples">
        <td>拥有最大人数统计</td>
<!--         <c:forTokens items="${sessionScope.branch_people_Maxnum.values()}" delims="[|,|]" var="num"> -->
<!--       	<td><span class="label label-primary radius">${num}</span></td> -->
<!--       	</c:forTokens> -->
      </tr>
      <tr class="text-c" id="normals">
        <td>考勤正常人数</td>
<!--         <c:forTokens items="${sessionScope.attendance_normal_num.values()}" delims="[|,|]" var="num"> -->
<!--       	<td><span class="label label-success radius">${num}</span></td> -->
<!--       	</c:forTokens> -->
      </tr>
      <tr class="text-c" id="lates">
        <td>考勤迟到人数</td>
<!--         <c:forTokens items="${sessionScope.attendance_late_num.values()}" delims="[|,|]" var="num"> -->
<!--       	<td><span class="label label-warning radius">${num}</span></td> -->
<!--       	</c:forTokens> -->
      </tr>
    </tbody>
  </table>
<!--   ${sessionScope.branch_people_num.keySet()} <br> -->
<!--   ${sessionScope.branch_people_num.values()} <br> -->
<!--   ${sessionScope.branch_people_Maxnum.keySet()} <br> -->
<!--   ${sessionScope.branch_people_Maxnum.values()} <br> -->
<!--   ${sessionScope.attendance_normal_num.keySet()} <br> -->
<!--   ${sessionScope.attendance_normal_num.values()} <br> -->
<!--   ${sessionScope.attendance_late_num.keySet()} <br> -->
<!--   ${sessionScope.attendance_late_num.values()} <br> -->
  <table class="table table-border table-bordered table-bg mt-20">
    <thead>
    <tr>
    <td>
    <div id="zzsc">
  		<canvas id="canvas"></canvas>
	</div>
	</td>
	<td>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	</td>
	</tr>
	</thead>
  </table>
</div>
<footer class="footer">
  <p>感谢jQuery、layer、laypage、Validform、UEditor、My97DatePicker、iconfont、Datatables、WebUploaded、icheck、highcharts、bootstrap-Switch<br>Copyright &copy;2015 H-ui.admin v2.3 All Rights Reserved.<br>
    本后台系统由<a href="#" target="_blank" title="计科1303班"> 计科1303班 唐 洋 </a>提供技术支持</p>
</footer>
</body>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript" src="lib/Highcharts/4.1.7/js/highcharts.js"></script>
<script type="text/javascript" src="lib/Highcharts/4.1.7/js/modules/exporting.js"></script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
$(function () {
    $(document).ready(function () {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

        $('#container').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function () {

                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function () {
                            var x = (new Date()).getTime(), // current time
                                y = Math.random();
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: '系统后台监控'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'PROPERTIES'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false //说明
            },
            exporting: {
                enabled: true //支持打印
            },
            series: [{
                name: '访问数据',
                data: (function () {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;

                    for (i = -19; i <= 0; i += 1) {
                        data.push({
                            x: time + i * 1000,
                            y: Math.random()
                        });
                    }
                    return data;
                }())
            }]
        });
    });
});
</script>
</html>