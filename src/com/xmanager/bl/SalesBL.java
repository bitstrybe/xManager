package com.xmanager.bl;

import com.xmanager.entity.Sales;
import com.xmanager.entity.Shops;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author scarface
 */
public class SalesBL extends XManagerBL {

    public SalesBL() {
    }

    @Override
    public int insertData(Object object) {
        try {
            if (object instanceof Sales) {
                Sales sale = (Sales) object;
                sale.setShop(new Shops(em.createNamedQuery("Shops.findByShopName", Shops.class)
                        .setParameter("shopName", sale.getShop().getShopName())
                        .getSingleResult().getShopsid()));
                em.getTransaction().begin();
                em.persist(sale);
                em.getTransaction().commit();
                em.refresh(sale);
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    @Override
    public int updateData(Object object) {
        try {
            if (object instanceof Sales) {
                Sales sale = (Sales) object;
                sale.setShop(new Shops(em.createNamedQuery("Shops.findByShopName", Shops.class)
                        .setParameter("shopName", sale.getShop().getShopName())
                        .getSingleResult().getShopsid()));
                em.getTransaction().begin();
                Sales oldsale = em.find(Sales.class, sale.getSalesId());
                oldsale = sale;
                em.merge(oldsale);
                em.getTransaction().commit();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    @Override
    public int removeData(Integer id) {
        try {
            em.getTransaction().begin();
            Sales oldsale = em.find(Sales.class, id);
            em.remove(oldsale);
            em.getTransaction().commit();
            return 1;

        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    public List<Sales> getNewSalesEntry() {
        Query q = em.createQuery("SELECT s FROM Sales s ORDER BY s.salesId DESC")
                .setMaxResults(300);
        em.clear();
        return q.getResultList();
    }

    public List<Sales> getSalesByShopName(String shopName) {
        Query q = em.createQuery("SELECT s FROM Sales s WHERE s.shop.shopName = :shopName")
                .setParameter("shopName", shopName);
        em.clear();
        return q.getResultList();
    }

    public List<Sales> getSalesByDate(Date salesdate) {
        try {
            Query q = em.createQuery("SELECT s FROM Sales s WHERE s.date = :salesdate")
                    .setParameter("salesdate", salesdate);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }
    
    public List<Sales> getSalesByDate(String shop, Date salesdate) {
        try {
            Query q = em.createQuery("SELECT s FROM Sales s WHERE s.date = :salesdate AND s.shop.shopName = :shop")
                    .setParameter("shop", shop)
                    .setParameter("salesdate", salesdate);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }
    
    public List<Sales> getSalesByDate(String shop, Date sd, Date ed) {
        try {
            Query q = em.createQuery("SELECT s FROM Sales s WHERE s.date BETWEEN :sd AND :ed AND s.shop.shopName = :shop")
                    .setParameter("shop", shop)
                    .setParameter("sd", sd)
                    .setParameter("ed", ed);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }
    
    public List<Sales> getSalesByDate(String shop, String item, Date sd, Date ed) {
        try {
            Query q = em.createQuery("SELECT s FROM Sales s WHERE s.date BETWEEN :sd AND :ed AND s.shop.shopName = :shop AND s.item.itemCode LIKE :item")
                    .setParameter("shop", shop)
                    .setParameter("item", item)
                    .setParameter("sd", sd)
                    .setParameter("ed", ed);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    } 
    
    public List<Sales> getSalesByDate(Date salesdate, Date sd) {
        try {
            Query q = em.createQuery("SELECT s FROM Sales s WHERE s.date BETWEEN :salesdate AND :sd")
                    .setParameter("salesdate", salesdate)
            .setParameter("sd", sd);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }
}
