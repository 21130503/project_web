<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xác thực email của bạn</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="./css/register.css">
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/verify.css">
</head>
<body>
<%
    String err = request.getAttribute("err") == null ? "" : (String) request.getAttribute("err");
%>
<div class="inner-verify">
    <h1 class="logo">Nhóm 26</h1>
    <h3 class="title">Xác thực Email của bạn</h3>
    <div class="code">
        <p class="code-content">
        </p>
    </div>
    <form action="./verify" method="post" class="form" id="form">
        <div class="form-group">
            <input type="number" placeholder="Nhập mã xác thực" class="caret" name="verify">
            <span class="show-message"><%=err%></span>
        </div>
        <div class="bt_sign_up">
            <button type="submit">Xác thực</button>
        </div>
    </form>
</div>
</body>
</html>
