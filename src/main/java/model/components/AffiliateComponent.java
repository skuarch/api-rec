package model.components;

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

}