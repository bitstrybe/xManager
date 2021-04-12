package com.xmanager.bl;

import com.xmanager.entity.Stockout;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author scarface
 */
public class StocksOutBL extends XManagerBL {

    public StocksOutBL() {
    }

    @Override
    public int insertData(Object object) {
        try {
            if (object instanceof Stockout) {
                Stockout s = (Stockout) object;
                em.getTransaction().begin();
                em.persist(s);
                em.getTransaction().commit();
                em.refresh(s);
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
            if (object instanceof Stockout) {
                Stockout s = (Stockout) object;
                em.getTransaction().begin();
                Stockout oldstock = em.find(Stockout.class, s.getStockoutId());
                oldstock = s;
                em.merge(oldstock);
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
            Stockout oldsale = em.find(Stockout.class, id);
            em.remove(oldsale);
            em.getTransaction().commit();
            return 1;

        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    public List<Stockout> getStockoutByDestination(String source) {
        Query q = em.createQuery("SELECT s FROM Stockout s WHERE s.source.destinationCode = :source", Stockout.class)
                .setParameter("source", source);
        em.clear();
        return q.getResultList();
    }

    public List<Stockout> getNewStocksOut() {
        Query q = em.createQuery("SELECT s FROM Stockout s ORDER BY s.stockoutId DESC")
                .setMaxResults(300);
        return q.getResultList();
    }

    public List<Stockout> getStockByDate(Date stockdate) {
        try {
            Query q = em.createQuery("SELECT s FROM Stockout s WHERE s.date = :stockdate")
                    .setParameter("stockdate", stockdate);
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }
    
    public List<Stockout> getStockByItem(String itemcode) {
        try {
            Query q = em.createQuery("SELECT s FROM Stockout s WHERE s.item.itemCode = :itemcode ORDER BY s.source.destinationCode")
                    .setParameter("itemcode", itemcode);
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }
    
    //returns total rows of items taken from a warehouse grouped by item code
    public List<Object[]> getTotalOfItemsTakenFromWarehouse(String warehouse, String itemCode, Date from) {
        try {
            return em.createQuery("SELECT s.source.destination, s.item.itemCode, s.item.itemName, SUM(s.quantity) FROM Stockout s WHERE s.source.destinationCode = :warehouse  AND s.item.itemCode = :itemcode AND s.date <= :from GROUP BY s.item.itemCode ORDER BY s.item.itemCode")
                    .setParameter("warehouse", warehouse)
                    .setParameter("itemcode", itemCode)
                    .setParameter("from", from)
                    .getResultList();
        } catch (Exception ex) {
            Logger.getLogger(StocksOutBL.class.getName()).log(Level.SEVERE, warehouse, ex);
            em.clear();
            return null;
        }
    }
    
    //returns total number of items taken from a warehouse 
    public List<Object[]> getItemsTakenFromWarehouse(String warehouse, String itemCode, Date from, Date to) {
        try {
            Query q = em.createQuery("SELECT s.source.destination, s.item.itemCode, s.item.itemName, s.date, s.quantity FROM Stockout s WHERE s.source.destination = :whouse  AND s.item.itemCode = :itemcode AND s.date BETWEEN :from AND :to")
                    .setParameter("whouse", warehouse)
                    .setParameter("itemcode", itemCode)
                    .setParameter("from", from)
                    .setParameter("to", to);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ReportBL.class.getName()).log(Level.SEVERE, warehouse, ex);
            em.clear();
            return null;
        }
    }
}
