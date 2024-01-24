package Controller;

import DAO.UserDAO;
import common.GooglePojo;
import common.GoogleUtils;
import common.RestFB;
import nhom26.User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login-google")
public class LoginGoogleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginGoogleServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        UserDAO userDAO = new UserDAO();
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            String email = googlePojo.getEmail();
            if (userDAO.checkEmailExist(email)) {
                User user = userDAO.getUserByEmailAndPass(email, "");
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(10 * 60);
                response.sendRedirect("index");
            } else {
                if(userDAO.resgisterWithEmail(email, "Google Account","")){
                    User user = userDAO.getUserByEmailAndPass(email, "");
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(10 * 60);
                    response.sendRedirect("index");
                }
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}