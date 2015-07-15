package model.components;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.beans.Company;
import model.beans.CompanyBasic;
import model.beans.Establishment;
import model.database.DAO;
import model.logic.SoftDeletedEstablishment;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class CompanyComponent {

    //==========================================================================
    public CompanyComponent() {
    }
    
    //==========================================================================
    public long saveCompany(Company company) throws Exception{
    
        if(company == null){
            throw new IllegalArgumentException("company is null");
        }
        
        
        long id;
        
        try {
         
            id = new DAO().create(company);
            
        } catch (Exception e) {
            throw e;
        } 
        
        return id;
        
    }
    
    //==========================================================================
    public Company getCompany(long id) throws Exception{
    
        if(id < 1){
            throw new IllegalArgumentException("id is less than 1");
        }
        
        
        Company company = null;
        List<Establishment> establishments;
        
        try {
         
            company = new DAO().get(id, new Company());
            establishments = company.getEstablishment();            
            establishments.removeIf(new SoftDeletedEstablishment());
            company.setEstablishment(establishments);
            
        } catch (Exception e) {
            throw e;
        } 
        
        return company;
        
    }
    
    //==========================================================================
    public void updateCompany(Company company) throws Exception{
    
        if(company == null){
            throw new IllegalArgumentException("company is null");
        }
        
        try {
         
            new DAO().update(company);
            
        } catch (Exception e) {
            throw e;
        }         
        
    }

    //==========================================================================
    public List<Company> getCompanies() throws Exception {
        
        List<Company> companies = null;
        
        try {
            
            companies = new DAO().getArrayList(new Company());
            
        } catch (Exception e) {
            throw e;
        }
        
        return companies;
        
    }
    
    //==========================================================================
    public ArrayList<Company> getCompaniesByCreator(long creatorId) throws Exception{
        
        if(creatorId < 1){
            throw new IllegalArgumentException("creatorId is less than 0");
        }
        
        ArrayList<Company> companies = null;
        HashMap parameters;
        
        try {
            
            parameters = new HashMap<>();
            parameters.put("id", creatorId + "");            
            companies = new DAO().query(parameters, "getCompaniesByCreator", new Company());
            
        } catch (Exception e) {
            throw e;
        }
 
        return companies;
    
    }
    
    
    //==========================================================================
    public List<Company> getCompanyList() throws Exception{
        
        List<Company> companies = null;        
        
        try {            
            
            companies = new DAO().query("getCompanyList", new Company());
            
        } catch (Exception e) {
            throw e;
        }
 
        return companies;
    
    }
    
    //==========================================================================
    public CompanyBasic getCompanyBasicByCashierId(long cashierId) throws Exception {

        StringBuilder sql = new StringBuilder();
        HashMap<String, String> parameters = new HashMap<>();
        List<HashMap> companiesList;
        CompanyBasic companyBasic = null;

        try {

            sql.append("SELECT").
                    append("  c.company_id as id").
                    append(", c.company_active as active").
                    append(", c.company_bank as bank").
                    append(", c.company_brand as brand").
                    append(", c.company_clabe as clabe").
                    append(", c.creator_person_id as creatorId").
                    append(", c.company_description as description").
                    append(", c.company_discount_percentage as discountPercentage").
                    append(", c.company_email_notifications as emailNotifications").
                    append(", c.company_is_soft_deleted as isSoftDeleted").
                    append(", c.company_logo_path_name as logoPathName").                    
                    append(", c.company_name as name").                    
                    append(", c.company_owner_account_bank as ownerAccountBank").
                    append(", c.company_password as password").                    
                    append(", c.company_registration_date as registrationDate").
                    append(", c.company_tax_company_name as taxCompanyName").
                    append(", c.company_taxId as taxId").
                    append(", c.company_url_logo as urlLogo").
                    append(", c.address_id as address").
                    append(", c.tax_contact_id as contact").
                    append(", c.person_id as person").
                    append(" FROM company c,").
                    append("  company_establishment ce,").
                    append("  establishment e,").
                    append("  cashier ca,").
                    append("  establishment_cashier ec,").
                    append("  person p").
                    append(" WHERE (ce.company_id = c.company_id)").
                    append("    AND (ce.establishment_id = e.establishment_id)").
                    append("    AND (ca.cashier_id = :cashierId)").
                    append("    AND (ec.cashier_id = :cashierId)").
                    append("    AND (ec.establishment_id = e.establishment_id)").
                    append("    AND (c.person_id = p.person_id)");

            parameters.put("cashierId", String.valueOf(cashierId));

            companiesList = new DAO().sqlQuery(sql.toString(), parameters);
            
            if(companiesList != null && companiesList.size() > 0){            
                HashMap hm = companiesList.get(0);
                    companyBasic = new CompanyBasic();
                    companyBasic.setId((BigInteger) hm.get("id"));
                    companyBasic.setBrand((String) hm.get("brand"));
                    companyBasic.setDescription((String) hm.get("description"));
                    companyBasic.setType((String) hm.get("type"));                    
                    companyBasic.setDiscountPercentage((int) hm.get("discountPercentage"));            
            }

        } catch (Exception e) {
            throw e;
        }

        return companyBasic;

    }
    
    //==========================================================================
    public Company getCompany(String email, String password) throws Exception {

        if (email == null || email.length() < 1) {
            throw new IllegalArgumentException("email is null or empty");
        }

        if (password == null || password.length() < 1) {
            throw new IllegalArgumentException("password is null or empty");
        }

        HashMap parameters = new HashMap();
        parameters.put("email", email);
        parameters.put("password", password);
        Company c = null;
        ArrayList<Company> companyList = null;

        try {

            companyList = new DAO().query(parameters, "getCompanyByEmailPassword", new Company());
            if (companyList != null && companyList.size() > 0) {
                c = companyList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return c;

    }
    
}
