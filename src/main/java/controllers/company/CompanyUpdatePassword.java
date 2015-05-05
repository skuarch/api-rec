package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.beans.UpdatePasswordBean;
import model.components.CompanyComponent;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author skuarch-lap
 */
@Controller
public class CompanyUpdatePassword extends BaseController {
    
    private static final Logger logger = getLogger(CompanyUpdatePassword.class);
    @Autowired
    private CompanyComponent companyComponent;

    //==========================================================================    
    @RequestMapping(value = {"/v1/company/update/password", "v1/affiliate/company/password"})
    public @ResponseBody String updatePassword(@ModelAttribute UpdatePasswordBean upb, HttpServletResponse response) {

        JSONObject jsono = null;
        Company company = null;

        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);
            
            //some validations
            if (upb == null) {
                return getJson(false).toString();                
            }

            if (upb.getNewPassword().length() != 32 || upb.getNewPassword2().length() != 32) {
                return getJson(false).toString();                
            }

            if (!upb.getNewPassword().equals(upb.getNewPassword2())) {
                return getJson(false).toString();                
            }

            company = companyComponent.getCompany(upb.getId());

            //update password
            company.setPassword(upb.getNewPassword());
            companyComponent.updateCompany(company);
            jsono = getJson(true);

        } catch (Exception e) {
            logger.error("CompanyUpdatePassword.updatePassword", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            jsono.put("updated", false);
        }

        return jsono.toString();

    }

    //==========================================================================
    private JSONObject getJson(boolean updated) {

        JSONObject jsono = new JSONObject();
        jsono.put("updated", updated);
        return jsono;

    }    
    
}
