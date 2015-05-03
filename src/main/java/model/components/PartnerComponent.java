package model.components;

import model.beans.Partner;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class PartnerComponent {

    public PartnerComponent() {
    }
    
    //==========================================================================
    public long createPartner(Partner partner) throws Exception{
    
        long id;
        
        try {
            
            id = new DAO().create(partner);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
        
    }
    
}
