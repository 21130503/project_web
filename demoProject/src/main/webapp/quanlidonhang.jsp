<%@ page import="java.util.ArrayList" %>
<%@ page import="nhom26.OddImage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="nhom26.Album" %>
<%@ page import="nhom26.Order" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Quản lí đơn hàng|| Admin</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100.000 VNĐ;200;300;400;500;600;700;800;900&display=swap"
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
</head>

<body id="listOrderContainer">
<% ArrayList<Order> listOrder = request.getAttribute("listOrder") == null ? new ArrayList<>() : (ArrayList<Order>) request.getAttribute("listOrder");
%>
<%
    Locale vnLocal = new Locale("vi", "VN");
    DecimalFormat vndFormat = new DecimalFormat("#,### VNĐ");%>
<!-- Topbar Start -->
<%
    int totalPage = (int) request.getAttribute("totalPage");
    int currentPage = (int) request.getAttribute("currentPage");
    String type = (String) request.getAttribute("type");
%>
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
            <a href="quanlichude.jsp" class="btn border">
                <i class="fa-solid fa-boxes-stacked text-primary"></i>
            </a>
            <a href="./quanlidonhang.html" class="btn border">
                <i class="fas fa-shopping-cart text-primary"></i>
            </a>
            <a href="quanlinguoidung.jsp" class="btn border">
                <i class="fa-regular fa-user text-primary"></i>

            </a>
            <a href="quanlisanpham.jsp" class="btn border">
                <i class="fa-brands fa-product-hunt text-primary"></i>
            </a>
        </div>
    </div>
</div>
<!-- Topbar End -->


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Quản lí đơn hàng</h1>
    </div>
</div>
<!-- Page Header End -->


<!-- Cart Start -->
<div class="container-fluid pt-5">
    <div class="mb-4">
        <h4>Trạng thái đơn hàng</h4>
        <form action="./orderManager" id="myForm">
            <p><input type="radio" class="" name="option" value="all"> Tất cả</p>
            <p><input type="radio" class="" name="option" value="Đang chuẩn bị"> Đang chuẩn bị</p>
            <p><input type="radio" class="" name="option" value="Đã giao cho đơn vị vận chuyển"> Đã giao cho đơn vị vận
                chuyển</p>
            <p><input type="radio" class="" name="option" value="Đang vận chuyển"> Đang vận chuyển</p>
            <p><input type="radio" class="" name="option" value="Đã giao"> Đã giao</p>
            <p><input type="radio" class="" name="option" value="Đã hủy"> Đã hủy</p>
        </form>
    </div>
    <div class="row px-xl-5">
        <div id="" class="col-lg-0 table-responsive mb-5">
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark">
                <tr>
                    <th>Id đơn hàng</th>
                    <th>Id người mua</th>
                    <th>Tên người nhận</th>
                    <th>Số điện thoại</th>
                    <th>Tên đơn hàng</th>
                    <th>Số lượng</th>
                    <th>Trạng thái</th>
                    <th>Địa chỉ</th>
                    <th>Thành tiền</th>
                    <th>Hủy đơn</th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <%if (listOrder.size() == 0) {%>
                <%} else {%>
                <%for (Order order : listOrder) {%>
                <%
                    String href = "cart".equals(order.getType()) ? "#" : ("./detail?type=" + order.getType() + "&id=" + order.getIdProduct());
                %>
                <tr>
                    <td class="align-middle"><%=order.getIdOrder()%>
                    </td>
                    <td class="align-middle"><%=order.getIdByer()%>
                    </td>
                    <td class="align-middle"><%=order.getReceiver()%>
                    </td>
                    <td class="align-middle"><%=order.getPhoneNumber()%>
                    </td>
                    <td class="align-middle"><a href="<%=href%>"><%=order.getNameProduct()%>
                    </a></td>
                    <td class="align-middle"><%=order.getQuantity()%>
                    </td>
                    <td class="align-middle"><a
                            href="./editShip?q=<%=order.getIdOrder()%>&type=<%=order.getType()%>"><%=order.getStatus()%>
                    </a></td>
                    <td class="align-middle"><%=order.getAddress()%>
                    </td>
                    <td class="align-middle"><%=vndFormat.format(order.getTotalPrice())%>
                    </td>
                    <td class="align-middle">
                        <button data-id="<%=order.getIdOrder()%>" value="<%=order.getType()%>" data-toggle="modal"
                                data-target="#deleteOrderAdmin" class="btn btn-sm btn-primary">
                            <i class="fa fa-times"></i></button>
                    </td>
                </tr>
                <%
                        }
                    }
                %>

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
                    <li class="page-item ml-1 <%=s%>"><a class="page-link" href="./orderManager?option=<%=type%>&page=<%=i%>"><%=i%>
                    </a></li>
                    <%}%>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Tiếp</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

    </div>
</div>
<!-- Cart End -->


<!-- Footer Start -->
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
                        <a class="text-dark mb-2" href="./index"><i class="fa fa-angle-right mr-2"></i>Trang
                            chủ</a>
                        <a class="text-dark mb-2" href="./shop"><i class="fa fa-angle-right mr-2"></i>Của
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

<div id="deleteOrderAdmin" class="modal" tabindex="-1" role="dialog">
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
<script>
    $(document).ready(function () {
        $("input[name='option']").change(function () {
            submitForm();
        });

        function submitForm() {
            var selectedOption = $("input[name='option']:checked");

            if (selectedOption.length > 0) {
                var actionValue = selectedOption.val();

                // Sử dụng AJAX để gửi yêu cầu GET
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/demoProject_war/orderManager",
                    data: {option: actionValue},
                    success: function (data) {
                        $("#listOrderContainer").empty();
                        // Xử lý phản hồi từ server và hiển thị tại trang
                        $("#listOrderContainer").html(data);
                    },
                    error: function (error) {
                        console.error("Error:", error);
                    }
                });
            } else {
                alert("Vui lòng chọn một tùy chọn!");
            }
        }
    });
</script>
<script>
    // Dialog("#deleteOrder","#btn-delete-order","/order/odd","idOrder", "delete")
    let where;
    document.addEventListener("DOMContentLoaded", () => {
        $("#deleteOrderAdmin").on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget) // Button that triggered the modal
            let type = button.val()
            id = button.data('id')// Extract info from data-* attributes
            if (type === "odd") {
                where = "/order/odd"
            }
            if (type === "album") {
                where = "/order/album"
            }
            if (type === "cart") {
                where = "/order/cart"
            }
        })
        const btnDelete = document.querySelector("#btn-delete-order")
        btnDelete.addEventListener('click', async () => {

            const xhr = new XMLHttpRequest();
            const url = `http://localhost:8080/demoProject_war/${where}?idOrder=${id}`;

            xhr.open('DELETE', url, true);

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