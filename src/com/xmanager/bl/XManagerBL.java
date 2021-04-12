package com.xmanager.bl;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author scarface
 */
public abstract class XManagerBL {

    Map p = new HashMap();
    EntityManager em = null;

    public XManagerBL() {
        //p.put("javax.persistence.jdbc.password", "xManagerP@$$w0rd");
        p.put("javax.persistence.jdbc.password", "1234");
        p.put("javax.persistence.jdbc.user", "root");
        //System.out.print(p);
        em = Persistence.createEntityManagerFactory("xManagerPU", p).createEntityManager();
    }

    abstract public int insertData(Object object);

    abstract public int updateData(Object object);

    abstract public int removeData(Integer id);

}
