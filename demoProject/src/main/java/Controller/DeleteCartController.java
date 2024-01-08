package Controller;

import cart.Cart;
import nhom26.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteCartController", value = "/delete-cart-item")
public class DeleteCartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String id = req.getParameter("idProduct");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        JSONObject jsonObject = new JSONObject();
        if (cart == null) {
            cart = new Cart();
        }

        if ( cart.removeCart( type + id)) {
            session.setAttribute("cart", cart);
            jsonObject.put("status", 200);
            jsonObject.put("message", "Giảm thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("message", "Giảm không thành công");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonObject.toString());
        }

    }

}
