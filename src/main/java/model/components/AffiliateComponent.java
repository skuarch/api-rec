package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import model.beans.Affiliate;
import model.database.DAO;
import org.hibernate.HibernateException;

/**
 * logic for Affiliate.
 * @author skuarch
 */
//@Component
public class AffiliateComponent {

    //==========================================================================
    /**
     * save a new Affiliate into database.
     * @param affiliate Affiliate
     * @return long id
     * @throws Exception if the argument incorrect
     */
    public long createAffiliate(Affiliate affiliate) throws Exception {

        if(affiliate == null){
            throw new IllegalArgumentException("affiliate is null");
        }
        
        if(affiliate.getAddress() == null){
            throw new IllegalArgumentException("address is null");
        }
        
        if(affiliate.getPerson() == null){
            throw new IllegalArgumentException("person is null");
        }
        
        long id;

        try {

            id = new DAO().create(affiliate);

        } catch (HibernateException e) {
            throw e;
        }

        return id;

    }
    
    
    //==========================================================================
    /**
     * get affiliate by id.
     * @param id long
     * @return Affiliate
     * @throws Exception if argument is incorrect 
     */
    public Affiliate getAffiliate(long id) throws Exception{
        
        if(id < 1){
            throw new IllegalArgumentException("id is less than 0");
        }
        
        Affiliate affiliate = null;
        
        try {
            
            affiliate = new DAO().get(id, new Affiliate());
            
        } catch (Exception e) {
            throw e;
        }
        
        return affiliate;
        
    }

    //==========================================================================
    public void updateAffiliate(Affiliate affiliate) throws Exception {
        
        if(affiliate == null){
            throw new IllegalArgumentException("affiliate is null");
        }
        
        try {
            
            new DAO().update(affiliate);
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    //==========================================================================
    public ArrayList<Affiliate> getAffiliatesByFreelancer(long freelancerId) throws Exception{
        
        if(freelancerId < 1){
            throw new IllegalArgumentException("freelancerId is less than 0");
        }
        
        ArrayList<Affiliate> affiliates = null;
        HashMap parameters;
        
        try {
            
            parameters = new HashMap<>();
            parameters.put("id", freelancerId + "");            
            affiliates = new DAO().query(parameters, "getAffiliateByFreelancer", new Affiliate());
            
        } catch (Exception e) {
            throw e;
        }
 
        return affiliates;
    
    }

}