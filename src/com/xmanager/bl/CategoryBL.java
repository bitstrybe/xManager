package com.xmanager.bl;

import com.xmanager.entity.Category;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author scarface
 */
public class CategoryBL extends XManagerBL {

    public CategoryBL() {
    }

    @Override
    public int insertData(Object object) {
        try {
            if (object instanceof Category) {
                Category c = (Category) object;
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
                em.refresh(c);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeData(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Category> getAllCategories() {
        Query q = em.createNamedQuery("Category.findAll", Category.class);
        em.clear();
        return q.getResultList();
    }

    public List<Category> getCategoryByCode(String code) {
        Query q = em.createNamedQuery("Category.findByCategoryCode", Category.class)
                .setParameter("categoryCode", code);
        em.clear();
        return q.getResultList();
    }

    public List<Category> getCategoryByFilterCode(String code) {
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryCode LIKE :wildcards", Category.class)
                .setParameter("wildcards", code + '%');
        em.clear();
        return q.getResultList();
    }
}
