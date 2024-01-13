package Controller;

import DAO.DiscountDAO;
import nhom26.Discount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "EditDiscountController", value = "/editDiscount")
public class EditDiscountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Hàm doget lấy ra dữ liệu mã CODE từ nút Sửa thuộc tính bên discountAdmin qua editDiscount để hiển thị
        //các thuộc tính của Mã giảm giá đó để cập nhật
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String code = req.getParameter("code");

        // Kiểm tra nếu code mã giảm giá không tồn tại
        if (code == null || code.isEmpty()) {
            resp.sendRedirect("discountAdmin");
            return;
        }

        DiscountDAO discountDAO = new DiscountDAO();
        Discount discount = discountDAO.getDiscountByCode(Integer.parseInt(code));

        // Kiểm tra mã giảm giá có tồn tại không
        if (discount == null) {
            resp.sendRedirect("discountAdmin");
            return;
        }

        req.setAttribute("discount", discount);
        req.getRequestDispatcher("EditDiscount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Hàm xử lí việc cập nhật lại thuộc tính của Mã giàm giá
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding(("UTF-8"));

        HttpSession session = req.getSession();

        // Đọc thông tin từ form
        String code = req.getParameter("code");
        String description = req.getParameter("description");
        int discountValue = Integer.parseInt(req.getParameter("discountValue"));
        LocalDate expiryDate = LocalDate.parse(req.getParameter("expiryDate"));

        DiscountDAO discountDAO = new DiscountDAO();
        boolean success = discountDAO.updateDiscount(code, description, discountValue / 100.0, expiryDate);

        if (success) {
            resp.sendRedirect("discountAdmin");
            session.setAttribute("discountSuccess", " Thuộc tính của mã giảm giá '" + code + "' đã được cập nhật.");
        } else {
            req.setAttribute("errorMessage", "Không thể cập nhật mã giảm giá");
            req.getRequestDispatcher("./discountAdmin").forward(req, resp);
        }
    }
}