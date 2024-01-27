<%@ page import="nhom26.OddImage" %>
<%@ page import="nhom26.Album" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Quản lí sản phẩm || Admin</title>
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
    <link rel="stylesheet" href="/css/logo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.6.2/axios.min.js"
            integrity="sha512-b94Z6431JyXY14iSXwgzeZurHHRNkLt9d6bAHt7BZT38eqV+GyngIi/tVye4jBKPYQ2lBdRs0glww4fmpuLRwA=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</head>

<body>
<%
    String errNameTopic = request.getAttribute("errNameTopic") == null ? "" : (String) request.getAttribute("errNameTopic");
    String errNameImg = request.getAttribute("errNameImg") == null ? "" : (String) request.getAttribute("errNameImg");
    String errPrice = request.getAttribute("errPrice") == null ? "" : (String) request.getAttribute("errPrice");
    String errDescription = request.getAttribute("errDescription") == null ? "" : (String) request.getAttribute("errDescription");
    String errImg = request.getAttribute("errImg") == null ? "" : (String) request.getAttribute("errImg");
    String errDiscount = request.getAttribute("errDiscount") == null ? "" : (String) request.getAttribute("errDiscount");
    String errNameOddExist = request.getAttribute("errNameOddExist") == null ? "" : (String) request.getAttribute("errNameOddExist");
//    Invalidate album
    String errNameTopic_Album = request.getAttribute("errNameTopic_Album") == null ? "" : (String) request.getAttribute("errNameTopic_Album");
    String errNameAlbum = request.getAttribute("errNameAlbum") == null ? "" : (String) request.getAttribute("errNameAlbum");
    String errDiscountAlbum = request.getAttribute("errDiscountAlbum") == null ? "" : (String) request.getAttribute("errDiscountAlbum");
    String errPriceAlbum = request.getAttribute("errPriceAlbum") == null ? "" : (String) request.getAttribute("errPriceAlbum");
    String errDescriptionAlbum = request.getAttribute("errDescriptionAlbum") == null ? "" : (String) request.getAttribute("errDescriptionAlbum");
    String errImageForAlbum = request.getAttribute("errImageForAlbum") == null ? "" : (String) request.getAttribute("errImageForAlbum");
    String errNameExist = request.getAttribute("errNameExist") == null ? "" : (String) request.getAttribute("errNameExist");

    ArrayList<String> listNamesTopic = request.getAttribute("listNamesTopic") == null ? new ArrayList<>() : (ArrayList<String>) request.getAttribute("listNamesTopic");
    ArrayList<OddImage> listOddImage = (ArrayList<OddImage>) request.getAttribute("listOddImage");
    ArrayList<Album> listAlbum = null;
    Object attribute = request.getAttribute("listAlbum");

    if (attribute != null && attribute instanceof ArrayList<?>) {
        listAlbum = (ArrayList<Album>) attribute;
    } else {
        listAlbum = new ArrayList<Album>();
    }

    Locale vnLocal = new Locale("vi", "VN");
    DecimalFormat vndFormat = new DecimalFormat("#,### VND");
%>
<%
    String albumStr = "Danh sách album";
    String oddStr = "Danh sách ảnh lẻ";
%>
<%
    int totalPage = (int) request.getAttribute("totalPage");
    int currentPage = (int) request.getAttribute("currentPage");
%>
<!-- Topbar Start -->
<div class="container-fluid">

    <div class="row align-items-center py-3 px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a href="index" class="text-decoration-none">
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
            <a href="./orderManager" class="btn border">
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
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Quản lí sản phẩm</h1>
    </div>
</div>
<!-- Page Header End -->


