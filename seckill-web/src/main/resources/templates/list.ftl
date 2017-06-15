<#import "spring.ftl" as s />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>秒杀页列表</title>
<#include "commons/css.ftl">
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>秒杀列表</h2>
            <select name="id" class="select2"></select>
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
<#include "commons/script.ftl">
<script>
    $(".select2").select2({
        ajax: {
            url: "api/list",
            data: function (params) {
                return {
                    name: params.term
                };
            },
            processResults: function (data, params) {
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
            },
            cache: true
        },
        language: "zh-CN", //设置 提示语言
        width: "100%", //设置下拉框的宽度
        placeholder: "请选择",
        allowClear: true,
        minimumInputLength: 1,
        maximumSelectionLength: 1//设置最多可以选择多少项
    });
</script>
</html>