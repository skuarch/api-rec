package model.components;

import model.beans.Responsable;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class ResponsableComponent {

    public ResponsableComponent() {
    }

    //==========================================================================
    public long createResponsable(Responsable responsable) throws Exception{
    
        if(responsable == null){
            throw new IllegalArgumentException("responsable is null");
        }
        
        long id;
        
        try {
            
            id = new DAO().create(responsable);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
        
    }
    
}
