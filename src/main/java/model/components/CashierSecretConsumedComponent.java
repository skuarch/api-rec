package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import model.beans.CashierSecretConsumed;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class CashierSecretConsumedComponent {   
    
    //==========================================================================
    public CashierSecretConsumedComponent() {
    }
    
    //==========================================================================
    public long saveCashierSecretConsumed(CashierSecretConsumed csc) throws Exception{
        
        if(csc == null){
            throw new IllegalArgumentException("cashierSecretConsumed is null");
        }
        
        long id;
        
        try {
            
            id = new DAO().create(csc);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;        
    }

    //==========================================================================
    public ArrayList<CashierSecretConsumed> getCashierListConsumedSecrets(long cashierId) throws Exception {        
        
        if(cashierId < 1){
            throw new IllegalArgumentException("cashierId is null");
        }
        
        ArrayList<CashierSecretConsumed> cashierSecretConsumedList;
        HashMap parameters;
        
        try {            
            
            parameters = new HashMap();
            parameters.put("cashierId", String.valueOf(cashierId));
            cashierSecretConsumedList = new DAO().query(parameters, "getCashierListSecretConsumed", new CashierSecretConsumed());            
            
        } catch (Exception e) {
            throw e;
        }
        
        return cashierSecretConsumedList;
        
    }
    
}
