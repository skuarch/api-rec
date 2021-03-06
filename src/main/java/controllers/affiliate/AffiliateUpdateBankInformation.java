package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.AffiliateBankInformation;
import model.components.AffiliateComponent;
import model.logic.Constants;
import model.util.MailUtil;
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
public class AffiliateUpdateBankInformation extends BaseController{

    @Autowired
    private AffiliateComponent affiliateComponent;    
    private static final Logger logger = getLogger(AffiliateUpdateBankInformation.class);
    
    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/update/bank/information","v1/affiliate/update/bank/information"})
    public @ResponseBody String affiliateUpdateBankInformation(@ModelAttribute AffiliateBankInformation affiliateBankInformation, HttpServletResponse response, Locale locale){
        
        JSONObject jsono = null;
        Affiliate affiliate = null;
        
        try {
            
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            
            affiliate = affiliateComponent.getAffiliate(affiliateBankInformation.getId());
            affiliate.setOwnerAccountBank(affiliateBankInformation.getOwnerAccountBank());
            affiliate.setBank(affiliateBankInformation.getBank());
            affiliate.setClabe(affiliateBankInformation.getClabe());
            affiliate.setEmailNotifications(affiliateBankInformation.getEmailNotifications());
            
            affiliateComponent.updateAffiliate(affiliate);            
            
            jsono = new JSONObject();
            jsono.put("update", true);
            
            TransactionUtil.createTransaction(Constants.TRANSACTION_UPDATE_BANK_INFORMATION_AFFILIATE, affiliate.getId());
            
            MailUtil.sendMailUpdateInformation(affiliate.getPerson().getEmail(), locale.getDisplayLanguage());
            
        } catch (Exception e) {
            logger.error("AffiliateUpdateBankInformation.affiliateUpdateBankInformation", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("update", false);
        }
        
        return jsono.toString();
    
    }
    
    
}
