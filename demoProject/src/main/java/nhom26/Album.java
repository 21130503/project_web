package nhom26;

import java.util.ArrayList;

public class Album {
    int idAlbum;
    String name;
    int price;
    int discount;
    ArrayList<String> listImage;
    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public ArrayList<String> getListImage() {
        return listImage;
    }

    public void setListImage(ArrayList<String> listImage) {
        this.listImage = listImage;
    }
}
