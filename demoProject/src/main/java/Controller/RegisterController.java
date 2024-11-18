package Controller;

import DAO.UserDAO;
import Properties.DBProperties;
import Regex.Regex;
import Services.Connect;
import nhom26.Config;
import nhom26.Security;
import org.json.JSONObject;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
     Regex regex = new Regex();
     Config config = new Config();
     Security security = new Security();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Đặt encoding cho request và response
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String username =  req.getParameter("username");
        String password =  req.getParameter("password");
        String confimPassword =  req.getParameter("password_confirmation");
        System.out.println(req.getParameter("email"));
        UserDAO userDAO = new UserDAO();
//        early return
        if(!regex.validateEmail(email)){
            req.setAttribute("invalidateEmail", "Trường này phải là email");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        if(email.length()==0){
            req.setAttribute("invalidateEmail", "Vui lòng nhập trường này");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        if(userDAO.checkEmailExist(email)){
            req.setAttribute("invalidateEmail","Email đã được đăng kí");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        if(username.length() < 6){
            req.setAttribute("invalidateUserName","Tên người dùng phải lớn hơn 6");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        if(password.length() <6){
            req.setAttribute("invalidatePassword","Mật khẩu phải nhiều hơn 6 kí tự");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }
        if(!confimPassword.equals(password)){
            req.setAttribute("invalidateConfimPassword","Mật khẩu không khớp");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }

        try {
            if(userDAO.resgisterWithEmail(security.DESEncrypt(email, config.getKey()),security.DESEncrypt(username, config.getKey()),security.Sha256Hash(password))){
                resp.sendRedirect("login.jsp");
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }
}