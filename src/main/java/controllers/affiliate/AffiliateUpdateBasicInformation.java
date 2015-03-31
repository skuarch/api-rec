package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.AffiliateBasicInformation;
import model.components.AffiliateComponent;
import model.components.PersonTypeComponent;
import model.logic.Constants;
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
public class AffiliateUpdateBasicInformation extends BaseController{
    
    @Autowired
    private AffiliateComponent affiliateComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    private static final Logger logger = getLogger(AffiliateUpdateBasicInformation.class);
    
    //==========================================================================
    @RequestMapping(value ={"/v1/affiliate/update/basic/information", "v1/affiliate/update/basic/information"})
    public @ResponseBody String updateBasicInformation(@ModelAttribute AffiliateBasicInformation affiliateBasicInformation, HttpServletResponse response){
        
        JSONObject jsono = null;
        Affiliate affiliate = null;
        
        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);                        
            affiliateBasicInformation
                    .getPerson()
                    .setPersonType(
                            personTypeComponent.getPersonType(Constants.AFFILIATE)
                    );
            affiliate = affiliateComponent.getAffiliate(affiliateBasicInformation.getId());
            affiliate.setPerson(affiliateBasicInformation.getPerson());
            affiliate.setBrand(affiliateBasicInformation.getBrand());            
            affiliate.setCategory(affiliateBasicInformation.getCategory());
            
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
