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
        TopicDAO topicDAO = new TopicDAO();
        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        ProductDAO productDAO = new ProductDAO();
        String type = req.getParameter("type");
        System.out.println("type: " + type );
        if("album".equals(type)){
            req.setAttribute("listAlbum", productDAO.getAllAlbumForClient());
            req.getRequestDispatcher("shop.jsp").forward(req, resp);
            return;
        }
        if("odd".equals(type)){
            req.setAttribute("listOddImage", productDAO.getAllOddImageForClient());
            req.getRequestDispatcher("shop.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("listOddImage", productDAO.getAllOddImageForClient());
        req.setAttribute("listAlbum", productDAO.getAllAlbumForClient());

        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }


}
