package ejb;
import TapnShop.models.FoodItem;
import TapnShop.models.Store;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Stateless
@Remote
public class FoodItemBean {
    @PersistenceContext
    private EntityManager em;


    FoodItem foodItem = new FoodItem();

    public void save(int storeId, HttpServletRequest req) throws Exception {

        try {
            Store mystore = this.em.getReference(Store.class, storeId);
            this.foodItem.setPrice(Integer.parseInt(req.getParameter("price")));
            this.foodItem.setFoodName(req.getParameter("foodName"));
            this.foodItem.setFoodImage(req.getParameter("foodImage"));
            this.foodItem.setFoodType(req.getParameter("foodType"));
            this.foodItem.setStore(mystore);

            this.em.merge(this.foodItem);
        }catch (EntityNotFoundException ex){
            throw  new Exception("Store not found");
        }
    }


    public FoodItem load(int storeId) {
        if (storeId == 0)
            return new FoodItem();
        FoodItem foods = em.find(FoodItem.class, storeId);
        if (foods == null)
            return new FoodItem();
        return foods;
    }


    @SuppressWarnings({"unchecked"})
    public List<FoodItem> list(int storeId) {
        List<FoodItem> foodItems = em.createQuery("select FI from FoodItem FI join fetch FI.store where FI.store.id = :id").setParameter("id" ,storeId).getResultList();
        if (foodItems.isEmpty())
            System.out.println("No FoodItems found");
        return foodItems;
    }


    public void delete(int foodId) throws Exception {
        if (foodId == 0)
            throw new Exception("Invalid Food id");
        em.remove(em.find(FoodItem.class, foodId));
    }

}