<!-- Cart Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <div class="col-lg-0 table-responsive mb-5">
            <h2 class="text-center mb-5 text-uppercase"><%=albumStr%>
            </h2>
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark">
                <tr>
                    <th>Tên bộ sưu tập</th>
                    <th>Giá</th>
                    <th>Giảm giá</th>
                    <th>Thuộc chủ đề</th>
                    <th>Ẩn</th>
                    <th>Sửa</th>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <%if (listAlbum.size() == 0) {%>
                <tr>
                    <td>Chưa có album nào</td>
                </tr>
                <%} else {%>
                <%for (Album album : listAlbum) {%>
                <%
                    boolean showAlbum = album.isShow();
                    String eyeIconClass = showAlbum ? "fa-regular fa-eye" : "fa-regular fa-eye-slash";
                    String title = showAlbum ? "Ẩn" : "Bán lại";
                %>
                <tr>
                    <td class="text-left"><img class="mr-5"
                                               src=<%=album.getListImage().get(album.getListImage().size()-1) ==null ? "" :album.getListImage().get(album.getListImage().size()-1) %> alt=""
                                               style="width: 50px;"> <%=album.getName()%>
                    </td>
                    <td class="align-middle"><%=vndFormat.format(album.getPrice())%>
                    </td>
                    <td class="align-middle"><%=vndFormat.format(album.getDiscount())%>
                    </td>
                    <td class="align-middle">
                        <p class="text-center"><%=album.getBelongTopic()%>
                        </p>
                    </td>
                    <td class="align-middle"><a title="<%=title%>" class="btn btn-sm btn-primary"
                                                data-id=<%=album.getIdAlbum()%> data-toggle="modal"
                                                data-target="#showAlbum"><i class="<%= eyeIconClass %>"></i></a></td>

                    <td class="align-middle">
                        <a href="album?q=<%=album.getIdAlbum()%>/edit" class="btn btn-sm btn-primary"><i
                                class="fa-solid fa-pen"></i></a>
                    </td>
                    <td class="align-middle">
                        <button data-id="<%=album.getIdAlbum()%>" data-target="#deleteAlbum" data-toggle="modal"
                                class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button>
                    </td>
                </tr>
                <%}%>
                <%}%>
                </tbody>
            </table>
        </div>
        <div class="col-lg-0 table-responsive mb-5">
            <h2 class="text-center mb-5 text-uppercase"><%=oddStr%>
            </h2>
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark">
                <tr>
                    <th>Tên ảnh</th>
                    <th>Giá</th>
                    <th>Giảm giá</th>
                    <th>Thuộc chủ đề</th>
                    <th>Ẩn</th>
                    <th>Sửa</th>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <%if (listOddImage == null || listOddImage.size() == 0) {%>
                <tr>
                    <td>Chưa có ảnh lẻ nào</td>
                </tr>
                <%} else {%>
                <%for (OddImage odd : listOddImage) {%>
                <%
                    boolean showOdd = odd.isShow();
                    String eyeIconClass = showOdd ? "fa-regular fa-eye" : "fa-regular fa-eye-slash";
                    String title = showOdd ? "Ẩn" : "Bán lại";
                %>
                <tr>
                    <td class="text-left"><img class="mr-5" src=<%=odd.getImage()%> alt=""
                                               style="width: 50px;"> <%=odd.getName()%>
                    </td>
                    <td class="align-middle"><%=vndFormat.format(odd.getPrice())%>
                    </td>
                    <td class="align-middle"><%=vndFormat.format(odd.getDiscount())%>
                    </td>
                    <td class="align-middle">
                        <p class="text-center"><%=odd.getBelongTopic()%>
                        </p>
                    </td>
                    <td class="align-middle"><a title="<%=title%>" class="btn btn-sm btn-primary"
                                                data-id=<%=odd.getIdOddImage()%> data-toggle="modal"
                                                data-target="#showOddImage"><i class="<%= eyeIconClass %>"></i></a></td>
                    <td class="align-middle">
                        <a href="oddImage?q=<%=odd.getIdOddImage()%>/edit"
                           class="btn btn-sm btn-primary" id="btn-change"><i class="fa-solid fa-pen"></i></a>
                    </td>
                    <td class="align-middle">
                        <button data-id="<%=odd.getIdOddImage()%>" data-toggle="modal" data-target="#deleteOdd"
                                class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button>
                    </td>
                </tr>
                <%}%>
                <%}%>
                </tbody>
            </table>
            <nav aria-label="Page navigation" class="mt-5">
                <ul class="pagination justify-content-center mb-3">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Quay lại</span>
                        </a>
                    </li>
                    <%for (int i = 1; i <= totalPage; i++) {%>
                    <%String s = currentPage == i ? "active" : "";%>
                    <li class="page-item ml-1 <%=s%>"><a class="page-link" href="./product?page=<%=i%>"><%=i%>
                    </a></li>
                    <%}%>
                    <%--                            <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
                    <%--                            <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Tiếp</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

        <div class="col-lg-6 table-responsive mb-5">
            <div class="card-header bg-secondary border-0">
                <h6 class="font-weight-semi-bold m-0">Thêm album mới </h6>
            </div>
            <form class="mb-5  mt-4" action="./album" method="post" id="formAlbum" enctype="multipart/form-data"
                  accept-charset="UTF-8">
                <div class="input-group d-flex justify-content-between mt-3">
                    <select class="form-control p-3 h-100 w-100" name="nameTopic">
                        <option value="">Vui lòng chọn chủ đề</option>
                        <%for (String nameTopic : listNamesTopic) {%>
                        <option value="<%=nameTopic%>"><%=nameTopic%>
                        </option>
                        <%}%>
                    </select>
                    <p class="show-message text-danger mt-2">
                        <%= errNameTopic_Album%>

                    </p>
                </div>
                <div class="input-group d-flex justify-content-between mt-4">
                    <input name="nameAlbum" type="text" id="name-album" class="form-control p-3 w-100"
                           placeholder="Tên bộ sưu tập">
                    <p class="show-message text-danger mt-2">
                        <%= errNameAlbum%>
                        <%=errNameExist%>
                    </p>
                </div>
                <div class="input-group d-flex justify-content-between mt-3">
                    <input name="price" type="number" id="price-album" class="form-control p-3 w-100" placeholder="Giá">
                    <p class="show-message text-danger mt-2">
                        <%= errPriceAlbum%>
                    </p>
                </div>
                <div class="input-group d-flex justify-content-between mt-3">
                    <input name="discount" value="0" type="number" id="discount-album" class="form-control p-3 w-100"
                           placeholder="Giảm giá">
                    <p class="show-message text-danger mt-2">
                        <%= errDiscountAlbum%>
                    </p>
                </div>
                <div class="input-group d-flex justify-content-between mt-3">
                    <textarea name="description" cols="30" rows="5" class="form-control w-100"
                              placeholder="Mô tả sản phẩm"></textarea>
                    <p class="show-message text-danger mt-2">
                        <%= errDescriptionAlbum%>
                    </p>
                </div>
                <div class="input-group d-flex justify-content-between mt-3">
                    <input type="file" accept="image/*" style="height: 100%;" class="form-control p-3 w-100 upload-img"
                           placeholder="Tải ảnh lên" name="listImg">
                    <p class="show-message text-danger mt-2">
                        <%= errImageForAlbum%>
                    </p>
                </div>
                <div id="dynamic-input-container"></div>


                <!-- Container to dynamically append new input -->
                <div id="show-upload-img" class="input-group d-flex  mt-3">

                </div>
                <div class="input-group-append mt-4">
                    <button class="btn btn-primary">Đăng bán</button>
                </div>
            </form>
        </div>
        <div class="col-lg-6 table-responsive mb-5">
            <div class="card-header bg-secondary border-0">
                <h6 class="font-weight-semi-bold m-0">Thêm ảnh lẻ mới</h6>
            </div>
            <form class="mb-5  mt-4" id="form-odd" method="post" action="./oddImage" enctype="multipart/form-data">
                <div class="input-group d-flex justify-content-between mt-3">
                    <%--                    <input type="text" id="idbts-odd" class="form-control p-3" placeholder="Thuộc chủ đề">--%>
                    <select class="form-control p-3 h-100 w-100" name="nameTopic" id="">
                        <option value="">Vui lòng chọn chủ đề</option>
                        <%for (String nameTopic : listNamesTopic) {%>
                        <option value="<%=nameTopic%>"><%=nameTopic%>
                        </option>
                        <%}%>
                    </select>
                    <p class="show-message text-danger mt-2">
                        <%= errNameTopic%>

                    </p>
                </div>
                <div class="input-group d-flex justify-content-between mt-4">
                    <input name="nameImg" type="text" id="name-odd" class="form-control p-3 w-100"
                           placeholder="Tên ảnh">
                    <p class="show-message text-danger mt-2">
                        <%= errNameImg%>
                        <%=errNameOddExist%>
                    </p>
                </div>
                <div class="input-group d-flex justify-content-between mt-3">
                    <input name="price" type="number" id="price-odd" class="form-control p-3 w-100" placeholder="Giá">
                    <p class="show-message text-danger mt-2">
                        <%= errPrice%>
                    </p>

                </div>
                <div class="input-group d-flex justify-content-between mt-3">
                    <input name="discount" value="0" type="number" id="discount-odd" class="form-control p-3 w-100"
                           placeholder="Giảm giá">
                    <p class="show-message text-danger mt-2">
                        <%=errDiscount%>
                    </p>
                </div>
                <div class="input-group d-flex justify-content-between mt-3">
                    <textarea name="description" id="message" cols="30" rows="5" class="form-control w-100"
                              placeholder="Mô tả sản phẩm"></textarea>
                    <p class="show-message text-danger mt-2">
                        <%= errDescription%>
                    </p>
                </div>
                <div class="input-group d-flex justify-content-between mt-3">
                    <input type="file" style="height: 100%;" id="oddImage" class="form-control p-3 w-100"
                           placeholder="Tải ảnh lên" accept="image/*" name="oddImage">
                    <p class="show-message text-danger mt-2">
                        <%= errImg%>
                    </p>
                </div>
                <img src="" alt="" class="mt-4" id="show-image-odd" style="height: 400px;">
                <div class="input-group-append mt-4">
                    <button class="btn btn-primary">Đăng bán</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Cart End -->

