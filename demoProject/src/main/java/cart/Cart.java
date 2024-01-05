package cart;

import DAO.ProductDAO;
import nhom26.Album;
import nhom26.OddImage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cart {
    Map<Integer, CartProduct> data = new HashMap<>();

    public Map<Integer, CartProduct> getData() {
        return this.data;
    }

    public Cart() {
    }

    //add sản phẩm vô giỏ
    public boolean addProduct(Object product, int quantity) {
        int productId;
        CartProduct cartProduct;

        //Phân biệt product là oddimage hay album
        if (product instanceof OddImage) {
            OddImage oddImage = (OddImage) product;
            productId = oddImage.getIdOddImage();
        } else if (product instanceof Album) {
            Album album = (Album) product;
            productId = album.getIdAlbum();
        } else {
            return false;
        }

        //nếu sản phẩm đã có thì tăng số lượng lên
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

    //Xóa sản phẩm khỏi giỏ có phân biệt odd hoặc album
    public boolean remove(int productId, String type) {
        if ("odd".equals(type) && data.containsKey(productId)) {
            data.remove(productId);
            return true;
        } else if ("album".equals(type) && data.containsKey(productId)) {
            data.remove(productId);
            return true;
        }
        return false;
    }


    //Tính tổng giá trị của cả giỏ hàng
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

    //Lấy ra tổng số sản phẩm hiện có trong giỏ hàng
    public int getTotal() {
        return data.size();
    }

}
