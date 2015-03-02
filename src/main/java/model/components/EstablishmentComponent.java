package model.components;

import java.util.ArrayList;
import model.beans.Establishment;
import model.database.DAO;
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
    public long createEstablishment(Establishment establishment) throws Exception {

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

}
