
package com.xmanager.bl;

import com.xmanager.entity.Bank;
import com.xmanager.entity.BankEntry;
import java.util.List;

/**
 *
 * @author scarface
 */
public class BankBL extends XManagerBL{

    public BankBL() {
    }

    
    @Override
    public int insertData(Object object) {
        try{
            if(object instanceof Bank){
                Bank acc = (Bank) object;
                em.getTransaction().begin();
                em.persist(acc);
                em.getTransaction().commit();
                em.refresh(acc);
                return 1;
            }
            else if(object instanceof BankEntry){
                BankEntry c = (BankEntry) object;
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
                em.refresh(c);
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
    
    public List<Bank> getBankData(){
        return em.createNamedQuery("Bank.findAll").getResultList();
    }
}
