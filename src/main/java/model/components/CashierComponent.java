package model.components;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    //==========================================================================
    public Cashier getCashier(String email, String password) throws Exception {

        if (email == null || email.length() < 1) {
            throw new IllegalArgumentException("email is null or empty");
        }

        if (password == null || password.length() < 1) {
            throw new IllegalArgumentException("password is null or empty");
        }

        HashMap parameters = new HashMap();
        parameters.put("email", email);
        parameters.put("password", password);
        Cashier c = null;
        ArrayList<Cashier> cashierList = null;

        try {

            cashierList = new DAO().query(parameters, "getCashierByEmailPassword", new Cashier());
            if (cashierList != null && cashierList.size() > 0) {
                c = cashierList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }
        
        return c;

    }

}
