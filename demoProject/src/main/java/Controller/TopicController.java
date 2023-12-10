package Controller;

import DAO.TopicDAO;
import DAO.UserDAO;
import Properties.URL;
import Upload.UploadFile;
import com.mysql.cj.Constants;
import nhom26.Topic;
import nhom26.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB)
@WebServlet(name = "TopicController", value = "/topic")
public class TopicController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user") == null ? null : (User) session.getAttribute("user");
//        // Kiểm tra quyền và chuyển hướng
        TopicDAO topicDAO = new TopicDAO();
        if (user == null || !user.isAdmin()) {
            System.out.println("redirect");
            resp.sendRedirect("404.jsp");
            return;
        } else if (user.isAdmin()) {
//            System.out.println("GET");
            req.setAttribute("listTopic", topicDAO.getAllTopics());
            req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("nameTopic");
        String fileName = null;
        TopicDAO topicDAO = new TopicDAO();
        UploadFile uploadFile = new UploadFile();
        if(topicDAO.checkNameTopicExist(name)){
            req.setAttribute("listTopic", topicDAO.getAllTopics());
            req.setAttribute("exist", "Tên chủ đề đã tồn tại");
            req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
            return;
        }
        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("listTopic", topicDAO.getAllTopics());
            req.setAttribute("errName", "Vui lòng nhập tên chủ đề");
            req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
            return;
        }

        for (Part part : req.getParts()) {
             fileName = uploadFile.extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            try {
                part.write(uploadFile.getFolderUpload().getAbsolutePath() + File.separator + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(fileName == null ||fileName.trim().isEmpty()){
            req.setAttribute("listTopic", topicDAO.getAllTopics());
            req.setAttribute("errImage", "Vui lòng nhập trường này");
            req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
            return;
        }
        if(topicDAO.insertTopic(name, "/images/" + fileName)){
            req.setAttribute("listTopic", topicDAO.getAllTopics());
            req.setAttribute("success", "Vui lòng nhập trường này");
            req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String idTopic = req.getParameter("idTopic");
        JSONObject jsonObject = new JSONObject();
        TopicDAO topicDAO = new TopicDAO();
        System.out.println("Delete idTopic: " + idTopic);
        System.out.println("At line 126 : " + topicDAO.deleteTopic(idTopic));
        if (topicDAO.deleteTopic(idTopic)) {
            jsonObject.put("status", 200);
            jsonObject.put("message", "Đã xóa thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("message", "Xóa chủ đề thất bại. Vui lòng thử lại");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }
    }
}
