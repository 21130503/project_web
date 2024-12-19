package DAO;

import Services.Connect;
import nhom26.PublicKeys;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public List<PublicKeys> getAllPublicKeys(int userID) {
        Connection connection = null;
        List<PublicKeys> publicKeysList = new ArrayList<>();
        try {
            connection = Connect.getConnection();
            String sql = "SELECT * FROM publickeys WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String publicKey = resultSet.getString("publicKey");
                LocalDateTime createTime = resultSet.getTimestamp("createTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();

                PublicKeys publicKeyObj = new PublicKeys(id, userId, publicKey, createTime, endTime);
                publicKeysList.add(publicKeyObj);
            }
        } catch (Exception e) {

        } finally {
            Connect.closeConnection(connection);
        }
        return publicKeysList;
    }

    public static void main(String[] args) {
        UserKeyDAO dao = new UserKeyDAO();
        System.out.println(dao.getAllPublicKeys(12).size());
    }
}
