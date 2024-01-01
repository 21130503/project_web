package Controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.TopicDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FavouriteController", value = "/FavouriteController")
public class FavouriteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        TopicDAO topicDAO = new TopicDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        request.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        request.setAttribute("listAlbumNew", productDAO.getTop8AlbumNew());
        request.setAttribute("listOddNew",productDAO.getTop8ddImageNew());
        request.setAttribute("listOddImageOrder", orderDAO.getTop8OddImageOrder());
        request.getRequestDispatcher("favourite.jsp").forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
