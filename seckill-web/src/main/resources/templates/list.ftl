<#import "spring.ftl" as s />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>秒杀页列表</title>
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://cdn.bootcss.com/select2/4.0.3/css/select2.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"/>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"/>
    <script src="http://cdn.bootcss.com/select2/4.0.3/js/select2.min.js"></script>
    <script src="http://cdn.bootcss.com/select2/4.0.3/js/i18n/zh-CN.js"></script>
    <![endif] -->
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>秒杀列表</h2>
            <select name="test" class="select2" multiple>
                <option>asd</option>
            </select>
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
                    <td>${sk.name}</td>
                    <td>${sk.count}</td>
                    <td>${sk.startTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td>${sk.endTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td>${sk.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td><a class="btn btn-info" href="<@s.url '/seckill/${sk.id}/detail'/>" target="blank">link</a></td>
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
<script src="http://cdn.bootcss.com/select2/4.0.3/js/select2.min.js"></script>
<script src="http://cdn.bootcss.com/select2/4.0.3/js/i18n/zh-CN.js"></script>
<script>
    $(".select2").select2({
        language: "zh-CN", //设置 提示语言
        width: "100%", //设置下拉框的宽度
        placeholder: "请选择",
        allowClear: true,
        tags: true,
        maximumSelectionLength: 2,  //设置最多可以选择多少项
        ajax: {
            url: "api/list",
            data: function (term, page) {
                return {
                    index: term.term
                };
            },
            cache: true,
            processResults: function (data, page) {
                var arr = [];
                for (x in data) {
                    var t = {};
                    t.id = data[x].id;
                    t.text = data[x].name;
                    arr.push(t);
                }
                console.log(arr);
                return {
                    results: arr
                };
            }
        }
    });
</script>
</html>