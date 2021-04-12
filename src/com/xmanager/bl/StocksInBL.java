package com.xmanager.bl;

import com.xmanager.entity.Stockin;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author scarface
 */
public class StocksInBL extends XManagerBL {

    public StocksInBL() {
    }

    @Override
    public int insertData(Object object) {
        try {
            if (object instanceof Stockin) {
                Stockin s = (Stockin) object;
                em.getTransaction().begin();
                em.persist(s);
                em.getTransaction().commit();
                em.refresh(s);
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int updateData(Object object) {
        try {
            if (object instanceof Stockin) {
                Stockin s = (Stockin) object;
                em.getTransaction().begin();
                Stockin olds = em.find(Stockin.class, s.getStockinId());
                olds = s;
                em.merge(olds);
                em.getTransaction().commit();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int removeData(Integer id) {
        try {
            em.getTransaction().begin();
            Stockin oldsale = em.find(Stockin.class, id);
            em.remove(oldsale);
            em.getTransaction().commit();
            return 1;

        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    public List<Stockin> getItemsFromStockByDestination(String destination) {
        Query q = em.createQuery("SELECT s FROM Stockin s WHERE s.destination.destinationCode = :dest", Stockin.class)
                .setParameter("dest", destination);
        return q.getResultList();
    }

    public List<Object[]> getTotalStockInEntries(String shopName, String itemCode, Date from, Date to) {
        try {
            Query q = em.createQuery("SELECT  s.item.itemCode, SUM(s.quantity) FROM Stockin s WHERE s.destination.destinationCode = :shopname AND s.item.itemCode = :itemcode AND s.date BETWEEN :from AND :to GROUP BY s.item.itemCode")
                    .setParameter("shopname", shopName)
                    .setParameter("itemcode", itemCode)
                    .setParameter("from", from)
                    .setParameter("to", to);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }

    public List<Stockin> getRequistionByDate(Date from, Date to) {
        try {
            Query q = em.createQuery("SELECT s FROM Stockin s WHERE s.date BETWEEN :from AND :to AND s.details LIKE 'req%' ")
                    .setParameter("from", from)
                    .setParameter("to", to);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }

    public List<Stockin> getRequistionByDate(String shop, Date from, Date to) {
        try {
            Query q = em.createQuery("SELECT s FROM Stockin s WHERE s.date BETWEEN :from AND :to AND s.details LIKE 'req%' AND s.destination.destination = :shop")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("shop", shop);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }
    
    public List<Stockin> getRequistionByDate(String shop, String item, Date from, Date to) {
        try {
            Query q = em.createQuery("SELECT s FROM Stockin s WHERE s.date BETWEEN :from AND :to AND s.details LIKE 'req%' AND s.destination.destination = :shop AND s.item.itemCode LIKE :item")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("item", item)
                    .setParameter("shop", shop);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }

    public List<Stockin> getNewStocksIn() {
        Query q = em.createQuery("SELECT s FROM Stockin s ORDER BY s.stockinId DESC")
                .setMaxResults(300);
        em.clear();
        return q.getResultList();
    }

    public List<Stockin> getStockInByDate(Date stockindate) {
        try {
            Query q = em.createQuery("SELECT s FROM Stockin s WHERE s.date = :stockindate")
                    .setParameter("stockindate", stockindate);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }

    public List<Stockin> getStockInByItem(String itemcode) {
        try {
            Query q = em.createQuery("SELECT s FROM Stockin s WHERE s.item.itemCode = :itemcode ORDER BY s.destination.destinationCode")
                    .setParameter("itemcode", itemcode);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            em.clear();
            return null;
        }
    }
}
