<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="_header.html"></c:import>
<style type="text/css">
#container {
	height: 500px; 
	min-width: 910px; 
	max-width: 1000px;
	margin: 0 auto;
}
</style>
<title>3D柱状图</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		统计管理 <span class="c-gray en">&gt;</span> 公司人数3D柱状图 <a
			class="btn btn-success radius r mr-20"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
	<div id="container" style="height: 500px"></div>
<!-- 	<div id="container" style="min-width:700px;height:400px"></div> -->
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript" src="lib/Highcharts/4.1.7/js/highcharts.js"></script>
<script type="text/javascript" src="lib/Highcharts/4.1.7/js/modules/exporting.js"></script>
<script type="text/javascript" src="lib/Highcharts/4.1.7/js/highcharts-3d.js"></script>

<script type="text/javascript" >
$(function () {
    $('#container').highcharts({
        chart: {
            type: 'column',
            margin: 75,
            options3d: {
                enabled: true,
                alpha: 10,
                beta: 25,
                depth: 70
            }
        },
        title: {
            text: '公司人数3D柱状图统计'
        },
        subtitle: {
            text: '统计年份：2016年'
        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        xAxis: {
            categories: Highcharts.getOptions().lang.shortMonths
        },
        yAxis: {
            title: {
                text: null
            }
        },
        series: [{
            name: '人数',
            data: [20, 30, 10, 40, 25, 50, 10, 40, 60, 30, 50, 30]
        }]
    });
});
</script>
</body>
</html>