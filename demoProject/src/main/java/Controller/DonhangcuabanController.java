package Controller;

import DAO.NotificationDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.TopicDAO;
import nhom26.Notification;
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
        int page =1;
        if(req.getParameter("page") !=null){
            page = Integer.parseInt(req.getParameter("page"));
        }
        int recSize = 2;
        TopicDAO topicDAO = new TopicDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        int total = Math.max(orderDAO.getCountOrderAlbum(user.getId()),
                Math.max(orderDAO.getCountOrderOdd(user.getId()),orderDAO.getCountOrderCart(user.getId())));
        int totalPage = (int) Math.ceil((double) total/recSize);
        ArrayList<Order> orders = new ArrayList<>(orderDAO.getAllOrderOddImageForUser(user.getId(),page,recSize));
        orders.addAll(orderDAO.getAllOrderAlbumForUser(user.getId(),page,recSize));
        orders.addAll(orderDAO.getAllCartOrderForUser(user.getId(),page,recSize));

        /*notification*/
        NotificationDAO notificationDAO = new NotificationDAO();
        ArrayList<Notification> notifications = notificationDAO.getNotification(user.getId());
        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        req.setAttribute("Order",orders);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("notifications", notifications);
        req.getRequestDispatcher("donhangcuaban.jsp").forward(req, resp);
    }
}
