package DAO;

import Properties.URL;
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
                        String sqlInsert = "Insert into topic values (?,?,?,?)";
                        PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlInsert);
                        preparedStatementInsert.setInt(1, maxIdTopic + 1);
                        preparedStatementInsert.setString(2, nameTopic);
                        preparedStatementInsert.setString(3, "true");
                        preparedStatementInsert.setString(4, interfaceImage);
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
    public ArrayList<Topic> getAllTopics(int page,int recSize){
        Connection connection = null;
        int startIndex = (page-1)*recSize;
        ArrayList<Topic> listTopic = new ArrayList<Topic>();
        BelongDAO belongDAO = new BelongDAO();
        try{
            connection = Connect.getConnection();
            // Câu truy vấn lấy dữ liệu topic
            String getAllTopic = "select idTopic , name, interfaceImage, isShow from topic LIMIT ? OFFSET ?";
            PreparedStatement preparedStatementGetTopic = connection.prepareStatement(getAllTopic);
            preparedStatementGetTopic.setInt(1,recSize);
            preparedStatementGetTopic.setInt(2,startIndex);
            ResultSet resultSetGetTopic = preparedStatementGetTopic.executeQuery();
            while (resultSetGetTopic.next()) {
                Topic topic = new Topic();
                topic.setIdTopic(resultSetGetTopic.getInt("idTopic"));
                topic.setName(resultSetGetTopic.getString("name"));
                topic.setImageInterface(URL.URL + resultSetGetTopic.getString("interfaceImage"));
                topic.setProduct(belongDAO.getCountOddForTopic(topic.getIdTopic()) + belongDAO.getCountAlbumForTopic(topic.getIdTopic()));
                topic.setShow(resultSetGetTopic.getBoolean("isShow"));
                listTopic.add(topic);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTopic;
    }
    public ArrayList<Topic> getAllTopicsForClient(){
        Connection connection = null;
        ArrayList<Topic> listTopic = new ArrayList<Topic>();
        BelongDAO belongDAO = new BelongDAO();
        try{
            connection = Connect.getConnection();
            // Câu truy vấn lấy dữ liệu topic
            String getAllTopicForClient = "select idTopic , name, interfaceImage, isShow from topic where  isShow= ?";
            PreparedStatement preparedStatementGetTopic = connection.prepareStatement(getAllTopicForClient);
            preparedStatementGetTopic.setString(1, "true");
            ResultSet resultSetGetTopic = preparedStatementGetTopic.executeQuery();
            while (resultSetGetTopic.next()) {
                Topic topic = new Topic();
                topic.setIdTopic(resultSetGetTopic.getInt("idTopic"));
                topic.setName(resultSetGetTopic.getString("name"));
                topic.setImageInterface(URL.URL + resultSetGetTopic.getString("interfaceImage"));
                topic.setProduct(belongDAO.getCountOddForTopic(topic.getIdTopic()) + belongDAO.getCountAlbumForTopic(topic.getIdTopic()));
                topic.setShow(resultSetGetTopic.getBoolean("isShow"));
                listTopic.add(topic);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTopic;
    }
    public ArrayList<String> getAllNamesTopic(){
        Connection connection = null;
        ArrayList<String> listNamesTopic = new ArrayList<>();
        try{
            connection = Connect.getConnection();
            // Câu truy vấn lấy dữ liệu topic
            String sql = "select name from topic";
            PreparedStatement preparedStatementGetTopic = connection.prepareStatement(sql);
            ResultSet resultSetGetTopic = preparedStatementGetTopic.executeQuery();
            while (resultSetGetTopic.next()) {

                listNamesTopic.add(resultSetGetTopic.getString("name"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listNamesTopic;
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
    public boolean checkNameTopicExistForUpdate(String idTopic,String nameTopic) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "select count(name) as total from topic where name = ? and idTopic <> ?";
            PreparedStatement preparedStatementCheckNameTopic = connection.prepareStatement(sql);
            preparedStatementCheckNameTopic.setString(1, nameTopic);
            preparedStatementCheckNameTopic.setString(2, idTopic);
            ResultSet res = preparedStatementCheckNameTopic.executeQuery();
            if (res.next()) {
                int count = res.getInt("total");
                return count > 0;
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
    public String checkTopicShow(String nameTopic){
        Connection connection = null;
        String res = "";
        try{
            connection = Connect.getConnection();
            String sql = "select isShow from topic where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameTopic);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getString("isShow");
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
    public String checkTopicShowById(String idTopic){
        Connection connection = null;
        String res = "";
        try{
            connection = Connect.getConnection();
            String sql = "select isShow from topic where idTopic = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idTopic);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getString("isShow");
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
    public boolean updateShowTopic(String idTopic, String status) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "Update  Topic set isShow = ? where  idTopic = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, idTopic);
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public Topic getTopicById(String id){
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql  = "select idTopic ,name , isShow, interfaceImage from topic where idTopic =?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Topic topic = new Topic();
                topic.setIdTopic(resultSet.getInt("idTopic"));
                topic.setName(resultSet.getString("name"));
                topic.setShow(resultSet.getBoolean("isShow"));
                topic.setImageInterface(URL.URL+ resultSet.getString("interfaceImage"));
                return  topic;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }

    return  null;
    }
    public String getInterfaceImage(String idTopic){
        Connection connection = null;
        String res = "";
        try{
            connection = Connect.getConnection();
            String sql = "select interfaceImage from topic where idTopic = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idTopic);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                res = resultSet.getString("interfaceImage");
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
    public  boolean updateTopic(String idTopic, String nameTopic, String interfaceImage){
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "Update Topic set name = ?, interfaceImage = ? where idTopic = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameTopic);
            preparedStatement.setString(2, interfaceImage);
            preparedStatement.setString(3, idTopic);
            int check = preparedStatement.executeUpdate();
            if(check > 0 ){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return false;
    }
    public int totalTopic(){
        Connection connection = null;
        try{
            connection = Connect.getConnection();
            String sql = "select count(idTopic) as total from Topic";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.closeConnection(connection);
        }
        return  0;
    }
}
