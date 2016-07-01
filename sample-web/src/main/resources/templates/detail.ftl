<#import "spring.ftl" as s />
<#assign getTime="org.sample.utils.TimeUtils"?new()>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>秒杀详情</title>
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" />
    <!--[if lt IE 9]>
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js" />
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js" />
    <![endif] -->
</head>
<body>
    <div class="container">
    	<div class="panel panel-default text-center">
    		<div class="panel-heading">
    			<h1>${seckill.name}</h1>
    		</div>
    		<div class="panel-body">
    			<h2 class="text-danger">
    				<span class="glyphicon glyphicon-time"></span>
    				<span class="glyphicon" id="seckill-box"></span>
    			</h2>
    		</div>
    	</div>
    </div>
    <div id="killMobileModal" class="modal fade">
    	<div class="modal-dialog">
    		<div class="modal-content">
    			<div class="modal-header">
    				<h3 class="modal-title text-center">
    					<span class="glphyicon glphyicon-phone"></span>秒杀电话:
    				</h3>
    			</div>
    			<div class="modal-body">
    				<div class="row">
    					<div class="col-xs-8 col-xs-offset-2">
    						<input type="text" name="userMobile" id="userMobileKey" placeholder="填手机号^O^" class="form-control">
    					</div>
    				</div>
    			</div>
    			<div class="modal-footer">
    				<span id="userMobileMessage" class="glyphicon"></span>
    				<button type="button" id="userMobileBtn" class="btn btn-success">
    					<span class="glyphicon glyphicon-phone"></span>Submit
    				</button>
    			</div>
    		</div>
    	</div>
    </div>
</body>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.js"></script>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<script src="<@s.url '/script/seckill.js'/>"></script>
<script type="text/javascript">
seckill.detail.init({
	seckillId : ${seckill.id},
	startTime : '${getTime(seckill.startTime)}',
	endTime : '${getTime(seckill.endTime)}'
});
</script>
</html>