package Controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.TopicDAO;
import nhom26.Order;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "DonhangcuabanController", value = "/donhangcuaban")
public class DonhangcuabanController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            resp.sendRedirect("login.jsp");
            return;
        }
        TopicDAO topicDAO = new TopicDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        ArrayList<Order> orders = new ArrayList<>(orderDAO.getAllOrderOddImageForUser(user.getId()));
        orders.addAll(orderDAO.getAllOrderAlbumForUser(user.getId()));
        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        req.setAttribute("Order",orders);
        req.getRequestDispatcher("donhangcuaban.jsp").forward(req, resp);
    }
}
