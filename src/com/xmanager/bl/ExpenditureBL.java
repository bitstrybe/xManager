
package com.xmanager.bl;

import com.xmanager.entity.Expenditures;
import com.xmanager.entity.GeneralLedger;
import java.util.List;

/**
 *
 * @author scarface
 */
public class ExpenditureBL extends XManagerBL{

    @Override
    public int insertData(Object object) {
        try{
            if(object instanceof Expenditures){
                Expenditures exp = (Expenditures) object;
                em.getTransaction().begin();
                em.persist(exp);
                em.getTransaction().commit();
                em.refresh(exp);
                return 1;
            }
            else if(object instanceof GeneralLedger){
                GeneralLedger c = (GeneralLedger) object;
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
    
    public List<Expenditures> getAllExpenditures(){
        return em.createNamedQuery("Expenditures.findAll").getResultList();
    }
}
