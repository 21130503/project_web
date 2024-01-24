package Controller;

import DAO.UserDAO;
import Login.GooglePojo;
import Login.GoogleUtils;
import nhom26.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginGoogleController", value = "/login-google")
public class LoginGoogleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginGoogleController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
