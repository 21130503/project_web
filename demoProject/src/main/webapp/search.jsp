<%@ page import="java.util.ArrayList" %>
<%@ page import="nhom26.OddImage" %>
<%@ page import="nhom26.Album" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="en">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<head>
    <meta charset="utf-8">
    <title>Kết quả tìm kiếm</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
</head>

<body>
<%
    ArrayList<OddImage> listOdd = (ArrayList<OddImage>) request.getAttribute("listOddImage");
    ArrayList<Album> listAlbum = (ArrayList<Album>) request.getAttribute("listAlbum");
    String param = request.getAttribute("param") == null ? "" : (String) request.getAttribute("param");
%>
<%
    Locale vnLocal = new Locale("vi", "VN");
    DecimalFormat vndFormat = new DecimalFormat("#,### VNĐ");
%>
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
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-3 col-6 text-right">
            <a href="" class="btn border">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge">0</span>
            </a>
            <a href="" class="btn border">
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
                    <a href="" class="nav-item nav-link">Con người</a>
                    <a href="" class="nav-item nav-link">Thiên nhiên</a>
                    <a href="" class="nav-item nav-link">Động vật</a>
                    <a href="" class="nav-item nav-link">Chó</a>
                    <a href="" class="nav-item nav-link">Mèo</a>
                    <a href="" class="nav-item nav-link">Xe</a>
                    <a href="" class="nav-item nav-link">Vũ trụ</a>
                    <a href="" class="nav-item nav-link">Hoạt hình</a>
                    <a href="" class="nav-item nav-link">Hoa</a>
                </div>
            </nav>
        </div>
        <div class="col-lg-9">
            <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                <a href="./index" class="text-decoration-none d-block d-lg-none">
                    <h1 class="logo">Nhóm 26</h1>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <a href="./index" class="nav-item nav-link">Trang chủ</a>
                        <a href="shop.html" class="nav-item nav-link active"> Cửa hàng</a>
                        <a href="albumnew.html" class="nav-item nav-link">Bộ sưu tập mới</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Trang</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="cart.jsp" class="dropdown-item">Giỏ hàng</a>
                                <a href="checkout.jsp" class="dropdown-item">Thanh toán</a>
                            </div>
                        </div>

                        <a href="contact.jsp" class="nav-item nav-link ">Liên hệ</a>
                    </div>
                    <div class="navbar-nav ml-auto py-0">
                        <a href="login.jsp" class="nav-item nav-link">Đăng nhập</a>
                        <a href="register.jsp" class="nav-item nav-link">Đăng ký</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</div>
<!-- Navbar End -->


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3"><%=listAlbum.size() + listOdd.size()%> Kết quả tìm kiếm
            cho "<%=param%>"</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="./index">Trang Chủ</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Cửa Hàng</p>
        </div>
    </div>
</div>
<!-- Page Header End -->

