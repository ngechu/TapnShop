package controllers;

import beans.FoodItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.DatabaseHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet("/HealthyDrinks")

public class FoodItemController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String store = req.getParameter("store");
        Session session= DatabaseHelper.getSessionFactory().openSession();
//        FoodItem foodItem = session.find(FoodItem.class, 1L);
//        List<FoodItem>foodItems=session.createQuery("FROM FoodItem FI FI.foods where store_id=:storeId")
//                .setParameter("storeId",Integer.parseInt(store))
//                .getResultList();

        List<FoodItem> foodItems = session.createQuery("select FI from FoodItem FI join fetch FI.store where FI.store.id = :id ", FoodItem.class)
                .setParameter("id", Integer.parseInt(store))
                .getResultList();



        final ObjectMapper json = new ObjectMapper();
        resp.getWriter().println(json.writeValueAsString(foodItems));
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        String action = request.getServletPath();
////        switch (action){
////            case "/HealthyDrinks":
////                getHealthyDrinks(request,response);
////                break;
////            case "/ChomaBase":
////                break;
////            case "/KukuBase":
////                break;
//            String store  = request.getParameter("store");
//
//        }
//    }

    private void getHealthyDrinks(HttpServletRequest request,HttpServletResponse response) throws IOException {

        Session session= DatabaseHelper.getSessionFactory().openSession();
        Transaction tx=session.getTransaction();
        tx.begin();
        List<FoodItem>foodItems=session.createQuery("From FoodItem foods where storeName=:HealthyDrinks").getResultList();
        final ObjectMapper json = new ObjectMapper();
        response.getWriter().println(json.writeValueAsString(foodItems));

        tx.commit();

    }

}
