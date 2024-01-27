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
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "DiscountAdminController", value = "/discountAdmin")
public class DiscountAdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Hàm lấy ra các mã giảm giá từ DB để hiển thị lên discountAdmin
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        DiscountDAO discountDAO = new DiscountDAO();
        req.setAttribute("discountList", discountDAO.getAllDiscounts());

        req.getRequestDispatcher("./discountAdmin.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        String action = req.getParameter("action");
        //Tạo thêm mã giảm giá mới
        if ("addDiscount".equals(action)) {
            int code = Integer.parseInt(req.getParameter("code"));
            String description = req.getParameter("description");
            double discountValue = Double.parseDouble(req.getParameter("discountValue"));
            Date expiryDate = Date.valueOf(req.getParameter("expiryDate"));
            int count = Integer.parseInt(req.getParameter("count"));

            DiscountDAO discountDAO = new DiscountDAO();

            // Kiểm tra xem CODE mã giảm giá đã tồn tại chưa
            if (discountDAO.checkDiscountExist(code)) {
                session.setAttribute("errorMessage", "Code mã giảm giá đã tồn tại");
                resp.sendRedirect("./discountAdmin");
                return;
            }

            // Tạo đối tượng Discount và thêm vào cơ sở dữ liệu
            Discount discount = new Discount();
            discount.setCode(code);
            discount.setDescription(description);
            discount.setDiscountValue(discountValue);
            discount.setExpiryDate(expiryDate);
            discount.setCount(count);

            boolean success = discountDAO.addDiscount(discount);

            if (success) {
                resp.sendRedirect("./discountAdmin");
                session.setAttribute("discountSuccess", "Mã " + discount.getDescription() + " đã được tạo ra thành công.");
            } else {
                session.setAttribute("errorMessage", "Không thể tạo mã giảm giá");
                resp.sendRedirect("./discountAdmin");
            }
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xử lý việc xóa mã giảm giá
        String action = req.getParameter("action");

        HttpSession session = req.getSession();

        if ("deleteDiscount".equals(action)) {
            String code = req.getParameter("code");

            DiscountDAO discountDAO = new DiscountDAO();
            boolean success = discountDAO.deleteDiscount(code);

            if (success) {
                // Nếu xóa thành công, cập nhật danh sách và chuyển hướng về trang discountAdmin
                req.setAttribute("discountList", discountDAO.getAllDiscounts());
                session.setAttribute("deleteSuccess", "Mã giám giá đã bị xóa thành công.");
                resp.sendRedirect("./discountAdmin");
            } else {
                req.setAttribute("errorMessage", "Không thể xóa mã giảm giá");
                req.getRequestDispatcher("./discountAdmin").forward(req, resp);
            }
        }
    }

}
