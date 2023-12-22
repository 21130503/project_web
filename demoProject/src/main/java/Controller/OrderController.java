package Controller;

import DAO.OrderDAO;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "OrderCintroller", value = "/order")
public class OrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        OrderDAO orderDAO = new OrderDAO();

        if(user == null){
            resp.sendRedirect("login.jsp");
            return;
        }
        String type = req.getParameter("type");
        String idProduct = req.getParameter("idProduct");
        String total = req.getParameter("total");
        String nameCity = req.getParameter("nameCity");
        String nameDistrict = req.getParameter("nameDistrict");
        String nameCommune = req.getParameter("nameCommune");
        String detailAddress = req.getParameter("detail-address");
        System.out.println(detailAddress + " " + nameCommune );
        HttpSession session1 = req.getSession();
        String URL = "/demoProject_war/detail?type=" + type + "&id=" + idProduct;
        String address = detailAddress + "," + nameCommune + "," + nameDistrict + "," + nameCity;
        if(Integer.parseInt(total) <= 0){
            session1.setAttribute("errTotal" ,"Số lượng phải lớn hơn 0");
            session1.setMaxInactiveInterval(30);
            resp.sendRedirect(URL);
            return;
        }
        if(nameCity.isEmpty() || nameDistrict.isEmpty() || nameCommune.isEmpty() || detailAddress.isEmpty()){
            session1.setAttribute("errAddress", "Vui lòng nhập địa chỉ nhận hàng");
            session1.setMaxInactiveInterval(30);
            resp.sendRedirect(URL);
            return;
        }
        if("odd".equals(type)){
            if(orderDAO.insertOrderOdd(Integer.parseInt(idProduct),user.getId(),Integer.parseInt(total),address )){
                resp.sendRedirect(URL);
                return;
            }
        }

    }
}
