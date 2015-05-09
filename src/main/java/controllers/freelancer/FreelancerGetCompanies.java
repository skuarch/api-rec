package controllers.freelancer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.beans.Freelancer;
import model.components.CompanyComponent;
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
public class FreelancerGetCompanies extends BaseController {
    
    private static final Logger logger = getLogger(FreelancerGetCompanies.class);
    
    @Autowired
    private CompanyComponent companyComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/freelancer/get/companies", "v1/freelancer/get/companies"})
    public @ResponseBody String getCompaniesByFreelancer(@ModelAttribute Freelancer freelancer, HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<Company> companies = null;
        
        try {            
            
            setContentType(response, MediaType.APPLICATION_JSON);
            companies = companyComponent.getCompaniesByCreator(freelancer.getPerson().getId());
            jsona = new JSONArray(companies);
            
        } catch (Exception e) {
            logger.error("FreelancerGetCompanies.getCompaniesByCreator", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
