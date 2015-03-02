package model.components;

import model.beans.AffiliateEstablishmet;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class AffiliateEstablishmentComponent {

    //==========================================================================
    public void createAffiliateEstablishment(AffiliateEstablishmet affiliateEstablishmet) throws Exception {
    
        if(affiliateEstablishmet == null){
            throw new IllegalArgumentException("affiliateEstablishmet is null");
        }
        
        try {
            
            new DAO().create(affiliateEstablishmet);
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
}
