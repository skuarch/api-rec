package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.AffiliateBasic;
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
public class AffiliateBasicList extends BaseController {
    
    private static final Logger logger = getLogger(AffiliateBasicList.class);
    
    @Autowired
    private AffiliateComponent affiliateComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/basic/get/list", "v1/affiliate/basic/get/list"})
    public @ResponseBody String getAffiliateBasicList(HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<AffiliateBasic> affiliates = null;
        
        try {            
            
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            
            affiliates = (ArrayList<AffiliateBasic>) affiliateComponent.getAffiliateBasicList();
            jsona = new JSONArray(affiliates);
            
        } catch (Exception e) {
            logger.error("AffiliateBasicList.getAffiliatesList", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
