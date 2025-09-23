import java.util.ArrayList;
import java.util.List;

public class Category {
    List<Product> products = new ArrayList<>();
    String categoryName;
    Category() {}

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName(){
        return categoryName;
    }


}


