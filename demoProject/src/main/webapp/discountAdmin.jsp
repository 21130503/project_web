<%@ page import="nhom26.User" %>
<%@ page import="java.util.List" %>
<%@ page import="nhom26.Discount" %>

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="vi">

<%-- Kiểm tra và chỉ cho phép admin mới có thể truy cập vào trang quản lí này  --%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp"); // Chuyển hướng người dùng chưa đăng nhập
        return;
    } else if (!user.isAdmin()) {
        response.sendRedirect("404.jsp"); // Chuyển hướng người dùng không phải admin
        return;
    }
%>

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Quản lí mã giảm giá || Admin</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.6.1/axios.min.js"
            integrity="sha512-m9S8W3a9hhBHPFAbEIaG7J9P92dzcAWwM42VvJp5n1/M599ldK6Z2st2SfJGsX0QR4LfCVr681vyU5vW8d218w=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/logo.css">
    <link rel="stylesheet" href="./css/common.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>

<body>

<!-- Topbar Start -->
<div class="container-fluid">
    <div class="row align-items-center py-3 px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a href="./index" class="text-decoration-none">
                <h1 class="logo">Nhóm 26</h1>
            </a>
        </div>
        <div class="col-lg-6 col-6 text-left">
            <form action="">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm">
                    <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-3 col-6 text-right">
            <a href="./topic" class="btn border">
                <i class="fa-solid fa-boxes-stacked text-primary"></i>
            </a>
            <a href="./order" class="btn border">
                <i class="fas fa-shopping-cart text-primary"></i>
            </a>
            <a href="./user" class="btn border">
                <i class="fa-regular fa-user text-primary"></i>
            </a>
            <a href="./product" class="btn border">
                <i class="fa-brands fa-product-hunt text-primary"></i>
            </a>
            <a href="./discountAdmin" class="btn border" title="Quản lí mã giảm giá">
                <i class="fa-solid fa-tag" style="color: #D19C97"></i>
            </a>
        </div>
    </div>
</div>
<!-- Topbar End -->


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Quản Lý Mã Giảm Giá</h1>
    </div>
</div>
<!-- Page Header End -->


<%-- Lấy dữ liệu từ DiscountAdminController qua --%>
<% List<Discount> discountList = (List<Discount>) request.getAttribute("discountList"); %>

<!-- DiscountAdmin Start -->
<div class="container-fluid pt-5" style="margin: auto; width: 90%;">
    <h2 style="margin-bottom: 25px">Các Mã Giảm Giá Hiện Có: </h2>

    <%-- Hiển thị thông báo Xóa mã thành công --%>
    <% if (session.getAttribute("deleteSuccess") != null) { %>
    <div class="alert alert-success">
        <%= session.getAttribute("deleteSuccess") %>
    </div>
    <% session.removeAttribute("deleteSuccess"); %>
    <% } %>

    <%-- Hiển thị thông báo Cập nhật thuộc tính và Tạo mã thành công --%>
    <% if (session.getAttribute("discountSuccess") != null) { %>
    <div class="alert alert-success">
        <%= session.getAttribute("discountSuccess") %>
    </div>
    <% session.removeAttribute("discountSuccess"); %>
    <% } %>


    <table class="table">
        <thead>
        <tr>
            <th>Mã</th>
            <th>Mô Tả</th>
            <th>Giá Trị Giảm</th>
            <th>Ngày Hết Hạn</th>
            <th>Còn Lại</th>
            <th>Hành Động Khác</th>
        </tr>
        </thead>

        <%-- Code hiển thị các mã giảm giá --%>
        <% if (discountList != null && !discountList.isEmpty()) { %>
        <tbody>
        <% for (Discount discount : discountList) { %>
        <tr>
            <td><%= discount.getCode() %>
            </td>
            <td><%= discount.getDescription() %>
            </td>
            <td><%= String.format("%.0f%%", discount.getDiscountValue() * 100.0) %> Tổng hóa đơn
            </td>
            <td><%= discount.getExpiryDate() != null ? discount.getExpiryDate().toString() : "" %>
            </td>
            <td><%= discount.getCount() %>
            </td>
            <td>
                <a href="editDiscount?code=<%= discount.getCode() %>" class="btn btn-primary">Sửa thuộc tính</a>

                <button class="btn btn-danger" onclick="confirmDelete('<%= discount.getCode() %>')">Xóa</button>
            </td>
        </tr>
        <% } %>
        </tbody>
        <% } else { %>
        <tbody>
        <tr>
            <td colspan="5">Hiện tại không có mã giảm giá nào.</td>
        </tr>
        </tbody>
        <% } %>
    </table>