<script type="module" src="js/quanlisanpham.js"></script>
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
                        <a class="text-dark mb-2" href="albumnew.html"><i class="fa fa-angle-right mr-2"></i>Bộ sưu
                            tập mới</a>
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

<%--Delete--%>
<div id="deleteOdd" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Xóa ảnh</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Bạn có chắc chắn muốn ảnh này không ? </p>
            </div>
            <div class="modal-footer">
                <button id="btn-delete-odd-image" type="button" class="btn btn-danger">Xóa</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>

<%--Delete album--%>
<div id="deleteAlbum" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Xóa album</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Bạn có chắc chắn muốn xóa album này không ? </p>
            </div>
            <div class="modal-footer">
                <button id="btn-delete-album" type="button" class="btn btn-danger">Xóa</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>
<div id="showOddImage" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Ẩn chủ đề</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Bạn có chắc chắn muốn thay đổi tùy chỉnh ? </p>
            </div>
            <div class="modal-footer">
                <button id="btn-hidden-odd" type="button" class="btn btn-danger">Cập nhật</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>
<div id="showAlbum" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Ẩn chủ đề</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Bạn có chắc chắn muốn thay đổi tùy chỉnh ? </p>
            </div>
            <div class="modal-footer">
                <button id="btn-hidden-album" type="button" class="btn btn-danger">Cập nhật</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>
