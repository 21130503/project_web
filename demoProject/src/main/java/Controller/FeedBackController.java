package Controller;

import DAO.FeedbackDAO;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FeedbackController", value = "/feedback")
public class FeedBackController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset= UTF-8");
        String type = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        String content = req.getParameter("content");
        System.out.println(type + id + content);
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String URL = "/demoProject_war/detail?type=" + type + "&id=" + id;
//        xử lí các case có thể xảy ra
//        người dùng chưa đăng nhập -> OK
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
//        Xử lí value rỗng
        if (content.length() == 0) {
            req.setAttribute("err", "Vui lòng nhập trường này");
            HttpSession session1 = req.getSession();
            session1.setAttribute("errMess", "Vui lòng nhập trường này");
            session1.setMaxInactiveInterval(60);
            resp.sendRedirect(URL);
            return;
        }
//        Chưa xử lí mua hàng rồi mới được bình luận
//        thỏa điều kiện thì mới cho insert
        if (type.equals("album")) {
            feedbackDAO.insertFeedbackForAlbum(id, user.getId(), content);

        } else if (type.equals("odd")) {
            feedbackDAO.insertFeedbackForOddImage(id, user.getId(), content);
        }
    }
}
