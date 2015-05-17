package model.components;

import model.beans.Transfer;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class TransferComponent {

    //==========================================================================
    public TransferComponent() {
    }
    
    //==========================================================================
    public long createTransfer(Transfer transfer) throws Exception{
    
        if(transfer == null){
            throw new IllegalArgumentException("transfer is null ");
        }
        
        long id;
        
        try {
            
            id = new DAO().create(transfer);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
        
    }
    
}
