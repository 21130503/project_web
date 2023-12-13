package DAO;

import Services.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BelongDAO {
//    Có nghĩa là cái ảnh này thuộc về chủ đề nào
    public boolean insertOddImageBelongTopic(int idTopic , int idOddImage){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "Insert into OddImageBelongTopic values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idTopic);
            preparedStatement.setInt(2,idOddImage);
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
    public  int getIdTopicFromIdOdd(int idOddImage){
        Connection connection = null;
        int i=0;
        try{
            connection = Connect.getConnection();
            String sql = "select idTopic from OddImageBelongTopic where  idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               i = resultSet.getInt("idTopic");
               return i;
            }
            else {
                return  i;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public  int getIdTopicFromIdAlbum(int idAlbum){
        Connection connection = null;
        int i=0;
        try{
            connection = Connect.getConnection();
            String sql = "select idTopic from AlbumBelongTopic where  idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                i = resultSet.getInt("idTopic");
                return i;
            }
            else {
                return  i;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public boolean insertAlbumBelongTopic(int idTopic , int idAlbum){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "Insert into albumbelongtopic values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idTopic);
            preparedStatement.setInt(2,idAlbum);
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
    public boolean deleteOddImage(int idOddImage){
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "delete from OddImageBelongTopic where idOddImage = ?";
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
    public boolean deleteAlbum(int idAlbum){
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "delete from AlbumBelongTopic where idAlbum = ?";
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
    public ArrayList<Integer> listIdOddImageBelongTopic(String idTopic){
        Connection connection = null;
        ArrayList<Integer> list = new ArrayList<>();
        try {
            connection = Connect.getConnection();
            String sql = "select idOddImage from OddImageBelongTopic where idTopic = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idTopic);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getInt("idOddImage"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return list;
    }
    public ArrayList<Integer> listIdAlbumBelongTopic(String idTopic){
        Connection connection = null;
        ArrayList<Integer> list = new ArrayList<>();
        try {
            connection = Connect.getConnection();
            String sql = "select idAlbum from AlbumBelongTopic where idTopic = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idTopic);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getInt("idAlbum"));
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
