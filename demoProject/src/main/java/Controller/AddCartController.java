package Controller;

import DAO.ProductDAO;
import cart.Cart;
import nhom26.Album;
import nhom26.OddImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "AddCartController", value = "/add-cart")
public class AddCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProduct = req.getParameter("idProduct");
        String type = req.getParameter("type");

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        ProductDAO productDAO = new ProductDAO();
        if (cart == null) {
            cart = new Cart(); // Nếu bạn không sử dụng ProductDAO trong Cart, thì không cần truyền nó vào đây
            session.setAttribute("cart", cart);
        }

        if ("odd".equals(type)) {
            OddImage oddImage = productDAO.getOddImageById(Integer.parseInt(idProduct));
            if (oddImage.getName() != null) {
                cart.addProduct(oddImage, 1);
            }
        } else if ("album".equals(type)) {
            Album album = productDAO.getAlbumById(Integer.parseInt(idProduct));
            if (album.getName() != null) {
                cart.addProduct(album, 1);
            }
        }

        session.setAttribute("cart", cart);

        resp.sendRedirect("cart");

//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().write("{\"message\":\"Sản phẩm đã được thêm vào giỏ hàng\"}");
    }
}
