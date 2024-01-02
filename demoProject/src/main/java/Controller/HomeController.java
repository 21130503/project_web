package Controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.TopicDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeController", value = "/index")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        TopicDAO topicDAO = new TopicDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        req.setAttribute("listAlbumNew", productDAO.getTop8AlbumNew());
        req.setAttribute("listOddNew",productDAO.getTop8ddImageNew());
        req.setAttribute("listOddImageOrder", orderDAO.getTop8OddImageOrder());
        req.setAttribute("listAlbumOrder", orderDAO.getTop8OAlbumOrder());
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
