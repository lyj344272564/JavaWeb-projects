<%--
  Created by IntelliJ IDEA.
  User: 34427
  Date: 2021-08-09
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<%--
    表单提交必须是POST ,
    表单的enctype属性:必须设置为   multipart/form-data.
    input的type类型必须指定为: file, 一定要有name属性
    --%>
<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="upload">
    <br>
    <input type="text" name="name">
    <input type="text" name="password">
    <input type="submit" value="文件上传">
    <img src="/upload/9c08303afc474f33a7fad7862c1d4d8e_课程模块的表设计与分析.png">
</form>


</body>
</html>
