<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<header>
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/aes.js"></script>
    <script type="text/javascript" src="js/encryption.js"></script>
    <script>
        function sub() {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "gateway",
                data: {
                    "beanName": $("#beanName").val(),
                    "methodName": $("#methodName").val(),
                    "requestJson": encrypt($("#requestJson").val())
                },
                success: function (data) {
                    console.log(data);
                    document.write(JSON.stringify(data));
                }
            });
        }
    </script>
</header>
<h2>Hello World!</h2>
<form onsubmit="return false" method="post">
    请求Bean:<input id="beanName" name="beanName"/><br><br>
    请求方法:<input id="methodName" name="methodName"/><br><br>
    请求参数:<textarea id="requestJson" name="requestJson"></textarea><br><br>
    <input type="button" onclick="sub()" value="提交">
</form>
</body>
</html>
