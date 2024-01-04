package DAO;

import Properties.URL;
import Services.Connect;
import nhom26.Album;
import nhom26.OddImage;
import nhom26.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO {
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    ProductDAO productDAO = new ProductDAO();

    public boolean insertOrderOdd(int idOddImage, int idUser,String receiver ,String phoneNumber ,int quantity, int totalPrice, String address) {
        Connection connection = null;
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "insert into OddImageOrder(idOddImage, idUser,receiver, phoneNumber, quantity ,totalPrice, status, address, purchareDate) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
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
    public boolean insertOrderAlbum(int idAlbum, int idUser,String receiver ,String phoneNumber ,int quantity, int totalPrice, String address) {
        Connection connection = null;
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "insert into AlbumOrder(idAlbum, idUser,receiver, phoneNumber, quantity ,totalPrice, status, address, purchareDate) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
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
    public ArrayList<Order> getAllOrderOddImageForUser(int idUser) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder, idOddImage,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from oddImageOrder where idUser = ? and status not like  ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2,"%Đã hủy%");
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
    public ArrayList<Order> getAllOrderAlbumForUser(int idUser) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder, idAlbum,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from albumOrder where idUser = ? and status not like  ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2,"%Đã hủy%");
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
public ArrayList<Order> getAllOrderOddImageForAdmin() {
    Connection connection = null;
    ArrayList<Order> listOrder = new ArrayList<>();
    ProductDAO productDAO = new ProductDAO();
    try {
        connection = Connect.getConnection();
        String sql = "select idOrder, idOddImage,idUser,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from oddImageOrder";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
    public ArrayList<Order> getAllOrderAlbumForAdmin() {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder, idAlbum,idUser,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from AlbumOrder";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
    public ArrayList<Order> getAllOrderOddImageForByStatus(String status) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder,idUser, idOddImage,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from oddImageOrder where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,status);
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
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return  listOrder;
    }
    public ArrayList<Order> getAllOrderAlbumForByStatus(String status) {
        Connection connection = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try {
            connection = Connect.getConnection();
            String sql = "select idOrder,idUser, idAlbum,receiver, phoneNumber, quantity, address, status , purchareDate ,totalPrice from albumOrder where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,status);
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

    public  boolean updateOddStatus(String idOrder,String status){
        Connection connection= null;
        try{
            connection = Connect.getConnection();
            String sql = "update OddImageOrder set status= ? where idOrder = ?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2,idOrder);
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
            String sql = "update AlbumOrder set status= ? where idOrder = ?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2,idOrder);
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
}
