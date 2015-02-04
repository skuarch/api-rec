package controllers.authentication;

import model.beans.Login;
import model.beans.Person;
import model.components.PersonComponent;
import model.util.Validator;
import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;
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
public class PersonAuthentication {

    private static final Logger logger = getLogger(PersonAuthentication.class);
    
    //==========================================================================
    public PersonAuthentication() {
    }
    
    @Autowired
    private PersonComponent personComponent;
    
    //==========================================================================
    @RequestMapping(value = {"v1/authentication", "/v1/authentication"})
    public @ResponseBody String authentication(@ModelAttribute Login login) {
        
        JSONObject jsono = null;
        Person p = null;

        try {

            //validations
            if (!Validator.validateEmail(login.getEmail())) {
                jsono.put("validate", false);
            }

            if (!Validator.validatePassword(login.getPassword())) {
                jsono.put("validate", false);
            }
            
            p = personComponent.getPerson(login.getEmail(), login.getPassword());
            
            if (p != null) {
                jsono = new JSONObject(p);                
            } else {
                jsono = new JSONObject();
                jsono.put("validate", false);
            }

        } catch (Exception e) {
            logger.error("PersonAuthentication.authentication", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
        }
        
        return jsono.toString();
    }
    
}
