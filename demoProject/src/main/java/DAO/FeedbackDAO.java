package DAO;

import Services.Connect;
import nhom26.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDAO {
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    UserDAO userDAO = new UserDAO();
    public boolean insertFeedbackForAlbum(int idAlbum, int idUser, String content){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "insert into AlbumFeedback(idUser, idAlbum, content, createdAt) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2,idAlbum);
            preparedStatement.setString(3, content);
            preparedStatement.setDate(4,sqlDate);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public boolean insertFeedbackForOddImage(int idOddImage, int idUser, String content){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "insert into OddImageFeedback(idUser, idOddImage, content,createdAt) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2,idOddImage);
            preparedStatement.setString(3, content);
            preparedStatement.setDate(4,sqlDate);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
//    get
    public ArrayList<Feedback> getAllFeedbackForAlbumById(int idAlbum){
        ArrayList<Feedback> list = new ArrayList<>();
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select idUser ,content, createdAt from AlbumFeedback where idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Feedback feedback = new Feedback();
                String username = userDAO.getUsernameById(resultSet.getInt("idUser"));
                feedback.setUsername(username);
                feedback.setContent(resultSet.getString("content"));
                feedback.setDate(resultSet.getDate("createdAt"));
                list.add(feedback);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return list;
    }
    public ArrayList<Feedback> getAllFeedbackForOddImageById(int idOddImage){
        ArrayList<Feedback> list = new ArrayList<>();
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select idUser ,content, createdAt from OddImageFeedback where idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Feedback feedback = new Feedback();
                String username = userDAO.getUsernameById(resultSet.getInt("idUser"));
                feedback.setUsername(username);
                feedback.setContent(resultSet.getString("content"));
                feedback.setDate(resultSet.getDate("createdAt"));
                list.add(feedback);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return list;
    }
}
