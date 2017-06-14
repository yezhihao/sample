<div>
    <ul>
        <#assign size = 10>
        <#assign left = 10 / 2 + 1>
        <#assign right = totalPage - left>

        <li class="firstPage"><a href="test?page=1">首页</a></li>
        <#if (page>1)>
        <li class="prevPage"><a href="test?page=${index-1}">上一页</a></li>
        </#if>

        <#if (total??)>
            <#list 1..10 as item>
                <#if (index < left)>
                    <#assign show = item>
                <#elseif (index > right)>
                    <#assign show = item + total - size>
                <#else>
                    <#assign show = item + index - left>
                </#if>
                <#assign curr = (index==show)>
                <li><a href="test?page=${show}">${show} ${curr?string('this','')}</a></li>
            </#list>
        </#if>

        <#if (page < total)>
        <li class="nextPage"><a href="test?page=${index+1}">下一页</a></li>
        </#if>
        <li class="lastPage"><a href="test?page=${total}">末页</a></li>
    </ul>
    <ul id="list">
    </ul>
    <script type="text/javascript">
    function buildPageNo(total, index){
    	var size = 10;
        var left = size / 2 + 1;
        var right = total - left;

        var show = 0;
        var htm = "";
        for (var i = 1; i <= size; i++) {
            if (index < left) {
                show = i;
            } else if (index > right) {
                show = i + total - size;
            } else {
                show = i + index - left;
            }
            htm+= '<li class="firstPage"><a href="test?page='+show+'">'+show+'</a></li>'
        }
        $('#list').html(htm);
    }
    window.onload = buildPageNo(${totalPage}, ${page});
	</script>
</div>
