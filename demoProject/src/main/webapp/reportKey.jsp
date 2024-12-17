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
    <title>Report PrivateKey</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
<%--    <h1 class="logo">BÁO MẤT PRIVATE KEY</h1>--%>
    <h3 class="title">BÁO MẤT PRIVATE KEY</h3>
    <p style="text-align: center; color: red; font-weight: bold;"> <%=err%> </p>
    <div>
<%--        <p style=" margin-bottom: 10px ">Thời điểm lộ PrivateKey : </p>--%>
        <form action="./report-priKey?action=process-input" class="form" method="post">
            <div style="width: 100%">
                <label for="email" style="margin-bottom: 4px; display: block">Xác minh Email của bạn : </label>
                <input id="email" type="email" placeholder="Nhập email đã đăng kí" class="caret" name="email" required>
            </div>
            <div style="width: 100%">
                <label for="password" style="margin-bottom: 4px; display: block">Nhập mật khẩu : </label>
                <input id="password" type="password" placeholder="Nhập mật khẩu của bạn" class="caret" name="password" required>
            </div>
            <div style="width: 100%">
                <label for="date" style="margin-bottom: 4px; display: block">Ngày lộ PrivateKey : </label>
                <input id="date" type="date" placeholder="Chọn ngày" class="caret" name="date" required>
            </div>
            <div style="width: 100%">
                <label for="time" style="margin-bottom: 4px; display: block">Xác định rõ thời gian lộ : </label>
                <input id="time" type="time" placeholder="Chọn giờ" class="caret" name="time" required>
            </div>
            <div style="width: 100%">
                <label for="reason" style="margin-bottom: 4px; display: block">Nhập lí do lộ Key ( không bắt buộc ) : </label>
                <textarea id="reason" placeholder="Nhập lý do nếu có" class="caret" name="reason" style="width: 100%; height: 80px;"></textarea>
            </div>
            <button type="submit">Báo cáo</button>
        </form>

    </div>
    <div class="register">
        <div>
            <a class="redirect-home" href="./index"><span><i
                    class="fa-solid fa-angle-left"></i></span><span>Về trang chủ</span></a>
        </div>
    </div>
</div>
</body>
</html>
