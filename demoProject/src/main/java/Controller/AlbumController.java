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
import java.util.ArrayList;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB)
@WebServlet(name = "AlbumController", value = "/album")
public class AlbumController extends HttpServlet {
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
        List<String> listFileNames = new ArrayList<>();
        if (nameTopic == null || nameTopic.trim().isEmpty()) {
            req.setAttribute("errNameTopic_Album", "Vui lòng chọn chủ đề");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (nameAlbum == null || nameAlbum.trim().isEmpty()) {
            req.setAttribute("errNameAlbum", "Vui lòng nhập tên sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (discount == null || discount.trim().isEmpty()) {
            req.setAttribute("errDiscountAlbum", "Vui lòng nhập giảm giá");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (price == null || price.trim().isEmpty()) {
            req.setAttribute("errPriceAlbum", "Vui lòng nhập giá sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        if (description == null || description.trim().isEmpty()) {
            req.setAttribute("errDescriptionAlbum", "Vui lòng nhập mô tả cho sản phẩm sản phẩm");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
            return;
        }
        for (Part part : req.getParts()) {
            String fileName = uploadFile.extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();

            try {
                part.write(uploadFile.getFolderUpload().getAbsolutePath() + File.separator + fileName);
                listFileNames.add(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(listFileNames.size() ==0){
            req.setAttribute("errImageForAlbum", "Vui lòng thêm ảnh cho album");
            req.setAttribute("listAlbum", productDAO.getAllAlbum());
            req.setAttribute("listOddImage", productDAO.getAllOddImage());
            req.setAttribute("listNamesTopic", topicDAO.getAllNamesTopic());
            req.getRequestDispatcher("quanlisanpham.jsp").forward(req, resp);
        }
    }
}
