<%--
  Created by IntelliJ IDEA.
  User: dfll
  Date: 2020/6/26
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/jquery/jquery-3.5.1.js"></script>
</head>
<body>

<div class="container" style="text-align: center;">
    <h3>系统信息页面</h3>
    <h4>${requestScope.exception.message }</h4>
    <button style="width: 300px;margin: 0px auto 0px auto;" class="btn btn-lg btn-success btn-block">返回刚才页面</button>
</div>

<script type="text/javascript">
    $(function(){
        $("button").click(function(){
            // 调用 back()方法类似于点击浏览器的后退按钮
            window.history.back();
        });
    });
</script>

</body>
</html>
