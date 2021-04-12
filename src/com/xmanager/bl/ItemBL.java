package com.xmanager.bl;

import com.xmanager.entity.Item;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author scarface
 */
public class ItemBL extends XManagerBL {

    public ItemBL() {
    }

    @Override
    public int insertData(Object object) {
        try {
            if (object instanceof Item) {
                Item item = (Item) object;
                em.getTransaction().begin();
                em.persist(item);
                em.getTransaction().commit();
                em.refresh(item);
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
            if (object instanceof Item) {
                Item item = (Item) object;
                em.getTransaction().begin();
                Item i = em.find(Item.class, item.getItemCode());
                i = item;
                em.merge(i);
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
            Item item = em.find(Item.class, id);
            em.remove(item);
            em.getTransaction().commit();
            return 1;

        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    public int removeData(String id) {
        try {
            em.getTransaction().begin();
            Item item = em.find(Item.class, id);
            em.remove(item);
            em.getTransaction().commit();
            return 1;

        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    public List<Item> getAllItems() {
        Query q = em.createQuery("SELECT i FROM Item i ORDER BY i.category.categoryCode");
                em.clear();
                return q.getResultList();
    }

    public List<Item> getItemByCategory(String category) {
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.category.categoryCode = :category")
                .setParameter("category", category);
        em.clear();
        return q.getResultList();
    }

    public List<Item> getItemByFilterItemCode(String itemCode) {
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemCode LIKE :itemCode", Item.class)
                .setParameter("itemCode", itemCode + '%');
        em.clear();
        return q.getResultList();
    }
}
