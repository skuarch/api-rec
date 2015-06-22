package controllers.administrator;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Administrator;
import model.beans.UpdatePasswordBean;
import model.components.AdministratorComponent;
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
public class AdmistratorUpdatePassword extends BaseController {

    private static final Logger logger = getLogger(AdmistratorUpdatePassword.class);
    
    @Autowired
    private AdministratorComponent administratorComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/administrator/update/password", "v1/administrator/update/password"})
    public @ResponseBody
    String updatePassword(@ModelAttribute UpdatePasswordBean upb, HttpServletResponse response) {
        System.out.println("chanclass " + upb.getId());
        JSONObject jsono;
        Administrator administrator;

        try {

            setHeaderNoChache(response);
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

            administrator = administratorComponent.getAdministrator(upb.getId());

            if (!administrator.getPassword().equals(upb.getCurrentPassword())) {
                return getJson(false).toString();                
            }

            //update password
            administrator.setPassword(upb.getNewPassword());
            administratorComponent.updateAdministrator(administrator);
            jsono = getJson(true);                        

        } catch (Exception e) {
            logger.error("AdmistratorUpdatePassword.updatePassword", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
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
