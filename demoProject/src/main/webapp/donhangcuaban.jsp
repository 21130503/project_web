<%@ page import="nhom26.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="nhom26.Topic" %>
<%@ page import="nhom26.Order" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<%--Dòng dưới để hiện lên theo charset UTF-8--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <%--Dòng dưới để hiện lên theo charset UTF-8--%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>Nhóm 26</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

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
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="/css/logo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>

<body>

<% User user = (User) session.getAttribute("user");
    ArrayList<Topic> listTopic = request.getAttribute("listTopic") == null ? new ArrayList<>() :
            (ArrayList<Topic>) request.getAttribute("listTopic");
    ArrayList<Order> listOrder = request.getAttribute("Order") == null ? new ArrayList<>() : (ArrayList<Order>) request.getAttribute("Order");
    ArrayList<Order> listOrderAlbum = request.getAttribute("OrderAlbum") == null ? new ArrayList<>() : (ArrayList<Order>) request.getAttribute("OrderAlbum");
%>
<%
    Locale vnLocal = new Locale("vi", "VN");
    DecimalFormat vndFormat = new DecimalFormat("#,### VNĐ");
%>
<!-- Start - Phần dùng chung cho các trang dành cho user -->
<!-- Topbar Start -->
<div class="container-fluid">
    <div class="row align-items-center py-3 px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
<<<<<<< HEAD
            <a href="index" class="text-decoration-none">
=======
            <a href="./index" class="text-decoration-none">
>>>>>>> 7d988eedcb7c20d06c678525c619cbcc9a97c9be
                <h1 class="logo">Nhóm 26</h1>
            </a>
        </div>
        <div class="col-lg-6 col-6 text-left">
            <form action="">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm">
                    <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary" title="Tìm kiếm">
                                <i class="fa fa-search"></i>
                            </span>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-3 col-6 text-right">
            <a href="favourite.jsp" class="btn border" title="Yêu thích">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge">0</span>
            </a>
            <a href="cart.jsp" class="btn border" title="Giỏ hàng">
                <i class="fas fa-shopping-cart text-primary"></i>
                <span class="badge">0</span>
            </a>
        </div>
    </div>
</div>
<!-- Topbar End -->


<!-- Navbar Start -->
<div class="container-fluid">
    <div class="row border-top px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100"
               data-toggle="collapse" href="#navbar-vertical"
               style="height: 65px; margin-top: -1px; padding: 0 30px;">
                <h6 class="m-0">Danh mục</h6>
                <i class="fa fa-angle-down text-dark"></i>
            </a>
            <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light"
                 id="navbar-vertical" style="width: calc(100% - 30px); z-index: 1;">
                <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">

                    <%--Phần danh mục hiển thị các chủ đề--%>
                    <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
                        <%if (listTopic.size() == 0) {%>
                        <p>Chưa có topic nào</p>
                        <%} else {%>
                        <%for (Topic topic : listTopic) {%>
                        <a href="/topic?q=<%=topic.getName()%>" class="nav-item nav-link"><%=topic.getName()%>
                        </a>
                        <%}%>
                        <%}%>
                    </div>

                </div>
            </nav>
        </div>
        <div class="col-lg-9">
            <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
<<<<<<< HEAD
<<<<<<< HEAD
                <a href="index" class="text-decoration-none d-block d-lg-none">
=======
                <a href="./index" class="text-decoration-none d-block d-lg-none">
>>>>>>> d68c03236a8fdf27d2a78f702eab60d762eb0701
=======
                <a href="./index" class="text-decoration-none d-block d-lg-none">
>>>>>>> 7d988eedcb7c20d06c678525c619cbcc9a97c9be
                    <h1 class="logo">Nhóm 26</h1>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <a href="index" class="nav-item nav-link">Trang chủ</a>
                        <a href="shop" class="nav-item nav-link">Cửa hàng</a>
                        <a href="donhangcuaban" class="nav-item nav-link active">Đơn hàng của bạn</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Trang</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="cart" class="dropdown-item">Giỏ hàng</a>
                                <a href="checkout" class="dropdown-item">Thanh toán</a>
                            </div>
                        </div>
                        <a href="contact" class="nav-item nav-link ">Liên hệ</a>
                    </div>

                    <%--Phần login--%>
                    <%if (user == null) {%>
                    <div class="navbar-nav ml-auto py-0">
                        <a href="login.jsp" class="nav-item nav-link">Đăng nhập</a>
                        <a href="register.jsp" class="nav-item nav-link">Đăng ký</a>
                    </div>
                    <%} else { %>
                    <div class="navbar-nav ml-auto py-0 position-relative">
                        <p class="nav-link dropdown-toggle m-0" data-toggle="dropdown">Hi, <%= user.getUsername()%>
                        </p>
                        <div class="dropdown-menu rounded-0 m-0">
                            <%if (!user.isVerifyEmail()) {%>
                            <a href="./verify" class="dropdown-item">Xác thực email của bạn</a>
                            <%}%>
                            <% if (user.isAdmin()) {%>
                            <a href="./topic" class="dropdown-item">Quản lí chủ đề</a>
                            <a href="./product" class="dropdown-item">Quản lí sản phẩm</a>
                            <a href="./order" class="dropdown-item">Quản lí đơn hàng</a>
                            <a href="./user" class="dropdown-item">Quản lí người dùng</a>
                            <%}%>
                            <button class="dropdown-item" id="logout">Đăng xuất</button>
                        </div>
                    </div>
                    <%}%>

                </div>
            </nav>
        </div>
    </div>
