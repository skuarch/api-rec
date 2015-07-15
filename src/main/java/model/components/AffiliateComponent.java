package model.components;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.beans.Affiliate;
import model.beans.AffiliateBasic;
import model.beans.Company;
import model.beans.Establishment;
import model.database.DAO;
import model.logic.SoftDeletedEstablishment;
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
        List<Establishment> establishments = null;

        try {

            affiliate = new DAO().get(id, new Affiliate());
            establishments = affiliate.getEstablishment();
            establishments.removeIf(new SoftDeletedEstablishment());
            affiliate.setEstablishment(establishments);
            
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

            affiliateList = new DAO().query(parameters, "getAffiliateByEmailPassword", new Affiliate());
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
                    
                    AffiliateBasic affiliateBasic = null;

                    try {
                        affiliateBasic = new AffiliateBasic();
                        affiliateBasic.setId((BigInteger) hm.get("id"));
                        affiliateBasic.setBrand((String) hm.get("brand"));
                        affiliateBasic.setDescription((String) hm.get("description"));
                        affiliateBasic.setType((String) hm.get("type"));
                        affiliateBasic.setLogo((String) hm.get("logo"));
                        affiliateBasic.setWebsite((String) hm.get("website"));
                        affiliateBasic.setFacebook((String) hm.get("facebook"));

                        if (affiliateBasic.getType().equals("1")) {

                            Affiliate affiliate = new AffiliateComponent().getAffiliate(affiliateBasic.getId().longValue());                            
                            affiliateBasic.setCategory(affiliate.getCategory());
                            
                        }else{
                        
                            Company company = new CompanyComponent().getCompany(affiliateBasic.getId().longValue());
                            affiliateBasic.setCategory(company.getCategory());
                        
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

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

    //==========================================================================
    public AffiliateBasic getAffiliateBasicByCashierId(long cashierId) throws Exception {

        StringBuilder sql = new StringBuilder();
        HashMap<String, String> parameters = new HashMap<>();
        List<HashMap> affiliatesList;
        AffiliateBasic affiliateBasic = null;

        try {

            sql.append("SELECT").
                    append("  a.affiliate_id as id").
                    append(", a.affiliate_active as active").
                    append(", a.affiliate_bank as bank").
                    append(", a.affiliate_brand as brand").
                    append(", a.affiliate_clabe as clabe").
                    append(", a.creator_person_id as creatorId").
                    append(", a.affiliate_description as description").
                    append(", a.affiliate_discount_percentage as discountPercentage").
                    append(", a.affiliate_email_notifications as emailNotifications").
                    append(", a.affiliate_is_soft_deleted as isSoftDeleted").
                    append(", a.affiliate_last_login as lastLogin").
                    append(", a.affiliate_logo_path_name as logoPathName").
                    append(", a.affiliate_owner_account_bank as ownerAccountBank").
                    append(", a.affiliate_password as password").
                    append(", a.affiliate_registration_date as registrationDate").
                    append(", a.affiliate_tax_company_name as taxCompanyName").
                    append(", a.affiliate_tax_id as taxId").
                    append(", a.affiliate_url_logo as urlLogo").
                    append(", a.address_id as address").
                    append(", a.contact_id as contact").
                    append(", a.person_id as person").
                    append(", ('1')type").
                    append(" FROM affiliate a,").
                    append("  affiliate_establishment ae,").
                    append("  establishment e,").
                    append("  cashier c,").
                    append("  establishment_cashier ec,").
                    append("  person p").
                    append(" WHERE (ae.affiliate_id = a.affiliate_id)").
                    append("    AND (ae.establishment_id = e.establishment_id)").
                    append("    AND (c.cashier_id = :cashierId)").
                    append("    AND (ec.cashier_id = :cashierId)").
                    append("    AND (ec.establishment_id = e.establishment_id)").
                    append("    AND (a.person_id = p.person_id)");

            parameters.put("cashierId", String.valueOf(cashierId));

            affiliatesList = new DAO().sqlQuery(sql.toString(), parameters);

            if (affiliatesList != null && affiliatesList.size() > 0) {
                HashMap hm = affiliatesList.get(0);
                affiliateBasic = new AffiliateBasic();
                affiliateBasic.setId((BigInteger) hm.get("id"));
                affiliateBasic.setBrand((String) hm.get("brand"));
                affiliateBasic.setDescription((String) hm.get("description"));
                affiliateBasic.setType((String) hm.get("type"));
                affiliateBasic.setDiscountPercentage((int) hm.get("discountPercentage"));
            }

        } catch (Exception e) {
            throw e;
        }

        return affiliateBasic;

    }

}
