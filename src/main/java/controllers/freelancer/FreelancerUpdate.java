package controllers.freelancer;

import controllers.application.BaseController;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Freelancer;
import model.components.FreelancerComponent;
import model.components.PersonComponent;
import model.logic.Constants;
import model.util.TransactionUtil;
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
public class FreelancerUpdate extends BaseController {
    
    private static final Logger logger = getLogger(FreelancerUpdate.class);
    
    @Autowired
    private FreelancerComponent freelancerComponent;        
    @Autowired
    private PersonComponent personComponent;    
    
    //==========================================================================
    @RequestMapping(value = {"/v1/freelancer/update", "v1/freelancer/update"}, method = RequestMethod.POST)
    public @ResponseBody
    String updateFreelancer(@ModelAttribute Freelancer freelancer, HttpServletResponse response, Locale locale) {
        
        JSONObject jsono;
        Freelancer f;
        
        try {            
            
            setContentType(response, MediaType.APPLICATION_JSON);
            
            f = freelancerComponent.getFreelancer(freelancer.getId());            
            freelancer.setId(f.getId());            
            
            //update freelancer person
            freelancer.getPerson().setId(f.getPerson().getId());
            freelancer.getPerson().setPersonType(f.getPerson().getPersonType());
            personComponent.updatePerson(freelancer.getPerson());            
            
            //update freelancer
            freelancer.setPassword(f.getPassword());            
            freelancer.getAddress().setId(f.getAddress().getId()); 
            freelancer.setActive((byte) 1);
            freelancerComponent.updateFreelancer(freelancer);
            
            jsono = new JSONObject();
            jsono.put("updated", true);
            
            TransactionUtil.createTransaction(Constants.TRANSACTION_UPDATE_FREELANCER, freelancer.getId());
            
        } catch (Exception e) {
            logger.error("FreelancerUpdate.updateFreelancer", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            jsono.put("updated", false);
        }
        
        return jsono.toString();
        
    }
    
}
