package DAO;

import Properties.URL;
import Services.Connect;
import nhom26.Album;
import nhom26.OddImage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    BelongDAO belongDAO = new BelongDAO();
    DescriptionDAO descriptionDAO = new DescriptionDAO();
    TopicDAO topicDAO = new TopicDAO();
    ImageDAO imageDAO = new ImageDAO();
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    public boolean insertOddImage(String nameTopic, String nameOddImage, String source, String description, int price, int discount, String isShow) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sqlInsert = "insert into oddImage(name, source, price, discount,isShow, createdAt) values (?,?,?,?,?,?)";
            PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlInsert);
            preparedStatementInsert.setString(1, nameOddImage);
            preparedStatementInsert.setString(2, source);
            preparedStatementInsert.setInt(3, price);
            preparedStatementInsert.setInt(4, discount);
            preparedStatementInsert.setString(5, isShow);
            preparedStatementInsert.setDate(6, sqlDate);
            int checkOddInsert = preparedStatementInsert.executeUpdate();
            if (checkOddInsert >= 0 &&
                    belongDAO.insertOddImageBelongTopic(topicDAO.getIdTopicByName(nameTopic), getIdOddImageByName(nameOddImage))
                    && descriptionDAO.insertDescriptionDAO(getIdOddImageByName(nameOddImage), description)) {
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
    }

    public boolean updateOddImage(int idTopic, String idOddImage, String nameOddImage, String description, int price, int discount) {
        Connection connection = null;
        System.out.println(" before insert " + price);
        try {
            connection = Connect.getConnection();
            String sql = "update oddImage set  name = ? , price = ? ,discount = ? where  idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameOddImage);
            preparedStatement.setInt(2, price);
            preparedStatement.setInt(3, discount);
//            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(4, idOddImage);
            int check = preparedStatement.executeUpdate();
            if (check > 0 && descriptionDAO.updateDescriptionOddImage(idOddImage, description) && belongDAO.updateOddImage(idTopic, Integer.parseInt(idOddImage))) {
                return true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }

    public boolean updateAlbum(int idTopic, String idAlbum, String nameAlbum, String description, int price, int discount, ArrayList<String> deleteImage, ArrayList<String> sources) {
        Connection connection = null;
        System.out.println(" before insert " + price);
        try {
            connection = Connect.getConnection();
            String sql = "update album set  name = ? , price = ? ,discount = ? where  idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameAlbum);
            preparedStatement.setInt(2, price);
            preparedStatement.setInt(3, discount);
//            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(4, idAlbum);
            int check = preparedStatement.executeUpdate();
            if (check > 0 && descriptionDAO.updateDescriptionAlbum(idAlbum, description) && imageDAO.addImage(sources, Integer.parseInt(idAlbum)) && imageDAO.updateImageForAlbum(Integer.parseInt(idAlbum), deleteImage) && belongDAO.updateAlbum(idTopic, Integer.parseInt(idAlbum))) {
                return true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;
    }

    public boolean checkOddNameExist(String name) {
        Connection connection = null;

        try {
            connection = Connect.getConnection();
            String sql = "select name from oddImage where name = ? ";
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

    public boolean checkOddNameExistForUpdate(String idOddImage, String name) {
        Connection connection = null;

        try {
            connection = Connect.getConnection();
            String sql = "select count(name) as total from oddImage where name = ? and idOddImage <> ?";
            PreparedStatement preparedStatementCheckEmail = connection.prepareStatement(sql);
            preparedStatementCheckEmail.setString(1, name);
            preparedStatementCheckEmail.setString(2, idOddImage);
            ResultSet check = preparedStatementCheckEmail.executeQuery();
            if (check.next()) {
                int count = check.getInt("total");
                return count > 0;
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
    public boolean insertAlbum(String nameTopic, String nameAlbum, String description, int price, int discount, String isShow, ArrayList<String> images) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
//            Tiến hành insert

            String sql = "Insert into album(name,price, discount,isShow,createdAt) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameAlbum);
            preparedStatement.setInt(2, price);
            preparedStatement.setInt(3, discount);
            preparedStatement.setString(4, isShow);
            preparedStatement.setDate(5, sqlDate);
            int check = preparedStatement.executeUpdate();
            if (check > 0 && belongDAO.insertAlbumBelongTopic(topicDAO.getIdTopicByName(nameTopic), getIdAlbumByName(nameAlbum))
                    && descriptionDAO.insertDescriptionAlbum(getIdAlbumByName(nameAlbum), description)
                    && imageDAO.insertImage(images, getIdAlbumByName(nameAlbum))
            ) {
                return true;
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
            String sql = "select name from album where name = ?";
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

    public int getIdAlbumByName(String name) {
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

    public ArrayList<OddImage> getAllOddImage(int page , int recSize) {
        Connection connection = null;
        ArrayList<OddImage> listOddImage = new ArrayList<OddImage>();
        int startIndex = (page-1)* recSize;
        try {
            connection = Connect.getConnection();
            String sql = "Select idOddImage, name, source, price, discount, isShow from oddImage LIMIT ?  OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,recSize);
            preparedStatement.setInt(2,startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OddImage oddImage = new OddImage();
                oddImage.setIdOddImage(resultSet.getInt("idOddImage"));
                oddImage.setName(resultSet.getString("name"));
                oddImage.setImage(URL.URL + resultSet.getString("source"));
                oddImage.setPrice(resultSet.getInt("price"));
                oddImage.setDiscount(resultSet.getInt("discount"));
                int idTopic = belongDAO.getIdTopicFromIdOdd(resultSet.getInt("idOddImage"));
                String nameTopic = topicDAO.getNameTopicById(idTopic);
                oddImage.setBelongTopic(nameTopic);
                oddImage.setShow(resultSet.getBoolean("isShow"));
                listOddImage.add(oddImage);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return listOddImage;
    }

    public ArrayList<OddImage> getAllOddImageForClient(int page, int recSize) {
        Connection connection = null;
        ArrayList<OddImage> listOddImage = new ArrayList<OddImage>();
        int startIndex = (page - 1) * recSize;
        try {
            connection = Connect.getConnection();
            String sql = "Select idOddImage, name, source, price, discount, isShow from oddImage where  isShow= ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "true");
            preparedStatement.setInt(2, recSize);
            preparedStatement.setInt(3, startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OddImage oddImage = new OddImage();
                oddImage.setIdOddImage(resultSet.getInt("idOddImage"));
                oddImage.setName(resultSet.getString("name"));
                oddImage.setImage(URL.URL + resultSet.getString("source"));
                oddImage.setPrice(resultSet.getInt("price"));
                oddImage.setDiscount(resultSet.getInt("discount"));
                int idTopic = belongDAO.getIdTopicFromIdOdd(resultSet.getInt("idOddImage"));
                String nameTopic = topicDAO.getNameTopicById(idTopic);
                oddImage.setBelongTopic(nameTopic);
                oddImage.setShow(resultSet.getBoolean("isShow"));
                listOddImage.add(oddImage);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return listOddImage;
    }


    public ArrayList<Album> getAllAlbum(int page, int recSize) {
        Connection connection = null;
        ArrayList<Album> listAlbum = new ArrayList<>();
        int startIndex = (page - 1) * recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idAlbum, name, price, discount ,isShow from album LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, recSize);
            preparedStatement.setInt(2,startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idAlbum");
                Album album = new Album();
                album.setIdAlbum(id);
                album.setName(resultSet.getString("name"));
                album.setPrice(resultSet.getInt("price"));
                album.setDiscount(resultSet.getInt("discount"));
                album.setShow(resultSet.getBoolean("isShow"));
                album.setListImage(imageDAO.getAllImageByIdAlbum(id));
                int idTopic = belongDAO.getIdTopicFromIdAlbum(id);
                String nameTopic = topicDAO.getNameTopicById(idTopic);
                album.setBelongTopic(nameTopic);
                listAlbum.add(album);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return listAlbum;
    }

    public ArrayList<Album> getAllAlbumForClient(int page, int recSize) {
        Connection connection = null;
        ArrayList<Album> listAlbum = new ArrayList<>();
        int startIndex = (page - 1) * recSize;
        try {
            connection = Connect.getConnection();
            String sql = "select idAlbum, name, price, discount ,isShow from album where isShow = ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "true");
            preparedStatement.setInt(2, recSize);
            preparedStatement.setInt(3, startIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idAlbum");
                Album album = new Album();
                album.setIdAlbum(id);
                album.setName(resultSet.getString("name"));
                album.setPrice(resultSet.getInt("price"));
                album.setDiscount(resultSet.getInt("discount"));
                album.setShow(resultSet.getBoolean("isShow"));
                album.setListImage(imageDAO.getAllImageByIdAlbum(id));
                int idTopic = belongDAO.getIdTopicFromIdAlbum(id);
                String nameTopic = topicDAO.getNameTopicById(idTopic);
                album.setBelongTopic(nameTopic);
                listAlbum.add(album);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return listAlbum;
    }

    public boolean deleteOddImage(int idOddImage) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "Delete from OddImage where idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            int check = preparedStatement.executeUpdate();
            if (check > 0 && belongDAO.deleteOddImage(idOddImage) && descriptionDAO.deleteDescriptionOddImage(idOddImage)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
    }

    public boolean deleteAlbum(int idAlbum) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "delete from album where idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
            int check = preparedStatement.executeUpdate();
            if (check > 0 && belongDAO.deleteAlbum(idAlbum) && imageDAO.deleteImageBelongAblum(idAlbum) && descriptionDAO.deleteDescriptionAlbum(idAlbum)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
    }

    //    get 1 sản phẩm
//    sd trong trang chi tiết
    public OddImage getOddImageById(int idOddImage) {
        Connection connection = null;
        OddImage oddImage = new OddImage();
        try {
            connection = Connect.getConnection();
            String sql = "select idOddImage, name , price, discount, source from OddImage where idOddImage = ? and isShow = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            preparedStatement.setString(2, "true");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                oddImage.setIdOddImage(resultSet.getInt("idOddImage"));
                oddImage.setName(resultSet.getString("name"));
                oddImage.setPrice(resultSet.getInt("price"));
                oddImage.setDiscount(resultSet.getInt("discount"));
                oddImage.setImage(URL.URL + resultSet.getString("source"));
                int idTopicFromIdOdd = belongDAO.getIdTopicFromIdOdd(idOddImage);
                String topicName = topicDAO.getNameTopicById(idTopicFromIdOdd);
                oddImage.setBelongTopic(topicName);
                String description = descriptionDAO.getDescriptionByOddImage(idOddImage);
                oddImage.setDescription(description);
                return oddImage;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return oddImage;
    }

    public OddImage getOddImageByIdForAdminUpdate(int idOddImage) {
        Connection connection = null;
        OddImage oddImage = new OddImage();
        try {
            connection = Connect.getConnection();
            String sql = "select idOddImage, name , price, discount, source from OddImage where idOddImage = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                oddImage.setIdOddImage(resultSet.getInt("idOddImage"));
                oddImage.setName(resultSet.getString("name"));
                oddImage.setPrice(resultSet.getInt("price"));
                oddImage.setDiscount(resultSet.getInt("discount"));
                oddImage.setImage(URL.URL + resultSet.getString("source"));
                int idTopicFromIdOdd = belongDAO.getIdTopicFromIdOdd(idOddImage);
                String topicName = topicDAO.getNameTopicById(idTopicFromIdOdd);
                oddImage.setBelongTopic(topicName);
                String description = descriptionDAO.getDescriptionByOddImage(idOddImage);
                oddImage.setDescription(description);
                return oddImage;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return oddImage;
    }

    //    cho trang detail
    public Album getAlbumById(int idAlbum) {
        Connection connection = null;
        Album album = new Album();
        try {
            connection = Connect.getConnection();
            String sql = "select idAlbum, name , price, discount from Album where idAlbum = ? and  isShow = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
            preparedStatement.setString(2, "true");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                album.setIdAlbum(resultSet.getInt("idAlbum"));
                album.setName(resultSet.getString("name"));
                album.setPrice(resultSet.getInt("price"));
                album.setDiscount(resultSet.getInt("discount"));
                album.setListImage(imageDAO.getAllImageByIdAlbum(idAlbum));
                String description = descriptionDAO.getDescriptionByAlbum(idAlbum);
                int idTopic = belongDAO.getIdTopicFromIdAlbum(idAlbum);
                String topicName = topicDAO.getNameTopicById(idTopic);
                album.setBelongTopic(topicName);
                album.setDescription(description);
                return album;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return album;
    }

    public Album getAlbumByIdForAdminUpdate(int idAlbum) {
        Connection connection = null;
        Album album = new Album();
        try {
            connection = Connect.getConnection();
            String sql = "select idAlbum, name , price, discount from Album where idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                album.setIdAlbum(resultSet.getInt("idAlbum"));
                album.setName(resultSet.getString("name"));
                album.setPrice(resultSet.getInt("price"));
                album.setDiscount(resultSet.getInt("discount"));
                album.setListImage(imageDAO.getAllImageByIdAlbum(idAlbum));
                String description = descriptionDAO.getDescriptionByAlbum(idAlbum);
                int idTopic = belongDAO.getIdTopicFromIdAlbum(idAlbum);
                String topicName = topicDAO.getNameTopicById(idTopic);
                album.setBelongTopic(topicName);
                album.setDescription(description);
                return album;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return album;
    }


    //    tìm kiesm sản phẩm
    public ArrayList<Album> searchAlbumWithParam(String name) {
        Connection connection = null;
        ArrayList<Album> listAlbum = new ArrayList<>();
        try {
            connection = Connect.getConnection();
            String sql = "select idAlbum , name, price, discount from album where name LIKE ? and  isShow = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "true");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                chỉ cần báy nhiêu dữ liệu thôi
                Album album = new Album();
                album.setIdAlbum(resultSet.getInt("idAlbum"));
                album.setName(resultSet.getString("name"));
                album.setPrice(resultSet.getInt("price"));
                album.setDiscount(resultSet.getInt("discount"));
                album.setListImage(imageDAO.getAllImageByIdAlbum(resultSet.getInt("idAlbum")));
                listAlbum.add(album);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return listAlbum;
    }

    public ArrayList<OddImage> searchOddImageWithParam(String name) {
        Connection connection = null;
        ArrayList<OddImage> listOddImage = new ArrayList<>();
        try {
            connection = Connect.getConnection();
            String sql = "select idOddImage, name, price, discount, source from oddImage where name LIKE ? AND isShow = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "true");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                chỉ cần báy nhiêu dữ liệu thôi
                OddImage oddImage = new OddImage();
                oddImage.setIdOddImage(resultSet.getInt("idOddImage"));
                oddImage.setName(resultSet.getString("name"));
                oddImage.setPrice(resultSet.getInt("price"));
                oddImage.setDiscount(resultSet.getInt("discount"));
                oddImage.setImage(URL.URL + resultSet.getString("source"));
                listOddImage.add(oddImage);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return listOddImage;
    }

    public boolean updateShowOddImage(int idOddImage, String status) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "update oddImage set isShow = ? where idOddImage= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, idOddImage);
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

    public boolean updateShowAlbum(int idAlbum, String status) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "update album set isShow = ? where idAlbum= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, idAlbum);
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

    public boolean checkOddImageExist(int idOddImage) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "select idOddImage from oddImage where  idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;

    }

    public boolean checkAlbumExist(int idAlbum) {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "select idAlbum from album where  idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return false;

    }

    //    get String show
    public String getShowOddImage(String idOddImage) {
        Connection connection = null;
        String res = "";
        try {
            connection = Connect.getConnection();
            String sql = "select isShow from oddImage where  idOddImage = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idOddImage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getString("isShow");
                return res;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return res;
    }

    public String getShowAlbum(String idAlbum) {
        Connection connection = null;
        String res = "";
        try {
            connection = Connect.getConnection();
            String sql = "select isShow from album where  idAlbum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getString("isShow");
                return res;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return res;
    }

    //    Lấy ra 10 sản phẩm mới nhất
    public ArrayList<OddImage> getTop8ddImageNew() {
        Connection connection = null;
        ArrayList<OddImage> list10OddImage = new ArrayList<>();
        try {
            connection = Connect.getConnection();
            String sql = "select idOddImage, name, price, discount , source from oddImage where isShow = ? order by createdAt desc LIMIT 8 ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "true");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OddImage oddImage = new OddImage();
                oddImage.setIdOddImage(resultSet.getInt("idOddImage"));
                oddImage.setName(resultSet.getString("name"));
                oddImage.setPrice(resultSet.getInt("price"));
                oddImage.setDiscount(resultSet.getInt("discount"));
                oddImage.setImage(URL.URL + resultSet.getString("source"));
                list10OddImage.add(oddImage);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return list10OddImage;
    }

    public ArrayList<Album> getTop8AlbumNew() {
        Connection connection = null;
        ArrayList<Album> list10Album = new ArrayList<>();
        try {
            connection = Connect.getConnection();
            String sql = "select idAlbum, name, price, discount  from album where isShow = ? order by createdAt desc LIMIT 8 ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "true");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Album album = new Album();
                album.setIdAlbum(resultSet.getInt("idAlbum"));
                album.setName(resultSet.getString("name"));
                album.setPrice(resultSet.getInt("price"));
                album.setDiscount(resultSet.getInt("discount"));
                album.setListImage(imageDAO.getAllImageByIdAlbum(resultSet.getInt("idAlbum")));
                list10Album.add(album);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return list10Album;
    }

    //    toatl product;
    public int totalOdd() {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "select count(idOddImage) as total from oddImage";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }

    public int totalAlbum() {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            String sql = "select count(idAlbum) as total from Album";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return 0;
    }


    
    //Lấy ra danh sách Album sau khi lọc theo giá tiền
    public List<Album> getFilteredAlbums(int page, int recSize, int minPrice, int maxPrice) {
        Connection connection = null;
        List<Album> listAlbum = new ArrayList<>();
        int start = (page - 1) * recSize;
        try {
            connection = Connect.getConnection();
            String sql = "SELECT * FROM album WHERE price BETWEEN ? AND ? LIMIT ?, ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, minPrice);
            preparedStatement.setInt(2, maxPrice);
            preparedStatement.setInt(3, start);
            preparedStatement.setInt(4, recSize);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Album album = new Album();
                album.setIdAlbum(resultSet.getInt("idAlbum"));
                album.setName(resultSet.getString("name"));
                album.setPrice(resultSet.getInt("price"));
                album.setDiscount(resultSet.getInt("discount"));
                album.setListImage(imageDAO.getAllImageByIdAlbum(resultSet.getInt("idAlbum")));
                listAlbum.add(album);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return listAlbum;
    }

    //Lấy ra danh sách OddImage sau khi lọc theo giá tiền
    public List<OddImage> getFilteredOddImages(int page, int recSize, int minPrice, int maxPrice) {
        Connection connection = null;
        List<OddImage> listOddImage = new ArrayList<>();
        int start = (page - 1) * recSize;
        try {
            connection = Connect.getConnection();
            String sql = "SELECT * FROM oddImage WHERE price BETWEEN ? AND ? LIMIT ?, ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, minPrice);
            preparedStatement.setInt(2, maxPrice);
            preparedStatement.setInt(3, start);
            preparedStatement.setInt(4, recSize);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OddImage oddImage = new OddImage();
                oddImage.setIdOddImage(resultSet.getInt("idOddImage"));
                oddImage.setName(resultSet.getString("name"));
                oddImage.setPrice(resultSet.getInt("price"));
                oddImage.setDiscount(resultSet.getInt("discount"));
                oddImage.setImage(URL.URL + resultSet.getString("source"));
                listOddImage.add(oddImage);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.closeConnection(connection);
        }
        return listOddImage;
    }

    //Tính tổng số item để phân trang
    public int totalFilteredItems(int minPrice, int maxPrice) {
        String sql = "SELECT (SELECT COUNT(*) FROM Album WHERE price BETWEEN ? AND ?) + " +
                "(SELECT COUNT(*) FROM OddImage WHERE price BETWEEN ? AND ?) AS total";

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, minPrice);
            preparedStatement.setInt(2, maxPrice);
            preparedStatement.setInt(3, minPrice);
            preparedStatement.setInt(4, maxPrice);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}