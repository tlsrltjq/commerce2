import java.util.ArrayList;
import java.util.List;

public class Product {
    String name;
    String price;
    String exp;
    int stock;


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

}
