package model.components;

import model.beans.SecretStatus;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class SecretStatusComponent {

    public SecretStatusComponent() {
    }

    //==========================================================================
    public SecretStatus getStatus(short secretStatusNumber) throws Exception{
        
        SecretStatus secretStatus = null;
        
        try {
            
            secretStatus = new DAO().get(secretStatusNumber, new SecretStatus());
            
        } catch (Exception e) {
            throw e;
        }
        
        return secretStatus;
        
    }
    
}
