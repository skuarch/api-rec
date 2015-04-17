package controllers.freelancer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Freelancer;
import model.beans.UpdatePasswordBean;
import model.components.FreelancerComponent;
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
public class UpdatePasswordFreelancer extends BaseController {

    private static final Logger logger = getLogger(UpdatePasswordFreelancer.class);
    @Autowired
    private FreelancerComponent freelancerComponent;

    @RequestMapping(value = {"/v1/freelancer/update/password", "v1/freelancer/update/password"})
    public @ResponseBody
    String updatePassword(@ModelAttribute UpdatePasswordBean upb, HttpServletResponse response) {

        JSONObject jsono = null;
        Freelancer freelancer = null;

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

            freelancer = freelancerComponent.getFreelancer(upb.getId());

            if (!freelancer.getPassword().equals(upb.getCurrentPassword())) {
                return getJson(false).toString();                
            }

            //update password
            freelancer.setPassword(upb.getNewPassword());
            freelancerComponent.updateFreelancer(freelancer);
            jsono = getJson(true);

        } catch (Exception e) {
            logger.error("UpdatePasswordFreelancer.updatePassword", e);
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
