package Controller;

import DAO.ProductDAO;
import DAO.TopicDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShopController", value = "/shop")

public class ShopController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        ProductDAO productDAO = new ProductDAO();
        req.setAttribute("listOddImage", productDAO.getAllOddImage());
        req.setAttribute("listAlbum", productDAO.getAllAlbum());

        TopicDAO topicDAO = new TopicDAO();
        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        req.setAttribute("listAlbumNew", topicDAO.getAllTopics());
        req.setAttribute("listOddNew", topicDAO.getAllTopics());

        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }


}
