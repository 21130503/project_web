package cart;

import nhom26.Album;
import nhom26.OddImage;

public class CartProduct {
     int quantity=1;
     Object object;

    public CartProduct(int quantity, Object object) {
        this.quantity = quantity;
        this.object = object;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    public  int increase(){
        return quantity++;
    }
    public int reduce(){
       return  quantity > 1? quantity--:quantity;
    }
    public int price(){
        int price = 0;
        if(object instanceof  Album){
            price = ((Album) object).getPrice() -((Album) object).getDiscount();
        }
        if (object instanceof  OddImage){
            price = ((OddImage) object).getPrice() - ((OddImage) object).getDiscount();
        }
        return  price;
    }
}
