package controllers.partner;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Partner;
import model.components.PartnerComponent;
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
public class PartnerToggleActive extends BaseController {

    private static final Logger logger = getLogger(PartnerToggleActive.class);

    @Autowired
    private PartnerComponent partnerComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/partner/toggle/active", "v1/partner/toggle/active"})
    public @ResponseBody
    String toggleFreelancerActive(@ModelAttribute Partner p, HttpServletResponse response, Locale locale) {
        
        JSONObject jsono;
        Partner partner;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            partner = partnerComponent.getPartner(p.getId());

            jsono = new JSONObject();
            if (partner == null) {
                jsono.put("updated", false);
            } else {
                if (partner.getActive() == 0) {
                    partner.setActive((byte) 1);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_ACTIVATE_PARTNER, partner.getId());
                } else {
                    partner.setActive((byte) 0);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_DEACTIVATE_PARTNER, partner.getId());
                }
                partnerComponent.updatePartner(partner);
                jsono.put("updated", true);
            }

        } catch (Exception e) {
            logger.error("PartnerToggleActive.togglePartnerActive", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
        }

        return jsono.toString();

    }

}
