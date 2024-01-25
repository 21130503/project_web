package Controller;

import DAO.UserDAO;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MessageController", value = "/messageAdmin")
public class MessageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        UserDAO userDAO = new UserDAO();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
//        if(user == null) {
//            resp.sendRedirect("login.jsp");
//            return;
//        } else if (user.isAdmin()) {
            req.setAttribute("users", userDAO.getAllUsers());
            req.getRequestDispatcher("MessageAdmin.jsp").forward(req,resp);
            return;
//        }
    }
}
