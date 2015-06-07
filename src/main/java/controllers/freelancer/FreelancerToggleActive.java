package controllers.freelancer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Freelancer;
import model.components.FreelancerComponent;
import model.logic.Constants;
import model.util.TransactionUtil;
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
public class FreelancerToggleActive extends BaseController {

    private static final Logger logger = getLogger(FreelancerToggleActive.class);

    @Autowired
    private FreelancerComponent freelancerComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/freelancer/toggle/active", "v1/freelancer/toggle/active"})
    public @ResponseBody
    String toggleFreelancerActive(@ModelAttribute Freelancer f, HttpServletResponse response, Locale locale) {
        
        JSONObject jsono;
        Freelancer freelancer;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            freelancer = freelancerComponent.getFreelancer(f.getId());

            jsono = new JSONObject();
            if (freelancer == null) {
                jsono.put("updated", false);
            } else {
                if (freelancer.getActive() == 0) {
                    freelancer.setActive((byte) 1);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_ACTIVATE_FREELANCER, freelancer.getId());
                } else {
                    freelancer.setActive((byte) 0);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_DEACTIVATE_FREELANCER, freelancer.getId());
                }
                freelancerComponent.updateFreelancer(freelancer);
                jsono.put("updated", true);
            }

        } catch (Exception e) {
            logger.error("FreelancerToggleActive.toggleFreelancerActive", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
        }

        return jsono.toString();

    }

}
