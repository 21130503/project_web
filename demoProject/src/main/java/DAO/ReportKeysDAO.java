package DAO;

import Services.Connect;
import nhom26.ReportKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<ReportKeys> getReportList(int userID) {
        List<ReportKeys> reportList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Connect.getConnection();

            String sql = "SELECT id, reportUserID, publicKeysId, date, time, reason FROM reportkeys WHERE reportUserID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                int publicKeysId = rs.getInt("publicKeysId");
                Date date = rs.getDate("date");
                Time time = rs.getTime("time");
                String reason = rs.getString("reason");

                ReportKeys report = new ReportKeys(id, userId, publicKeysId, date.toLocalDate(), time.toLocalTime(), reason);
                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reportList;
    }
}
