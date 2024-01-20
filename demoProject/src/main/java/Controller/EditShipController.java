package Controller;

import DAO.OrderDAO;
import nhom26.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "EditShipController", value = "/editShip")
public class EditShipController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String q = req.getParameter("q");
        String type = req.getParameter("type");
        OrderDAO orderDAO = new OrderDAO();
        Order order = null;
        if("album".equals(type)){
            order = orderDAO.getOrderAlbumEdit(q);
        }
        if("odd".equals(type)){
            order = orderDAO.getOrderOddEdit(q);
        }
        if("cart".equals(type)){
            order  =orderDAO.getOrderCartEdit(q);
        }
        req.setAttribute("order", order);
        req.getRequestDispatcher("EditShip.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String newStatus = req.getParameter("newStatus");
        String type = req.getParameter("type");
        String idOrder = req.getParameter("idOrder");
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(type);
        if("odd".equals(type)){
            if(orderDAO.updateOddStatus(idOrder,newStatus)){
                resp.sendRedirect("product");
                return;
            }
        }
        else if("album".equals(type)){
            if(orderDAO.updateAlbumStatus(idOrder,newStatus)){
                resp.sendRedirect("product");
                return;
            }
        }

        else if("cart".equals(type)){
            if(orderDAO.updateCartStatus(idOrder, newStatus)){
                resp.sendRedirect("product");
                return;
            }
        }


    }
}
