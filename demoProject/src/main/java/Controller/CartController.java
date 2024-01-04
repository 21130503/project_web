package Controller;

import DAO.ProductDAO;
import DAO.TopicDAO;
import cart.Cart;
import cart.CartProduct;
import nhom26.Album;
import nhom26.OddImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {

    //    Lấy ra xem phần topic từ lớp productdao
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ProductDAO productDAO = new ProductDAO();

        TopicDAO topicDAO = new TopicDAO();
        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        req.setAttribute("listAlbumNew", topicDAO.getAllTopics());
        req.setAttribute("listOddNew", topicDAO.getAllTopics());

        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }


    //Xử lí các chức năng của giỏ như thêm, sửa, xóa
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String idProduct = req.getParameter("idProduct");
        String type = req.getParameter("type");

        int productId = Integer.parseInt(idProduct);

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        if ("add".equals(action)) {
            //Chức năng thêm sản phẩm vào giỏ hàng
            ProductDAO productDAO = new ProductDAO();
            if ("odd".equals(type)) {
                OddImage oddImage = productDAO.getOddImageById(productId);
                if (oddImage != null) {
                    cart.addProduct(oddImage, 1);
                }
            } else if ("album".equals(type)) {
                Album album = productDAO.getAlbumById(productId);
                if (album != null) {
                    cart.addProduct(album, 1);
                }
            }

            session.setAttribute("cart", cart);
            resp.sendRedirect("cart");

        } else if ("remove".equals(action)) {
            //Chức năng xóa từng sản phẩm khỏi giỏ hàng
            cart.remove(productId, type);

            session.setAttribute("cart", cart);
            resp.sendRedirect("cart");


        } else if ("increase".equals(action) || "decrease".equals(action)) {
            // Chức năng tăng giảm số lượng sản phẩm bằng nút bấm  '+'  '-'
            CartProduct cartProduct = cart.getData().get(productId);

            if (cartProduct != null) {
                if ("increase".equals(action)) {
                    cartProduct.increQuantity(1);

                } else if ("decrease".equals(action)) {
                    if (cartProduct.getQuantity() <= 1) {
                        cartProduct.setQuantity(1);
                        session.setAttribute("errorMessage", "Bạn không thể giảm số lượng sản phẩm ít hơn 1. " +
                                "Nếu bạn muốn xóa, vui lòng bấm nút X.");
                    } else {
                        cartProduct.decreQuantity(1);
                    }
                }
            }

            session.setAttribute("cart", cart);
            resp.sendRedirect("cart");

        } else if ("updateQuantity".equals(action)) {
            //Chức năng cho phép người dùng đặt số lượng sản phẩm theo 1 số nhất định
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            CartProduct cartProduct = cart.getData().get(productId);

            if (cartProduct != null && quantity > 0) {
                cartProduct.setQuantity(quantity);
            }

            session.setAttribute("cart", cart);
            resp.sendRedirect("cart");
        }
    }

}