<script src="./js/Dialog.js"></script>
<script>
    Dialog("#deleteOdd", '#btn-delete-odd-image', "/product/deleteOddImage", 'idOddImage', 'delete')
    Dialog("#deleteAlbum", '#btn-delete-album', "/product/deleteAlbum", 'idAlbum', 'delete')
    Dialog("#showOddImage", "#btn-hidden-odd", "/product/editShowOddImage", "idOddImage", "put")
    Dialog("#showAlbum", "#btn-hidden-album", "/product/editShowAlbum", "idAlbum", "put")
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
<script>
    $(document).ready(function () {
        // Lắng nghe sự kiện thay đổi trên input file
        $(document).on('change', '.upload-img', function () {
            // Kiểm tra xem có file được chọn hay không
            if (this.files.length > 0) {
                // Sao chép input hiện tại và thêm vào container
                var newInput = $(this).clone();
                $('#dynamic-input-container').append(newInput);

                // Reset giá trị của input mới để không ảnh hưởng đến input trước đó
                newInput.val('');

                // Thêm sự kiện thay đổi cho input mới (nếu cần)
                newInput.on('change', function () {
                    // Xử lý sự kiện khi có thay đổi trên input mới
                });
            }
        });
    });
</script>
<script>
    function loadPage(pageNumber) {
        $.ajax({
            type: "GET",
            url: "./product",
            data: {page: pageNumber},
            success: function (data) {
                $("#itemContainer").html(data);
            },
            error: function () {
                alert("Lỗi khi tải dữ liệu trang.");
            }
        });
    }

    $(document).ready(function () {
        // Mặc định, tải trang đầu tiên khi trang web được nạp
        loadPage(1);

        // Bắt sự kiện click trên liên kết "Trang trước"
        $(document).on("click", "a#prevPage", function (event) {
            event.preventDefault();
            var currentPage = parseInt($("#currentPage").val());
            loadPage(currentPage - 1);
        });

        // Bắt sự kiện click trên liên kết "Trang sau"
        $(document).on("click", "a#nextPage", function (event) {
            event.preventDefault();
            var currentPage = parseInt($("#currentPage").val());
            loadPage(currentPage + 1);
        });
    });
</script>

</html>