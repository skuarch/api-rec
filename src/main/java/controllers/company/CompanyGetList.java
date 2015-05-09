package controllers.company;

import controllers.application.BaseController;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.components.CompanyComponent;
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
public class CompanyGetList extends BaseController {
    
    private static final Logger logger = getLogger(CompanyGetList.class);
    
    @Autowired
    private CompanyComponent companyComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/company/get/list", "v1/company/get/list"})
    public @ResponseBody String getCompaniesList(HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<Company> companies = null;
        
        try {            
            
            setContentType(response, MediaType.APPLICATION_JSON);
            companies = (ArrayList<Company>) companyComponent.getCompanyList();
            jsona = new JSONArray(companies);
            
        } catch (Exception e) {
            logger.error("CompanyGetList.getCompaniesList", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
