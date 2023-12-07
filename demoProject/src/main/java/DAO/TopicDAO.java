package DAO;

import Services.Connect;
import nhom26.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TopicDAO {
    public boolean insertTopic(String nameTopic, String interfaceImage) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(idTopic) FROM topic")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Kiểm tra giá trị NULL trước khi sử dụng
                        int maxIdTopic = (resultSet.getObject(1) != null) ? resultSet.getInt(1) : 0;
                        String sqlInsert = "Insert into topic values (?,?,?)";
                        PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlInsert);
                        preparedStatementInsert.setInt(1, maxIdTopic + 1);
                        preparedStatementInsert.setString(2, nameTopic);
                        preparedStatementInsert.setString(3, interfaceImage);
                        int check = preparedStatementInsert.executeUpdate();
                        if (check > 0) {
                            return true;
                        }
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public ArrayList<Topic> getAllTopics(){
        Connection connection = null;
        ArrayList<Topic> listTopic = new ArrayList<Topic>();
        try{
            connection = Connect.getConnection();
            // Câu truy vấn lấy dữ liệu topic
            String getAllTopic = "select idTopic , name, interfaceImage, isShow from topic";
            PreparedStatement preparedStatementGetTopic = connection.prepareStatement(getAllTopic);
            ResultSet resultSetGetTopic = preparedStatementGetTopic.executeQuery();
            while (resultSetGetTopic.next()) {
                Topic topic = new Topic();
                topic.setIdTopic(resultSetGetTopic.getInt("idTopic"));
                topic.setName(resultSetGetTopic.getString("name"));
                topic.setImageInterface(resultSetGetTopic.getString("interfaceImage"));
                topic.setProduct(0);
                topic.setShow(resultSetGetTopic.getBoolean("isShow"));
                listTopic.add(topic);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTopic;
    }
    public boolean checkNameTopicExist(String nameTopic) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "select name from topic where name = ?";
            PreparedStatement preparedStatementCheckNameTopic = connection.prepareStatement(sql);
            preparedStatementCheckNameTopic.setString(1, nameTopic);
            ResultSet resultSetEmail = preparedStatementCheckNameTopic.executeQuery();
            if (resultSetEmail.next()) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public boolean deleteTopic(String idTopic) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sqlDelete = "delete from topic where idTopic = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setString(1,idTopic);
            int check = preparedStatement.executeUpdate();
            if(check >= 0){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public int getIdTopicByName(String nameTopic){
        int res =0;
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "select idTopic from topic where name= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nameTopic);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getInt("idTopic");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  res;
    }
    public String getNameTopicById(int idTopic){
        Connection connection = null;
        String res = "";
        try{
            connection = Connect.getConnection();
            String sql = "select name from topic where idTopic = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idTopic);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getString("name");
                return  res;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  res;
    }
}
