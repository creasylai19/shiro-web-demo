<%--
  Created by IntelliJ IDEA.
  User: laicreasy
  Date: 2020/5/20
  Time: 01:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <h3>请登录</h3>
<form action="/user/login" method="post">
    <label>
        用户名
        <input type="text" name="username"/>
    </label><br/>

    <label>
        密码
        <input type="password" name="password"/>
    </label><br/>
    <input type="submit" value="登录"/>
</form>

</body>
</html>
