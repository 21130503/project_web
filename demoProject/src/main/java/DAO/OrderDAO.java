package DAO;

import Services.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDAO {
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    public  boolean insertOrderOdd(int idOddImage,int idUser,int quantity,  String address){
        Connection connection = null;
        try{
            connection  = Connect.getConnection();
            String sql = "insert into OddImageOrder(idOddImage, idUser, quantity, status, address, purchareDate) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setString(4,"Đang chuẩn bị");
            preparedStatement.setString(5,address);
            preparedStatement.setDate(6,sqlDate);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  false;
    }
}
