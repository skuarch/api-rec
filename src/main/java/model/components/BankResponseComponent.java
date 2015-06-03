package model.components;

import model.beans.BankResponse;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class BankResponseComponent {

    //==========================================================================
    public BankResponseComponent() {
    }

    //==========================================================================
    public long saveBankResponse(BankResponse bankResponse) throws Exception {

        if(bankResponse == null){
            throw new IllegalArgumentException("bankResponse is null");
        }
        
        long id;

        try {

            id = new DAO().create(bankResponse);

        } catch (Exception e) {
            throw e;
        }

        return id;

    }

    //==========================================================================
    public void updateBankResponse(BankResponse bankResponse) throws Exception {

        if(bankResponse == null){
            throw new IllegalArgumentException("bankResponse is null");
        }
        
        try {
            new DAO().update(bankResponse);
        } catch (Exception e) {
            throw e;
        }

    }

}
