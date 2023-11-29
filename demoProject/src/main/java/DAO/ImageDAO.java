package DAO;

import Services.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImageDAO {
    public boolean insertImage(ArrayList<String> sources, int idAlbum){
        Connection connection =null;
        try{
            connection = Connect.getConnection();
            String sql = "Insert into Image(idAlbum,source) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i=0; i< sources.size(); i++){
                preparedStatement.setInt(1, idAlbum);
                preparedStatement.setString(2,sources.get(i));
                int check = preparedStatement.executeUpdate();
                if(check < 0){
                    return false;
                }
            }
            return  true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public ArrayList<String> getAllImageByIdAlbum(int idAlbum){
        ArrayList<String> listImage = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "select source from image where idAlbum= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 listImage.add(resultSet.getString("source"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  listImage;
    }
    public  boolean deleteImageBelongAblum(int idAlbum){
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "delete from image where idAlbum = ?";
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
}
