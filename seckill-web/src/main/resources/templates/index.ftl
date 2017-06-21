<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome</title>
</head>
<body>
Time: ${time?datetime}
<br>
Message: ${message}
<form method="post" action="user/login">
    <input name="username" placeholder="Username">
    <br>
    <input type="password" name="password" placeholder="Password">
    <br>
    <button type="button" id="submit">Submit</button>
    <button type="button" id="test">Test</button>
    <span id="msg" style="color: red;"></span>
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