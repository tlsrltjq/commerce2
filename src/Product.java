import java.util.ArrayList;
import java.util.List;

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
    public String getExp() { return exp; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }
}
