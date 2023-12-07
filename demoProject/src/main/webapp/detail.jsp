<%@ page import="nhom26.Album" %>
<%@ page import="nhom26.OddImage" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.awt.*" %>
<%@ page import="nhom26.Feedback" %>
<%@ page import="nhom26.User" %>
<!DOCTYPE html>
<html lang="en">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>Chi tiết sản phẩm</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">
    <link rel="stylesheet" href="./css/detail.css">
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
</head>

<body>
<% User user = (User) session.getAttribute("user");%>
<% String type = (String) request.getAttribute("type");
    String name = null, description = null, sourceImage = null;
    int price = 0, discount =0, tottalImage = 1,id= 0 ;
    OddImage oddImage = null;
    Album album = null;
    List<String>  list = new ArrayList<>();
    ArrayList<Feedback> listFeedback = request.getAttribute("feedback") == null ? new ArrayList<>() :(ArrayList<Feedback>) request.getAttribute("feedback");
    int totalFeedbackStar = request.getAttribute("totalStar") == null ? 0 : (int) request.getAttribute("totalStar");
    double avgFeedbackStar = request.getAttribute("avgStar")== null ? 0 : (double) request.getAttribute("avgStar");
    if (type.equals("album")) {
        album = (Album) request.getAttribute("detail");
        name = album.getName();
        description = album.getDescription();
        price = album.getPrice();
        discount = album.getDiscount();
        tottalImage = album.getListImage().size();
        sourceImage = album.getListImage().get(0);
        id = album.getIdAlbum();
        list = album.getListImage();
    } else if (type.equals("odd")) {
        oddImage = (OddImage) request.getAttribute("detail");
        name = oddImage.getName();
        description = oddImage.getDescription();
        price = oddImage.getPrice();
        discount = oddImage.getDiscount();
        sourceImage = oddImage.getImage();
        id = oddImage.getIdOddImage();
        list.add(oddImage.getImage());
    }
%>
<%
    Locale vnLocal = new Locale("vi", "VN");
    DecimalFormat vndFormat = new DecimalFormat("#,### VND");
