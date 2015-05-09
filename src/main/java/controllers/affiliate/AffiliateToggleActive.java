package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.components.AffiliateComponent;
import model.logic.Constants;
import model.util.TransactionUtil;
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
public class AffiliateToggleActive extends BaseController {

    private static final Logger logger = getLogger(AffiliateToggleActive.class);
    
    @Autowired
    private AffiliateComponent affiliateComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/toggle/active", "v1/affiliate/toggle/active"}, method = RequestMethod.POST)
    public @ResponseBody
    String toggleAffiliateActive(@ModelAttribute Affiliate a, HttpServletResponse response, Locale locale) {
        
        JSONObject jsono;
        Affiliate affiliate;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            affiliate = affiliateComponent.getAffiliate(a.getId());

            jsono = new JSONObject();
            if (affiliate == null) {
                jsono.put("updated", false);
            } else {
                if (affiliate.getActive() == 0) {
                    affiliate.setActive((byte) 1);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_ACTIVATE_AFFILIADO, affiliate.getId());
                } else {
                    affiliate.setActive((byte) 0);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_DEACTIVATE_AFFILIADO, affiliate.getId());
                }
                affiliateComponent.updateAffiliate(affiliate);                
                jsono.put("updated", true);
            }

        } catch (Exception e) {
            logger.error("AffiliateToggleActive.toggleAffiliateActive", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
        }

        return jsono.toString();

    }

}
