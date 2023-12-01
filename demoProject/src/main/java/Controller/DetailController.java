package Controller;

import DAO.ProductDAO;
import nhom26.Album;
import nhom26.OddImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetailController", value = "/detail/*")
public class DetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        String path = req.getPathInfo();
//        String[] parts = path.split("/");
//        int id = Integer.parseInt(parts[parts.length-1]);
//        String type = parts[parts.length-2];
//        System.out.println(type);
//        if(type.equals("album")){
//            ProductDAO productDAO = new ProductDAO();
//            Album album = productDAO.getAlbumById(id);
//            req.setAttribute("detail", album);
//            req.getRequestDispatcher("detail.jsp").forward(req,resp);
        resp.sendRedirect("index.jsp");
//        } else if (type.equals("odd")) {
//            ProductDAO productDAO = new ProductDAO();
//            OddImage oddImage = productDAO.getOddImageById(id);
//            req.setAttribute("detail", oddImage);
//            req.getRequestDispatcher("detail.jsp").forward(req,resp);
//        }
    }
}
