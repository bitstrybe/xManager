
package com.xmanager.bl;

import com.xmanager.entity.Destination;
import java.util.List;

/**
 *
 * @author scarface
 */
public class WarehouseBL extends XManagerBL{

    @Override
    public int insertData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeData(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Destination> getWarehouseList(){
        return em.createNamedQuery("Destination.findAll", Destination.class).getResultList();
    }
}
