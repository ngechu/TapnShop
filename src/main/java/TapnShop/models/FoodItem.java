package TapnShop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tracom.base.BaseEntity;
import org.hibernate.annotations.Formula;

import javax.persistence.*;


@NamedQueries({
        @NamedQuery(name= FoodItem.FOODITEM_FIND_BY_FK,query="select FI from FoodItem FI join fetch FI.store where FI.store.id = :id")
})

@Entity
@Table(name = "food_items")



public class FoodItem {


    @Transient
    public static final String FOODITEM_FIND_BY_FK="FoodItem.FindByFk";

@Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String foodName;
    @Column
    private String foodType;
    @Column
    private String foodImage;
    @Column
    private int price;

    @JsonBackReference
    @ManyToOne (fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Store store;

    @Formula("store_id")
    private int storeId;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }


}
