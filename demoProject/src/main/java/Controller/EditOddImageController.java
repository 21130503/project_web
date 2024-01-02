package Controller;

import DAO.ProductDAO;
import DAO.TopicDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB)
@WebServlet(name = "OddImageEdit",value = "/editOddImage")
public class EditOddImageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding(("UTF-8"));
        String idOddImage =  req.getParameter("idOddImage");
        String nameOddImage = req.getParameter("nameOddImage");
        String price = req.getParameter("price");
        String discount = req.getParameter("discount");
        String description = req.getParameter("description");
        String nameTopic = req.getParameter("nameTopic");
        System.out.println(idOddImage + " "+ nameOddImage + " " + price + " " +  description + " "  + "     " + nameTopic);
        ProductDAO productDAO = new ProductDAO();
        TopicDAO topicDAO = new TopicDAO();
//        invalidate
        if(nameOddImage == null || nameOddImage.isEmpty()){
            req.setAttribute("errName" , "Vui lòng nhập trường này");
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("oddImage",productDAO.getOddImageByIdForAdminUpdate(Integer.parseInt(idOddImage)));
            req.getRequestDispatcher("EditOddImage.jsp").forward(req, resp);
            return;
        }
        if(productDAO.checkOddNameExist(nameOddImage)){
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errName" , "Tên đã tồn tại");
            req.setAttribute("oddImage",productDAO.getOddImageByIdForAdminUpdate(Integer.parseInt(idOddImage)));
            req.getRequestDispatcher("EditOddImage.jsp").forward(req, resp);
            return;
        }
        if(price == null || price.isEmpty()){
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errPrice" , "Vui lòng nhập trường này");
            req.setAttribute("oddImage",productDAO.getOddImageByIdForAdminUpdate(Integer.parseInt(idOddImage)));
            req.getRequestDispatcher("EditOddImage.jsp").forward(req, resp);
            return;
        }
        if(Integer.parseInt(price) < 0){
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errPrice" , "Vui lòng nhập lại trường này");
            req.setAttribute("oddImage",productDAO.getOddImageByIdForAdminUpdate(Integer.parseInt(idOddImage)));
            req.getRequestDispatcher("EditOddImage.jsp").forward(req, resp);
            return;
        }
        if(discount == null || discount.isEmpty() || Integer.parseInt(discount) < 0){
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errDiscount" , "Vui lòng nhập trường này");
            req.setAttribute("oddImage",productDAO.getOddImageByIdForAdminUpdate(Integer.parseInt(idOddImage)));
            req.getRequestDispatcher("EditOddImage.jsp").forward(req, resp);
            return;
        }
        if(Integer.parseInt(price) < Integer.parseInt(discount)){
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errDiscount" , "Giá giảm không vượt quá giá bán");
            req.setAttribute("oddImage",productDAO.getOddImageByIdForAdminUpdate(Integer.parseInt(idOddImage)));
            req.getRequestDispatcher("EditOddImage.jsp").forward(req, resp);
            return;
        }
        if(Integer.parseInt(price) < Integer.parseInt(discount)){
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errDiscount" , "Giá giảm không lớn hơn giá bán");
            req.setAttribute("oddImage",productDAO.getOddImageByIdForAdminUpdate(Integer.parseInt(idOddImage)));
            req.getRequestDispatcher("EditOddImage.jsp").forward(req, resp);
            return;
        }
        if(description == null || description.isEmpty()){
            req.setAttribute("listNameTopic", topicDAO.getAllNamesTopic());
            req.setAttribute("errDescription" , "Vui lòng nhập trường này");
            req.setAttribute("oddImage",productDAO.getOddImageByIdForAdminUpdate(Integer.parseInt(idOddImage)));
            req.getRequestDispatcher("EditOddImage.jsp").forward(req, resp);
            return;
        }
        int idTopic = topicDAO.getIdTopicByName(nameTopic);
        if(productDAO.updateOddImage(idTopic,idOddImage,nameOddImage,description,Integer.parseInt(price),Integer.parseInt(discount))){
            resp.sendRedirect("product");
        }

    }
}
