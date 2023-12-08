<!doctype html>
<html lang="en">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Đăng kí</title>
    <link rel="stylesheet" href="./css/register.css">
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/forgot.css">
</head>

<body>
<%
    String invalidateCode = request.getAttribute("invalidateCode") == null ? "" :(String) request.getAttribute("invalidateCode");
    String codeNotSame = request.getAttribute("codeNotSame") == null ? "" :(String) request.getAttribute("codeNotSame");
    String invalidatePassword = request.getAttribute("invalidatePassword") == null ? "" :(String) request.getAttribute("invalidatePassword");
    String invalidateConfimPassword = request.getAttribute("invalidateConfimPassword") == null ? "" :(String) request.getAttribute("invalidateConfimPassword");
    String invalidatePasswordLength = request.getAttribute("invalidatePasswordLength") == null ? "" :(String) request.getAttribute("invalidatePasswordLength");
    String invalidateConfimPasswordWithPass = request.getAttribute("invalidateConfimPasswordWithPass") == null ? "" :(String) request.getAttribute("invalidateConfimPasswordWithPass");



%>
<div class="inner-forgot">
    <h1 class="logo">Nhóm 26</h1>
    <h3 class="title">Đặt lại mật khẩu</h3>
    <p class="description" style="line-height: 1.6">Chúng tôi đã gửi mã xác thực cho bạn. Vui lòng kiểm tra và điền vào trường phía dưới</p>
    <form action="./forgot" method="post" class="form" id="form">
        <div class="form-group">
            <input type="number" id="code" placeholder="Mã xác thực" class="caret" name="code">
            <span class="show-message"><%=invalidateCode%></span>
            <span class="show-message"><%=codeNotSame%></span>
        </div>
        <div class="form-group">
            <input id="password" name="password" type="password" placeholder="Nhập mật khẩu" class="caret">
            <span class="show-message"><%= invalidatePassword%></span>
            <span class="show-message"><%= invalidatePasswordLength%></span>
        </div>
        <div class="form-group">
            <input type="password" placeholder="Nhập lại mật khẩu" class="caret" id="password_confirmation" name="password_confirmation">
            <span class="show-message"><%= invalidateConfimPassword%></span>
            <span class="show-message"><%= invalidateConfimPasswordWithPass%></span>
        </div>
        <div class="bt_sign_up">
            <button type="submit">Tiếp tục</button>
    </div>
    </form>


</div>
<%--<script src="./js/formValidation.js"></script>--%>
<%--<script>--%>
<%--    Validator({--%>
<%--        form: '#form',--%>
<%--        errorSelector:'.show-message',--%>
<%--        rules:[--%>
<%--            Validator.isRequired('#email','Vui lòng nhập trường này'),--%>
<%--            Validator.isEmail('#email','Trường này phải là email'),--%>
<%--            Validator.isRequired('#password','Vui lòng nhập trường này'),--%>
<%--            Validator.minLength('#password',6),--%>
<%--            Validator.isRequired('#password_confirmation','Vui lòng nhập trường này'),--%>
<%--            Validator.isConfirmed('#password_confirmation',function(){--%>
<%--              return document.querySelector('#form #password').value;--%>
<%--            },'Mật khẩu nhập lại không chính xác')--%>

<%--        ],--%>
<%--        onSubmit:(data)=>{--%>
<%--          console.log(data);--%>
<%--        }--%>
<%--    })--%>
<%--</script>--%>
</body>

</html>