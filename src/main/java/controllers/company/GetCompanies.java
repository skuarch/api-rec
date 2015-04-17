package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.components.CompanyComponent;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class GetCompanies extends BaseController {

    private static final Logger logger = getLogger(GetCompanies.class);        
    @Autowired
    private CompanyComponent companyComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/companies/get", "v1/companies/get"})
    public @ResponseBody String getCompanies(HttpServletResponse response){
    
        JSONObject jsono = null;
        List<Company> companies = null;
        
        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);
            companies = companyComponent.getCompanies();            
            
        } catch (Exception e) {
            logger.error("GetCompanies.getCompanies", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");            
        }
        
        return jsono.toString();      
    
    }
    
    
}
