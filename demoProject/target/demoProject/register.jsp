<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>Đăng kí</title>
    <link rel="stylesheet" href="./css/register.css">
    <link rel="stylesheet" href="./css/common.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.6.1/axios.min.js"
            integrity="sha512-m9S8W3a9hhBHPFAbEIaG7J9P92dzcAWwM42VvJp5n1/M599ldK6Z2st2SfJGsX0QR4LfCVr681vyU5vW8d218w=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>

</head>

<body>
<%
    String invalidateEmail = (String) request.getAttribute("invalidateEmail") == null ? "" : (String) request.getAttribute("invalidateEmail");
    String invalidateUserName = (String) request.getAttribute("invalidateUsername") == null ? "" : (String) request.getAttribute("invalidateUserName");
    String invalidatePassword = (String) request.getAttribute("invalidatePassword") == null ? "" : (String) request.getAttribute("invalidatePassword");
    String invalidateConfimPassword = (String) request.getAttribute("invalidateConfimPassword") == null ? "" : (String) request.getAttribute("invalidateConfimPassword");
%>
<div class="inner">
    <h1 class="logo">Nhóm 26</h1>
    <h3 class="title">Đăng kí</h3>
    <form action="./register" method="post" class="form" id="form">
        <div class="form-group">
            <input type="email" placeholder="Email" class="caret" name="email">
            <span class="show-message"><%=invalidateEmail%></span>
        </div>
        <div class="form-group">
            <input type="text" placeholder="Tên người dùng" class="caret" name="username">
            <span class="show-message"><%=invalidateUserName%></span>
        </div>
        <div class="form-group">
            <input name="password" type="password" placeholder="Nhập mật khẩu" class="caret">
            <span class="show-message"><%=invalidatePassword%></span>
        </div>
        <div class="form-group">
            <input type="password" placeholder="Nhập lại mật khẩu" class="caret"
                   name="password_confirmation">
            <span class="show-message"><%=invalidateConfimPassword%></span>
        </div>
        <div class="bt_sign_up">
            <button type="submit">Đăng kí</button>
        </div>
    </form>
    <div class="athoner-register">
        <span>Hoặc đăng kí với</span>
        <ul class="list">
            <li class="item"><a href="" class="link">
                <fb:login-button scope="public_profile,email" onlogin="checkLoginState();"></fb:login-button>
            </a></li>
            <li class="item"><a href="" class="link">
                <img src="./asset/gg.png" alt="" class="img-logo">
            </a></li>
        </ul>


    </div>
    <div class="signin mt-8">
        <span>Bạn đã có tài khoản ?</span>
        <a href="login.jsp" class="none_decorate">Đăng nhập</a>
    </div>

    <%--    <script src="./js/formValidation.js"></script>--%>
    <%--    <script>--%>
    <%--        const onSubmit = async (dataSub) => {--%>

    <%--            // const data =  await  axios.post("http://localhost:8080/demoProject_war/register", dataSub)--%>
    <%--            //   console.log(dataSub)--%>
    <%--            const xhr = new XMLHttpRequest();--%>
    <%--            xhr.open("POST", "http://localhost:8080/demoProject_war/register")--%>
    <%--            xhr.setRequestHeader("Content-Type", "application/json")--%>
    <%--            xhr.onreadystatechange = () => {--%>
    <%--                if (xhr.readyState === XMLHttpRequest.DONE) {--%>
    <%--                    if (xhr.status === 200) {--%>
    <%--                        const respone = JSON.parse(xhr.responseText);--%>
    <%--                        if (respone.status == 200) {--%>
    <%--                            window.onload = window.location.href = "http://localhost:8080/demoProject_war/login.jsp"--%>
    <%--                        } else {--%>
    <%--                            alert(respone.message)--%>
    <%--                        }--%>
    <%--                    } else {--%>
    <%--                        // alert("Thất bại !")--%>
    <%--                    }--%>
    <%--                }--%>
    <%--            }--%>
    <%--            xhr.send(JSON.stringify(dataSub))--%>
    <%--        }--%>
    <%--        Validator({--%>
    <%--            form: '#form',--%>
    <%--            errorSelector: '.show-message',--%>
    <%--            rules: [--%>
    <%--                Validator.isRequired('#fullname', 'Vui lòng nhập trường này'),--%>
    <%--                Validator.isRequired('#email', 'Vui lòng nhập trường này'),--%>
    <%--                Validator.isEmail('#email', 'Trường này phải là email'),--%>
    <%--                Validator.isRequired('#password', 'Vui lòng nhập trường này'),--%>
    <%--                Validator.minLength('#password', 6),--%>
    <%--                Validator.isRequired('#password_confirmation', 'Vui lòng nhập trường này'),--%>
    <%--                Validator.isConfirmed('#password_confirmation', function () {--%>
    <%--                    return document.querySelector('#form #password').value;--%>
    <%--                }, 'Mật khẩu nhập lại không chính xác')--%>

    <%--            ],--%>
    <%--            onSubmit: (data) => {--%>
    <%--                console.log(data);--%>
    <%--                onSubmit(data)--%>
    <%--            }--%>
    <%--        })--%>
    <%--    </script>--%>
    <script>
        window.fbAsyncInit = function() {
            FB.init({
                appId      : '1288411131843645',
                cookie     : true,
                xfbml      : true,
                version    : 'v13.0'
            });

            FB.AppEvents.logPageView();
        };
    </script>
    <script>
        function checkLoginState() {
            FB.getLoginStatus(function(response) {
                statusChangeCallback(response);
            });
        }

        function statusChangeCallback(response) {
            if (response.status === 'connected') {
                // Người dùng đã đăng nhập vào ứng dụng và đã cho phép
                // Truy cập thông tin với quyền được yêu cầu.
                console.log('Logged in and authenticated');
                getUserInfo();
            } else {
                // Người dùng chưa đăng nhập vào ứng dụng hoặc đã hủy đăng nhập.
                console.log('Not authenticated');
            }
        }

        function getUserInfo() {
            FB.api('/me', { fields: 'id,name,email' }, function(response) {
                console.log('User Info:', response);
                // Gửi thông tin người dùng đến máy chủ để xử lý đăng ký hoặc đăng nhập.
                // Implement logic for registration or login here.
            });
        }
    </script>

</body>

</html>