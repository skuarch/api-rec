package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public Affiliate getAffiliate(String email, String password) throws Exception {

        if (email == null || email.length() < 1) {
            throw new IllegalArgumentException("email is null or empty");
        }

        if (password == null || password.length() < 1) {
            throw new IllegalArgumentException("password is null or empty");
        }

        HashMap parameters = new HashMap();
        parameters.put("email", email);
        parameters.put("password", password);
        Affiliate a = null;
        ArrayList<Affiliate> affiliateList = null;

        try {

            affiliateList = new DAO().query(parameters, "getAffiliate", new Affiliate());
            if (affiliateList != null && affiliateList.size() > 0) {
                a = affiliateList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }
        
        return a;

    }
    
    //==========================================================================
    public ArrayList<Affiliate> getAffiliatesByCreator(long creatorId) throws Exception{
        
        if(creatorId < 1){
            throw new IllegalArgumentException("creatorId is less than 0");
        }
        
        ArrayList<Affiliate> affiliates = null;
        HashMap parameters;
        
        try {
            
            parameters = new HashMap<>();
            parameters.put("id", creatorId + "");            
            affiliates = new DAO().query(parameters, "getAffiliateByCreator", new Affiliate());
            
        } catch (Exception e) {
            throw e;
        }
 
        return affiliates;
    
    }
    
    //==========================================================================
    public List<Affiliate> getAffiliatesList() throws Exception{
        
        List<Affiliate> affiliates = null;        
        
        try {            
            
            affiliates = new DAO().query("getAffiliatesList", new Affiliate());
            
        } catch (Exception e) {
            throw e;
        }
 
        return affiliates;
    
    }

}