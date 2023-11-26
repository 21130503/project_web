package Controller;

import DAO.UserDAO;
import nhom26.Topic;
import nhom26.User;
import org.json.JSONObject;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "Usercontroller", value = "/user")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null || !user.isAdmin()){
            resp.sendRedirect("404.jsp");
        }
        else{
            UserDAO userDAO = new UserDAO();
            req.setAttribute("listUser", userDAO.getAllUsers());
            req.getRequestDispatcher("quanlinguoidung.jsp").forward(req,resp);
        }


    }

    //    Xóa người dùng
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String idUser = req.getParameter("idUser");
        System.out.println("delete " +idUser);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            String sqlDelete = "delete from user where idUser = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setString(1,idUser);
            int resultSet = preparedStatement.executeUpdate();
            JSONObject jsonObject = new JSONObject();
            if(resultSet > 0){
                jsonObject.put("status", 200);
                jsonObject.put("message", "Đã xóa thành công");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());
            }
            else {
                jsonObject.put("status", 500);
                jsonObject.put("message", "Xóa chủ đề thất bại. Vui lòng thử lại");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String idUser = req.getParameter("idUser");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            String sqlGetActive = "select isActive from user where idUser = ?";
            boolean isActive ;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetActive);
            preparedStatement.setString(1,idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                isActive = resultSet.getBoolean(1);
                String updateActive;
                if (isActive){
                    updateActive = "false";
                }
                else{
                    updateActive = "true";
                }
                String sqlBlockOrUB = "UPDATE user set isActive = ? where idUser = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sqlBlockOrUB);
                preparedStatement1.setString(1,updateActive);
                preparedStatement1.setString(2,idUser);
                int res = preparedStatement1.executeUpdate();
                JSONObject jsonObject = new JSONObject();
                if(res > 0){
                    jsonObject.put("status", 200);
                    jsonObject.put("message", isActive ? "Đã chặn thành công" : "Đã mở chặn thành công");
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonObject.toString());
                }
                else{
                    jsonObject.put("status", 500);
                    jsonObject.put("message", "Thất bại");
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonObject.toString());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
