package Controller;

import DAO.UserDAO;
import Regex.Regex;
import Services.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
@WebServlet(name = "ForgotController", value = "/forgot")
public class ForgotPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("forgotPassword.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Cookie[] cookies = req.getCookies();
        String codeVerify = null;
        String email = null;
        if(cookies != null){
            for(Cookie cookie :cookies){
                if("code".equals(cookie.getName())){
                    codeVerify = cookie.getValue();
                }
                if("email".equals(cookie.getName())){
                    email = cookie.getValue();
                }
            }
        }

        String code = req.getParameter("code");
        String password = req.getParameter("password");
        String confimPassword = req.getParameter("password_confirmation");
        if(!(code.length() ==5)){
            req.setAttribute("invalidateCode", "Mã xác thực không hợp lệ");
            req.getRequestDispatcher("forgotPassword.jsp").forward(req,resp);
        }
        if(!code.equals(codeVerify)){
            req.setAttribute("codeNotSame", "Mã xác thực không chính xác");
            req.getRequestDispatcher("forgotPassword.jsp").forward(req,resp);
        }
        if(password.length() ==0){
            req.setAttribute("invalidatePassword","Vui lòng nhập trường này");
            req.getRequestDispatcher("forgotPassword.jsp").forward(req,resp);
        }
        if(confimPassword.length() ==0){
            req.setAttribute("invalidateConfimPassword","Vui lòng nhập trường này");
            req.getRequestDispatcher("forgotPassword.jsp").forward(req,resp);
        }
        if(password.length() <6){
            req.setAttribute("invalidatePasswordLength","Mật khẩu phải nhiều hơn 6 kí tự");
            req.getRequestDispatcher("forgotPassword.jsp").forward(req,resp);
        }
        if(!confimPassword.equals(password)){
            req.setAttribute("invalidateConfimPasswordWithPass","Mật khẩu không khớp");
            req.getRequestDispatcher("forgotPassword.jsp").forward(req,resp);
        }
        UserDAO userDAO = new UserDAO();
        if(userDAO.updatePasswordByEmail(email, password)){
            resp.sendRedirect("login.jsp");
        }

    }
}
