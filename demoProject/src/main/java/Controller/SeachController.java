package Controller;

import DAO.ProductDAO;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SearchController" , value = "/search")
public class SeachController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String param = req.getParameter("q");
        ProductDAO productDAO = new ProductDAO();
        req.setAttribute("param", param);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user== null || !user.isAdmin()){
            req.setAttribute("listOddImage", productDAO.searchOddImageWithParam(param));
            req.setAttribute("listAlbum", productDAO.searchAlbumWithParam(param));
        }
        else if(user.isAdmin()){
            req.setAttribute("listOddImage", productDAO.searchOddImageWithParamForAdmin(param));
            req.setAttribute("listAlbum", productDAO.searchAlbumWithParamForAdmin(param));
        }
        req.getRequestDispatcher("search.jsp").forward(req,resp);

    }
}
