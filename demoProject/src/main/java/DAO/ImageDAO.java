package DAO;

import Services.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
