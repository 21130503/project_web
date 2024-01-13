package Controller;

import DAO.FeedbackDAO;
import DAO.ProductDAO;
import DAO.TopicDAO;
import nhom26.Album;
import nhom26.OddImage;
import nhom26.Topic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetailController", value = "/detail/*")
public class DetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String type = req.getParameter("type");
        ProductDAO productDAO = new ProductDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();

        if (type.equals("album")) {
            Album album = productDAO.getAlbumById(id);
            req.setAttribute("detail", album);
            req.setAttribute("type", "album");
            req.setAttribute("feedback", feedbackDAO.getAllFeedbackForAlbumById(id));
            req.setAttribute("totalStar", feedbackDAO.countRatingAlbum(id));
            req.setAttribute("avgStar", feedbackDAO.AvgRatingAlbum(id));

        } else if (type.equals("odd")) {
            OddImage oddImage = productDAO.getOddImageById(id);
            req.setAttribute("detail", oddImage);
            req.setAttribute("type", "odd");
            req.setAttribute("feedback", feedbackDAO.getAllFeedbackForOddImageById(id));
            req.setAttribute("totalStar", feedbackDAO.countRatingOddImage(id));
            req.setAttribute("avgStar", feedbackDAO.AvgRatingOddImage(id));


        }
        TopicDAO topicDAO = new TopicDAO();
        req.setAttribute("suggestedOdd", productDAO.getTop8ddImageNew());
        req.setAttribute("suggestedAlbum", productDAO.getTop8AlbumNew());
        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        req.getRequestDispatcher("detail.jsp").forward(req, resp);
        return;

    }

}
