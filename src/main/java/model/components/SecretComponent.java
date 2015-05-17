package model.components;

import model.beans.Secret;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class SecretComponent {

    //==========================================================================
    public SecretComponent() {
    }
    
    //==========================================================================
    public long saveKey(Secret secret) throws Exception{
        
        if(secret == null){
            throw new IllegalArgumentException("key is null");
        }
        
        long id;
        
        try {
    
            id = new DAO().create(secret);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
    
    }
    
    //==========================================================================
    public void updateSecret(Secret secret) throws Exception{
        
        try {
    
            new DAO().update(secret);
            
        } catch (Exception e) {
            throw e;
        }
    
    }
    
    //==========================================================================
    public Secret getSecret(long id) throws Exception{
        
        Secret key;
        
        try {
    
            key = new DAO().get(id, new Secret());
            
        } catch (Exception e) {
            throw e;
        }
        
        return key;
    
    }   
    
}
