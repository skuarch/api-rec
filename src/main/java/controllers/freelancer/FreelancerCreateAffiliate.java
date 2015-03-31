package controllers.freelancer;

import controllers.affiliate.AffiliateController;
import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.components.AffiliateComponent;
import model.components.ContactComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
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
 * @author skuarch
 */
@Controller
public class FreelancerCreateAffiliate extends BaseController {

    private static final Logger logger = getLogger(AffiliateController.class);

    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private ContactComponent contactComponent;
    @Autowired
    private PersonComponent personComponent;

    //==========================================================================
    /**
     * create a new Affiliate into database.
     *
     * @param affiliate Affiliate
     * @param response HttpServletResponse
     * @param locale locale
     * @return String
     */    
    @RequestMapping(value = {"/v1/freelancer/create/affiliate", "v1/affiliate/freelancer/create"})    
    public @ResponseBody
    String createAffiliate(@ModelAttribute Affiliate affiliate, HttpServletResponse response, Locale locale) {
        
        long id = 0;
        JSONObject jsono = null;
        long contactId = 0;
        long personContacId = 0;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            jsono = new JSONObject();
            
            //get person type
            affiliate.getContact().getPerson().setPersonType(personTypeComponent.getPersonType("contact"));
            affiliate.getPerson().setPersonType(personTypeComponent.getPersonType("affiliate"));
            
            //save the contact
            personContacId = personComponent.createPerson(affiliate.getContact().getPerson());
            affiliate.getContact().getPerson().setId(personContacId);            
            contactId = contactComponent.createContact(affiliate.getContact());
            affiliate.getContact().setId(contactId);

            //create affiliate
            id = new AffiliateComponent().createAffiliate(affiliate);
            affiliate.setId(id);
            jsono.append("created", true);
            jsono.append("id", id);

            //create transaction            
            TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_AFFILIATE, affiliate.getId());

            //send email to the affiliate
            sendMailNewAffiliate(affiliate, locale.getDisplayLanguage());

            //create notification
            
        } catch (Exception e) {
            logger.error("AffiliateController.createAffiliate", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            jsono.append("created", false);
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
