<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome</title>
</head>
<body>
Time: ${time?datetime}
<form method="post">
    <input name="username" placeholder="Username">
    <br>
    <input type="password" name="password" placeholder="Password">
    <br>
    <input type="checkbox" name="remember" value="true"> remember me
    <br>

    <button type="submit">Submit</button>
<#--<button type="button" id="submit">Submit</button>-->
    <button type="button" id="test">Test</button>
    <span id="msg" style="color: red;">${message!''}</span>
</form>
</body>
<script src="http://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    var form = $(document.forms[0]);
    $("#submit").on("click", function () {
        $.ajax({
            url: form.attr('action'),
            type: 'post',
            data: form.serialize(),
            success: function (result) {
                if (result.code == 0) {
                    window.location.href = "index.html"
                } else {
                    $("#msg").append(result.msg);
                }
            }
        });
    });
    $("#test").on("click", function () {
        $.ajax({
            url: form.attr('action'),
            type: 'post',
            data: {},
            success: function (result) {
                if (result.code == 0) {
                    window.location.href = "index.html"
                } else {
                    $("#msg").append(result.msg);
                }
            }
        });
    });
</script>
</html>