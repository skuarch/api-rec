package controllers.freelancer;

import controllers.application.BaseController;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Freelancer;
import model.components.FreelancerComponent;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class UpdateFreelancer extends BaseController {
    
    private static final Logger logger = getLogger(UpdateFreelancer.class);
    @Autowired
    private FreelancerComponent freelancerComponent;    
    
    //==========================================================================
    @RequestMapping(value = {"/v1/freelancer/update", "v1/freelancer/update"}, method = RequestMethod.POST)
    public @ResponseBody
    String updateFreelancer(@ModelAttribute Freelancer freelancer, HttpServletResponse response, Locale locale) {
        
        JSONObject jsono = new JSONObject();
        Freelancer f = null;
        
        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);
            
            f = freelancerComponent.getFreelancer(freelancer.getId());
            freelancer.setId(f.getId());
            freelancer.getPerson().setId(f.getPerson().getId());
            freelancer.getPerson().setPassword(f.getPerson().getPassword());
            freelancer.getPerson().setPersonType(f.getPerson().getPersonType());
            freelancer.getAddress().setId(f.getAddress().getId());            
            freelancerComponent.updateFreelancer(freelancer);
            jsono.put("updated", true);
            
        } catch (Exception e) {
            logger.error("FreelancerController.updateFreelancer", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            jsono.put("updated", false);
        }
        
        return jsono.toString();
        
    }
    
}
