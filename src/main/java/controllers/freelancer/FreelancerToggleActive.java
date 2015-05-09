package controllers.freelancer;

import static com.mchange.v2.c3p0.impl.C3P0Defaults.password;
import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Freelancer;
import model.components.FreelancerComponent;
import static model.logic.MailSender.sendMailNewFreelancer;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch-lap
 */
@RestController
public class FreelancerToggleActive extends BaseController {

    private static final Logger logger = getLogger(FreelancerToggleActive.class);

    @Autowired
    private FreelancerComponent freelancerComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/freelancer/toggle/active", "v1/freelancer/toggle/active"}, method = RequestMethod.POST)
    public @ResponseBody
    String toggleFreelancerActive(@ModelAttribute Freelancer f, HttpServletResponse response, Locale locale) {
        System.out.println("--------------------entro-------------------------");
        JSONObject jsono;
        Freelancer freelancer;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            freelancer = freelancerComponent.getFreelancer(f.getId());

            jsono = new JSONObject();
            if (freelancer == null) {
                jsono.put("updated", false);
            } else {
                if (freelancer.getActive() == 0) {
                    freelancer.setActive((byte) 1);
                } else {
                    freelancer.setActive((byte) 0);
                }
                freelancerComponent.updateFreelancer(freelancer);
                jsono.put("updated", true);
            }

        } catch (Exception e) {
            logger.error("ToggleFreelancerActive.toggleFreelancerActive", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
        }

        return jsono.toString();

    }

}
