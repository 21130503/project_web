<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.awt.*" %>
<%@ page import="nhom26.*" %>
<%@ page import="favourite.Favourite" %>
<%@ page import="cart.Cart" %>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/common.css">
</head>

<body>
<% User user = (User) session.getAttribute("user");
    ArrayList<Topic> listTopic = request.getAttribute("listTopic") == null ? new ArrayList<>() : (ArrayList<Topic>) request.getAttribute("listTopic");
    boolean isLogin =(boolean) request.getAttribute("isLogin");
%>
<% String type = (String) request.getAttribute("type");
    String name = null, description = null, sourceImage = null;
    int price = 0, discount = 0, tottalImage = 1, id = 0, currentPrice;
    OddImage oddImage = null;
    Album album = null;
    List<String> list = new ArrayList<>();
    ArrayList<Feedback> listFeedback = request.getAttribute("feedback") == null ? new ArrayList<>() : (ArrayList<Feedback>) request.getAttribute("feedback");
    int totalFeedbackStar = request.getAttribute("totalStar") == null ? 0 : (int) request.getAttribute("totalStar");
    double avgFeedbackStar = request.getAttribute("avgStar") == null ? 0 : (double) request.getAttribute("avgStar");
    ArrayList<OddImage> listSuggestedOddImage = (ArrayList<OddImage>) request.getAttribute("suggestedOdd");
    ArrayList<Album> listSuggestedAlbum = (ArrayList<Album>) request.getAttribute("suggestedAlbum");
    String editLink = null;
    if (type.equals("album")) {
        album = (Album) request.getAttribute("detail");
        name = album.getName();
        description = album.getDescription();
        price = album.getPrice();
        discount = album.getDiscount();
        tottalImage = album.getListWatermark().size();
        sourceImage = album.getListWatermark().get(0);
        id = album.getIdAlbum();
        list = album.getListImage();
        editLink = "album";
        System.out.println("This is log detail.jsp");
        System.out.println(album.getPrice());
    } else if (type.equals("odd")) {
        oddImage = (OddImage) request.getAttribute("detail");
        name = oddImage.getName();
        description = oddImage.getDescription();
        price = oddImage.getPrice();
        discount = oddImage.getDiscount();
        sourceImage = oddImage.getWatermark();
        id = oddImage.getIdOddImage();
        list.add(oddImage.getImage());
        editLink = "oddImage";
    }
    currentPrice = price- discount;
%>
<%
    Locale vnLocal = new Locale("vi", "VN");
    DecimalFormat vndFormat = new DecimalFormat("#,### VNĐ");
%>
<%
    String err = session.getAttribute("errMess") == null ? "" : (String) session.getAttribute("errMess");
    String errTotal = session.getAttribute("errTotal") == null ? "" : (String) session.getAttribute("errTotal");
    String errAddress = session.getAttribute("errAddress") == null ? "" : (String) session.getAttribute("errAddress");
    String errReceiver = session.getAttribute("errReceiver") == null ? "" : (String) session.getAttribute("errReceiver");
    String errPhoneNumber = session.getAttribute("errPhoneNumber") == null ? "" : (String) session.getAttribute("errPhoneNumber");
    String errVerify = session.getAttribute("errVerify") == null ? "" : (String) session.getAttribute("errVerify");
    String errActive = session.getAttribute("errActive") == null ? "" : (String) session.getAttribute("errActive");
    String errPrivateKey = session.getAttribute("errPrivate") == null ? "" : (String) session.getAttribute("errPrivate");

%>

<%
    Favourite  favourite = (Favourite) session.getAttribute("favourite");
    if(favourite ==null) favourite = new Favourite();
    Cart  cart = (Cart) session.getAttribute("cart");
    if(cart ==null) cart = new Cart();
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
                        <button type="submit" class="input-group-text bg-transparent text-primary">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-3 col-6 text-right">
            <a href="./favourite" class="btn border">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge"><%=favourite.total()%></span>
            </a>
            <a href="cart.jsp" class="btn border">
                <i class="fas fa-shopping-cart text-primary"></i>
                <span class="badge"><%=cart.total()%></span>
            </a>
        </div>
    </div>
