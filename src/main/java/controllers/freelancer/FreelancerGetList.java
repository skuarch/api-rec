package controllers.freelancer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Freelancer;
import model.components.FreelancerComponent;
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
public class FreelancerGetList extends BaseController {
   
     private static final Logger logger = getLogger(FreelancerGetList.class);
    
    @Autowired
    private FreelancerComponent freelancerComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/freelancer/get/list", "v1/freelancer/get/list"})
    public @ResponseBody String getFreelancerList(HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<Freelancer> freelancers = null;
        
        try {            
            
            setContentType(response, MediaType.APPLICATION_JSON);
            freelancers = freelancerComponent.getFreelancersList();
            jsona = new JSONArray(freelancers);
            
        } catch (Exception e) {
            logger.error("FreelancerComponent.getFreelancerList", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
