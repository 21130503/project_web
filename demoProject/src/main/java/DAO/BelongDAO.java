package DAO;

import Services.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
