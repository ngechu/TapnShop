package controllers;
import TapnShop.models.FoodItem;
import TapnShop.models.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import ejb.FoodItemBean;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/BuyNow"})

public class FoodItemController extends HttpServlet {
    @PersistenceContext
    EntityManager em;

    @EJB
    private FoodItemBean foodItemBean;
    @Inject
    private FoodItem foodItem;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String store = req.getParameter("store");

        try{
            List<FoodItem>foodItems=foodItemBean.list(Integer.parseInt(store));

            final ObjectMapper json = new ObjectMapper();

            resp.getWriter().println(json.writeValueAsString(foodItems));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    String storeId=req.getParameter("id");
        System.out.println(storeId);
        try {
            foodItemBean.save(Integer.parseInt(storeId),req);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
