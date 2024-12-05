package DAO;

import Services.Connect;
import nhom26.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationDAO {
    java.util.Date utilDate = new java.util.Date();

    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    public boolean insertNotification(int idUser , String  content, String type){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "Insert into Notification(idUser, content, type, createdAt) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2,content);
            preparedStatement.setString(3,type);
            preparedStatement.setDate(4, sqlDate);
            int check = preparedStatement.executeUpdate();
            if(check >=0){
                return  true;
            }
            else {
                return  false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public ArrayList<Notification> getNotification(int userId){
        Connection connection = null;
        ArrayList<Notification> notifications = new ArrayList<>();
        try {
            connection = Connect.getConnection();
            String sql = "select id,idUser, content, type  from notification where idUser = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Notification notification = new Notification();
                notification.setId(resultSet.getInt("id"));
                notification.setUserId(resultSet.getInt("idUser"));
                notification.setContent(resultSet.getString("content"));
                notification.setType(resultSet.getString("type"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return notifications;
    }
}
