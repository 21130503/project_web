package DAO;

import Properties.URL;
import Services.Connect;
import nhom26.Album;
import nhom26.OddImage;
import nhom26.Order;
import nhom26.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class OrderDAO {
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    ProductDAO productDAO = new ProductDAO();
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH) + 1;

    public boolean insertOrderOdd(int idOddImage, int idUser,String receiver ,String phoneNumber ,int quantity, double totalPrice, String address,String file) {
        Connection connection = null;
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "insert into OddImageOrder(idOddImage, idUser,receiver, phoneNumber, quantity ,totalPrice, status, address, purchareDate, fileName) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setString(3, receiver);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setDouble(6,totalPrice );
            preparedStatement.setString(7, "Đang chuẩn bị");
            preparedStatement.setString(8, address);
            preparedStatement.setDate(9, sqlDate);
            preparedStatement.setString(10, file);
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public boolean insertOrderAlbum(int idAlbum, int idUser,String receiver ,String phoneNumber ,int quantity, double totalPrice, String address,String file) {
        Connection connection = null;
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "insert into AlbumOrder(idAlbum, idUser,receiver, phoneNumber, quantity ,totalPrice, status, address, purchareDate,fileName) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setString(3, receiver);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setDouble(6,totalPrice );
            preparedStatement.setString(7, "Đang chuẩn bị");
            preparedStatement.setString(8, address);
            preparedStatement.setDate(9, sqlDate);
            preparedStatement.setString(10, file);
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public boolean inserOrderCart( String name,int idUser,String receiver ,String phoneNumber ,int quantity, int totalPrice, String address) {
        Connection connection = null;
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "insert into CartOrder(name,idUser,receiver, phoneNumber, quantity ,totalPrice, status, address, purchareDate,fileName) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setString(3, receiver);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setInt(6,totalPrice );
            preparedStatement.setString(7, "Đang chuẩn bị");
            preparedStatement.setString(8, address);
            preparedStatement.setDate(9, sqlDate);
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public int getCountOrderOdd(int idUser){
        Connection connection = null;
        try{
            connection =Connect.getConnection();
            String sql = "select count(idUser) as total from oddImageOrder where idUser = ? and status not like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, "%Đã hủy%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("total");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public int getCountOrderAlbum(int idUser){
        Connection connection = null;
        try{
            connection =Connect.getConnection();
            String sql = "select count(idUser) as total from albumOrder where idUser = ? and status not like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, "%Đã hủy%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("total");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public int getCountOrderCart(int idUser){
        Connection connection = null;
        try{
            connection =Connect.getConnection();
            String sql = "select count(idUser) as total from cartOrder where idUser = ? and status not like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, "%Đã hủy%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("total");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public ArrayList<Order> getAllOrderOddImageForUser(int idUser, int  page, int recSize) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        int startIndex  = (page-1)*recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder, idOddImage,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from oddImageOrder where idUser = ? and status not like  ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2,"%Đã hủy%");
            preparedStatement.setInt(3,recSize);
            preparedStatement.setInt(4,startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setNameProduct(productDAO.getOddImageByIdForAdminUpdate(resultSet.getInt("idOddImage")).getName());
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setType("odd");
                order.setIdProduct(resultSet.getInt("idOddImage"));
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public ArrayList<Order> getAllOrderAlbumForUser(int idUser,int page, int recSize) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        int startIndex  = (page-1)*recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder, idAlbum,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from albumOrder where idUser = ? and status not like  ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2,"%Đã hủy%");
            preparedStatement.setInt(3,recSize);
            preparedStatement.setInt(4,startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setNameProduct(productDAO.getAlbumByIdForAdminUpdate(resultSet.getInt("idAlbum")).getName());
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setType("album");
                order.setIdProduct(resultSet.getInt("idAlbum"));
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public ArrayList<Order> getAllCartOrderForUser(int idUser,int page, int recSize) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        int startIndex  = (page-1)*recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder,name,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from CartOrder where idUser = ? and status not like  ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2,"%Đã hủy%");
            preparedStatement.setInt(3,recSize);
            preparedStatement.setInt(4,startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setNameProduct(resultSet.getString("name"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setType("cart");
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public String getStatusOddById(String idOrder){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "select  status from oddImageOrder where  idOrder = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idOrder );
           ResultSet resultSet = preparedStatement.executeQuery();
           if (resultSet.next()){
               return  resultSet.getString("status");
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }

        return "";
    }
    public String getStatusCartOrderById(String idOrder){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "select  status from CartOrder where  idOrder = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idOrder );
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return  resultSet.getString("status");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }

        return "";
    }
    public String getStatusAlbumById(String idOrder){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "select  status from AlbumOrder where  idOrder = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idOrder );
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return  resultSet.getString("status");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }

        return "";
    }
    public  boolean deleteOddImageOrder(String idOrder){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "update  OddImageOrder  set status = ? where idOrder = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"Đã hủy");
            preparedStatement.setString(2,idOrder );
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }

        return false;
    }
    public  boolean  deleteCartOrder(String idOrder){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "update  CartOrder  set status = ? where idOrder = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"Đã hủy");
            preparedStatement.setString(2,idOrder );
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }

        return false;
    }
    public  boolean deleteAlbumOrder(String idOrder){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "update  AlbumOrder  set status = ? where idOrder = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"Đã hủy");
            preparedStatement.setString(2,idOrder );
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }

        return false;
    }
    public  ArrayList<OddImage> getTop8OddImageOrder(){
        Connection connection = null;
        ArrayList<OddImage> list8OddImageOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try{
            connection = Connect.getConnection();
            String sql = "select idOddImage, count(idOddImage) from oddImageOrder GROUP BY idOddImage order by count(idOddImage) desc LIMIT 8";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               int idOddImage = resultSet.getInt("idOddImage");
               OddImage oddImage = productDAO.getOddImageById(idOddImage);
               if(oddImage.getName() !=null){

                   list8OddImageOrder.add(oddImage);
               }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  list8OddImageOrder;
    }
    public  ArrayList<Album> getTop8OAlbumOrder(){
        Connection connection = null;
        ArrayList<Album> list8AlbumOrder = new ArrayList<Album>();
        ProductDAO productDAO = new ProductDAO();
        try{
            connection = Connect.getConnection();
            String sql = "select idAlbum, count(idAlbum) from albumOrder GROUP BY idAlbum order by count(idAlbum) desc LIMIT 8";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idAlbum = resultSet.getInt("idAlbum");
                Album album = productDAO.getAlbumById(idAlbum);
                if(album.getName() !=null){

                    list8AlbumOrder.add(album);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  list8AlbumOrder;
    }
//    for admin
public ArrayList<Order> getAllOrderOddImageForAdmin(int page,int recSize) {
    Connection connection = null;
    ArrayList<Order> listOrder = new ArrayList<>();
    ProductDAO productDAO = new ProductDAO();
    int startIndex  = (page-1)*recSize;
    try {
        connection = Connect.getConnection();
        String sql = "select idOrder, idOddImage,idUser,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from oddImageOrder LIMIT ? OFFSET ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,recSize);
        preparedStatement.setInt(2,startIndex);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Order order = new Order();
            order.setIdOrder(resultSet.getInt("idOrder"));
            order.setNameProduct(productDAO.getOddImageByIdForAdminUpdate(resultSet.getInt("idOddImage")).getName());
            order.setQuantity(resultSet.getInt("quantity"));
            order.setAddress(resultSet.getString("address"));
            order.setStatus(resultSet.getString("status"));
            order.setPurchareDate(resultSet.getDate("purchareDate"));
            order.setTotalPrice(resultSet.getInt("totalPrice"));
            order.setReceiver(resultSet.getString("receiver"));
            order.setPhoneNumber(resultSet.getString("phoneNumber"));
            order.setIdByer(resultSet.getInt("idUser"));
            order.setType("odd");
            order.setIdProduct(resultSet.getInt("idOddImage"));
            listOrder.add(order);
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    } finally {
        Connect.closeConnection(connection);
    }
    return  listOrder;
}
    public ArrayList<Order> getAllCartOrderForAdmin(int page,int recSize) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        int startIndex  = (page-1)*recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder, name,idUser,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from CartOrder  LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,recSize);
            preparedStatement.setInt(2,startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setNameProduct(resultSet.getString("name"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setIdByer(resultSet.getInt("idUser"));
                order.setType("cart");
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public ArrayList<Order> getAllOrderAlbumForAdmin(int page,int recSize) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        int startIndex  = (page-1)*recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder, idAlbum,idUser,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from AlbumOrder  LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,recSize);
            preparedStatement.setInt(2,startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setNameProduct(productDAO.getAlbumByIdForAdminUpdate(resultSet.getInt("idAlbum")).getName());
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setIdByer(resultSet.getInt("idUser"));
                order.setType("album");
                order.setIdProduct(resultSet.getInt("idAlbum"));
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public ArrayList<Order> getAllCartForAdmin() {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder,name,idUser,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from CartOrder";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setNameProduct(resultSet.getString("name"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setIdByer(resultSet.getInt("idUser"));
                order.setType("cart");
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public int getCountOrderOddByStatus(String status){
        Connection connection ;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from oddImageOrder where status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%"+status+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int getCountOrderAlbumByStatus(String status){
        Connection connection ;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from albumOrder where status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%"+status+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int getCountOrderCartByStatus(String status){
        Connection connection ;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from cartOrder where status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%"+status+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public ArrayList<Order> getAllOrderOddImageForByStatus(String status, int page, int recSize) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        int startIndex  = (page-1)*recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder,idUser, idOddImage,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice,fileName from oddImageOrder where status = ? limit ? offset ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2, recSize);
            preparedStatement.setInt(3, startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setNameProduct(productDAO.getOddImageById(resultSet.getInt("idOddImage")).getName());
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setIdByer(resultSet.getInt("idUser"));
                order.setType("odd");
                order.setIdProduct(resultSet.getInt("idOddImage"));
                order.setFileName(resultSet.getString("fileName"));
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public ArrayList<Order> getAllOrderOddImagePrepare() {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder,idUser, idOddImage,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice,fileName from oddImageOrder where status LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%Đang chuẩn bị%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setIdByer(resultSet.getInt("idUser"));
                order.setType("odd");

                order.setIdProduct(resultSet.getInt("idOddImage"));
                order.setFileName(resultSet.getString("fileName"));
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public ArrayList<Order> getAllOrderAlbumImagePrepare() {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder,idUser, idAlbum,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice,fileName from albumorder where status  LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%Đang chuẩn bị%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setIdByer(resultSet.getInt("idUser"));
                order.setFileName(resultSet.getString("fileName"));
                order.setType("album");
                order.setIdProduct(resultSet.getInt("idAlbum"));
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public ArrayList<Order> getAllCartOrderForByStatus(String status,int page, int recSize) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        int startIndex  = (page-1)*recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder,idUser, name,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from CartOrder where status = ? limit ? offset ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2, recSize);
            preparedStatement.setInt(3, startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setNameProduct(resultSet.getString("name"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setIdByer(resultSet.getInt("idUser"));
                order.setType("cart");
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public ArrayList<Order> getAllOrderAlbumForByStatus(String status, int page, int recSize) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        int startIndex  = (page-1)*recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder,idUser, idAlbum,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from albumOrder where status = ? limit ? offset ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2, recSize);
            preparedStatement.setInt(3, startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setNameProduct(productDAO.getAlbumById(resultSet.getInt("idAlbum")).getName());
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setIdByer(resultSet.getInt("idUser"));
                order.setType("album");
                order.setIdProduct(resultSet.getInt("idAlbum"));
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public boolean checkUserOrderOddImage(int idUser, int idOddImage){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select idUser from OddImageOrder where idUser= ? and idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return  true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  false;
    }
    public boolean checkUserOrderAlbum(int idUser, int idAlbum){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select idUser from AlbumOrder where idUser= ? and idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return  true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  false;
    }

//    get order
    public Order getOrderOddEdit(String idOrder){
        Connection connection = null;
        Order order = new Order();
        try{
            connection = Connect.getConnection();
            String sql = "select idOrder,idOddImage, receiver ,totalPrice, phoneNumber, address, status from oddImageOrder where idOrder = ? and status not like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idOrder);
            preparedStatement.setString(2,"%Đã hủy%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setType("odd");
                order.setNameProduct(productDAO.getOddImageByIdForAdminUpdate(resultSet.getInt("idOddImage")).getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return order;
    }
    public Order getOrderAlbumEdit(String idOrder){
        Connection connection = null;
        Order order = new Order();
        try{
            connection = Connect.getConnection();
            String sql = "select idOrder,idAlbum, receiver ,totalPrice, phoneNumber, address, status from AlbumOrder where idOrder = ? and status not like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idOrder);
            preparedStatement.setString(2, "%Đã hủy%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setType("album");
                order.setNameProduct(productDAO.getAlbumByIdForAdminUpdate(resultSet.getInt("idAlbum")).getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return order;
    }
    public Order getOrderCartEdit(String idOrder){
        Connection connection = null;
        Order order = new Order();
        try{
            connection = Connect.getConnection();
            String sql = "select idOrder,name, receiver ,totalPrice, phoneNumber, address, status from CartOrder where idOrder = ? and status not like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idOrder);
            preparedStatement.setString(2,"%Đã hủy%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                order.setIdOrder(resultSet.getInt("idOrder"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setType("cart");
                order.setNameProduct(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return order;
    }
    public  boolean updateOddStatus(String idOrder,String status){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "update OddImageOrder set status= ? ,purchareDate = ? where idOrder = ?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setString(3,idOrder);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public  boolean updateAlbumStatus(String idOrder,String status){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "update AlbumOrder set status= ? , purchareDate= ? where idOrder = ?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setString(3,idOrder);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public  boolean updateCartStatus(String idOrder,String status){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "update CartOrder set status= ? , purchareDate= ? where idOrder = ?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setString(3,idOrder);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public int totalOddOrder(String table){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from "+ table;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  0;
    }
    public int getOddTotalPrice(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select sum(totalPrice) as total from OddImageOrder where status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã giao%");
            ResultSet resultSet =  preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  0;
    }
    public int getOddTotalPriceThisMonth(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select sum(totalPrice) as total from OddImageOrder where status like ? and year(purchareDate) =? and month(purchareDate) = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã giao%");
            preparedStatement.setInt(2,currentYear);
            preparedStatement.setInt(3,currentMonth);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  0;
    }
    public int getAlbumTotalPrice(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select sum(totalPrice) as total from AlbumOrder where status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã giao%");
            ResultSet resultSet =  preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  0;
    }
    public int getAlbumTotalPriceThisMonth(){

        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select sum(totalPrice) as total from AlbumOrder where status like ? and year(purchareDate) =? and month(purchareDate) = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã giao%");
            preparedStatement.setInt(2,currentYear);
            preparedStatement.setInt(3,currentMonth);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  0;



    }
//    Đơn hàng đã hủy
    public  int getOddCanceled(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from OddImageOrder where  status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã hủy%");
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getOddCanceledThisMonth(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from OddImageOrder where  status like ? and year(purchareDate)=? and month(purchareDate)=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã hủy%");
            preparedStatement.setInt(2,currentYear);
            preparedStatement.setInt(3,currentMonth);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getAlbumCanceled(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from AlbumOrder where  status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã hủy%");
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getAlbumCanceledThisMonth(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from AlbumOrder where  status like ? and year(purchareDate) = ? and month(purchareDate) = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã hủy%");
            preparedStatement.setInt(2,currentYear);
            preparedStatement.setInt(3,currentMonth);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getCartCanceled(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from CartOrder where  status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã hủy%");
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getCartCanceledThisMonth(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from CartOrder where  status like ? and year(purchareDate) = ? and month(purchareDate) = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%Đã hủy%");
            preparedStatement.setInt(2,currentYear);
            preparedStatement.setInt(3,currentMonth);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
//    Tổng đơn đã đăt;
    public  int getTotalOddOrder(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select COUNT(idOrder) as total from OddImageOrder";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getTotalOddOrderThisMonth(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from OddImageOrder where year(purchareDate) = ? and month(purchareDate) = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,currentYear);
            preparedStatement.setInt(2,currentMonth);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getTotalAlbumOrder(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from AlbumOrder";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getTotalAlbumOrderThisMonth(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from AlbumOrder where year(purchareDate) = ? and month(purchareDate) = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,currentYear);
            preparedStatement.setInt(2,currentMonth);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getTotalCartOrder(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select COUNT(idOrder) as total from CartOrder ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }
    public  int getTotalCartOrderThisMonth(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idOrder) as total from CartOrder where year(purchareDate) = ? and month(purchareDate) = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,currentYear);
            preparedStatement.setInt(2,currentMonth);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }


    public int getTotalCancel(){
        return  this.getOddCanceled() + this.getAlbumCanceled() + this.getCartCanceled();
    }
    public int getTotalCancelThisMonth(){
        return  this.getCartCanceledThisMonth() + this.getAlbumCanceledThisMonth() + this.getOddCanceledThisMonth();
    }

    public int getTotalOrder(){
        return this.getTotalAlbumOrder()+ this.getTotalOddOrder() + this.getTotalCartOrder();
    }
    public int getTotalOrderThisMonth(){
        return this.getTotalAlbumOrderThisMonth() + this.getTotalCartOrderThisMonth() + this.getTotalOddOrderThisMonth();
    }


    public HashMap<User,Integer> getUserCancelOddHigh(){
        Connection connection = null;
        HashMap<User, Integer> map = new HashMap<>();
        try{
            connection = Connect.getConnection();
            String sql = " select user.idUser , user.name, count(oddimageorder.idOrder) as total from user join oddimageorder on user.idUser = oddimageorder.idUser where oddimageorder.status like ? group by  user.idUser, user.name";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%Đã hủy%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setUsername(resultSet.getString("name"));
                map.put(user, resultSet.getInt("total"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  map;
    }
    public HashMap<User,Integer> getUserCancelAlbumHigh(){
        Connection connection = null;
        HashMap<User, Integer> map = new HashMap<>();
        try{
            connection = Connect.getConnection();
            String sql = " select user.idUser , user.name, count(albumOrder.idOrder) as total from user join albumOrder on user.idUser = albumOrder.idUser where albumOrder.status like ? group by  user.idUser, user.name";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%Đã hủy%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setUsername(resultSet.getString("name"));
                map.put(user, resultSet.getInt("total"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  map;
    }
    public HashMap<User,Integer> getUserCancelCartHigh(){
        Connection connection = null;
        HashMap<User, Integer> map = new HashMap<>();
        try{
            connection = Connect.getConnection();
            String sql = " select user.idUser  , user.name, count(cartOrder.idOrder) as total from user join cartOrder on user.idUser = cartOrder.idUser where cartOrder.status like ? group by  user.idUser, user.name";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%Đã hủy%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setUsername(resultSet.getString("name"));
                map.put(user, resultSet.getInt("total"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  map;
    }
}


