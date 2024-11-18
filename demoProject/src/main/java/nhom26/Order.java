package nhom26;

import javax.lang.model.element.NestingKind;
import java.util.Date;

public class Order {
    int idOrder;
    String nameProduct;
    String receiver;
    String phoneNumber;
    int quantity;
    String status;
    String address;
    int idByer;
    String type;
    int idProduct;
    String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdByer() {
        return idByer;
    }

    public void setIdByer(int idByer) {
        this.idByer = idByer;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

   double totalPrice;
    Date purchareDate;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPurchareDate() {
        return purchareDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPurchareDate(Date purchareDate) {
        this.purchareDate = purchareDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", nameProduct='" + nameProduct + '\'' +
                ", receiver='" + receiver + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", idByer=" + idByer +
                ", type='" + type + '\'' +
                ", idProduct=" + idProduct +
                ", fileName='" + fileName + '\'' +
                ", totalPrice=" + totalPrice +
                ", purchareDate=" + purchareDate +
                '}';
    }
}
