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
        TopicDAO topicDAO = new TopicDAO();
        int recSize = 6;
        int page = 1;
        int totalOdd = productDAO.totalOdd();
        int totalAlbum = productDAO.totalAlbum();
        int totalPage = (int) Math.ceil((totalAlbum+totalOdd)/recSize);
        String type = req.getParameter("type");
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        System.out.println(type +" "+ page);
        if ("album".equals(type)) {
            req.setAttribute("listAlbum", productDAO.getAllAlbumForClient(page,recSize));
            req.getRequestDispatcher("shop.jsp").forward(req, resp);
            return;
        }
        if ("odd".equals(type)) {
            req.setAttribute("listOddImage", productDAO.getAllOddImageForClient(page,recSize));
            req.getRequestDispatcher("shop.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("listOddImage", productDAO.getAllOddImageForClient(page,recSize));
        req.setAttribute("listAlbum", productDAO.getAllAlbumForClient(page,recSize));
        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        req.setAttribute("totalPage",totalPage);
        req.setAttribute("currentPage",page);
        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }


}
