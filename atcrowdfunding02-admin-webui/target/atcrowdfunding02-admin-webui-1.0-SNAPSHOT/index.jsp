<%--
  Created by IntelliJ IDEA.
  User: dfll
  Date: 2020/6/24
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
    <script src="static/jquery/jquery-3.5.1.js" type="text/javascript"></script>
</head>
<body>
    <a href="admin/logout">退出用户</a>
    <a href="test/ssm/html">测试ssm整合环境</a>
    ${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }

    <%--测试ajax--%>
    <button class="ajax1">测试1</button>

    <script>

        var admin = {
            "id": 1
        };

        var jsonString = JSON.stringify(admin);

        $(function () {
            $('.ajax1').click(function () {
            //    发送ajax请求
                $.ajax({
                        url:'test/testAjax.json',
                        type:'post',
                        data: jsonString,
                        //发送到服务器时，所使用的内容类型
                        contentType:'application/json;charset=UTF-8',
                        //预期的服务器的响应类型
                        dataType:'json',
                        success:function (data) {
                            console.log(data);
                        }
                    }
                );
            });
        });
    </script>
</body>
</html>
