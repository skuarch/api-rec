package controllers.authentication;

import model.beans.Login;
import model.util.Validator;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import controllers.application.BaseController;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.Partner;
import model.components.AffiliateComponent;
import model.components.PartnerComponent;
import model.logic.Constants;

/**
 *
 * @author skuarch
 */
@RestController
public class AffiliatePartnerAuthentication extends BaseController {

    private static final Logger logger = getLogger(AffiliatePartnerAuthentication.class);

    @Autowired
    private PartnerComponent partnerComponent;
    @Autowired
    private AffiliateComponent affiliateComponent;

    //==========================================================================
    @RequestMapping(value = {"v1/authentication/partner/affiliate", "/v1/authentication/partner/affiliate"})
    public @ResponseBody String authentication(@ModelAttribute Login login, HttpServletResponse response) {
        
        JSONObject jsono = null;
        Partner p;
        Affiliate a;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);

            jsono = new JSONObject();

            //validations
            if (login == null) {
                jsono.put("validate", false);
                return jsono.toString();
            }

            if (!Validator.validateEmail(login.getEmail())) {
                jsono.put("validate", false);
            }

            if (!Validator.validatePassword(login.getPassword())) {
                jsono.put("validate", false);
            }

            p = partnerComponent.getPartner(login.getEmail(), login.getPassword());

            if (p != null) {
                jsono = createPartnerJson(p);
                jsono.put("validate", true);
            } else {
                //check if is affiliate
                a = affiliateComponent.getAffiliate(login.getEmail(), login.getPassword());
                if (a != null) {
                    jsono = createAffiliateJson(a);
                    jsono.put("validate", true);
                } else {
                    jsono.put("validate", false);
                }
            }
            
            Thread.sleep(1000);

        } catch (Exception e) {
            logger.error("AffiliatePartnerAuthentication.authentication", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
        }

        return jsono.toString();

    }

    //==========================================================================
    private JSONObject createPartnerJson(Partner p) {

        JSONObject jsono = null;

        try {
            
            jsono = new JSONObject();
            jsono.put("id", p.getId());
            jsono.put("name", p.getPerson().getName());
            jsono.put("lastName", p.getPerson().getLastName());
            jsono.put("email", p.getPerson().getEmail());
            jsono.put("gender", p.getPerson().getGender().getName());
            jsono.put("type", p.getPerson().getPersonType().getName());
            jsono.put("phone", p.getPerson().getPhone());
            jsono.put("registrationDate", p.getPerson().getRegistrationDate());
            jsono.put("personId", p.getPerson().getId());
            jsono.put("isPartner", true);
            jsono.put("isAffiliate", false);
            jsono.put("personType", Constants.PERSON_TYPE_PARTNER);

        } catch (Exception e) {
            throw e;
        }

        return jsono;

    }
    
    //==========================================================================
    private JSONObject createAffiliateJson(Affiliate a) {

        JSONObject jsono = null;

        try {
            
            jsono = new JSONObject();
            jsono.put("id", a.getId());
            jsono.put("name", a.getPerson().getName());
            jsono.put("lastName", a.getPerson().getLastName());
            jsono.put("email", a.getPerson().getEmail());
            jsono.put("gender", a.getPerson().getGender().getName());
            jsono.put("type", a.getPerson().getPersonType().getName());
            jsono.put("phone", a.getPerson().getPhone());
            jsono.put("registrationDate", a.getPerson().getRegistrationDate());
            jsono.put("personId", a.getPerson().getId());
            jsono.put("isPartner", false);
            jsono.put("isAffiliate", true);
            jsono.put("personType", Constants.PERSON_TYPE_PARTNER);

        } catch (Exception e) {
            throw e;
        }

        return jsono;

    }

}
