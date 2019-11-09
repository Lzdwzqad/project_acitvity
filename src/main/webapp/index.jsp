<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<header>
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/aes.js"></script>
    <script type="text/javascript" src="js/encryption.js"></script>

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
        function sub() {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "gateway",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: {
                    "beanName": $("#beanName").val(),
                    "methodName": $("#methodName").val(),
                    "requestJson": encrypt($("#requestJson").val())
                },
                success: function (data) {
                    alert(JSON.stringify(data));
                }
            });
        }
    </script>
</header>
<h2>请求测试</h2>
<form onsubmit="return false" method="post">
    <fieldset>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="beanName">Bean路径:</label>
            <div class="col-sm-4">
                <input class="form-control" id="beanName" type="text" value="alifenga.xyz.scheduleTaskHandle"/>
            </div>
            <label class="col-sm-2 control-label" for="methodName">请求方法:</label>
            <div class="col-sm-4">
                <input class="form-control" id="methodName" type="text"/>
            </div>
            <label class="col-sm-2 control-label" for="requestJson">请求参数:</label>
            <div class="col-sm-4">
                <textarea rows="5" cols="20" class="form-control" id="requestJson" type="text">{name:'张三',age:20,sex:'男'}</textarea>
            </div>
        </div>
    </fieldset>
    <input type="button" class="btn btn-success" onclick="sub()" value="提交">
</form>
</body>
</html>