</div>
<!-- Topbar End -->


<!-- Navbar Start -->

<div class="container-fluid mb-5">
    <div class="row border-top px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100"
               data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                <h6 class="m-0">Danh mục</h6>
                <i class="fa fa-angle-down text-dark"></i>
            </a>
            <nav class="collapse  navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0"
                 id="navbar-vertical">
                <div class="navbar-nav w-100 overflow" style="height: 410px">
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
                <a href="" class="text-decoration-none d-block d-lg-none">
                    <h1 class="logo" style="font-size: 34px;">Nhóm 26</h1>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <a href="./index" class="nav-item nav-link active">Trang chủ</a>
                        <a href="./shop" class="nav-item nav-link">Cửa hàng</a>
                        <a href="./donhangcuaban" class="nav-item nav-link">Đơn hàng của bạn</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Trang</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="cart.jsp" class="dropdown-item">Giỏ hàng</a>
                                <a href="checkout.jsp" class="dropdown-item">Thanh toán</a>
                            </div>
                        </div>
                        <a href="contact.jsp" class="nav-item nav-link">Liên hệ</a>
                    </div>
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


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Chi tiết sản phẩm</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="index">Home</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Chi tiết sản phẩm</p>
        </div>
    </div>
</div>
<!-- Page Header End -->
<div class="d-flex align-items-center justify-content-center">
    <h5 class="text-danger"><%=errActive%>
    </h5>
    <h5 class="text-danger"><%=errVerify%>
    </h5>
</div>

