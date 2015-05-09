package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.beans.Affiliate;
import model.beans.Company;
import model.database.DAO;
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
    public long createCompany(Company company) throws Exception{
    
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
        
        try {
         
            company = new DAO().get(id, new Company());
            
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
    
}
