package DAO;

import Services.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
//    get
    public String getDescriptionByOddImage(int idOddImage){
        String res= "";
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select description from ct_oddImage where idOddImage= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getString("description");
                return res;
            }
            else{
                return  res;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public String getDescriptionByAlbum(int idAlbum){
        String res= "";
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select description from ct_album where idAlbum= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getString("description");
                return res;
            }
            else{
                return  res;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public boolean updateDescriptionOddImage(String idOddImage,String description){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "update ct_oddImage set description = ? where  idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,description);
            preparedStatement.setString(2, idOddImage);
            int check = preparedStatement.executeUpdate();
           if(check > 0){
               return  true;
           }
           else {
            return  false;
           }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public boolean updateDescriptionAlbum(String idAlbum,String description){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "update ct_Album set description = ? where  idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,description);
            preparedStatement.setString(2, idAlbum);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return  true;
            }
            else {
                return  false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
}
