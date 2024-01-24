package Controller;

import DAO.UserDAO;
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

@WebServlet("/login-facebook")
public class LoginFacebookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginFacebookServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = RestFB.getToken(code);
            request.setAttribute("id", RestFB.getUserInfo(accessToken).getId());
            request.setAttribute("name", RestFB.getUserInfo(accessToken).getName());
            String email = RestFB.getUserInfo(accessToken).getEmail();
            if (userDAO.checkEmailExist(email)) {
                User user = userDAO.getUserByEmailAndPass(email, "");
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(10 * 60);
                response.sendRedirect("index");
            } else {
                if(userDAO.resgisterWithEmail(email,RestFB.getUserInfo(accessToken).getName(),"")){
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