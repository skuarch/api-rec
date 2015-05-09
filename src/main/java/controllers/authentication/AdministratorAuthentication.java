package controllers.authentication;

import controllers.application.BaseController;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Administrator;
import model.beans.Login;
import model.components.AdministratorComponent;
import model.logic.Constants;
import model.util.Validator;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch-lap
 */
@RestController 
public class AdministratorAuthentication extends BaseController {
    
    private static final Logger logger = getLogger(AdministratorAuthentication.class);
    
    @Autowired
    private AdministratorComponent administratorComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/authentication/administrator"})
    public @ResponseBody String authentication(@ModelAttribute Login login, HttpServletResponse response){
    
        JSONObject jsono = null;
        Administrator a = null;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            
            jsono = new JSONObject();
            
            //validations
            if(login == null){
                jsono.put("validate", false);
                return jsono.toString();
            }            
            
            if (!Validator.validateEmail(login.getEmail())) {
                jsono.put("validate", false);
            }

            if (!Validator.validatePassword(login.getPassword())) {
                jsono.put("validate", false);
            }
            
            a = administratorComponent.getAdministrator(login.getEmail(), login.getPassword());
        
            if (a != null && a.getActive() == 1) {                
                jsono = createAdministratorJson(a);                
                jsono.put("validate", true);
                lastLogin(a);
            } else {                
                jsono.put("validate", false);
            }

            Thread.sleep(1000);
            
        } catch (Exception e) {
            logger.error("AdministratorAuthentication.authentication", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
        }
        
        return jsono.toString();
    
    }
    
    //==========================================================================
    private JSONObject createAdministratorJson(Administrator a){
    
        JSONObject jsono = null;
        
        try {
            
            jsono = new JSONObject();
            jsono.put("id", a.getId());
            jsono.put("name", a.getPerson().getName());
            jsono.put("lastName", a.getPerson().getLastName());
            jsono.put("email", a.getPerson().getEmail());
            jsono.put("lastLogin", a.getLastLogin());                        
            jsono.put("gender", a.getPerson().getGender().getName());            
            jsono.put("type", a.getPerson().getPersonType().getName());            
            jsono.put("phone", a.getPerson().getPhone());            
            jsono.put("registrationDate", a.getPerson().getRegistrationDate());                        
            jsono.put("personId", a.getPerson().getId());                        
            jsono.put("personType", Constants.PERSON_TYPE_ADMINISTRATOR);
            
        } catch (Exception e) {
            throw e;
        }
        
        return jsono;
        
    }
    
    //==========================================================================
    private void lastLogin(Administrator administrator) throws Exception {

        new Thread(() -> {
            try {
                administrator.setLastLogin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                administratorComponent.updateAdministrator(administrator);
            } catch (Exception e) {
                logger.error("AdministratorAuthentication.lastLogin", e);
            }
        }).start();

    }
    
}
