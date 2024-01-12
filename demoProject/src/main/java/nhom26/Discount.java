package nhom26;

import java.util.Date;

public class Discount {
    //Chứa những thuộc tính y như dưới DB đê lấy lên trữ vòa
    private int code;
    private String description;
    private double discountValue;
    private Date expiryDate;

    public Discount() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expirydate) {
        this.expiryDate = expirydate;
    }

}
