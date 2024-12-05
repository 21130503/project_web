package Controller;

import DAO.UserDAO;
import nhom26.Config;
import nhom26.Security;
import nhom26.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    Security security = new Security();
    Config config = new Config();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Đặt encoding cho request và response
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDAO userDAO = new UserDAO();
        System.out.println("This is log in login");
        User user = null;
        try {
            user = userDAO.getUserByEmailAndPass(security.DESEncrypt(email,config.getKey() ), security.Sha256Hash(password));
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
        if(user == null){
            req.setAttribute("err", "Email hoặc mật khẩu không đúng");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        if(user.isAdmin()){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(10 * 60);
            resp.sendRedirect("admin");
            return;
        } else if (!user.isAdmin()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(10 * 60);
            resp.sendRedirect("index");
            return;
        } else{

        }
    }
}
