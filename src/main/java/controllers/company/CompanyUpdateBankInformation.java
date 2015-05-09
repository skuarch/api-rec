package controllers.company;

import controllers.affiliate.AffiliateUpdateBankInformation;
import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.beans.CompanyBankInformation;
import model.components.CompanyComponent;
import model.logic.Constants;
import model.util.TransactionUtil;
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
public class CompanyUpdateBankInformation extends BaseController {

    @Autowired
    private CompanyComponent companyComponent;    
    private static final Logger logger = getLogger(AffiliateUpdateBankInformation.class);
    
    //==========================================================================
    @RequestMapping(value = {"/v1/company/update/bank/information","v1/company/update/bank/information"})
    public @ResponseBody String companyUpdateBankInformation(@ModelAttribute CompanyBankInformation companyBankInformation, HttpServletResponse response){
        
        JSONObject jsono = null;
        Company company = null;
        
        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);
            
            company = companyComponent.getCompany(companyBankInformation.getId());
            company.setOwnerAccountBank(companyBankInformation.getOwnerAccountBank());
            company.setBank(companyBankInformation.getBank());
            company.setClabe(companyBankInformation.getClabe());
            company.setEmailNotifications(companyBankInformation.getEmailNotifications());
            
            companyComponent.updateCompany(company);            
            
            jsono = new JSONObject();
            jsono.put("updated", true);
            
            TransactionUtil.createTransaction(Constants.TRANSACTION_UPDATE_BANK_INFORMATION_COMPANY, company.getId());
            
        } catch (Exception e) {
            logger.error("CompanyUpdateBankInformation.companyUpdateBankInformation", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("updated", false);
        }
        
        return jsono.toString();
    
    }
    
    
}
