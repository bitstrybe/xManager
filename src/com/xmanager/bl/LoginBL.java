
package com.xmanager.bl;

import com.xmanager.entity.Users;
import javax.persistence.Query;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author scarface
 */
public class LoginBL extends XManagerBL{

    public LoginBL() {
    }

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
    
    public Users validateUser(Users user){
        try{
            Query q = em.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password", Users.class)
                    .setParameter("username", user.getUsername())
                    .setParameter("password", DigestUtils.md5Hex(user.getPassword()));
            em.clear();
         return (Users) q.getSingleResult();
        }
        catch(Exception ex){
            em.clear();
            return new Users();
        }
    }
}
