package Controller;

import DAO.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderManganerController", value = "/orderManager")
public class OrderManganerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        OrderDAO orderDAO = new OrderDAO();
        String optionValue = req.getParameter("option");
        System.out.println(optionValue);
        if(optionValue == null || "all".equals(optionValue)) {
            req.setAttribute("listOrder", orderDAO.getAllOrderOddImageForAdmin());
            req.getRequestDispatcher("quanlidonhang.jsp").forward(req, resp);
            return;
        }
        else{
            req.setAttribute("listOrder", orderDAO.getAllOrderOddImageForByStatus(optionValue));
            req.getRequestDispatcher("quanlidonhang.jsp").forward(req,resp);
            return;
        }

    }
}
