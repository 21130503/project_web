package Controller;

import DAO.OrderDAO;
import Regex.Regex;
import cart.Cart;
import cart.CartProduct;
import nhom26.Album;
import nhom26.OddImage;
import nhom26.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CartOrderController", value = "/cart-order")
public class CartOrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String receiver = req.getParameter("receiver");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        User user = (User) session.getAttribute("user");
        OrderDAO orderDAO = new OrderDAO();
        Regex regex = new Regex();
        JSONObject jsonObject = new JSONObject();

        if (cart.total() == 0) {
            jsonObject.put("status", 500);
            jsonObject.put("message", "Giỏ hàng trống");


        } else if (!user.isActive()) {
            jsonObject.put("status", 500);
            jsonObject.put("message", "Bạn không thể mua hàng");
        } else if (receiver.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            jsonObject.put("status", 500);
            jsonObject.put("message", "Vui lòng nhập đầy đủ thông tin");

        } else if (!regex.isValidPhoneNumber(phoneNumber)) {
            jsonObject.put("status", 500);
            jsonObject.put("message", "Vui lòng nhập đúng sdt");
        } else {
//            System.out.println("Receiver: " + receiver);
//            System.out.println("Phone Number: " + phoneNumber);
//            System.out.println("Address: " + address);

            int quantity = 0;
            String name= "" ;
            for (Map.Entry<String, CartProduct> entry : cart.getData().entrySet()) {
                CartProduct cartProduct = entry.getValue();
                quantity += cartProduct.getQuantity();
                if(cartProduct.getObject() instanceof Album ) {
                    name = name + "," + ((Album) cartProduct.getObject()).getName();
                } else if (cartProduct.getObject() instanceof  OddImage) {
                    name = name + "," + ((OddImage) cartProduct.getObject()).getName();
                }
                else {
                    name=name;
                }

            }
            if (orderDAO.inserOrderCart(name,user.getId(), receiver, phoneNumber, quantity, cart.totalPrice()+30000, address)) {
                jsonObject.put("status", 200);
                jsonObject.put("message", "Đã xử lí đơn thành công");
            } else {
                jsonObject.put("status", 500);
                jsonObject.put("message", "Xử lí đơn thất bại");
            }


        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonObject.toString());
    }
}
