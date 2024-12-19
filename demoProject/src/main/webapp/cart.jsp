<%@ page import="cart.Cart" %>
<%@ page import="cart.CartProduct" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="nhom26.User" %>
<%@ page import="nhom26.Topic" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="favourite.Favourite" %>
<%@ page import="nhom26.OddImage" %>
<%@ page import="nhom26.Album" %>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
    }

    Locale vnLocal = new Locale("vi", "VN");
    DecimalFormat vndFormat = new DecimalFormat("#,### VNĐ");
%>
<%
    Favourite favourite = (Favourite) session.getAttribute("favourite");
    if (favourite == null) favourite = new Favourite();
%>
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
    <link rel="stylesheet" href="./css/logo.css">
    <link rel="stylesheet" href="./css/common.css">
</head>

<body>

<% User user = (User) session.getAttribute("user");
    ArrayList<Topic> listTopic = request.getAttribute("listTopic") == null ? new ArrayList<>() :
            (ArrayList<Topic>) request.getAttribute("listTopic");
%>


<!-- Start - Phần dùng chung cho các trang dành cho user -->
<!-- Topbar Start -->
<div class="container-fluid">

    <div class="row align-items-center py-3 px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a href="./index" class="text-decoration-none">
                <h1 class="logo">Nhóm 26</h1>
            </a>
        </div>
        <div class="col-lg-6 col-6 text-left">
            <form action="./search" method="get">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Tìm kiếm sản phẩm">
                    <div class="input-group-append">
                        <button type="submit" class="input-group-text bg-transparent text-primary">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-3 col-6 text-right">
            <%-- btn : showKeyList() --%>
            <a href="./userkeys-management" class="btn border">
                <i class="fas fa-key text-primary"></i>
                <span class="badge"></span>
            </a>
            <a href="./favourite" class="btn border">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge"><%=favourite.total()%></span>
            </a>
            <a href="./cart" class="btn border">
                <i class="fas fa-shopping-cart text-primary"></i>
                <span class="badge"><%=cart.total()%></span>
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

            </nav>
        </div>
        <div class="col-lg-9">
            <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                <a href="index" class="text-decoration-none d-block d-lg-none">
                    <h1 class="logo">Nhóm 26</h1>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <a href="index" class="nav-item nav-link">Trang chủ</a>
                        <a href="shop" class="nav-item nav-link">Cửa hàng</a>
                        <a href="donhangcuaban" class="nav-item nav-link">Đơn hàng của bạn</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle active" data-toggle="dropdown">Trang</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="cart" class="dropdown-item active">Giỏ hàng</a>
                                <a href="checkout" class="dropdown-item">Thanh toán</a>
                            </div>
                        </div>
                        <a href="contact" class="nav-item nav-link">Liên hệ</a>
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
                            <a href="./discountAdmin" class="dropdown-item">Quản lí mã giảm giá</a>
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
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Giỏ Hàng</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="index">Trang Chủ</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Giỏ Hàng</p>
        </div>
    </div>
</div>
<!-- Page Header End -->


