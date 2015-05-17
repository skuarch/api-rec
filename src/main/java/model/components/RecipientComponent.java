package model.components;

import model.beans.Recipient;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class RecipientComponent {

    //==========================================================================
    public RecipientComponent() {
    }
    
    //==========================================================================
    public long createRecipient(Recipient recipient) throws Exception{
    
        if(recipient == null){
            throw new IllegalArgumentException("recipient is null ");
        }
        
        long id;
        
        try {
            
            id = new DAO().create(recipient);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
        
    }
    
}
