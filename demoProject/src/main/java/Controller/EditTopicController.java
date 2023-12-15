package Controller;

import DAO.TopicDAO;
import Upload.UploadFile;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB)
@WebServlet(name = "EditTopicController", value = "/editTopic")
public class EditTopicController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user") == null ? null : (User) session.getAttribute("user");
//        // Kiểm tra quyền và chuyển hướng
        TopicDAO topicDAO = new TopicDAO();
        String id = req.getParameter("q").substring(0, 1);
//        if (user == null || !user.isAdmin()) {
//            System.out.println("redirect");
//            resp.sendRedirect("404.jsp");
//            return;
//        } else if (user.isAdmin()) {
//            System.out.println("GET");
        req.setAttribute("topic", topicDAO.getTopicById(id));
        req.getRequestDispatcher("EditTopic.jsp").forward(req, resp);
//        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String idTopic = req.getParameter("idTopic");
        String nameTopic = req.getParameter("nameTopic");
        System.out.println(idTopic + nameTopic);
        String fileName = null;
        TopicDAO topicDAO = new TopicDAO();
        UploadFile uploadFile = new UploadFile();
        if(nameTopic == null || nameTopic.isEmpty()){
            req.setAttribute("errNameTopic", "Không được để trống trường này");
            req.setAttribute("topic", topicDAO.getTopicById(idTopic));
            req.getRequestDispatcher("EditTopic.jsp").forward(req,resp);
            return;
        }
        if(topicDAO.checkNameTopicExistForUpdate(idTopic,nameTopic)){
            req.setAttribute("errNameTopic", "Tên topic đã tồn tại");
            req.setAttribute("topic", topicDAO.getTopicById(idTopic));
            req.getRequestDispatcher("EditTopic.jsp").forward(req,resp);
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
        if (fileName == null || fileName.isEmpty()) {
            fileName = topicDAO.getInterfaceImage(idTopic);
            if(topicDAO.updateTopic(idTopic, nameTopic, fileName)){
                resp.sendRedirect("topic");
            }
        }
        if(topicDAO.updateTopic(idTopic, nameTopic, "/images/" +fileName)){
            resp.sendRedirect("topic");
        }

    }
}
