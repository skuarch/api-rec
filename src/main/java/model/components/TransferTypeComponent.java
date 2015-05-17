package model.components;

import model.beans.TransferType;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class TransferTypeComponent {

    public TransferTypeComponent() {
    }
    
    //==========================================================================
    public TransferType getTransferType(int transferTypeNumber) throws Exception{
    
        TransferType transferType = null;
        
        try {
            
            transferType = new DAO().get(transferTypeNumber, new TransferType());
            
        } catch (Exception e) {
            throw e;
        }
        
        return transferType;
        
    }
    
}
