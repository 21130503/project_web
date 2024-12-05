package Controller;

import DAO.ProductDAO;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/trash/*")
public class TrashController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ProductDAO productDAO = new ProductDAO();
        req.setAttribute("trashAlbum",productDAO.getAllAlbumTrash(1,20000));
        req.setAttribute("trashOdd", productDAO.getAllOddImageTrash(1,20000));
        req.getRequestDispatcher("Trash.jsp").forward(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getPathInfo();
        System.out.println(path);
        ProductDAO productDAO = new ProductDAO();
        JSONObject jsonObject = new JSONObject();
        if ("/restoreOddImage".equals(path)) {
            String idOddImage = req.getParameter("idOddImage");

            if (productDAO.deleteOddImagePhantom(Integer.parseInt(idOddImage), "false")) {
                jsonObject.put("status", 200);
                jsonObject.put("message", "Khôi phục thành công");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());
                System.out.println("update thành công");
            } else {
                jsonObject.put("status", 500);
                jsonObject.put("message", "Cập nhật thất bại thất bại. Vui lòng thử lại");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());

            }
        }
        if ("/restoreAlbum".equals(path)) {
            String idAlbum = req.getParameter("idAlbum");
            if (productDAO.deleteAlbumPhantom(Integer.parseInt(idAlbum), "false")) {
                jsonObject.put("status", 200);
                jsonObject.put("message", "Khôi phục thành công");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());
                System.out.println("update thành công");
            } else {
                jsonObject.put("status", 500);
                jsonObject.put("message", "Cập nhật thất bại thất bại. Vui lòng thử lại");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());

            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getPathInfo();
        System.out.println(path);
        ProductDAO productDAO = new ProductDAO();
        JSONObject jsonObject = new JSONObject();
        if ("/deleteOddImage".equals(path)) {
            String idOddImage = req.getParameter("idOddImage");

            if (productDAO.deleteOddImage(Integer.parseInt(idOddImage))) {
                jsonObject.put("status", 200);
                jsonObject.put("message", "Xóa thành công");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());
                System.out.println("update thành công");
            } else {
                jsonObject.put("status", 500);
                jsonObject.put("message", "Xóa thất bại thất bại. Vui lòng thử lại");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());

            }
        }
        if ("/deleteAlbum".equals(path)) {
            String idAlbum = req.getParameter("idAlbum");
            if (productDAO.deleteAlbum(Integer.parseInt(idAlbum))) {
                jsonObject.put("status", 200);
                jsonObject.put("message", "Xóa thành công");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());
                System.out.println("update thành công");
            } else {
                jsonObject.put("status", 500);
                jsonObject.put("message", "Xóa thất bại thất bại. Vui lòng thử lại");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());

            }
        }
    }
}