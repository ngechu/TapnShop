package controllers;

import TapnShop.models.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import ejb.StoreBean;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/stores"})

public class StoreController extends HttpServlet {
    @EJB
    private StoreBean storeBean;

    @Inject
    private Store store;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            BeanUtils.populate(store, req.getParameterMap());
            storeBean.save(store);

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();

        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<Store> storesList = storeBean.list();

            ObjectMapper json = new ObjectMapper();

            String stores = json.writerWithDefaultPrettyPrinter().writeValueAsString(storesList);
            resp.getWriter().println(stores);


        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String storeId=req.getParameter("store");
            storeBean.delete(Integer.parseInt(storeId));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

