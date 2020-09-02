package beans;

import javax.persistence.*;

@Entity
@Table(name = "food_items")
public class FoodItem{

    public FoodItem() {
    }
@Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String foodName;
    @Column
    private String foodType;
    @Column
    private String foodImage;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Column
    private int price;
    @ManyToOne
    private Store store;

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


}
