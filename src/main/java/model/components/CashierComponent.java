package model.components;

import model.beans.Cashier;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class CashierComponent {

    public CashierComponent() {
    }

    //==========================================================================
    public long saveCashier(Cashier cashier) throws Exception {

        if (cashier == null) {
            throw new IllegalArgumentException("cashier is null");
        }

        long id;

        try {

            id = new DAO().create(cashier);

        } catch (Exception e) {
            throw e;
        }

        return id;

    }

    //==========================================================================
    public void updateCashier(Cashier cashier) throws Exception {

        if (cashier == null) {
            throw new IllegalArgumentException("cashier is null");
        }

        try {

            new DAO().update(cashier);

        } catch (Exception e) {
            throw e;
        }

    }
    
    //==========================================================================
    public Cashier getCashier(long id) throws Exception {

        if (id < 1) {
            throw new IllegalArgumentException("id is less than 1");
        }

        Cashier cashier = null;
        
        try {

            cashier = new DAO().get(id, new Cashier());

        } catch (Exception e) {
            throw e;
        }
        
        return cashier;

    }

}