<!-- Cart Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">

        <div class="col-lg-8 table-responsive mb-5">
            <% if (cart.total() > 0) { %>
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark">

                <%--Thông báo lỗi khi cố giảm số lượng sản phẩm xuống dưới 1--%>
                <% if (session.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-warning">
                    <%= session.getAttribute("errorMessage") %>
                </div>
                <% session.removeAttribute("errorMessage"); %> <%--Xóa thông báo khi tải lại trang--%>
                <% } %>


                <%-- Khi có sản phẩm trong giỏ --%>
                <div class="align-middle" style="display: flex;justify-content: space-between">
                    <div class="cols-md-6 mb-4">
                        <a href="./shop" style="display: flex;justify-content: center">
                            <button class="btn btn-block btn-primary"
                                    style="width: 100%">Mua sắm tiếp
                            </button>
                        </a>
                    </div>
                    <div class="cols-md-6 mb-4">
                        <%--Nút xóa toàn bộ sản phẩm khỏi giỏ hàng --%>
                        <button id="removeAll" class="btn btn-block btn-primary" style="width: 100%" data-toggle="modal"
                                data-target="#deleteCart">
                            Làm trống giỏ hàng
                        </button>
                    </div>
                </div>

                <tr class="align-middle">
                    <th>Sản Phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Thành tiền</th>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tbody class="align-middle">

                <%-- Dữ liệu cho cart --%>
                <%
                    String name = null, type = null, image = null;
                    int id = 0, price = 0, discount = 0;
                %>
                <% for (Map.Entry<String, CartProduct> entry : cart.getData().entrySet()) {
                    CartProduct cartProduct = entry.getValue();
                    if (cartProduct.getObject() instanceof OddImage) {
                        id = ((OddImage) cartProduct.getObject()).getIdOddImage();
                        price = ((OddImage) cartProduct.getObject()).getPrice();
                        discount = ((OddImage) cartProduct.getObject()).getDiscount();
                        name = ((OddImage) cartProduct.getObject()).getName();
                        type = ((OddImage) cartProduct.getObject()).getType();
                        image = ((OddImage) cartProduct.getObject()).getImage();
                    }
                    if (cartProduct.getObject() instanceof Album) {
                        id = ((Album) cartProduct.getObject()).getIdAlbum();
                        price = ((Album) cartProduct.getObject()).getPrice();
                        discount = ((Album) cartProduct.getObject()).getDiscount();
                        name = ((Album) cartProduct.getObject()).getName();
                        type = ((Album) cartProduct.getObject()).getType();
                        image = ((Album) cartProduct.getObject()).getListImage().get(0);
                    }
                %>
                <tr>
                    <td class="text-left"><img class="mr-5" src="<%=image %>" alt="<%=name %>"
                                               style="width: 50px;">
                        <a
                                href="./detail?type=<%=type%>&id=<%=id%>"><%=name%>
                        </a>
                    </td>

                    <td class="align-middle"><%=vndFormat.format(price - discount)%>
                    </td>

                    <td class="align-middle">
                        <div class="input-group quantity mx-auto"
                             style="width: 300px;justify-content: center ; align-items: center">
                            <button value="<%=id%>" title="<%=type%>" class="p-2 border mr-3 decre"
                                    style="cursor: pointer">-
                            </button>
                            <span id="quantity"><%=cartProduct.getQuantity()%></span>
                            <button value="<%=id%>" title="<%=type%>" class="p-2 border ml-3 incre"
                                    style="cursor: pointer">+
                            </button>

                        </div>
                    </td>

                    <td class="align-middle"><%=vndFormat.format(price - discount)%>
                    </td>

                    <td class="align-middle">

                        <button type="submit" id="btnRemove" value="<%=id%>" title="<%=type%>"
                                class="btnRemove btn btn-sm btn-primary">
                            <i class="fa fa-times"></i>
                        </button>

                    </td>

                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <%-- Khi không có sản phẩm trong giỏ --%>
            <div class="">
                <div class="text-center mb-4">
                    <h2 class="section px-5"><span
                    >Bạn chưa mua sản phẩm nào.</span></h2>
                </div>

                <div class="" style="justify-content: center; display: flex">
                    <img class="align-middle" style="width: 16%; margin-top: 40px" src="./asset/remove-from-cart.png"
                         alt="ảnh giỏ hàng">
                </div>

                <div class="text-center" style="margin-top: 40px; ">
                    <a href="./shop" style="display: flex;justify-content: center">
                        <button class="btn btn-block btn-primary my-3 py-3"
                                style="width: 50%">Mua sắm
                        </button>
                    </a>
                </div>

            </div>
            <% } %>
        </div>


        <%-- Xác nhận xóa mã giảm giá --%>
        <script>
            function confirmRemoveDiscount() {
                var result = confirm('Bạn có chắc muốn xóa mã giảm giá này chứ?');
                if (result) {
                    location.href = 'removeDiscount';
                }
            }
        </script>


        <div class="col-lg-4">
            <%-- Thông báo cho việc mã giảm giá bị xóa --%>
            <% String message = (String) session.getAttribute("message"); %>
            <% if (message != null) { %>
            <p style="color: green;"><%= message %>
            </p>
            <% session.removeAttribute("message"); %>
            <% } %>

            <%-- Thông báo cho việc nhập mã giảm giá --%>
            <% String discountError = (String) session.getAttribute("discountError"); %>
            <% String discountSuccess = (String) session.getAttribute("discountSuccess"); %>

            <% if (discountError != null) { %>
            <p style="color: red;"><%= discountError %>
            </p>
            <% session.removeAttribute("discountError"); %>
            <% } %>

            <% if (discountSuccess != null) { %>
            <p style="color: green;"><%= discountSuccess %>
            </p>
            <% session.removeAttribute("discountSuccess"); %>
            <% } %>

            <form action="applyDiscount" method="post">
                <div class="input-group">
                    <input type="text" class="form-control p-4" name="discountCode" placeholder="Mã Giảm Giá">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-primary">Áp Dụng Mã</button>
                    </div>
                </div>
            </form>


            <div class="card border-secondary mb-5">
                <div class="card-header bg-secondary border-0">
                    <h4 class="font-weight-semi-bold m-0">Tóm Tắt Giỏ Hàng</h4>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-between mb-3 pt-1">
                        <h6 class="font-weight-medium">Tổng Tiền Các Sản Phẩm</h6>
                        <h6 class="font-weight-medium"><%=vndFormat.format(cart.totalPrice())%>
                        </h6>
                    </div>

                    <%-- Hiển thị mã giảm giá được áp --%>
                    <% if (cart.getAppliedDiscount() != null) { %>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">Mã <h6 class="font-weight-semi-bold"><%= cart.getAppliedDiscount().getDescription() %></h6> được áp
                            dụng.
                        </h6>
                        <%-- Nút xóa mã giảm giá ra khỏi tổng tiền --%>
                        <button type="button" class="close" aria-label="Close" onclick="confirmRemoveDiscount();">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <% } %>

                </div>
                <div class="card-footer border-secondary bg-transparent">
                    <div class="d-flex justify-content-between mt-2">
                        <h5 class="font-weight-bold">Tổng Cộng</h5>
                        <h5 class="font-weight-bold"><%=vndFormat.format(cart.totalPrice())%>
                        </h5>
                    </div>
                    <a href="checkout">
                        <button class="btn btn-block btn-primary my-3 py-3">Tiến Hành Thanh
                            Toán
                        </button>
                    </a>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- Cart End -->


<!-- Footer Start -->
<div class="container-fluid bg-secondary text-dark mt-5 pt-5">

    <!-- Footer chung cho các trang -->
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
                        <a class="text-dark mb-2" href="index.jsp"><i class="fa fa-angle-right mr-2"></i>Trang
                            chủ</a>
                        <a class="text-dark mb-2" href="shop.jsp"><i class="fa fa-angle-right mr-2"></i>Của
                            hàng</a>
                        <a class="text-dark mb-2" href="donhangcuaban.jsp"><i
                                class="fa fa-angle-right mr-2"></i>Đơn hàng của bạn</a>
                        <a class="text-dark mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Giỏ
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
    <!-- Footer chung cho các trang -->

</div>
<!-- Footer End -->


<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>
<%--Confirm delete--%>
<div id="deleteCart" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Xóa toàn bộ giỏ hàng</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Bạn có chắc chắn muốn xóa toàn bộ không ? </p>
            </div>
            <div class="modal-footer">
                <button id="btn-delete-cart" type="button" class="btn btn-danger">Xóa</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>


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
<script src="js/removeSession.js"></script>
<script> removeSession("cart")</script>
<script>
    const btnRemoveAll = document.querySelector("#btn-delete-cart");
    btnRemoveAll.addEventListener("click", () => {
        const xhr = new XMLHttpRequest();
        const url = `http://localhost:8080/demoProject_war/cart`;
        xhr.open("DELETE", url, true);

        xhr.onload = function () {
            if (xhr.status === 200) {
                const data = JSON.parse(xhr.responseText);
                alert(data.message);
                location.reload();
            } else if (xhr.status === 500) {
                const data = JSON.parse(xhr.responseText);
                alert(data.message);
                // window.location.href="http://localhost:8080/demoProject_war/favourite"
            }
        };

        xhr.send();
    })
</script>
<script>
    const inrceArr = Array.from(document.querySelectorAll(".incre"))
    const derceArr = Array.from(document.querySelectorAll(".decre"))

    const quantity = document.querySelector("#quantity");
    let value = +quantity.innerHTML;
    inrceArr.forEach((inrce) => {
        inrce.addEventListener("click", () => {
            value++;
            quantity.innerHTML = value;
            const type = inrce.title;
            const id = inrce.value
            const xhr = new XMLHttpRequest();
            const url = `http://localhost:8080/demoProject_war/cart?type=${type}&idProduct=${id}`;

            xhr.open("POST", url, true);

            xhr.onload = function () {
                if (xhr.status === 200) {
                    const data = JSON.parse(xhr.responseText);
                    alert(data.message);
                    location.reload();
                } else if (xhr.status === 500) {
                    const data = JSON.parse(xhr.responseText);
                    alert(data.message);
                }
            };

            xhr.send();
        })
    })
    derceArr.forEach((derce) => {
        derce.addEventListener("click", () => {
            if (value <= 1) {
                return;
            }
            value--;

            quantity.innerHTML = value;
            const type = derce.title;
            const id = derce.value
            const xhr = new XMLHttpRequest();
            const url = `http://localhost:8080/demoProject_war/delete-cart-item?type=${type}&idProduct=${id}`;

            xhr.open("POST", url, true);

            xhr.onload = function () {
                if (xhr.status === 200) {
                    const data = JSON.parse(xhr.responseText);
                    alert(data.message);
                    location.reload();
                } else if (xhr.status === 500) {
                    const data = JSON.parse(xhr.responseText);
                    alert(data.message);
                }
            };

            xhr.send();
        })
    })
</script>
</body>

</html>