package Controller;

import DAO.OrderDAO;
import nhom26.Order;
import nhom26.User;

import javax.print.attribute.standard.MediaSizeName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OrderManganerController", value = "/orderManager")
public class OrderManganerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null || !user.isAdmin()){
            resp.sendRedirect("404.jsp");
            return;
        }
        OrderDAO orderDAO = new OrderDAO();
        String optionValue = req.getParameter("option");
        System.out.println(optionValue);
        if(optionValue == null || "all".equals(optionValue)) {
            ArrayList<Order> orders = new ArrayList<>( orderDAO.getAllOrderOddImageForAdmin());
            orders.addAll(orderDAO.getAllOrderAlbumForAdmin());
            orders.addAll(orderDAO.getAllCartOrderForAdmin());
            req.setAttribute("listOrder",orders);
            req.getRequestDispatcher("quanlidonhang.jsp").forward(req, resp);
            return;
        }
        else{
            ArrayList<Order> orders  = new ArrayList<>(orderDAO.getAllOrderOddImageForByStatus(optionValue));
            orders.addAll(orderDAO.getAllOrderAlbumForByStatus(optionValue));
            orders.addAll(orderDAO.getAllCartOrderForByStatus(optionValue));
            req.setAttribute("listOrder", orders);
            req.getRequestDispatcher("quanlidonhang.jsp").forward(req,resp);
            return;
        }

    }
}
