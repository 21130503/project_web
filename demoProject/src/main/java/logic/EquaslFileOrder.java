package logic;

import DAO.NotificationDAO;
import DAO.OrderDAO;
import DAO.UserDAO;
import Services.SendEmail;
import nhom26.*;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EquaslFileOrder {
    OrderDAO orderDAO = new OrderDAO();
    UserDAO userDAO = new UserDAO();
    NotificationDAO notificationDAO = new NotificationDAO();
    Security security = new Security();
    Config config = new Config();
    SendEmail sendEmail = new SendEmail();
    ArrayList<Order> ordersOdd = orderDAO.getAllOrderOddImagePrepare();
    ArrayList<Order> ordersAlbum = orderDAO.getAllOrderAlbumImagePrepare();
    ArrayList<Order> orders = (ArrayList<Order>) Stream.of(ordersOdd, ordersAlbum).flatMap(x-> x.stream()).collect(Collectors.toList());
    public  boolean checkEqualsFileOrderWithAlbum() throws Exception {
        System.out.println("This is log is check database: " + orders.toString());
        boolean bool = false;
        if(orders.size() > 0){
            for (Order o: orders) {

                FileOrder fileOrder = security.AESDecryptFile(o.getFileName(),config.getKeyAES());
                if (o.getIdProduct() == fileOrder.getIdProduct()
                        && o.getQuantity() == fileOrder.getQuantity()
                        && o.getType().equals(fileOrder.getType())
                        && o.getPhoneNumber().equals(fileOrder.getPhoneNumber())
                        && o.getAddress().equals(fileOrder.getAddress())
                        && o.getReceiver().equals(fileOrder.getReceiver())
                        && Double.compare(o.getTotalPrice(), fileOrder.getTotalPrice()) == 0) {
                    bool = true;
                } else {
                    User user = userDAO.getUserById(String.valueOf(fileOrder.getUserId()));
                    System.out.println(user.toString());
                    notificationDAO.insertNotification(user.getId(), "Đơn hàng của bạn đã bị hủy vì có sự cố", "order");
                    orderDAO.updateAlbumStatus(String.valueOf(o.getIdOrder()), "Đã hủy");
                    System.out.println("Bị thay dổi");

                    sendEmail.sendEmail(
                            "21130503@st.hcmuaf.edu.vn",
                            "gyvzbtqzyjoqveyn",
                            security.DESDecrypt(user.getEmail(), config.getKey()),
                            "Đơn hàng của bạn đã có sự thay đổi",
                            "Đơn hàng của bạn đã bị hủy vì có sự thay đổi"
                    );
                    bool = false;
                }

                System.out.println(fileOrder);
                System.out.println(o);
            }
        }
        return bool;
    }
}
