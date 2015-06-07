package controllers.partner;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Partner;
import model.beans.UpdatePasswordBean;
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
 * @author skuarch
 */
@RestController
public class PartnerUpdatePassword extends BaseController {

    private static final Logger logger = getLogger(PartnerUpdatePassword.class);
    
    @Autowired
    private PartnerComponent partnerComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/partner/update/password", "v1/partner/update/password"})
    public @ResponseBody
    String updatePassword(@ModelAttribute UpdatePasswordBean upb, HttpServletResponse response) {

        JSONObject jsono;
        Partner partner;

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

            partner = partnerComponent.getPartner(upb.getId());

            if (!partner.getPassword().equals(upb.getCurrentPassword())) {
                return getJson(false).toString();                
            }

            //update password
            partner.setPassword(upb.getNewPassword());
            partnerComponent.updatePartner(partner);
            jsono = getJson(true);
            
            TransactionUtil.createTransaction(Constants.TRANSACTION_UPDATE_PARTNER_PASSWORD, partner.getId());

        } catch (Exception e) {
            logger.error("FreelancerUpdatePassword.updatePassword", e);
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
