package Controller;

import DAO.ReportKeysDAO;
import DAO.UserDAO;
import Services.SendEmail;
import nhom26.Config;
import nhom26.ReportKeys;
import nhom26.Security;
import nhom26.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Random;

@WebServlet(value = "/report-priKey")
public class ReportKeyController extends HttpServlet {

    Security security = new Security();
    Config config = new Config();
    UserDAO userDAO = new UserDAO();

    ReportKeysDAO reportKeysDAO = new ReportKeysDAO();
    Random random = new Random();
    int code = random.nextInt(89999) + 10000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if(action.equals("direct")) {
            req.getRequestDispatcher("reportKey.jsp").forward(req,resp);
        } else if(action.equals("process-input")) {
            processInput(req,resp);
        } else if (action.equals("verify-email")) {
            verifyRPEmail(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void verifyRPEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) { // nếu user hết phiên đăng nhập thì login lại
            resp.sendRedirect("login.jsp");
            return;
        }

        int codeVerify = Integer.parseInt(req.getParameter("verify")); // lấy ra mã xác thực user nhập
        if(code != codeVerify){
            req.setAttribute("err", "Mã xác thực không chính xác");
            req.getRequestDispatcher("verifyEmailForReportKey.jsp").forward(req,resp);
        }
        else{
            // note : thực hiện vô hiệu public key bằng cách set endTime
                // đã có rpTime, rpReason, rpDate -> lưu xún database reportKeys
                int curPublicKeyID = 1; // xử lí lấy ra publicKey hiện tại
                String rpDate = (String) session.getAttribute("rp-date");
                System.out.println(rpDate);
                String rpTime = (String) session.getAttribute("rp-time");
                System.out.println(rpTime);
                String rpReason = (String) session.getAttribute("rp-reason");
                reportKeysDAO.insertReportKeys(user.getId(), curPublicKeyID, rpDate, rpTime, rpReason);
                // set endTime của publicKey hiện tại là rpTime trong HttpSession

            // chuyển đến trang thông báo report Key thành công
            req.getRequestDispatcher("reportKeySuccess.jsp").forward(req,resp);
        }
    }

    private void processInput(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String reason = req.getParameter("reason"); // có thể null

        User curUser = null;
        try {
            curUser = userDAO.getUserByEmailAndPass(security.DESEncrypt(email,config.getKey() ), security.Sha256Hash(password));
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

        if(curUser == null){ // nếu người dùng nhập email hoặc mật khẩu sai -> chuyển về lại trang reportKey.jsp
            req.setAttribute("err", "Email hoặc mật khẩu không đúng");
            req.getRequestDispatcher("reportKey.jsp").forward(req,resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("rp-email", email); // dùng để xác thực
        session.setAttribute("rp-password", password); // dùng để xác thực mật khẩu
        session.setAttribute("rp-date", date);
        session.setAttribute("rp-time", time);
        session.setAttribute("rp-reason", reason); // có thể null

        if(curUser != null) { // nếu người dùng nhập đúng email thì chuyển đến trang xác thực
            // gửi email đến email của người dùng để xác thực
            SendEmail sendEmailSystem = new SendEmail();
            String senderEmail = "21130503@st.hcmuaf.edu.vn"; // email của hệ thống = email quý
            String passwwordSenderEmail = "gyvzbtqzyjoqveyn";
            sendEmailSystem.sendEmail(senderEmail, passwwordSenderEmail ,curUser.getEmail(),
                    "Xác thực Email để báo cáo lộ PrivateKey","Vui lòng nhập mã "+String.valueOf(code)+" để xác thực email của bạn");
            req.getRequestDispatcher("verifyEmailForReportKey.jsp").forward(req,resp);
        }
    }
}