<!-- Shop Detail Start -->
<div class="container-fluid py-5">
    <div class="row px-xl-5">
        <div class="col-lg-4 ">
            <img id="image-interface" style="width: 100%;" src="<%=sourceImage%>" alt="alt">
        </div>

        <div class="col-lg-8">
            <div class="title-and-option d-flex align-items-center justify-content-between">
                <h3 class="font-weight-semi-bold"><%=name%>
                </h3>
                <div class="option">
                    <button id="btn-favourite" title="<%=type%>" value="<%=id%>" class="btn border"><i class="fas fa-heart text-primary"></i></button>
                    <button id="btn-download" class="btn border " title="<%=type%>" value="<%=id%>"><i class="fa-regular fa-circle-down text-primary"></i></button>
                    <%if(user !=null&&user.isAdmin()){%>
                    <button id="btn-cart" class="btn border " title="edit"><a href="./<%=editLink%>?q=<%=id%>/edit"><i class="fa-solid fa-pen"></i></a></button>
                    <%}%>
                </div>
            </div>
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
                <%=vndFormat.format(price - discount)%>
            </h3>

            </p>
            <div class="d-flex mb-3">
                <p class="text-dark font-weight-medium mb-0 mr-3">Số ảnh:</p>
                <p><%=tottalImage%>
                </p>
            </div>
            <div class="d-flex mb-3">
                <%for (String source : list) {%>
                <img class="img-item" style="margin-left: 10px;width: 100px;height: 100px;object-fit: cover"
                     src="<%=source%>" alt="">
                <%}%>
            </div>

            <div class="d-flex mb-3 mt-5">
                <form action="./order" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="type" value="<%=type%>">
                    <input type="hidden" name="idProduct" value="<%=id%>">
                    <input type="hidden" name="price" value="<%=price-discount%>">
                    <div class="d-flex align-items-center ">
                        <p class="mb-0">Số lượng: </p>
                        <button id="minus" type="button" class="ml-2 btn border">-</button>
                        <input type="number" name="total" value="1" class="total-product text-center"
                               style="width: 40px; padding: 5px 0;">
                        <button id="plus" type="button" class=" btn border">+</button>
                    </div>
                    <p class="text-danger mt-1"><%=errTotal%>
                    </p>
                    <div class="information-receive mt-2">
                        <label for="reciver">Tên người nhận:</label>
                        <input type="text" id="reciver" name="receiver" class="mt-1 mb-1 d-block p-2"
                               placeholder="Thông tin người nhận">
                        <p class="text-danger"><%=errReceiver%>
                        </p>
                        <label for="phonenumber">Số điện thoại: </label>
                        <input type="number" id="phonenumber" class="mt-1 mb-1  d-block p-2" name="phoneNumber"
                               placeholder="Số điện thoại">
                        <p class="text-danger"><%=errPhoneNumber%>
                        </p>
                        <label for="discount">Mã giảm giá(nếu có): </label>
                        <input type="number" id="discount" class="mt-1 mb-1  d-block p-2" name="discount"
                               placeholder="Mã giảm giá(nếu có)">
                        <label for="privateKey">Chọn Private Key của bạn: </label>
                        <input type="file" id="privateKey" class="mt-1 mb-1  d-block p-2" name="privateKey"
                               placeholder="Vui lòng chọn tệp chứa Key của bạn">
                        <p class="text-danger"><%=errPrivateKey%>
                    </div>
                    <div class="address mt-2">
                        <select name="nameCity" id="nameCity" class="mt-3 p-2" style="width: 250px">
                            <option value="">Vui lòng chọn Tỉnh/Thành phố</option>
                        </select>
                        <select name="nameDistrict" id="nameDistrict" class="mt-3 p-2" style="width: 250px">
                            <option value="">Vui lòng chọn Quận/Huyện</option>
                        </select>
                        <select name="nameCommune" id="nameCommune" class="mt-3 p-2" style="width: 250px">
                            <option value="">Vui lòng chọn Xã/Thị trấn</option>
                        </select>
                        <input type="text" name="detail-address" class="mt-3 p-2" style="width: 250px"
                               placeholder="Địa chỉ cụ thể">
                        <p class="text-danger mt-1"><%=errAddress%>
                        </p>
                    </div>
                    <button class="btn btn-primary mt-2"  id="btn-buy">Mua Ngay</button>
                </form>
            </div>

        </div>
    </div>
    <div class="row px-xl-5 mt-8">
        <div class="col">
            <div class="nav nav-tabs justify-content-center border-secondary mb-4">
                <a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">Mô tả</a>
                <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Đánh giá (<%=listFeedback.size()%>
                    )</a>
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
                            <%if (listFeedback.size() == 0) { %>
                            <p>Chưa có bình luận nào</p>
                            <%} else {%>
                            <%for (Feedback feedback : listFeedback) {%>
                            <div class="media mb-4">
                                <img src="img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">
                                <div class="media-body">
                                    <h6><%=feedback.getUsername()%><small> - <i><%=feedback.getDate()%>
                                    </i></small></h6>
                                    <!-- <div class="text-primary mb-2">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star-half-alt"></i>
                                        <i class="far fa-star"></i>
                                    </div> -->
                                    <p><%=feedback.getContent()%>
                                    </p>
                                </div>
                            </div>
                            <%}%>
                            <%}%>

                        </div>
                        <div class="col-md-6">
                            <h4 class="mb-4">Viết phản hồi của bạn</h4>
                            <form action="/demoProject_war/feedback" method="post">
                                <p class="text-danger"><%=err%>
                                </p>
                                <div class="form-group">
                                    <label for="message">Đánh giá của bạn: </label>
                                    <textarea name="content" id="message" cols="30" rows="5"
                                              class="form-control"></textarea>
                                </div>
                                <div class="d-flex my-3">
                                    <p class="mb-0 mr-2 d-flex align-items-center">Đánh giá dạng sao * :</p>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="1" class="star-radio"/>
                                        <small class="far fa-star"></small>
                                    </label>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="2" class="star-radio"/>
                                        <small class="far fa-star"></small>
                                    </label>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="3" class="star-radio"/>
                                        <small class="far fa-star"></small>
                                    </label>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="4" class="star-radio"/>
                                        <small class="far fa-star"></small>
                                    </label>
                                    <label class="feedback-star">
                                        <input type="radio" name="star" value="5" class="star-radio"/>
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
                <%if (listSuggestedAlbum.size() > 0 && "album".equals(type)) {%>
                <%for (Album album1 : listSuggestedAlbum) {%>
                <div class="card product-item border-0">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <img class="img-fluid w-100 image-view" src="<%=album1.getListImage().get(0)%>" alt="">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3"><%=album1.getName()%></h6>
                        <div class="d-flex justify-content-center">
                            <h6><%=vndFormat.format(album1.getPrice() - album1.getDiscount())%></h6>
                            <h6 class="text-muted ml-2">
                                <del><%=vndFormat.format(album1.getPrice())%></del>
                            </h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="./detail?type=album&id=<%=album1.getIdAlbum()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem chi
                            tiết</a>
                        <a href="" class="btn btn-sm text-dark p-0"><i
                                class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>
                <%
                        }
                    }
                %>

            </div>
        </div>
    </div>
    <div class="row px-xl-5">
        <div class="col">
            <div class="owl-carousel related-carousel">
                <%if (listSuggestedOddImage.size() > 0 && "odd".equals(type)) {%>
                <%for (OddImage oddImage1 : listSuggestedOddImage) {%>
                <div class="card product-item border-0">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <img class="img-fluid w-100 image-view" src="<%=oddImage1.getWatermark() !=null ? oddImage1.getWatermark(): oddImage1.getImage()%>" alt="">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3"><%=oddImage1.getName()%></h6>
                        <div class="d-flex justify-content-center">
                            <h6><%=vndFormat.format(oddImage1.getPrice() - oddImage1.getDiscount())%></h6>
                            <h6 class="text-muted ml-2">
                                <del><%=vndFormat.format(oddImage1.getPrice())%></del>
                            </h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="./detail?type=odd&id=<%=oddImage1.getIdOddImage()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem chi
                            tiết</a>
                        <a href="" class="btn btn-sm text-dark p-0"><i
                                class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>
                <%
                        }
                    }
                %>

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
<%--Banking--%>
<div id="myModal" class="modal">
    <div class="modal-content-banking">
        <span class="close" id="closeModalBtn">&times;</span>
        <h2 class="text-center">Thanh toán</h2>
        <p class="text-center text-warning ">Vui lòng quét mã bên dưới để có thể mua ảnh.</p>
        <div class="body-modal my-2 flex flex-column justify-content-center align-items-center ">
            <img id="qr-banking" alt="qr-banking"/>
        </div>
    </div>
