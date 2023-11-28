package DAO;

import Services.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    BelongDAO belongDAO = new BelongDAO();
    DescriptionDAO descriptionDAO = new DescriptionDAO();
    TopicDAO topicDAO = new TopicDAO();
    ImageDAO imageDAO = new ImageDAO();
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    public boolean insertOddImage(String nameOddImage, String source, String description, int price, int discount, String nameTopic) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            if (checkOddNameExist(nameOddImage)) {
                return false;
            } else {

                String sqlInsert = "insert into oddImage(name, source, price, discount, createdAt) values (?,?,?,?,?)";
                PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlInsert);
                preparedStatementInsert.setString(1, nameOddImage);
                preparedStatementInsert.setString(2, source);
                preparedStatementInsert.setInt(3, price);
                preparedStatementInsert.setInt(4, discount);
                preparedStatementInsert.setDate(5, sqlDate);
                int checkOddInsert = preparedStatementInsert.executeUpdate();
                if (checkOddInsert >= 0 &&
                        belongDAO.insertOddImageBelongTopic(topicDAO.getIdTopicByName(nameTopic), getIdOddImageByName(nameOddImage))
                        && descriptionDAO.insertDescriptionDAO(getIdOddImageByName(nameOddImage), description)) {
                    return true;
                } else {
                    return false;
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
    }

    public boolean checkOddNameExist(String name) {
        Connection connection = null;

        try {
            connection = Connect.getConnection();
            String sql = "select name from oddImage where name = ?";
            PreparedStatement preparedStatementCheckEmail = connection.prepareStatement(sql);
            preparedStatementCheckEmail.setString(1, name);
            ResultSet check = preparedStatementCheckEmail.executeQuery();
            if (check.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }

    public int getIdOddImageByName(String name) {
        Connection connection = null;
        int res = 0;
        try {
            connection = Connect.getConnection();
            String sql = "select idOddImage from oddImage where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt("idOddImage");
                return res;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return res;
    }

    //    Album
    public boolean insertAlbum(String nameTopic, String nameAlbum, String description, int price, int discount, ArrayList<String> images) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
//            Nếu tên album đã tồn tại thì trả về false
            if(checkNameAlbumExist(nameAlbum)){
                return false;
            }
//            Tiến hành insert
            else{
                String sql = "Insert into album(name,price, discount,createdAt) values (?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,nameAlbum);
                preparedStatement.setInt(2, price);
                preparedStatement.setInt(3, discount);
                preparedStatement.setDate(4, sqlDate);
                int check = preparedStatement.executeUpdate();
                if(check >0 && belongDAO.insertAlbumBelongTopic(topicDAO.getIdTopicByName(nameTopic),getIdAlbumByName(nameAlbum))
                        && descriptionDAO.insertDescriptionAlbum(getIdAlbumByName(nameAlbum), description)
                        && imageDAO.insertImage(images, getIdAlbumByName(nameAlbum))
                ){
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }

    public boolean checkNameAlbumExist(String name) {

        Connection connection = null;

        try {
            connection = Connect.getConnection();
            String sql = "select name from oddImage where name = ?";
            PreparedStatement preparedStatementCheckEmail = connection.prepareStatement(sql);
            preparedStatementCheckEmail.setString(1, name);
            ResultSet check = preparedStatementCheckEmail.executeQuery();
            if (check.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public int getIdAlbumByName(String name){
        Connection connection = null;
        int res = 0;
        try {
            connection = Connect.getConnection();
            String sql = "select idAlbum from album where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt("idAlbum");
                return res;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return res;
    }
}