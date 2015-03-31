package controllers.affiliate;

import static controllers.application.BaseController.getLogger;
import model.beans.Affiliate;
import model.beans.AffiliateBankInformation;
import model.components.AffiliateComponent;
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
public class AffiliateUpdateBankInformation {

    @Autowired
    private AffiliateComponent affiliateComponent;    
    private static final Logger logger = getLogger(AffiliateUpdateBankInformation.class);
    
    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/update/bank/information","v1/affiliate/update/bank/information"})
    public @ResponseBody String affiliateUpdateBankInformation(@ModelAttribute AffiliateBankInformation affiliateBankInformation){
        
        JSONObject jsono = null;
        Affiliate affiliate = null;
        
        try {
            
            affiliate = affiliateComponent.getAffiliate(affiliateBankInformation.getId());
            affiliate.setOwnerAccountBank(affiliateBankInformation.getOwnerAccountBank());
            affiliate.setBank(affiliateBankInformation.getBank());
            affiliate.setClabe(affiliateBankInformation.getClabe());
            affiliate.setEmailNotifications(affiliateBankInformation.getEmailNotifications());
            
            affiliateComponent.updateAffiliate(affiliate);            
            
            jsono = new JSONObject();
            jsono.put("update", true);
            
        } catch (Exception e) {
            logger.error("AffiliateUpdateBasicInformation.updateBasicInformation", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("update", false);
        }
        
        return jsono.toString();
    
    }
    
    
}
