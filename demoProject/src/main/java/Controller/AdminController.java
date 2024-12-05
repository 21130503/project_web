package Controller;

import DAO.OrderDAO;
import DAO.UserDAO;
import logic.EquaslFileOrder;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AdminController", value = "/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        EquaslFileOrder equaslFileOrder = new EquaslFileOrder();
//        check edit in database
        try {
            if(equaslFileOrder.checkEqualsFileOrderWithAlbum() == true){
                System.out.println("Không có sự thay dổi nào");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("totalUser", userDAO.getCount());
        req.setAttribute("userNew", userDAO.getCountThisMonth());
//        Tổng thu nhập
        OrderDAO orderDAO = new OrderDAO();
        req.setAttribute("totalPrice", orderDAO.getAlbumTotalPrice() + orderDAO.getOddTotalPrice());
        req.setAttribute("priceThisMonth", orderDAO.getAlbumTotalPriceThisMonth()+ orderDAO.getOddTotalPriceThisMonth());
//        Tổng đơn hàng hủy
        req.setAttribute("totalCancel", orderDAO.getTotalCancel());
        req.setAttribute("totalCancelThisMonth", orderDAO.getTotalCancelThisMonth());
//        Tổng đơn đặt hàng
        req.setAttribute("totalOrder", orderDAO.getTotalOrder());
        req.setAttribute("totalOrderThisMonth", orderDAO.getTotalOrderThisMonth());
//        Người dùng có sl hỷ đơn cao
        HashMap<User, Integer> mapOdd = orderDAO.getUserCancelOddHigh();
        HashMap<User, Integer> mapAlbum = orderDAO.getUserCancelAlbumHigh();
        HashMap<User, Integer> mapCart = orderDAO.getUserCancelCartHigh();
        HashMap<User , Integer> mergedMap = mergeMaps(mapOdd,mapCart,mapAlbum);
//        for (Map.Entry<User, Integer> entry : mergedMap.entrySet()) {
//            User user = entry.getKey();
//            int totalCancelCount = entry.getValue();
//
//            System.out.println("User: " + user.toString() + ", Total Cancel Count: " + totalCancelCount);
//        }
        req.setAttribute("mapUserCancelHigh", mergedMap);
        req.getRequestDispatcher("admin.jsp").forward(req,resp);
    }
    private static HashMap<User, Integer> mergeMaps(HashMap<User, Integer>... maps) {
        HashMap<User, Integer> mergedMap = new HashMap<>();

        for (HashMap<User, Integer> map : maps) {
            for (Map.Entry<User, Integer> entry : map.entrySet()) {
                User user = entry.getKey();
                int cancelCount = entry.getValue();

                if (mergedMap.containsKey(user)) {

                    mergedMap.put(user, mergedMap.get(user) + cancelCount);
                } else {
                    mergedMap.put(user, cancelCount);
                }
//                mergedMap.merge(user, cancelCount, (oldValue, newValue) -> oldValue + newValue);
            }
        }

        return mergedMap;
    }
}
