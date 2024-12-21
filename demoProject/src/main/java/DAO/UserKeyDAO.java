package DAO;

import Services.Connect;
import nhom26.PublicKeys;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            String sql = "SELECT * FROM `publickeys` WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String publicKey = resultSet.getString("publicKey");
                LocalDateTime createTime = resultSet.getTimestamp("createTime").toLocalDateTime();
                LocalDateTime endTime = null;
                try {
                    endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                }catch (Exception e){
                }

                PublicKeys publicKeyObj = new PublicKeys(id, userId, publicKey, createTime, endTime);
                publicKeysList.add(publicKeyObj);
            }
        } catch (Exception e) {

        } finally {
            Connect.closeConnection(connection);
        }
        return publicKeysList;
    }

    public PublicKeys getPublicKey(int id) {
        Connection connection = null;
        PublicKeys publicKeyObj = null;
        try {
            connection = Connect.getConnection();
            String sql = "SELECT * FROM publickeys WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int publicKeyId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String publicKey = resultSet.getString("publicKey");
                LocalDateTime createTime = resultSet.getTimestamp("createTime").toLocalDateTime();
                LocalDateTime endTime = null;
                try {
                    endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                }catch (Exception e){

                }

                publicKeyObj = new PublicKeys(publicKeyId, userId, publicKey, createTime, endTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(connection);
        }
        return publicKeyObj;
    }

    public static void main(String[] args) {
        UserKeyDAO dao = new UserKeyDAO();
        System.out.println(dao.getCurrentPublicKey(12));
        String dateTimeString = "2023-12-19T10:15:30";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
        dao.setEndTime(4, localDateTime);
    }

    public PublicKeys getCurrentPublicKey(int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PublicKeys curPubKey = null;

        try {
            connection = Connect.getConnection();
            String sql = "SELECT * FROM publickeys WHERE userId = ? AND endTime IS NULL ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int publicKeyId = resultSet.getInt("id");
                String publicKey = resultSet.getString("publicKey");
                LocalDateTime createTime = resultSet.getTimestamp("createTime").toLocalDateTime();
                System.out.println("createTime "  +  createTime);
                LocalDateTime endTime = null;
                try {
                    endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                }catch (Exception e){

                }
                System.out.println("endTime " + endTime);
                curPubKey = new PublicKeys(publicKeyId, userId, publicKey, createTime, endTime);
            }
        } catch (SQLException e) {
        }
        return curPubKey;
    }

    public void setEndTime(int curPublicKeyID, LocalDateTime localDateTime) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Connect.getConnection();
            String sql = "UPDATE publickeys SET endTime = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(localDateTime));
            preparedStatement.setInt(2, curPublicKeyID);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("EndTime updated successfully for ID: " + curPublicKeyID);
            } else {
                System.out.println("No record found with ID: " + curPublicKeyID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
