<#import "spring.ftl" as s />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>秒杀页列表</title>
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" />
    <!--[if lt IE 9]>
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js" />
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js" />
    <![endif] -->
</head>
<body>
    <div class="container">
    	<div class="panel panel-default">
    		<div class="panel-heading text-center">
    			<h2>秒杀列表</h2>
    		</div>
    		<div class="panel-body">
    			<table class="table table-hover">
    				<thead>
    					<tr>
    						<th>名称</th>
    						<th>库存</th>
    						<th>开始时间</th>
    						<th>结束时间</th>
    						<th>创建时间</th>
    						<th>详情页</th>
    					</tr>
    				</thead>
    				<tbody>
    					<#list list as sk>
    					<tr>
							<td>${sk.name}<td>
							<td>${sk.count}<td>
							<td>${sk.startTime?string("yyyy-MM-dd HH:mm:ss")}<td>
							<td>${sk.endTime?string("yyyy-MM-dd HH:mm:ss")}<td>
							<td>${sk.createTime?string("yyyy-MM-dd HH:mm:ss")}<td>
							<td><a class="btn btn-info" href="<@s.url '/seckill/${sk.id}/detail'/>" target="blank">link</a><td>
    					</tr>
    					</#list>
    				</tbody>
    			</table>
    		</div>
    	</div>
    </div>
</body>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>