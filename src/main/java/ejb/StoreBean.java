package ejb;

import TapnShop.models.Store;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote

public class StoreBean {
    @PersistenceContext
    private EntityManager em;


    public Store save(Store store) throws Exception {


        if (store == null || StringUtils.isBlank(store.getStoreName()))
            throw new Exception("Invalid store details");

        return em.merge(store);
    }


    public Store load(int storeId) {
        if (storeId == 0)
            return new Store();
        Store store = em.find(Store.class, storeId);
        if (store == null)
            return new Store();
        return store;
    }


    @SuppressWarnings({"unchecked"})
    public List<Store> list() {
        List<Store> stores = em.createQuery("SELECT s FROM Store s").getResultList();
        if (stores.isEmpty())
            System.out.println("No stores found");
        return stores;
    }


    public void delete(int storeId) throws Exception {
        if (storeId == 0)
            throw new Exception("Invalid store id");
        em.remove(em.find(Store.class, storeId));
    }


}