</div>
<div id="myModalKey" class="modal">
    <div class="modal-verify-key">
        <span class="close" id="closeModal">&times;</span>
        <h2 class="text-center">Vui lòng tải Private Key của bạn</h2>
        <form onsubmit="" method="post">
            <input value="<%=type%>" name="type"/>
            <input value="<%=name%>" name="name"/>
            <input value="<%=price%>" name="price"/>
            <input value="<%=discount%>" name="discount">
            <input value="<%=description%>" name="description"/>
            <input type="file" accept="*/*" name="privateKey"/>
            <button type="submit">Xác thực</button>
        </form>

    </div>
</div>
<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>
<script src="js/detail.js"></script>
<%--<script>--%>
<%--&lt;%&ndash;    Buy product&ndash;%&gt;--%>
<%--const btnBuy = document.querySelector("#btn-buy");--%>
<%--console.log(isActive, isVerifyEmail)--%>
<%--btnBuy.addEventListener("click", ()=>{--%>

<%--    if(!isActive){--%>
<%--        alert("Bạn không thể mua hàng")--%>
<%--    }--%>
<%--    else if(!isVerifyEmail){--%>
<%--        alert("Vui lòng xác thực email trước khi mua hàng")--%>
<%--        window.location.href = "http://localhost:8080/demoProject_war/verify"--%>
<%--    }--%>
<%--    else if(idUser === null){--%>
<%--        alert("Vui lòng đăng nhập")--%>
<%--        window.location.href = "http://localhost:8080/demoProject_war/login.jsp"--%>
<%--    }--%>

<%--})--%>
<%--</script>--%>

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
<script>
    const  idProduct = <%=id%>
    const isLogin = <%=isLogin%>
    const type = "<%=type%>"
    <%--const id = <%=user.getId()%>--%>
