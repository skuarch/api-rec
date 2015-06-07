package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.UpdatePasswordBean;
import model.components.AffiliateComponent;
import model.logic.Constants;
import model.util.TransactionUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author skuarch-lap
 */
@Controller
public class AffiliateUpdatePassword extends BaseController {
    
    private static final Logger logger = getLogger(AffiliateUpdatePassword.class);
    @Autowired
    private AffiliateComponent affiliateComponent;

    //==========================================================================    
    @RequestMapping(value = {"/v1/affiliate/update/password", "v1/affiliate/update/password"})
    public @ResponseBody String updatePassword(@ModelAttribute UpdatePasswordBean upb, HttpServletResponse response) {

        JSONObject jsono = null;
        Affiliate affiliate = null;

        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);
            setHeaderNoChache(response);
            
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

            affiliate = affiliateComponent.getAffiliate(upb.getId());

            //update password
            affiliate.setPassword(upb.getNewPassword());
            affiliateComponent.updateAffiliate(affiliate);
            jsono = getJson(true);
            
            TransactionUtil.createTransaction(Constants.TRANSACTION_UPDATE_AFFILIATE_PASSWORD, affiliate.getId());

        } catch (Exception e) {
            logger.error("AffiliateUpdatePassword.updatePassword", e);
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
