package logic;

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
                if(o.getIdProduct() == fileOrder.getIdProduct()
                        && o.getType() == fileOrder.getType()
                        && o.getPhoneNumber() == fileOrder.getPhoneNumber()
                        && o.getAddress() == fileOrder.getAddress()
                        && o.getReceiver() == fileOrder.getReceiver()
                        && o.getTotalPrice() == fileOrder.getTotalPrice()

                ){
                    bool= true;
                }
//                else{
//                    User user  = userDAO.getUserById(String.valueOf(fileOrder.getUserId()));
//                    sendEmail.sendEmail("21130503@st.hcmuaf.edu.vn", "QUY10042003",user.getEmail(),"Đơn hàng của bạn đã có sự thay đổi","Đơn hàng của bạn đã bị hủy vì có sự thay đổi");
//                    orderDAO.updateAlbumStatus(String.valueOf(o.getIdOrder()), "Đã hủy");
//                    bool =false;
//                }
                System.out.println(fileOrder);
                System.out.println(o);
            }
        }
        return bool;
    }
}
