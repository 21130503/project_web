package Controller;

import DAO.DiscountDAO;
import DAO.OrderDAO;
import nhom26.Discount;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@WebServlet(name = "DiscountClientController", value = "/discount")
public class DiscountClientController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            resp.sendRedirect("login.jsp");
            return;
        }

        if (session.getAttribute("count") == null) {
            session.setAttribute("count", 3);
        }
        int count = (int) session.getAttribute("count");
        HttpSession listCode = req.getSession();
        Set<Discount> set = (HashSet<Discount>) listCode.getAttribute("codes");
        if (set == null) set = new HashSet<>();
        req.setAttribute("count", count);
        req.setAttribute("codes", set);
        req.getRequestDispatcher("discountClient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiscountDAO discountDAO = new DiscountDAO();
        HttpSession session = req.getSession();
        int count = (int) session.getAttribute("count");
        session.setAttribute("count", count - 1);
        System.out.println(count);
        if (count <= 0) {
            req.getRequestDispatcher("discountClient.jsp").forward(req, resp);
            return;

        } else {
            HttpSession listCode = req.getSession();
            Set<Discount> set = (HashSet<Discount>) listCode.getAttribute("codes");
            if (set == null) set = new HashSet<>();

            Discount newDiscount;
            do {
                newDiscount = this.getDiscount();
            } while (set.contains(newDiscount));

            set.add(newDiscount);
            req.setAttribute("code", this.getDiscount());
            session.setAttribute("codes", set);
            req.getRequestDispatcher("discountClient.jsp").forward(req, resp);
            return;
        }
    }

    private Discount getDiscount() {
        DiscountDAO discountDAO = new DiscountDAO();
        Random random = new Random();
        int index = random.nextInt(discountDAO.getAllDiscounts().size());
        return discountDAO.getAllDiscounts().get(index);
    }
}
