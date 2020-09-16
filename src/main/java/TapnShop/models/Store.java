package TapnShop.models;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tracom.base.BaseEntity;


@Entity
@Table(name = "stores")
public class Store extends BaseEntity {
    public Store() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String storeName;
    @Column
    private String storeLocation;
    @Column
    private String storeRating;
    @Column
    private String storeImage;


   @OneToMany(targetEntity = FoodItem.class, mappedBy = "store", fetch = FetchType.LAZY,cascade = CascadeType.ALL)

    private List<FoodItem> foodItems;

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }



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
