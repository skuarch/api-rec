package controllers.authentication;

import controllers.application.BaseController;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Freelancer;
import model.beans.Login;
import model.components.FreelancerComponent;
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
 * @author skuarch
 */
@RestController
public class FreelancerAuthentication extends BaseController {

    private static final Logger logger = getLogger(FreelancerAuthentication.class);

    @Autowired
    private FreelancerComponent freelancerComponent;

    //==========================================================================
    @RequestMapping(value = {"v1/authentication/freelancer", "/v1/authentication/freelancer"})
    public @ResponseBody
    String authentication(@ModelAttribute Login login, HttpServletResponse response) {
        
        JSONObject jsono = null;
        Freelancer f = null;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            jsono = new JSONObject();

            //validations
            if (login == null) {
                jsono.put("validate", false);
                return jsono.toString();
            }

            if (!Validator.validateEmail(login.getEmail())) {
                jsono.put("validate", false);
            }

            if (!Validator.validatePassword(login.getPassword())) {
                jsono.put("validate", false);
            }

            f = freelancerComponent.getFreelancer(login.getEmail(), login.getPassword());

            if (f != null && f.getActive() == 1) {
                jsono = createFreelancerJson(f);
                jsono.put("validate", true);
                lastLogin(f);
            } else {
                jsono.put("validate", false);
            }

            Thread.sleep(1000);

        } catch (Exception e) {
            logger.error("FreelancerAuthentication.authentication", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
        }

        return jsono.toString();

    }

    //==========================================================================
    private JSONObject createFreelancerJson(Freelancer f) {

        JSONObject jsono = null;

        try {

            jsono = new JSONObject();
            jsono.put("id", f.getId());
            jsono.put("name", f.getPerson().getName());
            jsono.put("lastName", f.getPerson().getLastName());
            jsono.put("email", f.getPerson().getEmail());
            jsono.put("lastLogin", f.getLastLogin());
            jsono.put("key", f.getKey());
            jsono.put("taxId", f.getTaxId());
            jsono.put("addressAll", f.getAddress().getAll());
            jsono.put("city", f.getAddress().getCity());
            jsono.put("country", f.getAddress().getCountry());
            jsono.put("state", f.getAddress().getState());
            jsono.put("zipCode", f.getAddress().getZipCode());
            jsono.put("gender", f.getPerson().getGender().getName());
            jsono.put("type", f.getPerson().getPersonType().getName());
            jsono.put("phone", f.getPerson().getPhone());
            jsono.put("registrationDate", f.getPerson().getRegistrationDate());
            jsono.put("personId", f.getPerson().getId());
            jsono.put("personType", Constants.PERSON_TYPE_FREELANCER);

        } catch (Exception e) {
            throw e;
        }

        return jsono;

    }

    //==========================================================================
    private void lastLogin(Freelancer freelancer) throws Exception {

        new Thread(() -> {
            try {
                freelancer.setLastLogin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                freelancerComponent.updateFreelancer(freelancer);
            } catch (Exception e) {
                logger.error("FreelancerAuthentication.lastLogin", e);
            }
        }).start();

    }

}
