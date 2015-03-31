package model.components;

import model.beans.Contact;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class ContactComponent {

    public ContactComponent() {
    }

    //==========================================================================
    public long createContact(Contact contact) throws Exception{    
        
        if(contact == null){
            throw new IllegalArgumentException("company is null");
        }
        
        long id;
        
        try {
         
            id = new DAO().create(contact);
            
        } catch (Exception e) {
            throw e;
        } 
        
        return id;
        
    }
    
    
    //==========================================================================
    public void updateContact(Contact contact) throws Exception{
    
        if(contact == null){
            throw new IllegalArgumentException("contact is null");
        }
        
        try {
         
            new DAO().update(contact);
            
        } catch (Exception e) {
            throw e;
        }         
        
    }
    
}
