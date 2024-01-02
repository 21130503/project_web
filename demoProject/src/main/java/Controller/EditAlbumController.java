package Controller;

import DAO.ImageDAO;
import DAO.ProductDAO;
import DAO.TopicDAO;
import Upload.UploadFile;
import com.mysql.cj.xdevapi.JsonArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB)
@WebServlet(name = "EditAlbumController", value = "/editAlbum")
public class EditAlbumController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding(("UTF-8"));
        String idAlbum = req.getParameter("idAlbum");
        String nameAlbum = req.getParameter("nameAlbum");
        String price = req.getParameter("price");
        String discount = req.getParameter("discount");
        String description = req.getParameter("description");
        String nameTopic = req.getParameter("nameTopic");
        String deleteImage = req.getParameter("deleteImage");
        ArrayList<String> imageDeleteFinal = new ArrayList<>();
        if(deleteImage.length()>1){
            String cleanedString = deleteImage.substring(1, deleteImage.length() - 1);
            String[] pathsArray = cleanedString.split(",");
            for (String path : pathsArray) {
                imageDeleteFinal.add(path.substring(1, path.length() - 1));
            }
        }

        ProductDAO productDAO = new ProductDAO();
        ImageDAO imageDAO = new ImageDAO();
        TopicDAO topicDAO = new TopicDAO();
        UploadFile uploadFile = new UploadFile();
        ArrayList<String> listFileNames = new ArrayList<>();
//        invalidate
        if (imageDeleteFinal.size() == imageDAO.getAllImageByIdAlbum(Integer.parseInt(idAlbum)).size()) {
            req.setAttribute("errDelete", "Không thể xóa tất cả các ảnh");
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("album", productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(idAlbum)));
            req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
            return;
        }
        if (nameAlbum == null || nameAlbum.isEmpty()) {
            req.setAttribute("errName", "Vui lòng nhập trường này");
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("album", productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(idAlbum)));
            req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
            return;
        }
        if (productDAO.checkNameAlbumExist(nameAlbum)) {
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errName", "Tên đã tồn tại");
            req.setAttribute("album", productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(idAlbum)));
            req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
            return;
        }
        if (price == null || price.isEmpty()) {
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errPrice", "Vui lòng nhập trường này");
            req.setAttribute("album", productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(idAlbum)));
            req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
            return;
        }
        if (Integer.parseInt(price) < 0) {
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errPrice", "Vui lòng nhập lại trường này");
            req.setAttribute("album", productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(idAlbum)));
            req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
            return;
        }
        if (discount == null || discount.isEmpty() || Integer.parseInt(discount) < 0) {
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errDiscount", "Vui lòng nhập trường này");
            req.setAttribute("album", productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(idAlbum)));
            req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
            return;
        }
        if (Integer.parseInt(price) < Integer.parseInt(discount)) {
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errDiscount", "Giá giảm không vượt quá giá bán");
            req.setAttribute("album", productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(idAlbum)));
            req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
            return;
        }
        if (Integer.parseInt(price) < Integer.parseInt(discount)) {
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errDiscount", "Giá giảm không lớn hơn giá bán");
            req.setAttribute("album", productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(idAlbum)));
            req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
            return;
        }
        if (description == null || description.isEmpty()) {
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errDescription", "Vui lòng nhập trường này");
            req.setAttribute("album", productDAO.getAlbumByIdForAdminUpdate(Integer.parseInt(idAlbum)));
            req.getRequestDispatcher("EditAlbum.jsp").forward(req, resp);
            return;
        }

        for (Part part : req.getParts()) {
            String fileName = uploadFile.extractFileName(part);
            // Tạo một tên tệp duy nhất

            if(fileName.length() >3 || !fileName.isEmpty()){
                listFileNames.add(fileName);
                try {
                    part.write(uploadFile.getFolderUpload().getAbsolutePath() + File.separator + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        int idTopic = topicDAO.getIdTopicByName(nameTopic);

        if (productDAO.updateAlbum(idTopic, idAlbum, nameAlbum, description, Integer.parseInt(price), Integer.parseInt(discount), imageDeleteFinal,listFileNames)) {
            resp.sendRedirect("product");
        }

    }

}
