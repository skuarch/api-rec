package model.components;

import model.beans.TransactionType;
import model.database.DAO;

/**
 *
 * @author skuarch
 */
//@Component
public class TransactionTypeComponent {

    public TransactionTypeComponent() {
    }
 
    //==========================================================================
    public TransactionType getTransaction(short transactionTypeNumber) throws Exception{
        
        TransactionType transactionType = null;
        
        try {
            
            transactionType = new DAO().get(transactionTypeNumber, new TransactionType());
            
        } catch (Exception e) {
            throw e;
        }
        
        return transactionType;
        
    }
    
}