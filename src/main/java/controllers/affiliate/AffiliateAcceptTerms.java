package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.components.AffiliateComponent;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author skuarch
 */
@Controller
public class AffiliateAcceptTerms extends BaseController {

    private static final Logger logger = getLogger(AffiliateAcceptTerms.class);

    @Autowired
    private AffiliateComponent affiliateComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/accept/terms", "v1/affiliate/accept/terms"})
    public @ResponseBody
    String getAffiliateBasic(@RequestParam("id") long id, HttpServletResponse response) {

        JSONObject jsono = new JSONObject();
        Affiliate affiliate;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            affiliate = affiliateComponent.getAffiliate(id);

            if (affiliate == null) {
                jsono.put("accept", false);
            } else {
                affiliate.setApproved((byte) 1);
                affiliateComponent.updateAffiliate(affiliate);
                jsono.put("accept", true);
            }

        } catch (Exception e) {
            logger.error("AffiliateAcceptTerms.getAffiliatesList", e);
            jsono = new JSONObject();
            jsono.put("error", "error");
        }

        return jsono.toString();

    }

}
