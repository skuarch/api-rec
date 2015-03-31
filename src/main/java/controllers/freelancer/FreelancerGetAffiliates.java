package controllers.freelancer;

import controllers.application.BaseController;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.Freelancer;
import model.components.AffiliateComponent;
import org.apache.log4j.Logger;
import org.json.JSONArray;
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
public class FreelancerGetAffiliates extends BaseController {
    
    private static final Logger logger = getLogger(FreelancerGetAffiliates.class);
    
    @Autowired
    private AffiliateComponent affiliateComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/freelancer/get/affiliates", "v1/freelancer/get/affiliates"})
    public @ResponseBody String getAffiliatesByFreelancer(@ModelAttribute Freelancer freelancer, HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<Affiliate> affiliates = null;
        
        try {            
            
            setContentType(response, MediaType.APPLICATION_JSON);
            affiliates = affiliateComponent.getAffiliatesByFreelancer(freelancer.getId());            
            jsona = new JSONArray(affiliates);
            
        } catch (Exception e) {
            logger.error("FreelancerGetAffiliates.getAffiliatesByFreelancer", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
