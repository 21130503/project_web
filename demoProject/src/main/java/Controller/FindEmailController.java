package Controller;

import DAO.UserDAO;
import Regex.Regex;
import Security.Security;
import Services.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "FindEmail", value = "/findemail")
public class FindEmailController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Regex regex = new Regex();
        String email = req.getParameter("email");
        if(!regex.validateEmail(email)){
            req.setAttribute("err", "Trường này phải là email");
            req.getRequestDispatcher("findEmail.jsp").forward(req, resp);
        }
        else{
            UserDAO userDAO = new UserDAO();
            if(userDAO.checkEmailExist(email)){
                Random random = new Random();
                int code = random.nextInt(89999) +10000;
                SendEmail sendEmail = new SendEmail();
                sendEmail.sendEmail(Security.EMAIL, Security.PASS, email, "Xác thực email của bạn", "Vui lòng nhập " + code + "để xác thực email của bạn");
                Cookie cookie = new Cookie("code", String.valueOf(code));
                Cookie cookie1 = new Cookie("email", email);
                resp.addCookie(cookie);
                resp.addCookie(cookie1);
                resp.sendRedirect("forgot");
                return;
            }
            else{
                req.setAttribute("email-not-found", "Email chưa tồn tại");
                req.getRequestDispatcher("findEmail.jsp").forward(req, resp);
            }
        }
    }
}
