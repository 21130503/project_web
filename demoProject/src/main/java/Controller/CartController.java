package Controller;

import DAO.ProductDAO;
import DAO.TopicDAO;
import cart.Cart;
import cart.CartProduct;
import favourite.Favourite;
import nhom26.Album;
import nhom26.OddImage;
import nhom26.User;
import org.json.JSONObject;

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

        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }


    //Xử lí các chức năng của giỏ như thêm, sửa, xóa
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String id = req.getParameter("idProduct");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        JSONObject jsonObject = new JSONObject();
        if (cart == null) {
            cart = new Cart();
        }

        if ( cart.add(type, type + id, Integer.parseInt(id))) {
            session.setAttribute("cart", cart);
            jsonObject.put("status", 200);
            jsonObject.put("message", "Thêm thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("message", "Thêm không thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }

    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");
        String id = req.getParameter("idProduct");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        JSONObject jsonObject = new JSONObject();
        if(cart.remove(type+id)){
            session.setAttribute("cart", cart);
            jsonObject.put("status", 200);
            jsonObject.put("message", "Xóa thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }
        else {
            session.setAttribute("cart", cart);
            jsonObject.put("status", 500);
            jsonObject.put("message", "Xóa thất bại");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        JSONObject jsonObject = new JSONObject();
        if(cart.removeAll()){
            session.setAttribute("cart", cart);
            jsonObject.put("status", 200);
            jsonObject.put("message", "Xóa thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }
        else{
            session.setAttribute("cart", cart);
            jsonObject.put("status", 500);
            jsonObject.put("message", "Xóa không thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }
    }
}