<!-- Shop Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <!-- Shop Sidebar Start -->
        <div class="col-lg-3 col-md-12">
            <!-- Price Start -->
            <div class="border-bottom mb-4 pb-4">
                <h5 class="font-weight-semi-bold mb-4">Lọc theo giá</h5>
                <form>
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                        <input type="checkbox" class="custom-control-input" checked id="price-all">
                        <label class="custom-control-label" for="price-all">Tất cả loại giá</label>
                        <span class="badge border font-weight-normal">1.000.000</span>
                    </div>
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                        <input type="checkbox" class="custom-control-input" id="price-1">
                        <label class="custom-control-label" for="price-1">0 - 100.000</label>
                        <span class="badge border font-weight-normal">150.000</span>
                    </div>
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                        <input type="checkbox" class="custom-control-input" id="price-2">
                        <label class="custom-control-label" for="price-2">100.000 - 200.000</label>
                        <span class="badge border font-weight-normal">295.000</span>
                    </div>
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                        <input type="checkbox" class="custom-control-input" id="price-3">
                        <label class="custom-control-label" for="price-3">200.000 - 300.000</label>
                        <span class="badge border font-weight-normal">246.000</span>
                    </div>
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                        <input type="checkbox" class="custom-control-input" id="price-4">
                        <label class="custom-control-label" for="price-4">300.000 - 400.000</label>
                        <span class="badge border font-weight-normal">345.000 </span>
                    </div>
                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                        <input type="checkbox" class="custom-control-input" id="price-5">
                        <label class="custom-control-label" for="price-5">400.000 - 500.000 </label>
                        <span class="badge border font-weight-normal">450.000</span>
                    </div>
                </form>
            </div>
            <!-- Price End -->
        </div>


        <!-- Shop Product Start -->
        <div class="col-lg-9 col-md-12">
            <div class="row pb-3">
                <div class="col-12 pb-1">
                    <div class="d-flex align-items-center justify-content-between mb-4">
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
                        <div class="dropdown ml-4">
                            <button class="btn border dropdown-toggle" type="button" id="triggerId"
                                    data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                Sắp xếp theo
                            </button>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="triggerId">
                                <a class="dropdown-item" href="#">Cũ nhất</a>
                                <a class="dropdown-item" href="#">Phổ biến</a>
                                <a class="dropdown-item" href="#">Mới nhất</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 pb-1 bg-secondary"><h4 class="text-center text-primary font-weight-bold"><%=listAlbum.size()%> kết quả cho album</h4></div>
                <%for (Album album : listAlbum) {%>
                <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                    <div class="card product-item border-0 mb-4">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="<%=album.getListImage().get(0)%>" alt="">
                        </div>
                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3"><%=album.getName()%>
                            </h6>
                            <div class="d-flex justify-content-center">
                                <h6><%=vndFormat.format(album.getPrice() - album.getDiscount()) %>
                                </h6>
                                <h6 class="text-muted ml-2">
                                    <del><%=vndFormat.format(album.getPrice())%>
                                    </del>
                                </h6>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between bg-light border">
                            <a href="./detail?type=album&id=<%=album.getIdAlbum()%>" class="btn btn-sm text-dark p-0"><i
                                    class="fas fa-eye text-primary mr-1"></i>Xem chi tiết</a>
                            <button title="<%=album.getType()%>" value="<%=album.getIdAlbum()%>"
                                    class="btn btn-sm text-dark p-0 addCart"><i
                                    class="fas fa-shopping-cart text-primary mr-1"></i>Thêm
                                vào giỏ
                            </button>
                        </div>
                    </div>
                </div>
                <%}%>
                <div class="col-12 pb-1 bg-secondary"><h4 class="text-center text-primary font-weight-bold"><%=listOdd.size()%> kết cho ảnh lẻ</h4>
                </div>
                <%for (OddImage oddImage : listOdd) {%>
                <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                    <div class="card product-item border-0 mb-4">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="<%=oddImage.getImage()%>" alt="">
                        </div>
                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3"><%=oddImage.getName()%></h6>
                            <div class="d-flex justify-content-center">
                                <h6><%=vndFormat.format(oddImage.getPrice() - oddImage.getDiscount())%></h6>
                                <h6 class="text-muted ml-2">
                                    <del><%=vndFormat.format(oddImage.getPrice())%></del>
                                </h6>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between bg-light border">
                            <a href="./detail?type=odd&id=<%=oddImage.getIdOddImage()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem
                                chi tiết</a>
                            <button title="<%=oddImage.getType()%>" value="<%=oddImage.getIdOddImage()%>"
                                    class="btn btn-sm text-dark p-0 addCart"><i
                                    class="fas fa-shopping-cart text-primary mr-1"></i>Thêm
                                vào giỏ
                            </button>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
        <!-- Shop Product End -->
    </div>
</div>
<!-- Shop End -->


<!-- Footer chung cho các trang - Start -->
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
                        <a class="text-dark mb-2" href="index.jsp"><i class="fa fa-angle-right mr-2"></i>Trang
                            chủ</a>
                        <a class="text-dark mb-2" href="shop.html"><i class="fa fa-angle-right mr-2"></i>Cửa
                            hàng</a>
                        <a class="text-dark mb-2" href="donhangcuaban.jsp"><i
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

<!-- Template Javascript -->
<script src="js/main.js"></script>
<script src="./js/addCart.js"></script>
</body>

</html>