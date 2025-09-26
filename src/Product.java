public class Product {
    private String name;
    private String price;
    private String exp;
    private int stock;

    Product(String name, String price, String exp, int stock) {
        this.name = name;
        this.price = price;
        this.exp = exp;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return name + " | " + price + " | " + exp;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }
    public void setName(String name) { this.name = name; }
    public void setPrice(String price) { this.price = price; }
}