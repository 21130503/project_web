package Controller;

import DAO.DiscountDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.TopicDAO;
import Regex.Regex;
import nhom26.Config;
import nhom26.Security;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.rmi.server.UID;
import java.util.UUID;

@WebServlet(name = "OrderController", value = "/order")
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        TopicDAO topicDAO = new TopicDAO();
        OrderDAO orderDAO = new OrderDAO();
//        req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
        req.getRequestDispatcher("detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        Regex regex = new Regex();
        Config config = new Config();
        Security security = new Security();
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String type = req.getParameter("type");
        String idProduct = req.getParameter("idProduct");
        String price = req.getParameter("price");
        String quantity = req.getParameter("total");
        String nameCity = req.getParameter("nameCity");
        String nameDistrict = req.getParameter("nameDistrict");
        String nameCommune = req.getParameter("nameCommune");
        String detailAddress = req.getParameter("detail-address");
        String phoneNumber = req.getParameter("phoneNumber");
        String receiver = req.getParameter("receiver");
        int code = 0;
        try {
            code = Integer.parseInt(req.getParameter("discount"));
        } catch (NumberFormatException e) {
            code = 0;
        }
        DiscountDAO discountDAO = new DiscountDAO();

        double discount = 0;
        if (discountDAO.getDiscountByCode(code) != null) {
            discount = discountDAO.getDiscountByCode(code).getDiscountValue();
        } else {
            discount = 0;
        }
        HttpSession session1 = req.getSession();
        String URL = "/demoProject_war/detail?type=" + type + "&id=" + idProduct;
        String address = detailAddress + "," + nameCommune + "," + nameDistrict + "," + nameCity;

        if (!user.isActive()) {
            session1.setAttribute("errActive", "Bạn không thể mua hàng");
            session1.setMaxInactiveInterval(30);
            resp.sendRedirect(URL);
            return;
        }
        if (!user.isVerifyEmail()) {
            session1.setAttribute("errVerify", "Vui lòng xác thực email. <a href=" + "http://localhost:8080/demoProject_war/verify" + ">" + "Tại đây" + "</a>");
            session1.setMaxInactiveInterval(30);
            resp.sendRedirect(URL);
            return;
        }
        if (Integer.parseInt(quantity) <= 0) {
            session1.setAttribute("errTotal", "Số lượng phải lớn hơn 0");
            session1.setMaxInactiveInterval(30);
            resp.sendRedirect(URL);
            return;
        }
        if (receiver == null || receiver.isEmpty()) {
            session1.setAttribute("errReceiver", "Vui lòng nhập tên người nhận");
            session1.setMaxInactiveInterval(30);
            resp.sendRedirect(URL);
            return;

        }
        if (phoneNumber == null || phoneNumber.isEmpty() || !regex.isValidPhoneNumber(phoneNumber)) {
            session1.setAttribute("errPhoneNumber", "Vui lòng nhập đúng số điện thoại");
            session1.setMaxInactiveInterval(30);
            resp.sendRedirect(URL);
            return;
        }
        if (nameCity.isEmpty() || nameDistrict.isEmpty() || nameCommune.isEmpty() || detailAddress.isEmpty()) {
            session1.setAttribute("errAddress", "Vui lòng nhập địa chỉ nhận hàng");
            session1.setMaxInactiveInterval(30);
            resp.sendRedirect(URL);
            return;
        }
        double totalPrice = ((Integer.parseInt(quantity) * Integer.parseInt(price) )- (Integer.parseInt(quantity) * Integer.parseInt(price) * discount)) + 30000;
        File directory = new File(req.getServletContext().getRealPath("/orders/"));

        if (!directory.exists()) {
            directory.mkdirs();
        }
        if(!directory.exists()) directory.mkdirs();
        String file = directory+"/order" + UUID.randomUUID().toString() + ".txt";
        OutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        bos.write(("idProduct: " + idProduct + "\n").getBytes());
        bos.write(("userId: " + user.getId() + "\n").getBytes());
        bos.write(("receiver: " + receiver + "\n").getBytes());
        bos.write(("phoneNumber: " + phoneNumber + "\n").getBytes());
        bos.write(("quantity: " + quantity + "\n").getBytes());
        bos.write(("totalPrice: " + totalPrice + "\n").getBytes());
        bos.write(("address: " + address + "\n").getBytes());
        bos.write(("type: " + type + "\n").getBytes());
        bos.flush();
        bos.close();
        try {
            security.AESEncryptFile(file,config.getKeyAES());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Order saved successfully at: " + file);

        if ("odd".equals(type)) {
            if (orderDAO.insertOrderOdd(Integer.parseInt(idProduct), user.getId(), receiver, phoneNumber, Integer.parseInt(quantity), totalPrice, address,file)) {
                resp.sendRedirect("./donhangcuaban");
                return;
            }
        }
        if ("album".equals(type)) {
            if (orderDAO.insertOrderAlbum(Integer.parseInt(idProduct), user.getId(), receiver, phoneNumber, Integer.parseInt(quantity), totalPrice, address,file)) {
                resp.sendRedirect("./donhangcuaban");
                return;
            }
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