</div>
<!-- Navbar End -->

<!-- End - Phần dùng chung cho các trang dành cho user -->


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Đơn hàng của bạn</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="index.jsp">Trang chủ</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Đơn hàng của bạn</p>
        </div>
    </div>
</div>
<!-- Page Header End -->


<!-- Đơn hàng của bạn Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <div class="col-lg-0 table-responsive mb-5">
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark">
                <tr>
                    <th>Mã đơn hàng</th>
                    <th>Tên đơn hàng</th>
                    <th>Số lượng</th>
                    <th>Tên người nhận</th>
                    <th>Số điện thoại</th>
                    <th>Ngày đặt hàng</th>
                    <th>Trạng thái</th>
                    <th>Địa chỉ nhận hàng</th>
                    <th>Thành tiền</th>
                    <th>Hủy đơn</th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <%if(listOrder.size() == 0){%>

                <%}else{%>
                <%for(Order order : listOrder){%>
                    <tr>
                        <td class="align-middle"><%= order.getIdOrder()%></td>
                        <td class="align-middle"><a href="./detail?type=<%=order.getType()%>&id=<%=order.getIdProduct()%>"><%= order.getNameProduct()%></a></td>
                        <td class="align-middle"><%= order.getQuantity()%></td>
                        <td class="align-middle"><%= order.getReceiver()%></td>
                        <td class="align-middle"><%= order.getPhoneNumber()%></td>
                         <td class="align-middle">
                              <%= order.getPurchareDate()%>
                        </td>
                        <td class="align-middle">
                            <%= order.getStatus()%>
                        </td>
                        <td class="align-middle td-address" title="<%= order.getAddress()%>"><%= order.getAddress()%></td>
                        <td><%=vndFormat.format(order.getTotalPrice())%></td>
                        <td class="align-middle">
                            <button data-id="<%=order.getIdOrder()%>" data-toggle="modal" data-target="#deleteOrder"  class="btn btn-sm btn-primary"><i
                                    class="fa fa-times"></i></button>
                        </td>
                </tr>
                <%}%>
               <%}%>


                </tbody>
            </table>
        </div>

    </div>
</div>
<!-- Đơn hàng của bạn End -->


<!-- Footer Start -->
<div class="container-fluid bg-secondary text-dark mt-5 pt-5">
    <div class="row px-xl-5 pt-5">
        <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
            <a href="index.jsp" class="text-decoration-none">
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
                        <a class="text-dark mb-2" href="index.jsp"><i class="fa fa-angle-right mr-2"></i>Trang
                            chủ</a>
                        <a class="text-dark mb-2" href="shop.jsp"><i class="fa fa-angle-right mr-2"></i>Của
                            hàng</a>
                        <a class="text-dark mb-2" href="donhangcuaban.html"><i
                                class="fa fa-angle-right mr-2"></i>Đơn hàng của bạn</a>
                        <a class="text-dark mb-2" href="cart.jsp"><i class="fa fa-angle-right mr-2"></i>Giỏ
                            hàng</a>
                        <a class="text-dark mb-2" href="checkout.jsp"><i class="fa fa-angle-right mr-2"></i>Thanh
                            toán</a>
                        <a class="text-dark" href="contact.jsp"><i class="fa fa-angle-right mr-2"></i>Liên hệ</a>
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
<div id="deleteOrder" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Hủy đơn hàng</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Bạn có chắc chắn muốn hủy đơn hàng ? </p>
            </div>
            <div class="modal-footer">
                <button id="btn-delete-order" type="button" class="btn btn-danger">Xóa</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>

<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

<script src="js/Dialog.js"></script>
<script>
    Dialog("#deleteOrder","#btn-delete-order","/order/odd","idOrder", "delete")
    Dialog()
</script>

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
</body>

</html>