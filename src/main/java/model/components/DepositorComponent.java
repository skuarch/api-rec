package model.components;

import model.beans.Depositor;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class DepositorComponent {

    //==========================================================================
    public DepositorComponent() {        
    }
    
    //==========================================================================
    public long createDepositor(Depositor depositor) throws Exception{
    
        if(depositor == null){
            throw new IllegalArgumentException("depositor is null ");
        }
        
        long id;
        
        try {
            
            id = new DAO().create(depositor);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
        
    }
    
}
