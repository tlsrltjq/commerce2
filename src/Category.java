import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<Product> products = new ArrayList<>();
    private String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName(){ return categoryName; }
    public List<Product> getProducts() { return products; }
}