</script>
<script>
    const favouriteBtn = document.getElementById('btn-favourite');
    const cartBtn = document.getElementById('btn-cart');
    console.log(favouriteBtn)
    const type = favouriteBtn.title;
    console.log(idProduct)
    favouriteBtn.addEventListener("click", ()=>{
        const xhr = new XMLHttpRequest();
        const url = `http://localhost:8080/demoProject_war/favourite?type=<%=type%>&idProduct=<%=id%>`;

        xhr.open("POST", url, true);

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
    cartBtn.addEventListener("click", ()=>{
        const xhr = new XMLHttpRequest();
        const url = `http://localhost:8080/demoProject_war/cart?type=<%=type%>&idProduct=<%=id%>`;

        xhr.open("POST", url, true);

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
    const btnDown = document.getElementById("btn-download")
    console.log(btnDown)
    var modal = document.getElementById("myModal");
    var closeModalBtn = document.getElementById("closeModalBtn");
    var image = document.getElementById('qr-banking')
    btnDown.onclick =()=>{

        const link = document.createElement("a");
        // const isLogin =isLogin
        // const type = type
        if(!<%=isLogin%>){
            alert('c')
            window.location.href = 'http://localhost:8080/demoProject_war/login.jsp';
        }



        let BANK_ID = 970436
        let ACCOUNT_NO = 1016574856
        let TEMPLATE= 'compact'
        let description = `${type == 'odd' ?  'Thanh toán mua ảnh lẻ' : 'Thanh toán mua album'}`
        let amount = <%=currentPrice%>
        var QR= `https://img.vietqr.io/image/${BANK_ID}-${ACCOUNT_NO}-${TEMPLATE}.png?amount=${amount}&addInfo=${description}`
        image.src = QR
        image.height= 332
        image.style.width= 'auto'
        console.log(QR)
        modal.style.display = "block";
//
        if(type === 'odd'){
            <%--const data = checPay();--%>
            <%--data.forEach((item,index)=>{--%>
            <%--    if(item['Mô tả'].substring(0) === id){--%>
<%----%>
            <%--        link.href ='<%=oddImage.getImage()%>'--%>
            <%--        link.download = '<%=oddImage.getImage().substring(45)%>'--%>
            <%--        link.click()--%>
            <%--}--%>
            <%--})--%>
<%----%>
        }
        else {
            console.log('a')
        }
//
    }
    closeModalBtn.onclick = function() {
        modal.style.display = "none";
    }
//
    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
//
//
    // const checPay = async  ()=>{
    //     const resp = await  fetch(`https://script.googleusercontent.com/macros/echo?user_content_key=VdvILEjkR7prGau60nO6JywHzOdRjL-e7y6Ls7Towy1QCWFy1Z1CQAbuyNFFIh6gYu_5VLINXfignFzfMkyh6ZSlYZaXQfRXm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnJXUcFNWVk6SD_eNLQSqM2jGqw2ZNFhvoOCBoE1DTGpRJg9N06TZDERdlXyQwVs0Q9olhK1kW-q9i8z1Bes2eXal1G61OByJXNz9Jw9Md8uu&lib=MOqSGi395BE9CLYWj1QBkvqhHvNT7C3Ew`,
    //     );
    //     const data = await resp.json();
    //     return data
    // }

</script>

</body>

</html>