package Controller;

import DAO.OrderDAO;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "DeleteOrderController", value = "/order/*")
public class DeleteOrderController extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo();
        String idOrder  = req.getParameter("idOrder");
        OrderDAO orderDAO = new OrderDAO();
        JSONObject jsonObject = new JSONObject();
        if("/odd".equals(path)){
            if(!"Đang chuẩn bị".equals(orderDAO.getStatusOddById(idOrder))){
                jsonObject.put("status", 500);
                jsonObject.put("message", "Không thể hủy đơn hàng");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());
            }
            else{
                if(orderDAO.deleteOddImageOrder(idOrder)){
                    jsonObject.put("status", 200);
                    jsonObject.put("message", "Hủy đơn thành công");
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonObject.toString());
                }
                else{
                    jsonObject.put("status", 500);
                    jsonObject.put("message", "Hủy đơn thất bại");
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonObject.toString());
                }
            }
        } else if ("/album".equals(path)) {
            if(!"Đang chuẩn bị".equals(orderDAO.getStatusAlbumById(idOrder))){
                jsonObject.put("status", 500);
                jsonObject.put("message", "Không thể hủy đơn hàng");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObject.toString());
            }
            else{
                if(orderDAO.deleteAlbumOrder(idOrder)){
                    jsonObject.put("status", 200);
                    jsonObject.put("message", "Hủy đơn thành công");
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonObject.toString());
                }
                else{
                    jsonObject.put("status", 500);
                    jsonObject.put("message", "Hủy đơn thất bại");
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonObject.toString());
                }
            }
        }

    }
}
