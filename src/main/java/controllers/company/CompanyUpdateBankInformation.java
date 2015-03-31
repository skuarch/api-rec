package controllers.company;

import controllers.affiliate.AffiliateUpdateBankInformation;
import static controllers.application.BaseController.getLogger;
import model.beans.Company;
import model.beans.CompanyBankInformation;
import model.components.CompanyComponent;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class CompanyUpdateBankInformation {

    @Autowired
    private CompanyComponent companyComponent;    
    private static final Logger logger = getLogger(AffiliateUpdateBankInformation.class);
    
    //==========================================================================
    @RequestMapping(value = {"/v1/company/update/bank/information","v1/company/update/bank/information"})
    public @ResponseBody String companyUpdateBankInformation(@ModelAttribute CompanyBankInformation companyBankInformation){
        
        JSONObject jsono = null;
        Company company = null;
        
        try {
            
            company = companyComponent.getCompany(companyBankInformation.getId());
            company.setOwnerAccountBank(companyBankInformation.getOwnerAccountBank());
            company.setBank(companyBankInformation.getBank());
            company.setClabe(companyBankInformation.getClabe());
            company.setEmailNotifications(companyBankInformation.getEmailNotifications());
            
            companyComponent.updateCompany(company);            
            
            jsono = new JSONObject();
            jsono.put("updated", true);
            
        } catch (Exception e) {
            logger.error("AffiliateUpdateBasicInformation.updateBasicInformation", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("updated", false);
        }
        
        return jsono.toString();
    
    }
    
    
}
