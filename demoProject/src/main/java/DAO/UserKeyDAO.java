package DAO;

import Services.Connect;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class UserKeyDAO {
    Date utilDate = new Date();

    Timestamp createDate = new Timestamp(utilDate.getTime());

    LocalDateTime localDateTime = LocalDateTime.of(9999, 12, 31, 23, 59, 59);

    Timestamp expiryDate = Timestamp.valueOf(localDateTime);


    public boolean insertPublicKey(int idUser, String publicKey) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "insert into publickeys (userId, publicKey, createTime ) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, publicKey);
            preparedStatement.setTimestamp(3, createDate);
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }


}
