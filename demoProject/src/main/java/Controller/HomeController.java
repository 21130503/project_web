package Controller;

import DAO.NotificationDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.TopicDAO;
import nhom26.Notification;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomeController", value = "/index")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        TopicDAO topicDAO = new TopicDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
//        NotificationDAO notificationDAO = new NotificationDAO();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            resp.sendRedirect("login.jsp");
            return;
        }
//        ArrayList<Notification> notifications = notificationDAO.getNotification(user.getId());
        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        req.setAttribute("listAlbumNew", productDAO.getTop8AlbumNew());
        req.setAttribute("listOddNew",productDAO.getTop8ddImageNew());
        req.setAttribute("listOddImageOrder", orderDAO.getTop8OddImageOrder());
        req.setAttribute("listAlbumOrder", orderDAO.getTop8OAlbumOrder());
//        req.setAttribute("notifications", notifications);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
