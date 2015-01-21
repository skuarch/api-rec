package controllers.affiliate;

import controllers.application.BaseController;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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
 * @author skuarch
 */
@RestController
public class AffiliateController extends BaseController {

    private static final Logger logger = getLogger(AffiliateController.class);    

    //==========================================================================
    /**
     * create a new Affiliate into database.
     *
     * @param affiliate Affiliate
     * @param response HttpServletResponse
     * @param locale locale
     * @return String
     */
    //@RequestMapping(value = {"/v1/affiliate/create", "v1/affiliate/create"}, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST)
    @RequestMapping(value = {"/v1/affiliate/create", "v1/affiliate/create"})
    //@Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public @ResponseBody
    String createAffiliate(@ModelAttribute Affiliate affiliate, HttpServletResponse response, Locale locale) {

        long id = 0;
        JSONObject jsono = null;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            jsono = new JSONObject();

            //create affiliate
            id = new AffiliateComponent().createAffiliate(affiliate);
            affiliate.setId(id);
            jsono.append("id", id);

            //create transaction            
            TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_AFFILIATE, affiliate.getId());
            
            //send email to the affiliate
            sendMailNewAffiliate(affiliate, locale.getDisplayLanguage());            
            
            //create notification
            
        } catch (Exception e) {
            logger.error("AffiliateController.createAffiliate", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
        }

        return jsono.toString();

    }

    //==========================================================================
    private void sendMailNewAffiliate(Affiliate affiliate, String displayLanguage) {

        if (affiliate == null) {
            return;
        }

        new Thread(() -> {
            try {
                model.logic.MailSender.sendMailNewAffiliate(
                        affiliate.getPerson().getName(),
                        affiliate.getPerson().getEmail(),
                        displayLanguage
                );
            } catch (Exception e) {
                logger.error("AffiliateController.sendMailNewAffiliate", e);
            }
        }).start();

    }
    
}