public class Item {

    private int price;
    private int specialPrice;
    private int specialQuantity;

    public Item (int price, int specialPrice, int specialQuantity) {
        this.price = price;
        this.specialPrice = specialPrice;
        this.specialQuantity = specialQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    public int getSpecialQuantity() {
        return specialQuantity;
    }

    public void setSpecialQuantity(int specialQuantity) {
        this.specialQuantity = specialQuantity;
    }
}
