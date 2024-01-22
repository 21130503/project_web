package Controller;

import DAO.BackupDAO;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BackupController", value = "/backup")
public class BackUpController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String backupPath = req.getParameter("path");
        System.out.println(backupPath);
        BackupDAO backupDAO = new BackupDAO();
        JSONObject jsonObject = new JSONObject();
        if(backupDAO.backup(backupPath)){
            jsonObject.put("status", 200);
            jsonObject.put("message", "Sao lưu dữ liệu thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
            System.out.println("ok");
        }
        else{
            jsonObject.put("status", 500);
            jsonObject.put("message", "Sao lưu dữ liệu không thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
            System.out.println("fail");

        }

    }
}
