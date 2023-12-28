package cart;

import DAO.ProductDAO;
import nhom26.Album;
import nhom26.OddImage;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Integer, CartProduct> data = new HashMap<>();

    public Map<Integer, CartProduct> getData() {
        return this.data;
    }

    public boolean addProduct(Object product, int quantity) {
        int productId;
        CartProduct cartProduct;

        if (product instanceof OddImage) {
            OddImage oddImage = (OddImage) product;
            productId = oddImage.getIdOddImage();
        } else if (product instanceof Album) {
            Album album = (Album) product;
            productId = album.getIdAlbum();
        } else {
            return false; // Nếu sản phẩm không phải OddImage hoặc Album
        }

        if (data.containsKey(productId)) {
            cartProduct = data.get(productId);
            cartProduct.increQuantity(quantity);
        } else {
            cartProduct = new CartProduct(quantity, (product instanceof OddImage) ?
                    (OddImage) product : null, (product instanceof Album) ? (Album) product : null);
        }

        data.put(productId, cartProduct);
        return true;
    }


    public boolean remove(int id) {
        if (!data.containsKey(id)) return false;
        data.remove(id);
        return true;
    }

    //Tính tổng giá trị của giỏ hàng
    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartProduct cartProduct : data.values()) {
            if (cartProduct.getOddImage() != null) {
                totalPrice += cartProduct.getQuantity() * cartProduct.getOddImage().getPrice();
            } else if (cartProduct.getAlbum() != null) {
                totalPrice += cartProduct.getQuantity() * cartProduct.getAlbum().getPrice();
            }
        }
        return totalPrice;
    }

    public int gettotal() {
        return data.size();
    }
}
