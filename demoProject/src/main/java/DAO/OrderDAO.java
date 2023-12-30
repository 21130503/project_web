package DAO;

import Properties.URL;
import Services.Connect;
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
                order.setNameProduct(productDAO.getOddImageById(resultSet.getInt("idOddImage")).getName());
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setPurchareDate(resultSet.getDate("purchareDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setReceiver(resultSet.getString("receiver"));
                order.setPhoneNumber(resultSet.getString("phoneNumber"));
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
               list8OddImageOrder.add(oddImage);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  list8OddImageOrder;
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
            order.setNameProduct(productDAO.getOddImageById(resultSet.getInt("idOddImage")).getName());
            order.setQuantity(resultSet.getInt("quantity"));
            order.setAddress(resultSet.getString("address"));
            order.setStatus(resultSet.getString("status"));
            order.setPurchareDate(resultSet.getDate("purchareDate"));
            order.setTotalPrice(resultSet.getInt("totalPrice"));
            order.setReceiver(resultSet.getString("receiver"));
            order.setPhoneNumber(resultSet.getString("phoneNumber"));
            order.setIdByer(resultSet.getInt("idUser"));
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
}
