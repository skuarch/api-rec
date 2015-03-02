package controllers.affiliate;

import static controllers.application.BaseController.getLogger;
import model.beans.Affiliate;
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
public class GetAffiliate {
    
    private static final Logger logger = getLogger(GetAffiliate.class);        
    @Autowired
    private AffiliateComponent affiliateComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/get"})
    public @ResponseBody String getAffiliate(@ModelAttribute Affiliate affiliate){
        
        JSONObject jsono = null;
        
        try {
            
            affiliate = affiliateComponent.getAffiliate(affiliate.getId());
            jsono = new JSONObject(affiliate);
            
        } catch (Exception e) {
            logger.error("AffiliateController.createAffiliate", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("created", false);
        }
        
        return jsono.toString();
    
    }
    
}
