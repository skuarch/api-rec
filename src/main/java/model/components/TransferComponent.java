package model.components;

import java.util.ArrayList;
import model.beans.Partner;
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
    
    
    //==========================================================================
    public ArrayList<Transfer> getTransferList() throws Exception {

        ArrayList<Transfer> transfers = new ArrayList<>();

        try {

            transfers = new ArrayList<>(new DAO().query("getTransferList", new Transfer()));

        } catch (Exception e) {
            throw e;
        }

        return transfers;

    }
    
}
