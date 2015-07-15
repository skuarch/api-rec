package controllers.authentication;

import controllers.application.BaseController;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.Company;
import model.beans.Login;
import model.beans.Partner;
import model.components.AffiliateComponent;
import model.components.CompanyComponent;
import model.components.PartnerComponent;
import model.logic.Constants;
import model.util.Validator;
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
public class AffiliatePartnerAuthentication extends BaseController {

    private static final Logger logger = getLogger(AffiliatePartnerAuthentication.class);

    @Autowired
    private PartnerComponent partnerComponent;
    @Autowired
    private AffiliateComponent affiliateComponent;
    @Autowired
    private CompanyComponent companyComponent;

    //==========================================================================
    @RequestMapping(value = {"v1/authentication/partner/affiliate", "/v1/authentication/partner/affiliate"})
    public @ResponseBody
    String authentication(@ModelAttribute Login login, HttpServletResponse response) {

        JSONObject jsono = null;
        Partner p;
        Affiliate a;
        Company c;

        try {

            setHeaderNoChache(response);
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

            //is partner?
            p = partnerComponent.getPartner(login.getEmail(), login.getPassword());
            if (p != null && p.getActive() == 1) {
                jsono = createPartnerJson(p);
                jsono.put("validate", true);
                lastLoginPartner(p);
                Thread.sleep(1000);
                return jsono.toString();
            }

            //is affiliate?
            a = affiliateComponent.getAffiliate(login.getEmail(), login.getPassword());
            if (a != null && a.getActive() == 1) {
                jsono = createAffiliateJson(a);
                jsono.put("validate", true);
                lastLoginAffiliate(a);
            } else if (a != null && a.getActive() == 0) {
                //the affiliate is not active but he should login and accept 
                //privacy politics
                jsono = createAffiliateJson(a);
                jsono.put("validate", true);
                lastLoginAffiliate(a);
                Thread.sleep(1000);
                return jsono.toString();
            }
            
            //is company
            c = companyComponent.getCompany(login.getEmail(), login.getPassword());
            if (c != null && c.getActive() == 1) {
                jsono = createCompanyJson(c);
                jsono.put("validate", true);
                lastLoginCompany(c);
            } else if (c != null && c.getActive() == 0) {
                //the company is not active but he should login and accept 
                //privacy politics
                jsono = createCompanyJson(c);
                jsono.put("validate", true);
                lastLoginCompany(c);
                Thread.sleep(1000);
                return jsono.toString();
            }

            jsono.put("validate", false);

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
            jsono.put("isCompany", false);
            jsono.put("personType", Constants.PERSON_TYPE_PARTNER);
            jsono.put("approved", 1);
            jsono.put("active", p.getActive());

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
            jsono.put("isCompany", false);
            jsono.put("personType", Constants.PERSON_TYPE_AFFILIATE);
            jsono.put("approved", a.getApproved());
            jsono.put("active", a.getActive());

        } catch (Exception e) {
            throw e;
        }

        return jsono;

    }
    
    //==========================================================================
    private JSONObject createCompanyJson(Company c) {

        JSONObject jsono = null;

        try {

            jsono = new JSONObject();
            jsono.put("id", c.getId());
            jsono.put("name", c.getPerson().getName());
            jsono.put("lastName", c.getPerson().getLastName());
            jsono.put("email", c.getPerson().getEmail());
            jsono.put("gender", c.getPerson().getGender().getName());
            jsono.put("type", c.getPerson().getPersonType().getName());
            jsono.put("phone", c.getPerson().getPhone());
            jsono.put("registrationDate", c.getPerson().getRegistrationDate());
            jsono.put("personId", c.getPerson().getId());
            jsono.put("isPartner", false);
            jsono.put("isAffiliate", false);
            jsono.put("isCompany", true);
            jsono.put("personType", Constants.PERSON_TYPE_CONTACT_COMPANY);
            jsono.put("approved", c.getApproved());
            jsono.put("active", c.getActive());

        } catch (Exception e) {
            throw e;
        }

        return jsono;

    }

    //==========================================================================
    private void lastLoginPartner(Partner partner) throws Exception {

        new Thread(() -> {
            try {
                partner.setLastLogin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                partnerComponent.updatePartner(partner);
            } catch (Exception e) {
                logger.error("AdministratorAuthentication.lastLogin", e);
            }
        }).start();

    }

    //==========================================================================
    private void lastLoginAffiliate(Affiliate affiliate) throws Exception {

        new Thread(() -> {
            try {
                affiliate.setLastLogin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                affiliateComponent.updateAffiliate(affiliate);
            } catch (Exception e) {
                logger.error("AdministratorAuthentication.lastLogin", e);
            }
        }).start();

    }
    
    //==========================================================================
    private void lastLoginCompany(Company company) throws Exception {

        new Thread(() -> {
            try {
                company.setLastLogin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                companyComponent.updateCompany(company);
            } catch (Exception e) {
                logger.error("AdministratorAuthentication.lastLogin", e);
            }
        }).start();

    }

}
