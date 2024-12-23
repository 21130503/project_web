package nhom26;

public class OddImage {
    int idOddImage;
    String name;
    int price;
    int discount;
    String image;
    String belongTopic;
    String description;
    boolean isShow;
    String type = "odd";

    String watermark;

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public String getType() {
        return type;
    }

    public OddImage() {
    }

    public int getIdOddImage() {
        return idOddImage;
    }

    public void setIdOddImage(int idOddImage) {
        this.idOddImage = idOddImage;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBelongTopic() {
        return belongTopic;
    }

    public void setBelongTopic(String belongTopic) {
        this.belongTopic = belongTopic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    @Override
    public String toString() {
        return "OddImage{" +
                "idOddImage=" + idOddImage +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", image='" + image + '\'' +
                ", belongTopic='" + belongTopic + '\'' +
                ", description='" + description + '\'' +
                ", isShow=" + isShow +
                '}';
    }
}
