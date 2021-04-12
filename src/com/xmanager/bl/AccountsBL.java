
package com.xmanager.bl;

import com.xmanager.entity.Accounts;
import com.xmanager.entity.Creditors;
import com.xmanager.entity.Debtors;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author scarface
 */
public class AccountsBL extends XManagerBL{

    @Override
    public int insertData(Object object) {
        try{
            if(object instanceof Accounts){
                Accounts acc = (Accounts) object;
                em.getTransaction().begin();
                em.persist(acc);
                em.getTransaction().commit();
                em.refresh(acc);
                em.clear();
                em.close();
                return 1;
            }
            else if(object instanceof Creditors){
                Creditors c = (Creditors) object;
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
                em.refresh(c);
                return 1;
            }
            else if(object instanceof Debtors){
                Debtors d = (Debtors) object;
                em.getTransaction().begin();
                em.persist(d);
                em.getTransaction().commit();
                em.refresh(d);
                return 1;
            }
            else { return 0; }
        }
        catch(Exception ex){
            System.out.println(ex);
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
    
    public List<Accounts> getAllAccounts(String type){
        Query q =  em.createNamedQuery("Accounts.findByType")
                .setParameter("type", type);
        em.clear();
        return q.getResultList();
    }
}
