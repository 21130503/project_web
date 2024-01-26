package Controller;

import DAO.ProductDAO;
import DAO.TopicDAO;
import Upload.UploadFile;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB)
@WebServlet(name = "AlbumController", value = "/album")
public class AlbumController extends HttpServlet {
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
                req.setAttribute("album",productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(id)));
                req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
                return;
            }
            resp.sendRedirect("index");
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

       UploadFile uploadFile = new UploadFile();
        TopicDAO topicDAO = new TopicDAO();
        ProductDAO productDAO = new ProductDAO();
        String nameTopic = req.getParameter("nameTopic");
        String nameAlbum = req.getParameter("nameAlbum");
        String price = req.getParameter("price");
        String discount = req.getParameter("discount");
        String description = req.getParameter("description");
        ArrayList<String> listFileNames = new ArrayList<>();
        if (nameTopic == null || nameTopic.trim().isEmpty()) {
            req.setAttribute("errNameTopic_Album", "Vui lòng chọn chủ đề");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (nameAlbum == null || nameAlbum.trim().isEmpty()) {
            req.setAttribute("errNameAlbum", "Vui lòng nhập tên sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (productDAO.checkNameAlbumExist(nameAlbum)) {
            req.setAttribute("errNameExist", "Tên album đã tồn tại");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (price == null || price.trim().isEmpty()) {
            req.setAttribute("errPriceAlbum", "Vui lòng nhập giá sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (Integer.parseInt(price) < 0) {
            req.setAttribute("errPriceAlbum", "Vui lòng nhập lại giá sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (discount == null || discount.trim().isEmpty() || Integer.parseInt(discount) < 0) {
            req.setAttribute("errDiscountAlbum", "Vui lòng nhập giảm giá");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (Integer.parseInt(discount) > Integer.parseInt(price)) {
            req.setAttribute("errPriceAlbum", "Giá giảm không được vượt quá giá sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (description == null || description.trim().isEmpty()) {
            req.setAttribute("errDescriptionAlbum", "Vui lòng nhập mô tả cho sản phẩm sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        for (Part part : req.getParts()) {
            String fileName = uploadFile.extractFileName(part);
            // Tạo một tên tệp duy nhất

           if(fileName.length() >3 || !fileName.isEmpty()){
               listFileNames.add(fileName);
               try {
                   part.write(uploadFile.getFolderUpload(req).getAbsolutePath()+ "/" + fileName);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }


        }

        for (String file : listFileNames) {
            System.out.println("Ảnh: " + file);
        }
        if(listFileNames.size() ==0){
            req.setAttribute("errImageForAlbum", "Vui lòng thêm ảnh cho album");
            req.setAttribute("listAlbum", productDAO.getAllAlbum(1,5));
            req.setAttribute("listOddImage", productDAO.getAllOddImage(1,5));
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
        }
        if(productDAO.insertAlbum(nameTopic,nameAlbum,description,Integer.parseInt(price),Integer.parseInt(discount),topicDAO.checkTopicShow(nameTopic),listFileNames)){
            resp.sendRedirect("product");
        }
    }
}
