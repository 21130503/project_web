package Controller;

import favourite.Favourite;
import nhom26.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FavouriteController", value = "/favourite")
public class FavouriteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            resp.sendRedirect("login.jsp");
            return;
        }
        req.getRequestDispatcher("favourite.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");
        String id = req.getParameter("idProduct");
        System.out.println("FAV" + type + id);
        HttpSession session = req.getSession();
        Favourite favourite = (Favourite) session.getAttribute("favourite");
        JSONObject jsonObject = new JSONObject();
        if (favourite == null) {
            favourite = new Favourite();
        }

        if (favourite.add(type, type + id, Integer.parseInt(id))) {
            session.setAttribute("favourite", favourite);
            jsonObject.put("status", 200);
            jsonObject.put("message", "Thêm thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("message", "Thêm không thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");
        String id = req.getParameter("idProduct");
        HttpSession session = req.getSession();
        Favourite favourite = (Favourite) session.getAttribute("favourite");
        JSONObject jsonObject = new JSONObject();
        if(favourite.remove(type+id)){
            session.setAttribute("favourite", favourite);
            jsonObject.put("status", 200);
            jsonObject.put("message", "Xóa thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }
        else {
            session.setAttribute("favourite", favourite);
            jsonObject.put("status", 500);
            jsonObject.put("message", "Xóa thất bại");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }
    }
}
