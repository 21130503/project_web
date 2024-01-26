package Controller;

import DAO.ProductDAO;
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
@WebServlet(name = "OddImageController", value = "/oddImage")
public class OddImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding(("UTF-8"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user") == null ? null : (User) session.getAttribute("user");
//        // Kiểm tra quyền và chuyển hướng
        ProductDAO productDAO = new ProductDAO();
        TopicDAO topicDAO = new TopicDAO();
        String param = req.getParameter("q");
        String[] path =  param.split("/");
        String id = path[0];
        String type = path[1];
        if (user == null || !user.isAdmin()) {
            System.out.println("redirect");
            resp.sendRedirect("404.jsp");
            return;
        } else if (user.isAdmin()) {
            System.out.println("GET");
        if("edit".equals(type)){
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("oddImage",productDAO.getOddImageByIdForAdminUpdate(Integer.parseInt(id)));
            req.getRequestDispatcher("EditOddImage.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("index");
        return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TopicDAO topicDAO = new TopicDAO();
        ProductDAO productDAO = new ProductDAO();
        UploadFile uploadFile = new UploadFile();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String nameTopic = req.getParameter("nameTopic");
        String nameImg = req.getParameter("nameImg");
        String price = req.getParameter("price");
        String discount =req.getParameter("discount");
        String description = req.getParameter("description");
        String fileName = null;


//            invalidate
        if (nameTopic == null || nameTopic.trim().isEmpty()) {
            req.setAttribute("errNameTopic", "Vui lòng chọn chủ đề");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (nameImg == null || nameImg.trim().isEmpty()) {
            req.setAttribute("errNameImg", "Vui lòng nhập tên sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if(productDAO.checkOddNameExist(nameImg)){
            req.setAttribute("errNameOddExist", "Tên sản phẩm đã tồn tại");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (price == null || price.trim().isEmpty()) {
            req.setAttribute("errPrice", "Vui lòng nhập giá sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (Integer.parseInt(price) < 0) {
            req.setAttribute("errPrice", "Vui lòng nhập lại giá sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if(discount == null || discount.trim().isEmpty() || Integer.parseInt(discount) < 0){
            req.setAttribute("errDiscount", "Vui lòng nhập giảm giá cho sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if(Integer.parseInt(discount) > Integer.parseInt(price)){
            req.setAttribute("errDiscount", "Giá giảm không được vượt quá giá sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (description == null || description.trim().isEmpty()) {
            req.setAttribute("errDescription", "Vui lòng nhập mô tả cho sản phẩm sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        for (Part part : req.getParts()) {
            fileName = uploadFile.extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            try {
//                part.write(uploadFile.getFolderUpload().getAbsolutePath() + File.separator + fileName);
                part.write(uploadFile.getFolderUpload(req).getAbsolutePath()+ "/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (fileName == null || fileName.trim().isEmpty()) {
            req.setAttribute("errImg", "Vui lòng chọn ảnh");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (productDAO.insertOddImage(nameTopic, nameImg, "/images/" + fileName, description, Integer.parseInt(price), Integer.parseInt(discount), topicDAO.checkTopicShow(nameTopic))) {
            resp.sendRedirect("product");
            return;
        }

    }
}
