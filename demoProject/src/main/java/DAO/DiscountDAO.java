package DAO;

import Services.Connect;
import nhom26.Discount;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAO {

    //Lấy ra mã giảm giá bằng code
    public Discount getDiscountByCode(int code) {
        Connection connection = null;
        Discount discount = null;

        try {
            connection = Connect.getConnection();
            String sql = "SELECT code, description, discountValue, expiryDate, count FROM discount WHERE code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                discount = new Discount();
                discount.setCode(resultSet.getInt("code"));
                discount.setDescription(resultSet.getString("description"));
                discount.setDiscountValue(resultSet.getDouble("discountValue"));
                discount.setExpiryDate(resultSet.getDate("expiryDate"));
                discount.setCount(resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return discount;
    }


    //Lấy ra 1 danh sách mã giảm giá
    public List<Discount> getAllDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Connect.getConnection();
            String sql = "SELECT code, description, discountValue, expiryDate, count FROM discount";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Discount discount = new Discount();
                discount.setCode(resultSet.getInt("code"));
                discount.setDescription(resultSet.getString("description"));
                discount.setDiscountValue(resultSet.getDouble("discountValue"));
                discount.setExpiryDate(resultSet.getDate("expiryDate"));
                discount.setCount(resultSet.getInt("count"));
                discounts.add(discount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }

        return discounts;
    }


    //Tạo 1 mã giảm giá mới vào trong csdl
    public boolean addDiscount(Discount discount) {
        try (Connection connection = Connect.getConnection()) {
            String sql = "INSERT INTO discount (code, description, discountValue, expiryDate, count) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, discount.getCode());
            preparedStatement.setString(2, discount.getDescription());

            // Chuyển đổi giá trị giảm giá từ số nguyên sang số thập phân để lưu vào DB
            double discountValue = discount.getDiscountValue() / 100.0;
            preparedStatement.setDouble(3, discountValue);

            preparedStatement.setDate(4, new java.sql.Date(discount.getExpiryDate().getTime()));
            preparedStatement.setInt(5, discount.getCount());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Xóa mã giảm giá dựa trên code
    public boolean deleteDiscount(String code) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sqlDelete = "delete from discount where code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setString(1, code);
            int check = preparedStatement.executeUpdate();
            if (check >= 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }


    //Kiểm tra code mã giảm giá đã tồn tại chưa trong TH tạo mã mới
    public boolean checkDiscountExist(int code) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "SELECT code FROM discount WHERE code = ?";
            PreparedStatement preparedStatementCheckCode = connection.prepareStatement(sql);
            preparedStatementCheckCode.setInt(1, code);
            ResultSet resultSet = preparedStatementCheckCode.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
    }


    //Cập nhật lại 1 mã giảm giá đã tồn tại trong csdl
    public boolean updateDiscount(String code, String description, double discountValue, LocalDate expiryDate, int count) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sqlUpdate = "update discount set description = ?, discountValue = ?, expiryDate = ?, count = ? where code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, description);
            preparedStatement.setDouble(2, discountValue);
            preparedStatement.setDate(3, Date.valueOf(expiryDate));
            preparedStatement.setInt(4, count);
            preparedStatement.setString(5, code);

            int check = preparedStatement.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
    }


    //Kiểm tra code mã giảm giá đã tồn tại chưa trong TH cập nhật mã giảm giá
    public boolean checkDiscountExistForUpdate(int code, String idDiscount) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "SELECT COUNT(code) AS total FROM discount WHERE code = ? AND idDiscount <> ?";
            PreparedStatement preparedStatementCheckCode = connection.prepareStatement(sql);
            preparedStatementCheckCode.setInt(1, code);
            preparedStatementCheckCode.setString(2, idDiscount);
            ResultSet resultSet = preparedStatementCheckCode.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("total");
                return count > 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }


    //Kiểm tra tên mã giảm giá đã tồn tại chưa trong TH tạo mã mới
    public boolean checkDiscountExist(String description) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "SELECT description FROM discount WHERE description = ?";
            PreparedStatement preparedStatementCheckCode = connection.prepareStatement(sql);
            preparedStatementCheckCode.setString(1, description);
            ResultSet resultSet = preparedStatementCheckCode.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
    }
}