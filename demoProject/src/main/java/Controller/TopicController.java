package Controller;

import DAO.TopicDAO;
import DAO.UserDAO;
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
        String name = null;
        String filename = null;
        TopicDAO topicDAO = new TopicDAO();

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            // Lấy đường dẫn thực tế của thư mục /images trong ứng dụng
            String realPath = req.getServletContext().getRealPath("/images");
            // Kiểm tra xem thư mục có tồn tại không, nếu không thì tạo mới
            File imagesDirectory = new File(realPath);
            if (!imagesDirectory.exists()) {
                imagesDirectory.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }
            for (FileItem item : items) {
                if (item.isFormField() && "nameTopic".equals(item.getFieldName())) {
                    name = item.getString("utf-8");
                    if (topicDAO.checkNameTopicExist(name)) {
                        req.setAttribute("Exist", "Tên chủ đề đã tồn tại");
                        req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
                        return;
                    }
                    if (name.trim().length() == 0) {
                        req.setAttribute("ErrNameTopic", "*Vui lòng nhập trường này");
                        req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
                    }

                } else if (!item.isFormField() && "interfaceImage".equals(item.getFieldName())) {
                    if(item.getSize() >0 ){
//                        String originalFileName = item.getName();
//                        int index = originalFileName.lastIndexOf(".");
//                        String ext = originalFileName.substring(index +1);
//                        String fileName = System.currentTimeMillis() + "." +ext;
//                        File file = new File()
                    filename = Paths.get(item.getName()).getFileName().toString();
                    }

                    // Lưu tệp ảnh vào thư mục /images
                    File uploadedFile = new File(realPath, filename);
                    item.write(uploadedFile);
                }
            }
            if (filename == null) {
                req.setAttribute("errImage", "Vui lòng nhập ảnh bìa");
                req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
            }
            if (topicDAO.insertTopic(name, "/images/" + filename)) {
                req.setAttribute("success", "Thêm chủ đề thành công");
                req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi khi xử lý upload file");
            req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi xử lý không xác định");
            req.getRequestDispatcher("quanlichude.jsp").forward(req, resp);
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
