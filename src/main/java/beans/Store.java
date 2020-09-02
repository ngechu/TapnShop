package beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store {
    public Store() {
    }
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String storeName;
    @Column
    private String storeLocation;
    @Column
    private String storeRating;
    @Column
    private String storeImage;

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @OneToMany
    private List<FoodItem>foodItems;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getStoreRating() {
        return storeRating;
    }

    public void setStoreRating(String storeRating) {
        this.storeRating = storeRating;
    }
}
