package controllers.affiliate;

import controllers.application.BaseController;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.components.AffiliateComponent;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class AffiliateGetList extends BaseController {
    
    private static final Logger logger = getLogger(AffiliateGetList.class);
    
    @Autowired
    private AffiliateComponent affiliateComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/get/list", "v1/affiliate/get/list"})
    public @ResponseBody String getAffiliatesList(HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<Affiliate> affiliates = null;
        
        try {            
            
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            affiliates = (ArrayList<Affiliate>) affiliateComponent.getAffiliatesList();
            jsona = new JSONArray(affiliates);
            
        } catch (Exception e) {
            logger.error("AffiliateGetList.getAffiliatesList", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
