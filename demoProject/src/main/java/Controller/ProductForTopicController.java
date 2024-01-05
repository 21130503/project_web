package Controller;

import DAO.BelongDAO;
import DAO.ProductDAO;
import DAO.TopicDAO;
import nhom26.Album;
import nhom26.OddImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductForTopicController",value = "/pTopic")
public class ProductForTopicController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        TopicDAO topicDAO = new TopicDAO();
        BelongDAO belongDAO = new BelongDAO();
        ProductDAO productDAO = new ProductDAO();
        String nameTopic = req.getParameter("q");
        String type = req.getParameter("type");
        System.out.println(type);
        int idTopic = topicDAO.getIdTopicByName(nameTopic);
        ArrayList<Integer> listIdAlbum = belongDAO.listIdAlbumBelongTopic(String.valueOf(idTopic));
        ArrayList<Integer> listIdOdd= belongDAO.listIdOddImageBelongTopic(String.valueOf(idTopic));
        ArrayList<OddImage> listOdd = new ArrayList<>();
        ArrayList<Album> listAlbum = new ArrayList<>();
        for (int id : listIdOdd){
            listOdd.add(productDAO.getOddImageById(id));
        }
        for (int id : listIdAlbum){
            listAlbum.add(productDAO.getAlbumById(id));
        }
        if("odd".equals(type)){
            req.setAttribute("listOddImage",listOdd);
            req.setAttribute("listTopic",topicDAO.getAllTopicsForClient());
            req.getRequestDispatcher("Topic.jsp").forward(req,resp);
            return;
        }
        if("album".equals(type)){
            req.setAttribute("listAlbum",listAlbum);
            req.setAttribute("listTopic",topicDAO.getAllTopicsForClient());
            req.getRequestDispatcher("Topic.jsp").forward(req,resp);
            return;
        }

        req.setAttribute("listAlbum",listAlbum);
        req.setAttribute("listOddImage",listOdd);
        req.setAttribute("listTopic",topicDAO.getAllTopicsForClient());
        req.getRequestDispatcher("Topic.jsp").forward(req,resp);
    }
}
