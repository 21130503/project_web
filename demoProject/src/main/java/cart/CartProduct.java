package cart;

import nhom26.Album;
import nhom26.OddImage;

public class CartProduct {
    private int quantity;
    private OddImage oddImage;
    private Album album;

    public CartProduct(int quantity, OddImage oddImage, Album album) {
        this.quantity = quantity;
        this.oddImage = oddImage;
        this.album = album;
    }

    public void increQuantity(int quantity) {
//        this.quantity = quantity + 1;
        this.quantity += quantity;
    }

    public void decreQuantity(int quantity) {
//        this.quantity = quantity - 1;
        this.quantity -= quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OddImage getOddImage() {
        return oddImage;
    }

    public void setOddImage(OddImage oddImage) {
        this.oddImage = oddImage;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    //Tính tổng tiền của món hàng khi tăng số lượng
    public int getTotalPrice() {
        int totalPrice = 0;

        if (getOddImage() != null) {
            totalPrice += getQuantity() * getOddImage().getPrice();
        } else if (getAlbum() != null) {
            totalPrice += getQuantity() * getAlbum().getPrice();
        }

        return totalPrice;
    }
}
