package model.components;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.beans.Affiliate;
import model.beans.AffiliateBasic;
import model.database.DAO;
import org.hibernate.HibernateException;

/**
 * logic for Affiliate.
 *
 * @author skuarch
 */
//@Component
public class AffiliateComponent {

    //==========================================================================
    /**
     * save a new Affiliate into database.
     *
     * @param affiliate Affiliate
     * @return long id
     * @throws Exception if the argument incorrect
     */
    public long saveAffiliate(Affiliate affiliate) throws Exception {

        if (affiliate == null) {
            throw new IllegalArgumentException("affiliate is null");
        }

        if (affiliate.getAddress() == null) {
            throw new IllegalArgumentException("address is null");
        }

        if (affiliate.getPerson() == null) {
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
     *
     * @param id long
     * @return Affiliate
     * @throws Exception if argument is incorrect
     */
    public Affiliate getAffiliate(long id) throws Exception {

        if (id < 1) {
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

        if (affiliate == null) {
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
    public ArrayList<Affiliate> getAffiliatesByCreator(long creatorId) throws Exception {

        if (creatorId < 1) {
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
    public List<Affiliate> getAffiliatesList() throws Exception {

        List<Affiliate> affiliates = null;

        try {

            affiliates = new DAO().query("getAffiliatesList", new Affiliate());

        } catch (Exception e) {
            throw e;
        }

        return affiliates;

    }

    //==========================================================================
    public List<AffiliateBasic> getAffiliateBasicList() throws Exception {

        StringBuilder sql = new StringBuilder();
        List<HashMap> list;
        List<AffiliateBasic> affiliateBasics = new ArrayList<>();        

        try {

            sql.append("SELECT * FROM (");
                sql.append("(SELECT (affiliate_id)id, (affiliate_url_logo)logo, (affiliate_brand) brand, (affiliate_description)description, ('1')type FROM affiliate where affiliate_active = 1)");
                sql.append(" UNION ");
                sql.append("(SELECT (company_id)id, (company_url_logo)logo, (company_brand) brand, (company_description)description, ('2')type FROM company where company_active = 1)");
            sql.append(") affiliates ORDER BY brand asc");
            
            list = new DAO().sqlQuery(sql.toString());

            if (list.size() > 0) {

                list.stream().map((hm) -> {
                    AffiliateBasic affiliateBasic = new AffiliateBasic();
                    affiliateBasic.setId((BigInteger) hm.get("id"));
                    affiliateBasic.setBrand((String) hm.get("brand"));
                    affiliateBasic.setDescription((String) hm.get("description"));
                    affiliateBasic.setType((String) hm.get("type"));
                    affiliateBasic.setLogo((String) hm.get("logo"));
                    return affiliateBasic;
                }).forEach((affiliateBasic) -> {
                    affiliateBasics.add(affiliateBasic);
                });

            }

        } catch (Exception e) {
            throw e;
        }

        return affiliateBasics;

    }

}
