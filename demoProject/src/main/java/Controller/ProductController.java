package Controller;

import DAO.ProductDAO;
import DAO.TopicDAO;
import Upload.UploadFile;
import com.mysql.cj.xdevapi.JsonValue;
import nhom26.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB)
@WebServlet(name = "ProductController",value = "/product")
public class ProductController extends HttpServlet {
    TopicDAO topicDAO = new TopicDAO();
    ProductDAO productDAO = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user") == null ? null : (User) session.getAttribute("user");
//        // Kiểm tra quyền và chuyển hướng
//        if(user == null || !user.isAdmin() ) {
//            System.out.println("redirect");
//            resp.sendRedirect( "404.jsp");
//            return;
//        }
//        else if (user.isAdmin()) {
//            System.out.println("GET");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic",topicDAO.getAllNamesTopic() );
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req,resp);
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String type = req.getPathInfo();
        System.out.println(type);
        TopicDAO topicDAO = new TopicDAO();
        UploadFile uploadFile = new UploadFile();
        if (type.equals("/addImg")) {

        } else if (type.equals("/addAlbum")) {
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getPathInfo();
        System.out.println(path);
        ProductDAO productDAO = new ProductDAO();
        JSONObject jsonObjectResults = new JSONObject();
        if(path.equals("/deleteOddImage")){
            int idOddImage = Integer.parseInt(req.getParameter("idOddImage"));
            if(productDAO.deleteOddImage(idOddImage)){
                jsonObjectResults.put("status", 200);
                jsonObjectResults.put("message", "Đã xóa ảnh thành công");

            }
            else{
                jsonObjectResults.put("status", 500);
                jsonObjectResults.put("message", "Xóa ảnh thất bại");
            }
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObjectResults.toString());

        }
        if(path.equals("/deleteAlbum")){
            int idAlbum = Integer.parseInt(req.getParameter("idAlbum"));
            System.out.println(idAlbum);
            if(productDAO.deleteAlbum(idAlbum)){
                jsonObjectResults.put("status", 200);
                jsonObjectResults.put("message", "Đã xóa album thành công");

            }
            else{
                jsonObjectResults.put("status", 500);
                jsonObjectResults.put("message", "Xóa album thất bại");
            }
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObjectResults.toString());
        }
    }
}
