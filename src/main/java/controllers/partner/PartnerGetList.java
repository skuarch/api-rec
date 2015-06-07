package controllers.partner;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Partner;
import model.components.PartnerComponent;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch-lap
 */
@RestController
public class PartnerGetList extends BaseController {
   
     private static final Logger logger = getLogger(PartnerGetList.class);
    
    @Autowired
    private PartnerComponent partnerComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/partner/get/list", "v1/partner/get/list"})
    public @ResponseBody String getPartnerList(HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<Partner> partners = null;
        
        try {            
            
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            partners = partnerComponent.getPartnerList();
            jsona = new JSONArray(partners);
            
        } catch (Exception e) {
            logger.error("PartnerGetList.getPartnerList", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
