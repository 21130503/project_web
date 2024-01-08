package cart;

import DAO.ProductDAO;
import nhom26.Album;
import nhom26.OddImage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cart {
    Map<String, CartProduct> data = new HashMap<>();

    public Map<String, CartProduct> getData() {
        return this.data;
    }

    ProductDAO productDAO = new ProductDAO();

    public Cart() {
    }

    public boolean add(String type, String idMap, int id) {
        System.out.println("idMap: " + idMap);
        if (data.containsKey(idMap)) {
            CartProduct cartProduct = data.get(idMap);
            int quantity = cartProduct.getQuantity();
            cartProduct.setQuantity(++quantity);
            return  true;

        } else {
            if ("odd".equals(type)) {
                OddImage oddImage = productDAO.getOddImageById(id);
                data.put(idMap, new CartProduct(1, oddImage));
                return true;

            }
            if ("album".equals(type)) {
                Album album = productDAO.getAlbumById(id);
                data.put(idMap, new CartProduct(1, album));
                return true;
            }
        }
        return false;
    }
    public boolean removeCart(String idMap){
        if(!data.containsKey(idMap)){
            return  false;

        }
        CartProduct cartProduct = data.get(idMap);
        int quantity = cartProduct.getQuantity();
        System.out.println(quantity);
        if(quantity <=1){
            return  false;
        }
        else{
            cartProduct.setQuantity(quantity-1);
            return true;
        }
    }

    public int total() {
        return data.size();
    }

    public boolean remove(String idMap) {
        if (!data.containsKey(idMap)) {
            return false;
        }
        data.remove(idMap);
        return true;
    }

    public boolean removeAll() {
        data.clear();
        return true;
    }
    public int totalPrice(){
        int totalPrice = 0;
        for(CartProduct cartProduct :getData().values()){
            totalPrice += cartProduct.getQuantity()*cartProduct.price();
        }
        return  totalPrice;
    }
}
