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
    public boolean insertFeedbackForAlbum(int idAlbum, int idUser, String content, String star){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "insert into AlbumFeedback(idUser, idAlbum, content,star, createdAt) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2,idAlbum);
            preparedStatement.setString(3, content);
            preparedStatement.setString(4, star);
            preparedStatement.setDate(5,sqlDate);
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
    public boolean insertFeedbackForOddImage(int idOddImage, int idUser, String content,String star){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "insert into OddImageFeedback(idUser, idOddImage, content,star,createdAt) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2,idOddImage);
            preparedStatement.setString(3, content);
            preparedStatement.setString(4, star);
            preparedStatement.setDate(5,sqlDate);
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
//    Update lại star
    public  boolean updateStarForOddImage(String star, int idUser, int idOddImage){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "UPDATE OddImageFeedback SET star = ? where idUser =? and idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,star);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setInt(3, idOddImage);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public  boolean updateStarForAlbum(String star, int idUser, int idAlbum){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "UPDATE AlbumFeedback SET star = ? where idUser =? and  idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,star);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setInt(3, idAlbum);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
//    update cả
    public boolean updateOddImageFeedback(int idUser, int idOddImage, String star, String content){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "update OddImageFeedback set star = ?, content = ? where idUser = ? and idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, star);
            preparedStatement.setString(2, content);
            preparedStatement.setInt(3,idUser );
            preparedStatement.setInt(4, idOddImage);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
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
    public boolean updateAlbumFeedback(int idUser, int idAlbum, String star, String content){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "update AlbumFeedback set star = ?, content = ? where idUser = ? and idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, star);
            preparedStatement.setString(2, content);
            preparedStatement.setInt(3,idUser );
            preparedStatement.setInt(4, idAlbum);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
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
//    kiểm tra người ta đã bình luận chưa trước
    public boolean checkUserFeedbackForOddImage(int idUser, int idOddImage){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select idUser from  OddImageFeedback where idUser = ? and  idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idUser);
            preparedStatement.setInt(2, idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return  true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public boolean checkUserFeedbackForAlbum(int idUser, int idAlbum){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select idUser from  AlbumFeedback where idUser = ? and idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idUser);
            preparedStatement.setInt(2, idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return  true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
//    Toatal star feedback
    public int countRatingOddImage(int idOddImage){
        int res = 0;
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(star) as total from OddImageFeedback where idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getInt("total");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  res;
    }
    public int countRatingAlbum(int idAlbum){
        int res = 0;
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(star) as total from AlbumFeedback where idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getInt("total");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  res;
    }
    //    Star avg
    public double AvgRatingOddImage(int idOddImage){
        double res = 0;
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select avg(star) as avgStar from OddImageFeedback where idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getDouble("avgStar");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  res;
    }
    public double AvgRatingAlbum(int idAlbum){
        double res = 0;
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select avg(star) as avgStar from AlbumFeedback where idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getDouble("avgStar");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  res;
    }
}
