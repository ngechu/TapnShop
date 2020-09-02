package controllers;

import beans.Store;
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

@WebServlet(urlPatterns = {"/stores"})
public class StoreController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session session = DatabaseHelper.getSessionFactory().openSession();
        Transaction tx=session.getTransaction();
        tx.begin();

        Store healthyDrinks =new Store();
//        healthyDrinks.setStoreName(req.getParameter());
//        healthyDrinks.setStoreLocation(req.getParameter());
//        healthyDrinks.setStoreImage(req.getParameter());
        session.save(healthyDrinks);


        tx.commit();


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = DatabaseHelper.getSessionFactory().openSession();
        Transaction tx=session.getTransaction();
        tx.begin();
        List<Store>stores=session.createQuery("From Store s").getResultList();
        final ObjectMapper json = new ObjectMapper();
        resp.getWriter().println(json.writeValueAsString(stores));

        tx.commit();

    }
}
