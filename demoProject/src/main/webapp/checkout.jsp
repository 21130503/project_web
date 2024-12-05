<%@ page import="nhom26.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="nhom26.Topic" %>
<%@ page import="cart.Cart" %>
<%@ page import="favourite.Favourite" %>
<%@ page import="nhom26.OddImage" %>
<%@ page import="java.util.Map" %>
<%@ page import="cart.CartProduct" %>
<%@ page import="nhom26.Album" %>
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
    <link rel="stylesheet" href="./css/logo.css">
    <link rel="stylesheet" href="./css/common.css">
</head>

<body>

<% User user = (User) session.getAttribute("user");
    ArrayList<Topic> listTopic = request.getAttribute("listTopic") == null ? new ArrayList<>() :
            (ArrayList<Topic>) request.getAttribute("listTopic");
%>
<%
    Cart cart = (Cart) session.getAttribute("cart");
    if(cart ==null) cart = new Cart();
    Favourite favourite = (Favourite) session.getAttribute("favourite");
    if(favourite ==null) favourite = new Favourite();
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
            <a href="./favourite" class="btn border" title="Yêu thích">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge"><%=favourite.total()%></span>
            </a>
            <a href="./cart" class="btn border" title="Giỏ hàng">
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
                                <a href="cart" class="dropdown-item">Giỏ hàng</a>
                                <a href="checkout" class="dropdown-item active">Thanh toán</a>
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
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Thanh Toán</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="index">Trang chủ</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Thanh Toán</p>
        </div>
    </div>
</div>
<!-- Page Header End -->


<!-- Checkout Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <div class="col-lg-8">

            <div class="mb-4" >
                <h4 class="font-weight-semi-bold mb-4">Địa Chỉ Thanh Toán</h4>
                <div class="row">
                    <div class="col-md-6 form-group">
                        <label>Họ</label>
                        <input id="fist-name" class="form-control" type="text" placeholder="Nguyễn">
                    </div>
                    <div class="col-md-6 form-group">
                        <label>Tên</label>
                        <input id="last-name" class="form-control" type="text" placeholder="Sáng">
                    </div>
                    <div class="col-md-6 form-group">
                        <label>Email</label>
                        <input id="email" class="form-control" type="email" placeholder="example@email.com">
                    </div>
                    <div class="col-md-6 form-group">
                        <label>Số Điện Thoại</label>
                        <input id="phoneNumber" class="form-control" type="text" placeholder="+123 456 789">
                    </div>
                    <div class="col-md-6 form-group">
                        <label>Tỉnh/Thành Phố</label>
                        <select class="form-control" name="nameCity" id="nameCity" >
                            <option value="">Vui lòng chọn Tỉnh/Thành phố</option>
                        </select>
                        </div>
                    <div class="col-md-6 form-group">
                        <label>Quận/Huyện</label>
                        <select class="form-control" name="nameDistrict" id="nameDistrict">
                            <option value="">Vui lòng chọn Quận/Huyện</option>
                        </select>
                        </div>
                    <div class="col-md-6 form-group">
                        <label>Xã/Phường/Thị Trấn</label>
                        <select class="form-control" name="nameCommune" id="nameCommune" >
                            <option value="">Vui lòng chọn Xã/Thị trấn</option>
                        </select>
                        </div>
                    <div class="col-md-6 form-group">
                        <label>Địa chỉ chi tiết</label>
                        <input id="address-detail" class="form-control" type="text" placeholder="Đồng Nai">
                    </div>
                    <div class="col-md-6 form-group">
                        <label>Quốc Gia</label>
                        <select disabled class="custom-select">
                            <option selected>Việt Nam</option>

                        </select>
                    </div>

                    <div class="col-md-6 form-group">
                        <label>Mã ZIP</label>
                        <input disabled class="form-control" type="text" placeholder="76116">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">

            <!-- Mã mới có Js -->
            <div class="card border-secondary mb-5">
                <div class="card-header bg-secondary border-0">
                    <h4 class="font-weight-semi-bold m-0">Tổng Đơn Hàng</h4>
                </div>
                <div class="card-body">
                    <h5 class="font-weight-medium mb-3">Các Sản Phẩm</h5>
                    <%
                        String name = null, type = null, image = null;
                        int id = 0, price = 0, discount = 0,quantity=0;
                    %>
                    <% for (Map.Entry<String, CartProduct> entry : cart.getData().entrySet()) {
                        CartProduct cartProduct = entry.getValue();
                        quantity =cartProduct.getQuantity();
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
                    <div class="d-flex justify-content-between">

                        <p id="product">
                            <input type="checkbox" checked >
                            <a class="ml-1" href="./detail?type=<%=type%>&id=<%=id%>"><%=name%></a> x <%=quantity%>
                        </p>
                        <p id="price"><%=vndFormat.format((price -discount)*quantity)%></p>
                    </div>
                    <%}%>
                    <hr class="mt-0">
                    <div class="d-flex justify-content-between mb-3 pt-1">
                        <h6 class="font-weight-medium">Tổng Tiền Chưa Ship</h6>
                        <h6 class="font-weight-medium" id="subtotal"><%=vndFormat.format(cart.totalPrice())%></h6>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">Giá Vận Chuyển</h6>
                        <h6 class="font-weight-medium" id="shippingFee">30.000 VNĐ</h6>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">Thuế VAT <i style="padding-left: 10px;"
                                                                   class="far fa-question-circle"
                                                                   title=" (Value Added Tax) là một loại thuế tiêu thụ được áp dụng trên giá trị gia tăng
                                    của sản phẩm hoặc dịch vụ trong quá trình chuỗi cung ứng."></i>
                        </h6>
                        <h6 class="font-weight-medium" id="vatRate">0%</h6>
                    </div>
                </div>
                <div class="card-footer border-secondary bg-transparent">
                    <div class="d-flex justify-content-between mt-2">
                        <h5 class="font-weight-bold">Tổng Cộng</h5>
                        <h5 class="font-weight-bold" id="total"><%=vndFormat.format(cart.totalPrice() +30000)%></h5>
                    </div>
                </div>
            </div>

            <div class="card border-secondary mb-5">
                <div class="card-header bg-secondary border-0">
                    <h4 class="font-weight-semi-bold m-0">Phương Thức Trả</h4>
                </div>
                <div class="card-body">
                    <!-- <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="paypal">
                            <label class="custom-control-label" for="paypal">Paypal</label>
                        </div>
                    </div> -->
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="directcheck">
                            <label class="custom-control-label" for="directcheck">Trả Khi Nhận Hàng</label>
                        </div>
                    </div>
                    <div class="">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="banktransfer">
                            <label class="custom-control-label" for="banktransfer">Chuyển Khoản Qua Ngân
                                Hàng</label>
                        </div>
                    </div>
                </div>
                <div class="card-footer border-secondary bg-transparent">
                    <button id="btn-submit" class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Đặt Hàng</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Checkout End -->


