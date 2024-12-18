package DAO;

import Services.Connect;

import java.sql.*;

public class ReportKeysDAO {
    public boolean insertReportKeys(int userId, int publicKeysId, String date, String time, String reason) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            Date sqlDate = Date.valueOf(date); // Chuyển chuỗi thành kiểu Date
            Time sqlTime = Time.valueOf(time + ":00"); // Đảm bảo định dạng HH:mm:ss

            String sql = "INSERT INTO reportkeys(reportUserID, publicKeysId, date, time, reason) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, publicKeysId);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setTime(4, sqlTime);
            preparedStatement.setString(5, reason);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu chèn thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ReportKeysDAO dao = new ReportKeysDAO();
        dao.insertReportKeys(10, 1, "2024-12-07", "02:20", " ");
    }
}
