package Controller;

import DAO.UserDAO;
import Security.Security;
import Services.SendEmail;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "VerifyController", value = "/verify")
public class VerifyController extends HttpServlet {
    Random random = new Random();
    int code = random.nextInt(89999) + 10000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        SendEmail email = new SendEmail();
        email.sendEmail(Security.EMAIL, Security.PASS,user.getEmail(),"Xác thực Email",String.valueOf(code));
        req.getRequestDispatcher("VerifyEmail.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int codeVerify = Integer.parseInt(req.getParameter("verify"));
        if(code != codeVerify){
            req.setAttribute("err", "Nhập mã không chính xác");
            req.getRequestDispatcher("VerifyEmail.jsp").forward(req,resp);
        }
        else{
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            UserDAO userDAO = new UserDAO();
            if(userDAO.verifyEmail(user.getEmail())){
                resp.sendRedirect("index");
            }
        }
    }
}
