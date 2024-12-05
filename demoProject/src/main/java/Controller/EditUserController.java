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

@WebServlet(value = "/edit-infor")
public class EditUserController extends HttpServlet {
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
        email.sendEmail(Security.EMAIL, Security.PASS,user.getEmail(),"Xác thực Email","Vui lòng nhập mã "+String.valueOf(code)+" để xác thực email của bạn");

        req.getRequestDispatcher("EditUser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String codeVerify  = req.getParameter("code");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confim = req.getParameter("password_confirmation");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        if(Integer.parseInt(codeVerify) != code){
            req.setAttribute("invalidateCode", "Mã xác nhận không chính xác");
            req.getRequestDispatcher("EditUser.jsp").forward(req,resp);
            return;
        }
        if(username.isEmpty() || username == null){
            req.setAttribute("invalidateUserName","Tên người dùng phải tồn tại");
            req.getRequestDispatcher("EditUser.jsp").forward(req,resp);
            return;
        }
        if(!password.equals(confim)){
            req.setAttribute("invalidatePassword","Mật khẩu xác nhận không đúng");
            req.setAttribute("invalidateConfimPassword","Mật khẩu xác nhận không đúng");
            req.getRequestDispatcher("EditUser.jsp").forward(req,resp);
            return;
        }
        if(password.length() <6){
            req.setAttribute("invalidatePassword","Mật khẩu phải ít nhất 6 kí tự");
            req.getRequestDispatcher("EditUser.jsp").forward(req,resp);
            return;
        }
        UserDAO userDAO = new UserDAO();
        if(userDAO.updateUser(username, password,user.getId())){
            resp.sendRedirect("index");
        }



    }
}
