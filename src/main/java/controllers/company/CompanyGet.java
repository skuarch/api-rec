package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.components.CompanyComponent;
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
public class CompanyGet extends BaseController {

    private static final Logger logger = getLogger(CompanyGet.class);        
    @Autowired
    private CompanyComponent companyComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/company/get","v1/company/get"})
    public @ResponseBody String getCompany(@ModelAttribute Company company, HttpServletResponse response){
    
        JSONObject jsono = null;        
        Company c = null;
        
        try {
            
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            
            c = companyComponent.getCompany(company.getId());
            jsono = new JSONObject(c);
            
        } catch (Exception e) {
            logger.error("CompanyGet.getCompanies", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");            
        }
        
        return jsono.toString();
    
    }
    
}
