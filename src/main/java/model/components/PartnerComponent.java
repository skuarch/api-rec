package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import model.beans.Partner;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class PartnerComponent {

    //==========================================================================
    public PartnerComponent() {
    }
    
    //==========================================================================
    public long createPartner(Partner partner) throws Exception{
    
        long id;
        
        try {
            
            id = new DAO().create(partner);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
        
    }
    
    //==========================================================================
    public Partner getPartner(long id) throws Exception{
        
        Partner p;
        
        try {
            
            p = new DAO().get(id, new Partner());
            
        } catch (Exception e) {
            throw e;
        }
        
        return p;
        
    }
    
    //==========================================================================
    public Partner getPartner(String email, String password) throws Exception {

        if (email == null || email.length() < 1) {
            throw new IllegalArgumentException("email is null or empty");
        }

        if (password == null || password.length() < 1) {
            throw new IllegalArgumentException("password is null or empty");
        }

        HashMap parameters = new HashMap();
        parameters.put("email", email);
        parameters.put("password", password);
        Partner p = null;
        ArrayList<Partner> partnerList = null;

        try {

            partnerList = new DAO().query(parameters, "getPartner", new Partner());
            if (partnerList != null && partnerList.size() > 0) {
                p = partnerList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }
        
        return p;

    }
    
    //==========================================================================
    public void updatePartner(Partner partner) throws Exception{
        
        if(partner == null){
            throw new IllegalArgumentException("partner is null");
        }
        
        Partner p;
        
        try {
            
            new DAO().update(partner);
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
}
