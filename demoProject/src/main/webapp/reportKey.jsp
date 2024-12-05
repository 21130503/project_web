<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 12/5/2024
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>

    <link rel="stylesheet" href="./css/login.css">
    <link rel="stylesheet" href="./css/common.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<% String err =(String) request.getAttribute("err") == null ? "" : (String) request.getAttribute("err") ;%>
<div class="inner-report">
    <h1 class="logo">Nhóm 26</h1>
    <h3 class="title">Báo cáo </h3>
    <p style="text-align: center; color: red; font-weight: bold;"><%=err%> </p>
    <div>
        <p style="margin-bottom: 10px">Thời điểm bạn bị mất key: </p>
        <form action="./reportKey" class="form" method="post">
            <div style="width: 100%">
                <label for="date" style="margin-bottom: 4px; display: block">Chọn ngày: </label>
                <input id="date" type="date" placeholder="Email" class="caret" name="date">
            </div>
            <div style="width: 100%">
                <label for="time" style="margin-bottom: 4px;  display: block">Chọn giờ: </label>
                <input id="time" type="time" placeholder="Email" class="caret" name="time">
            </div>
            <button type="submit">Báo cáo</button>
        </form>
    </div>
    <div class="register">
        <div>
            <a class="redirect-home" href="index.jsp"><span><i
                    class="fa-solid fa-angle-left"></i></span><span>Trang chủ</span></a>

        </div>
    </div>
</div>
</body>
</html>
