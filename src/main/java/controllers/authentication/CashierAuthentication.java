package controllers.authentication;

import controllers.application.BaseController;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Cashier;
import model.beans.Login;
import model.components.CashierComponent;
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
public class CashierAuthentication extends BaseController {
    
    private static final Logger logger = getLogger(CashierAuthentication.class);
    
    @Autowired
    private CashierComponent cashierComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/authentication/cashier"})
    public @ResponseBody String authentication(@ModelAttribute Login login, HttpServletResponse response){
    
        JSONObject jsono = null;
        Cashier c = null;

        try {

            setHeaderNoChache(response);
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
            
            c = cashierComponent.getCashier(login.getEmail(), login.getPassword());
            
            if (c != null && c.getActive() == 1) {                
                jsono = createCashierJson(c);                
                jsono.put("validate", true);
                lastLogin(c);
            } else {                
                jsono.put("validate", false);
            }

            Thread.sleep(1000);
            
        } catch (Exception e) {
            logger.error("CashierAuthentication.authentication", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
        }
        
        return jsono.toString();
    
    }
    
    //==========================================================================
    private JSONObject createCashierJson(Cashier c){
    
        JSONObject jsono = null;
        
        try {
            
            jsono = new JSONObject();
            jsono.put("id", c.getId());
            jsono.put("name", c.getPerson().getName());
            jsono.put("lastName", c.getPerson().getLastName());
            jsono.put("email", c.getPerson().getEmail());
            jsono.put("lastLogin", c.getLastLogin());                        
            jsono.put("gender", c.getPerson().getGender().getName());            
            jsono.put("type", c.getPerson().getPersonType().getName());            
            jsono.put("phone", c.getPerson().getPhone());            
            jsono.put("registrationDate", c.getPerson().getRegistrationDate());                        
            jsono.put("personId", c.getPerson().getId());                        
            jsono.put("personType", Constants.PERSON_TYPE_ADMINISTRATOR);
            
        } catch (Exception e) {
            throw e;
        }
        
        return jsono;
        
    }
    
    //==========================================================================
    private void lastLogin(Cashier cashier) throws Exception {

        new Thread(() -> {
            try {
                cashier.setLastLogin(new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date()));
                cashierComponent.updateCashier(cashier);
            } catch (Exception e) {
                logger.error("CashierAuthentication.lastLogin", e);
            }
        }).start();

    }
    
}
