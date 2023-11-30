package DAO;

import Services.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DescriptionDAO {
    //    dán moo tả của ảnh lẻ
    public boolean insertDescriptionDAO(int idOddImage, String description) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "insert into ct_oddImage values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            preparedStatement.setString(2, description);
            int check = preparedStatement.executeUpdate();
            if (check >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
    }
    public boolean insertDescriptionAlbum(int idAlbum, String description){
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "insert into ct_album values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
            preparedStatement.setString(2, description);
            int check = preparedStatement.executeUpdate();
            if (check >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
    }

    public boolean deleteDescriptionAlbum(int idAlbum){
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "delete from ct_album where idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idAlbum);
            int check = preparedStatement.executeUpdate();
            if(check >0){
                return  true;
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
    public boolean deleteDescriptionOddImage(int idOddImage){
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "delete from ct_oddImage where idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idOddImage);
            int check = preparedStatement.executeUpdate();
            if(check >0){
                return  true;
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
}
