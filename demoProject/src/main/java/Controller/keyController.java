package Controller;

import DAO.UserKeyDAO;
import alg.DS;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@WebServlet(name = "keyController", value = "/dsKey")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // Kích thước file tối đa được giữ trong bộ nhớ (1MB)
        maxFileSize = 1024 * 1024 * 10,  // Kích thước file tối đa được tải lên (10MB)
        maxRequestSize = 1024 * 1024 * 20 // Tổng kích thước request tối đa (20MB)
)
public class keyController extends HttpServlet {
    DS ds = new DS();
    KeyPair kp;
    PublicKey publicKey;
    PrivateKey privateKey;
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
        try {
             kp = ds.genKey();
             publicKey = kp.getPublic();
            privateKey = kp.getPrivate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        int userid = user.getId();;

        // lưu public key lên db
        UserKeyDAO userKeyDAO = new UserKeyDAO();
        userKeyDAO.insertPublicKey(userid, publicKeyString);

        // Tên file khi tải xuống
        String fileName = userid+"/publicKey.txt";
        // Thiết lập kiểu dữ liệu trả về
        resp.setContentType("text/plain"); // MIME type cho file text
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // Gửi nội dung file về phía client
        try (OutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(privateKeyString.getBytes());
            outputStream.flush();
        }
    }


}
