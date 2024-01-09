package Controller;

import cart.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RemoveDiscountController", value = "/removeDiscount")
public class RemoveDiscountController extends HttpServlet {
    //Lớp này để xử lí việc xóa mã giảm giá đã được áp vào ở trang cart
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null && cart.getAppliedDiscount() != null) {
            session.setAttribute("message", "Mã " + cart.getAppliedDiscount().getDescription() + " đã được xóa.");
            cart.setAppliedDiscount(null);
        }

        response.sendRedirect("cart");
    }
}