<!-- Footer chung cho các trang - Start -->
<div class="container-fluid bg-secondary text-dark mt-5 pt-5">
    <div class="row px-xl-5 pt-5">
        <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
            <a href="./index" class="text-decoration-none">
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
                        <a class="text-dark mb-2" href="shop.jsp"><i class="fa fa-angle-right mr-2"></i>Cửa
                            hàng</a>
                        <a class="text-dark mb-2" href="donhangcuaban.jsp"><i
                                class="fa fa-angle-right mr-2"></i>Đơn hàng của bạn</a>
                        <a class="text-dark mb-2" href="cart.jsp"><i class="fa fa-angle-right mr-2"></i>Giỏ
                            hàng</a>
                        <a class="text-dark mb-2" href="checkout.html"><i class="fa fa-angle-right mr-2"></i>Thanh
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
<!-- Footer chung cho các trang - End -->


<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Contact Javascript File -->
<script src="mail/jqBootstrapValidation.min.js"></script>
<script src="mail/contact.js"></script>


<script src="./js/locationAPI.js"></script>

<script src="js/main.js"></script>
<script>getAddressWithAPI()</script>
<script>
    const firstNameInput = document.querySelector("#fist-name");
    const lastNameInput = document.querySelector("#last-name");
    const phoneNumberInput = document.querySelector("#phoneNumber");
    const cityInput = document.querySelector('#nameCity');
    const districtInput = document.querySelector('#nameDistrict');
    const communeInput = document.querySelector('#nameCommune');
    const addressDetailInput = document.querySelector('#address-detail');

    let firstName = firstNameInput.value;
    let lastName = lastNameInput.value;
    let phoneNumber = phoneNumberInput.value;
    let city = cityInput.value;
    let district = districtInput.value;
    let commune = communeInput.value;
    let addresDetail = addressDetailInput.value;

    // Hàm xử lý khi giá trị thay đổi
    function handleInputChange() {
        firstName = firstNameInput.value;
        lastName = lastNameInput.value;
        phoneNumber = phoneNumberInput.value;
        city = cityInput.value;
        district = districtInput.value;
        commune = communeInput.value;
        addresDetail = addressDetailInput.value;
        console.log(firstName, lastName, phoneNumber, city, district, commune);
    }

    // Gán sự kiện onchange cho từng trường nhập liệu
    firstNameInput.addEventListener('change', handleInputChange);
    lastNameInput.addEventListener('change', handleInputChange);
    phoneNumberInput.addEventListener('change', handleInputChange);
    cityInput.addEventListener('change', handleInputChange);
    districtInput.addEventListener('change', handleInputChange);
    communeInput.addEventListener('change', handleInputChange);
    addressDetailInput.addEventListener("change",handleInputChange)

    // Submit
    const btnSubmit  =document.querySelector("#btn-submit")
    btnSubmit.addEventListener("click",()=>{
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/demoProject_war/cart-order",
            data: { receiver: firstName+" "+ lastName, phoneNumber,address:addresDetail+","+commune+','+ district +','+ city},
            success: function (data) {
               alert(data.message)
                if(data.status===200){
                    location.href = "http://localhost:8080/demoProject_war/donhangcuaban";
                }
            },
            error: function (error) {
                console.error("Error:", error);
            }
        });
    })
</script>
</body>

</html>