package Controller;

import DAO.DiscountDAO;
import cart.Cart;
import nhom26.Discount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ApplyDiscountController", value = "/applyDiscount")
public class ApplyDiscountController extends HttpServlet {
    //Lớp xử lí việc người dùng áp mã giảm trong trang cart
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String discountCodeStr = request.getParameter("discountCode");
        DiscountDAO discountDAO = new DiscountDAO();

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        session.removeAttribute("discountError");
        session.removeAttribute("discountSuccess");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        if (discountCodeStr == null || discountCodeStr.isEmpty()) {
            session.setAttribute("discountError", "Xin nhập mã giảm giá vào.");
            response.sendRedirect("cart");
            return;
        }

        try {
            int discountCode = Integer.parseInt(discountCodeStr);
            Discount discount = discountDAO.getDiscountByCode(discountCode);

            if (discount == null) {
                session.setAttribute("discountError", "Mã giảm giá không tồn tại.");
            } else if (discount.getExpiryDate().before(new Date())) {
                session.setAttribute("discountError", "Mã giảm giá đã hết hạn.");
            } else if (discount.getCount() <= 0) {
                session.setAttribute("discountError", "Mã giảm giá đã hết số lần sử dụng.");
            } else {
                cart.setAppliedDiscount(discount);
                session.setAttribute("discountSuccess", "Mã " + discount.getDescription() + " đã được áp dụng.");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("discountError", "Mã giảm giá không hợp lệ.");
        }

        response.sendRedirect("cart");
    }

}
