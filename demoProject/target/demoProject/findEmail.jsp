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
<% String err = request.getAttribute("err")== null ? "" :(String) request.getAttribute("err");
    String emailNotFound = request.getAttribute("email-not-found")== null ? "" :(String) request.getAttribute("email-not-found");
%>
<div class="inner-forgot">
    <h1 class="logo">Nhóm 26</h1>
    <h3 class="title">Tìm kiếm email</h3>
    <form action="./findemail" method="post" class="form" id="form">
        <div class="form-group">
            <input type="email" id="email" placeholder="Email" class="caret" name="email">
            <span class="show-message"><%= err%></span>
            <span class="show-message"><%=emailNotFound%></span>
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