</div>

<h2 style="padding-bottom: 30px; margin: auto; width: 90%;">Thêm mã giảm giá mới:</h2>

<%-- Hiển thị thông báo lỗi --%>
<% if (session.getAttribute("errorMessage") != null) { %>
<div class="alert alert-warning">
    <%= session.getAttribute("errorMessage") %>
</div>
<% session.removeAttribute("errorMessage"); %>
<% } %>


<%-- Form tạo ra mã giảm giá mới --%>
<form action="discountAdmin" method="post" style="margin: auto; width: 90%;">
    <input type="hidden" name="action" value="addDiscount" required>
    <input type="text" name="code" placeholder="Xin nhập mã từ 001-999" required lang="vi">
    <input type="text" name="description" placeholder="Mô tả - ví dụ: Giảm giá 10%" required lang="vi">
    <input type="number" name="discountValue" placeholder="Giá trị giảm - ví dụ: 10" min="1" max="100" required
           lang="vi">
    <input type="date" name="expiryDate" placeholder="Ngày hết hạn" required lang="vi">
    <input type="number" name="count" placeholder="Số lần sử dụng còn lại" min="0" required lang="vi">
    <input type="submit" class="btn btn-success" value="Thêm Mã Mới">
</form>

<!-- DiscountAdmin End -->


<!-- Footer Start -->
<div class="container-fluid bg-secondary text-dark mt-5 pt-5">
    <div class="row px-xl-5 pt-5">
        <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
            <a href="" class="text-decoration-none">
                <h1 class="logo" style="height: 60px; text-align: start; margin-top: -16px;">Nhóm 26</h1>
            </a>
            <p>Shop Nhóm 26 - Điểm đến đáng tin cậy cho các loại ảnh bản quyền, với sự đa dạng và phong phú trong
                tất cả các thể loại. Khi bạn cần ảnh bản quyền. Hãy nhớ "Cần ảnh bản quyền đến với Shop Nhóm 26".
            </p>
            <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>ĐH Nông Lâm HCM, Tp.Thủ Đức</p>
            <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>nhom26@gmail.com</p>
            <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+010 345 67890</p>
        </div>
        <div class="col-lg-8 col-md-12">
            <div class="row">
                <div class="col-md-6 mb-5" style="padding-left: 70px;">
                    <h5 class="font-weight-bold text-dark mb-4">Di Chuyển Nhanh</h5>
                    <div class="d-flex flex-column justify-content-start">
                        <a class="text-dark mb-2" href="./index"><i class="fa fa-angle-right mr-2"></i>Trang
                            chủ</a>
                        <a class="text-dark mb-2" href="./shop"><i class="fa fa-angle-right mr-2"></i>Của
                            hàng</a>
                        <a class="text-dark mb-2" href="./donhangcuaban"><i
                                class="fa fa-angle-right mr-2"></i>Đơn hàng của bạn</a>
                        <a class="text-dark mb-2" href="./cart"><i class="fa fa-angle-right mr-2"></i>Giỏ
                            hàng</a>
                        <a class="text-dark mb-2" href="./checkout"><i class="fa fa-angle-right mr-2"></i>Thanh
                            toán</a>
                        <a class="text-dark" href="./contact"><i class="fa fa-angle-right mr-2"></i>Liên hệ</a>
                    </div>
                </div>
                <div class="col-md-6 mb-5">
                    <h5 class="font-weight-bold text-dark mb-4">Đăng ký mới</h5>
                    <form action="">
                        <div class="form-group">
                            <input type="text" class="form-control border-0 py-4" placeholder="Tên của bạn"
                                   required="required"/>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control border-0 py-4" placeholder="Email của bạn"
                                   required="required"/>
                        </div>
                        <div>
                            <button class="btn btn-primary btn-block border-0 py-3" type="submit">Đăng ký
                                ngay!
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer End -->


<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

<%-- Mã js để xóa mã giảm giá --%>
<script src="./js/discountDelete.js"></script>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>


<!-- Contact Javascript File -->
<script src="mail/jqBootstrapValidation.min.js"></script>
<script src="mail/contact.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>
<script src="js/topic.js"></script>
</body>

</html>