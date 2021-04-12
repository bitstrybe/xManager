package com.xmanager.bl;

import com.xmanager.entity.Shops;
import java.util.List;

/**
 *
 * @author scarface
 */
public class ShopBL extends XManagerBL {

    public ShopBL() {
    }

    @Override
    public int insertData(Object object) {
        try {
            if (object instanceof Shops) {
                Shops shop = (Shops) object;
                em.getTransaction().begin();
                em.persist(shop);
                em.getTransaction().commit();
                em.refresh(shop);
                return 1;
            } 
            else {
                return 0;
            }
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int updateData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeData(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Shops> getAllShops() {
        return em.createNamedQuery("Shops.findAll", Shops.class).getResultList();
    }
    
    public List<Shops> getAllShops(int i) {
        return em.createQuery("SELECT s FROM Shops s WHERE s.type = :i")
                .setParameter("i", i)
                .getResultList();
    }
}
