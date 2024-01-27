package DAO;

import Services.Connect;
import nhom26.User;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class UserDAO {
    public boolean checkEmailExist(String email) {
        Connection connection = null;
        boolean checkEmail = false;
        try {
            connection = Connect.getConnection();
            String checkEmailQuery = "select email from user where email = ?";
            PreparedStatement preparedStatementCheckEmail = connection.prepareStatement(checkEmailQuery);
            preparedStatementCheckEmail.setString(1, email);
            ResultSet resEmail = preparedStatementCheckEmail.executeQuery();
            if (resEmail.next()) {
                checkEmail = true;
                return checkEmail;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return checkEmail;
    }
    public boolean resgisterWithEmail(String email, String username, String pass) {
        Connection connection = null;
        if (checkEmailExist(email)) {
            return false;
        } else {
            try {
                connection = Connect.getConnection();
                String sql = "SELECT MAX(idUser) AS max_user_id FROM user";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                if (resultSet.next()) {
                    int maxUserId = resultSet.getInt("max_user_id");
                    String insert = "Insert into User values (?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
                    preparedStatement1.setInt(1, maxUserId + 1);
                    preparedStatement1.setString(3, email);
                    preparedStatement1.setString(2, username);
                    preparedStatement1.setString(4, pass);
                    preparedStatement1.setString(5, "false");
                    preparedStatement1.setString(6, "true");
                    preparedStatement1.setString(7, "false");
                    preparedStatement1.setDate(8, sqlDate);
                    int resultSet1 = preparedStatement1.executeUpdate();
                    JSONObject jsonObject1 = new JSONObject();
                    if (resultSet1 >= 0) {
                        return true;
                    } else {
                        return false;
                    }

                } else {
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
    public User getUserByEmailAndPass(String email, String pass) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "select idUser, email,name, password, isVerifyEmail, isActive, isAdmin, createdAt from user where email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPasword(resultSet.getString(4));
                user.setVerifyEmail(resultSet.getBoolean(5));
                user.setActive(resultSet.getBoolean(6));
                user.setAdmin(resultSet.getBoolean(7));
                user.setCreatedAt(resultSet.getDate(8));
//                System.out.println(user);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return null;
    }
    public ArrayList<User> getAllUsers(){
        Connection connection= null;
        ArrayList<User> listUser = new ArrayList<>();
        ArrayList<User> res = new ArrayList<User>();
        try{
            connection = Connect.getConnection();
            String getAllUser = "select idUser, email,name, password, isVerifyEmail, isActive, isAdmin, createdAt from user";
            PreparedStatement preparedStatementGetUser= connection.prepareStatement(getAllUser);
            ResultSet resultSetGetUser = preparedStatementGetUser.executeQuery();
            while (resultSetGetUser.next()) {
                User user = new User();
                user.setId(resultSetGetUser.getInt("idUser"));
                user.setEmail(resultSetGetUser.getString("email"));
                user.setUsername(resultSetGetUser.getString("name"));
                user.setPasword(resultSetGetUser.getString("password"));
                user.setVerifyEmail(resultSetGetUser.getBoolean("isVerifyEmail"));
                user.setActive(resultSetGetUser.getBoolean("isActive"));
                user.setAdmin(resultSetGetUser.getBoolean("isAdmin"));
                user.setCreatedAt(resultSetGetUser.getDate("createdAt"));
                listUser.add(user);
            }
            for (User user : listUser) {
                if(!user.isAdmin()){
                    res.add(user);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return res;
    }
    public  String getUsernameById(int idUser){
        Connection connection = null;
        String username = "";
        try{
            connection = Connect.getConnection();
            String sql = "select name from user where idUser= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                username = resultSet.getString("name");
                return  username;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return username;
    }
    public boolean verifyEmail(String email){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "update user SET isVerifyEmail = ? where email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "true");
            preparedStatement.setString(2, email);
            int check = preparedStatement.executeUpdate();
            if(check > 0 ){
                return  true;
            }
            else{
                return  false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
    }
    public boolean updatePasswordByEmail(String email, String pass){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "UPDATE user set password = ? where email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pass);
            preparedStatement.setString(2, email);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
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
    public User getUserById(String id){
        Connection connect = null;
        User user = new User();
        try {
            connect = Connect.getConnection();
            String sql = "SELECT idUser,email,name,isVerifyEmail,isAdmin,isActive,createdAt from user WHERE idUser =?";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("idUser"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("name"));
                user.setVerifyEmail(resultSet.getBoolean("isVerifyEmail"));
                user.setActive(resultSet.getBoolean("isActive"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                user.setCreatedAt(resultSet.getDate("createdAt"));
                return  user;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connect);
        }
        return  user;
    }
    public ArrayList<User> getUserByName(String name){
        Connection connection= null;
        ArrayList<User> listUser = new ArrayList<>();
        ArrayList<User> res = new ArrayList<User>();
        try{
            connection = Connect.getConnection();
            String getAllUser = "select idUser, email,name, isVerifyEmail, isActive, isAdmin, createdAt from user where  name like ?";
            PreparedStatement preparedStatementGetUser= connection.prepareStatement(getAllUser);
            preparedStatementGetUser.setString(1,"%" + name+"%");
            ResultSet resultSetGetUser = preparedStatementGetUser.executeQuery();
            while (resultSetGetUser.next()) {
                User user = new User();
                user.setId(resultSetGetUser.getInt("idUser"));
                user.setEmail(resultSetGetUser.getString("email"));
                user.setUsername(resultSetGetUser.getString("name"));
                user.setVerifyEmail(resultSetGetUser.getBoolean("isVerifyEmail"));
                user.setActive(resultSetGetUser.getBoolean("isActive"));
                user.setAdmin(resultSetGetUser.getBoolean("isAdmin"));
                user.setCreatedAt(resultSetGetUser.getDate("createdAt"));
                listUser.add(user);
            }
            for (User user : listUser) {
                if(!user.isAdmin()){
                    res.add(user);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return res;
    }
    public int getCount(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idUser) as total from user ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  0;
    }
    public int getCountThisMonth(){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idUser) as total from user where year(createdAt) = ? and month(createdAt) = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,currentYear);
            preparedStatement.setInt(2,currentMonth);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return res.getInt("total");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  0;
    }
    public boolean updateUser(String username, String password,int idUser){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "update user set name= ? ,password= ? where idUser = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setInt(3, idUser);
            int check = preparedStatement.executeUpdate();
            if(check > 0){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  false;
    }
}
