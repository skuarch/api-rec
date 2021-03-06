package model.components;

import java.util.ArrayList;
import java.util.List;
import model.beans.Cashier;
import model.beans.Establishment;
import model.database.DAO;
import model.logic.SoftDeletedCashier;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class EstablishmentComponent {

    public EstablishmentComponent() {
    }

    //==========================================================================
    public long saveEstablishment(Establishment establishment) throws Exception {

        if (establishment == null) {
            throw new IllegalArgumentException("establishment is null");
        }

        long id;

        try {

            id = new DAO().create(establishment);

        } catch (Exception e) {
            throw e;
        }

        return id;

    }

    //==========================================================================
    public void updateEstablishment(Establishment establishment) throws Exception {

        if (establishment == null) {
            throw new IllegalArgumentException("establishment is null");
        }

        try {

            new DAO().update(establishment);

        } catch (Exception e) {
            throw e;
        }

    }
    
    //==========================================================================
    public ArrayList<Establishment> getEstablishmentByFreelancer(long id){
    
        if(id < 1){
            throw new IllegalArgumentException("id is less than 0");
        }
        
        try {
            
        } catch (Exception e) {
            throw e;
        }        
        
        return null;
    
    }

    //==========================================================================
    public Establishment getEstablishment(long id) throws Exception {
        
        if(id < 1){
            throw new IllegalArgumentException("id is less than 0");
        }
        
        Establishment establishment = null;
        List<Cashier> cashiers;
        
        try {
            
            establishment = new DAO().get(id, new Establishment());
            cashiers = establishment.getCashier();
            cashiers.removeIf(new SoftDeletedCashier());
            establishment.setCashier(cashiers);
            
        } catch (Exception e) {
            throw e;
        }
        
        return establishment;
        
    }    
    
}