%>
<%String err = session.getAttribute("errMess") ==null ? "": (String) session.getAttribute("errMess"); %>
<!-- Topbar Start -->
<div class="container-fluid">

    <div class="row align-items-center py-3 px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a href="index.jsp" class="text-decoration-none">
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
            <a href="favourite.jsp" class="btn border">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge">0</span>
            </a>
            <a href="cart.jsp" class="btn border">
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
                <a href="" class="text-decoration-none d-block d-lg-none">
                    <h1 class="logo">Nhóm 26</h1>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <a href="index.jsp" class="nav-item nav-link">Trang chủ</a>
                        <a href="shop.jsp" class="nav-item nav-link">Cửa hàng</a>
                        <a href="donhangcuaban.jsp" class="nav-item nav-link">Đơn hàng của bạn</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Trang</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="cart.jsp" class="dropdown-item">Giỏ hàng</a>
                                <a href="checkout.jsp" class="dropdown-item">Thanh toán</a>
                            </div>
                        </div>
                        <a href="contact.jsp" class="nav-item nav-link active">Liên hệ</a>
                    </div>
                    <%if(user == null) {%>
                    <div class="navbar-nav ml-auto py-0">
                        <a href="login.jsp" class="nav-item nav-link">Đăng nhập</a>
                        <a href="register.jsp" class="nav-item nav-link">Đăng ký</a>
                    </div>

                    <%} else { %>
                    <div class="navbar-nav ml-auto py-0 position-relative">
                        <p class="nav-link dropdown-toggle m-0" data-toggle="dropdown">Hi, <%= user.getUsername()%></p>
                        <div class="dropdown-menu rounded-0 m-0">
                            <%if(!user.isVerifyEmail()){%>
                            <a href="./verify" class="dropdown-item">Xác thực email của bạn</a>
                            <%}%>
                            <% if(user.isAdmin()){%>
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


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Chi tiết sản phẩm</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="index.jsp">Home</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Chi tiết sản phẩm</p>
        </div>
    </div>
</div>
<!-- Page Header End -->


<!-- Shop Detail Start -->
<div class="container-fluid py-5">
    <div class="row px-xl-5">
        <div class="col-lg-4 ">
            <img id="image-interface" style="width: 100%;" src="<%=sourceImage%>"alt="alt">
        </div>

        <div class="col-lg-8">
            <h3 class="font-weight-semi-bold"><%=name%>
            </h3>
            <div class="d-flex mb-3">
<%--                <div class="text-primary mr-2">--%>
<%--                    <small class="fas fa-star"></small>--%>
<%--                    <small class="fas fa-star"></small>--%>
<%--                    <small class="fas fa-star"></small>--%>
<%--                    <small class="fas fa-star-half-alt"></small>--%>
<%--                    <small class="far fa-star"></small>--%>
<%--                </div>--%>
                <h4 class="text-primary mr-2"><%=avgFeedbackStar%> <small class="fas fa-star"></small></h4>
                <small class="pt-2">(<%=totalFeedbackStar%> Đánh giá)</small>
            </div>
            <h3 class="font-weight-semi-bold mb-4">
               <%=vndFormat.format(price-discount)%>
            </h3>

            </p>
            <div class="d-flex mb-3">
                <p class="text-dark font-weight-medium mb-0 mr-3">Số ảnh:</p>
                <p><%=tottalImage%></p>
            </div>
            <div class="d-flex mb-3">
                <%for(String source : list){%>
                <img class="img-item" style="margin-left: 10px;width: 100px;height: 100px;object-fit: cover" src="<%=source%>" alt="">
                <%}%>
            </div>

        </div>
    </div>
    <div class="row px-xl-5 mt-8">
        <div class="col">
            <div class="nav nav-tabs justify-content-center border-secondary mb-4">
                <a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">Mô tả</a>
                <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Đánh giá (<%=listFeedback.size()%>)</a>
            </div>
            <div class="tab-content">
                <div class="tab-pane fade show active" id="tab-pane-1">
                    <h4 class="mb-3">Mô tả album</h4>
                    <p class="mb-4"><%=description%>
                </div>
                <div class="tab-pane fade" id="tab-pane-3">
                    <div class="row">
                        <div class="col-md-6">
                            <h4 class="mb-4"><%=listFeedback.size()%> đánh giá cho "<%=name%>"</h4>
                            <%if(listFeedback.size() == 0){ %>
                                <p>Chưa có bình luận nào</p>
                            <%}else{%>
                                <%for(Feedback feedback: listFeedback){%>
                            <div class="media mb-4">
                                <img src="img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">
                                <div class="media-body">
                                    <h6><%=feedback.getUsername()%><small> - <i><%=feedback.getDate()%></i></small></h6>
                                    <!-- <div class="text-primary mb-2">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star-half-alt"></i>
                                        <i class="far fa-star"></i>
                                    </div> -->
                                    <p><%=feedback.getContent()%></p>
                                </div>
                            </div>
                                <%}%>
                            <%}%>

                        </div>
                        <div class="col-md-6">
                            <h4 class="mb-4">Viết phản hồi của bạn</h4>
                            <form action="/demoProject_war/feedback" method="post">
                                <p class="text-danger"><%=err%></p>
                                <div class="form-group">
                                    <label for="message">Đánh giá của bạn: </label>
                                    <textarea name="content" id="message" cols="30" rows="5" class="form-control"></textarea>
                                </div>
                                <div class="d-flex my-3">
                                    <p class="mb-0 mr-2 d-flex align-items-center">Đánh giá dạng sao * :</p>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="1" class="star-radio" />
                                        <small class="far fa-star"></small>
                                    </label>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="2" class="star-radio" />
                                        <small class="far fa-star"></small>
                                    </label>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="3" class="star-radio" />
                                        <small class="far fa-star"></small>
                                    </label>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="4" class="star-radio" />
                                        <small class="far fa-star"></small>
                                    </label>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="5" class="star-radio" />
                                        <small class="far fa-star"></small>
                                    </label>
                                </div>
                                <div class="form-group">
                                    <input type="hidden" name="type" value="<%=type%>" class="">
                                    <input type="hidden" name="id" value="<%=id%>" class="">
                                </div>

                                <div class="form-group mb-0">
                                    <input type="submit" value="Bình luận" class="btn btn-primary px-3">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Shop Detail End -->


<!-- Products Start -->
<div class="container-fluid py-5">
    <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">Có lẽ bạn sẽ thích</span></h2>
    </div>
    <div class="row px-xl-5">
        <div class="col">
            <div class="owl-carousel related-carousel">
                <div class="card product-item border-0">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <img class="img-fluid w-100" src="img/cat.avif" alt="">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3">Ablum mèo</h6>
                        <div class="d-flex justify-content-center">
                            <h6>$123.00</h6>
                            <h6 class="text-muted ml-2">
                                <del>200.000VNĐ</del>
                            </h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem chi
                            tiết</a>
                        <a href="" class="btn btn-sm text-dark p-0"><i
                                class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>
                <div class="card product-item border-0">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <img class="img-fluid w-100" src="img/dog.avif" alt="">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3">Ablum chó</h6>
                        <div class="d-flex justify-content-center">
                            <h6>$123.00</h6>
                            <h6 class="text-muted ml-2">
                                <del>200.000VNĐ</del>
                            </h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem chi
                            tiết</a>
                        <a href="" class="btn btn-sm text-dark p-0"><i
                                class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>
                <div class="card product-item border-0">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <img class="img-fluid w-100" src="img/car.avif" alt="">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3">Album Car</h6>
                        <div class="d-flex justify-content-center">
                            <h6>$123.00</h6>
                            <h6 class="text-muted ml-2">
                                <del>500.000VNĐ</del>
                            </h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem chi
                            tiết</a>
                        <a href="" class="btn btn-sm text-dark p-0"><i
                                class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>
                <div class="card product-item border-0">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <img class="img-fluid w-100" src="img/anime.avif" alt="">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3">Hoạt hình tuyển tập</h6>
                        <div class="d-flex justify-content-center">
                            <h6>$123.00</h6>
                            <h6 class="text-muted ml-2">
                                <del>50.000VNĐ</del>
                            </h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem chi
                            tiết</a>
                        <a href="" class="btn btn-sm text-dark p-0"><i
                                class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>
                <div class="card product-item border-0">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <img class="img-fluid w-100" src="img/animal.avif" alt="">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3">Thế giới động vật</h6>
                        <div class="d-flex justify-content-center">
                            <h6>$123.00</h6>
                            <h6 class="text-muted ml-2">
                                <del>70.000VNĐ</del>
                            </h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem chi
                            tiết</a>
                        <a href="" class="btn btn-sm text-dark p-0"><i
                                class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Products End -->


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
                        <a class="text-dark mb-2" href="donhangcuaban.jsp"><i class="fa fa-angle-right mr-2"></i>Đơn
                            hàng của bạn</a>
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


<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>
<script src="js/detail.js"></script>

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
<script src="./js/user.js"></script>
</body>

</html>