package favourite;

import DAO.ProductDAO;
import nhom26.Album;
import nhom26.OddImage;

import java.util.HashMap;
import java.util.Map;

public class Favourite {
    Object product;
    ProductDAO productDAO = new ProductDAO();
    Map<String, Object> data = new HashMap<>();
    public boolean add(String type , String idMap , int id){
       if(data.containsKey(idMap)){
           return false;
       }
        if("odd".equals(type)){
            OddImage oddImage = productDAO.getOddImageById(id);
            data.put(idMap,oddImage);
            return  true;
        } else if ("album".equals(type)) {
            Album album = productDAO.getAlbumById(id);

            data.put(idMap,album);
            return  true;
        }
        else {
            return  false;
        }

    }
    public  boolean exist(String idMap){
        return  data.containsKey(idMap);
    }
    public boolean remove(String id){
        if(!data.containsKey(id)){
            return  false;
        }
        data.remove(id);
        return  true;
    }
    public int total(){
        return data.size();
    }
    public Map<String , Object> getData(){
        return  data;
    }
}
