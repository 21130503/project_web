package Controller;

import DAO.UserDAO;
import Login.RestFB;
import nhom26.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginFacebookController", value = "/login-facebook")
public class LoginFacebookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginFacebookController() {
        super();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
