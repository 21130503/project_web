package Controller;

import DAO.ProductDAO;
import DAO.TopicDAO;
import Upload.UploadFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB)
@WebServlet(name = "OddImageController", value = "/oddImage")
public class OddImageController extends HttpServlet {
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
        int discount = Integer.parseInt(req.getParameter("discount"));
        String description = req.getParameter("description");
        String fileName = null;

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
        System.out.println(nameTopic + " " + nameImg + " " + price + " " + discount + " " + description);
//            invalidate
        if (nameTopic == null || nameTopic.trim().isEmpty()) {
            req.setAttribute("errNameTopic", "Vui lòng chọn chủ đề");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (nameImg == null || nameImg.trim().isEmpty()) {
            req.setAttribute("errNameImg", "Vui lòng nhập tên sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (price == null || price.trim().isEmpty()) {
            req.setAttribute("errPrice", "Vui lòng nhập giá sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (description == null || description.trim().isEmpty()) {
            req.setAttribute("errDescription", "Vui lòng nhập mô tả cho sản phẩm sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (fileName == null || fileName.trim().isEmpty()) {
            req.setAttribute("errImg", "Vui lòng chọn ảnh");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (productDAO.insertOddImage(nameTopic, nameImg, "/images/" + fileName, description, Integer.parseInt(price), discount, topicDAO.checkTopicShow(nameTopic))) {
            resp.sendRedirect("product");
            return;
        }


    }
}
