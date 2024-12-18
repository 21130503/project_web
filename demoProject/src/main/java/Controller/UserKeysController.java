package Controller;

import DAO.ReportKeysDAO;
import DAO.TopicDAO;
import DAO.UserKeyDAO;
import favourite.Favourite;
import nhom26.PublicKeys;
import nhom26.ReportKeys;
import nhom26.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserKeysController", value = "/userkeys-management")
public class UserKeysController extends HttpServlet {

    ReportKeysDAO reportKeysDAO ;
    UserKeyDAO userKeyDAO ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        reportKeysDAO = new ReportKeysDAO();
        userKeyDAO = new UserKeyDAO();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            resp.sendRedirect("login.jsp");
            return;
        }
        int userID = user.getId(); // id của user hiện tại
        // lấy ra list key của user - publicKeys
        List<PublicKeys> publicKeysList = userKeyDAO.getAllPublicKeys(userID);
        // lấy ra list report key của user - reportKey
        List<ReportKeys> reportKeysList = reportKeysDAO.getReportList(userID);
        // gửi 2 list này cho userKeysManagement.jsp để hiển thị
        session.setAttribute("reportKeysList", reportKeysList);
        session.setAttribute("publicKeysList", publicKeysList);
        // chuyển trang
        req.getRequestDispatcher("userKeysManagement.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

    }
}
