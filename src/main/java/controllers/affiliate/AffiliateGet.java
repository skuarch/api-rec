package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
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
public class AffiliateGet extends BaseController{
    
    private static final Logger logger = getLogger(AffiliateGet.class);        
    @Autowired
    private AffiliateComponent affiliateComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/get"})
    public @ResponseBody String getAffiliate(@ModelAttribute Affiliate affiliate, HttpServletResponse response){
        
        JSONObject jsono = null;
        
        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);
            
            affiliate = affiliateComponent.getAffiliate(affiliate.getId());            
            jsono = new JSONObject(affiliate);
            
        } catch (Exception e) {
            logger.error("AffiliateGet.createAffiliate", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");                        
        }
        
        return jsono.toString();
    
    }
    
}
