package DAO;

import Services.Connect;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ReportDAO {
    public boolean insertReport(int userId, String date, String time) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            Date sqlDate = Date.valueOf(date);
            Time sqlTime = Time.valueOf( time += ":00");
            String sql = "insert into reportKey(userId,date,time) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setTime(3, sqlTime);
            int check = preparedStatement.executeUpdate();
            return check > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
